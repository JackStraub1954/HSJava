package edu.uweo.javaintro.game_of_life;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main
	implements ActionListener, ControlListener
{
	private Board          board;
	private boolean        running     = false;
	private JFileChooser   fileChooser = new JFileChooser();
    private Controls       controls     = new Controls();;
	
	public static void main( String[] args )
	{
	    Main       app     = new Main();
		app.execute();
	}
	
	private void execute()
	{
		board = new Board( 100 );
		board.addActionListener( this );
		SwingUtilities.invokeLater( board );
		
		controls.addControlListener( this );
		controls.start();
	}
	
	public void actionPerformed( ActionEvent evt )
	{
		Cell	cell	= (Cell)evt.getSource();
		System.out.println( cell );
		cell.toggleAlive();
		board.setCell( cell );
		board.refresh();
	}
	
	public void controlActivated( ControlEvent evt )
	{
        String text    = evt.getLabel();
        switch ( text )
        {
        case "Run":
            doRun();
            break;
        case "Step":
            doStep();
            break;
        case "Save":
            doSave();
            break;
        case "Open":
            doOpen();
            break;
        case "Clear":
            doClear();
            break;
        default:
            System.err.println( "eh?" );
            break;
	    }
	}
	
	public void sliderAdjusted( ControlEvent evt )
    {
        
    }
	
	private void doClear()
	{
	    board.clear();
	    board.refresh();
	}
	
	private void doRun()
	{
        running = !running;
        if ( running )
        {
            Runner runner  = new Runner();
            new Thread( runner ).start();
        }
	}
	
	private void doStep()
	{
	    nextState( board.getCells() );
	}
	
	private void doSave()
	{
        int    rcode   = fileChooser.showSaveDialog( null );
        if ( rcode == JFileChooser.APPROVE_OPTION )
        {
            File    file    = fileChooser.getSelectedFile();
    	    try ( FileOutputStream fStream = new FileOutputStream( file );
    	    )
    	    {
    	        
    	        ObjectOutputStream oStream = new ObjectOutputStream( fStream );
    	        oStream.writeObject( board.getCells() );
    	    }
    	    catch ( IOException exc )
    	    {
    	        JOptionPane.showMessageDialog( null, "Save failure" );
    	        exc.printStackTrace();
    	    }
        }
	}
	
	private void doOpen()
	{
	    int    rcode   = fileChooser.showOpenDialog( null );
	    if ( rcode == JFileChooser.APPROVE_OPTION )
	    {
	        File   file    = fileChooser.getSelectedFile();
            try ( FileInputStream fStream = new FileInputStream( file );
            )
            {
                
                ObjectInputStream   oStream = new ObjectInputStream( fStream );
                boolean[][] cells   = (boolean[][])oStream.readObject();
                board.setCells( cells );
                board.refresh();
            }
            catch ( IOException | ClassNotFoundException
                    | ClassCastException exc )
            {
                JOptionPane.showMessageDialog( null, "Open failure" );
                exc.printStackTrace();
            }
	    }
	}
	
	/*
	 * Rules: 
	 * 1. Off-board cells are always dead.
	 * 2. A live cell with fewer than two live neighbors dies.
	 * 3. A live cell with two or three live neighbors remains alive.
	 * 4. A live cell with more than three live neighbors dies.
	 * 5. A dead cell with exactly three live neighbors becomes alive.
	 */
	private void nextState( boolean[][] cells )
	{
	    int            len     = cells.length;
	    boolean[][]    temp    = new boolean[cells.length][cells.length];
	    Neighborhood   neigh   = new Neighborhood();
	    for ( int row = 0 ; row < len ; ++row )
	        for ( int col = 0 ; col < len ; ++col )
	        {
	            neigh.reset( row,  col,  cells );
	            int    count   = neigh.getLivingCellCount();
	            if ( count < 2 || count > 3 )
	                temp[row][col] = false;
	            else if ( count == 3 )
	                temp[row][col] = true;
	            else
	                temp[row][col] = cells[row][col];
	        }
	    
	    board.setCells( temp );
	    board.refresh();
	}
    
    private static void pause( int millis )
    {
        try
        {
            Thread.sleep( millis );
        }
        catch ( InterruptedException exc )
        {
            // don't care
        }
    }
	
	public class Runner implements Runnable
	{
	    private    long   millis; 
	    public void run()
	    {
	        while ( running )
	        {
	            nextState( board.getCells() );
	            pause( 250 );
	        }
	    }
	    
	    public void updateGPS()
	    {
	        double val = controls.getSliderValue();
	        
	    }
	}
}

