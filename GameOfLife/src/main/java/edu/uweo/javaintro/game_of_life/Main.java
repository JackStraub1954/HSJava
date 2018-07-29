package edu.uweo.javaintro.game_of_life;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

public class Main
	implements ActionListener, ControlListener
{
	private Board	board;
	
	public static void main( String[] args )
	{
		Main		app			= new Main();
		Controls	controls	= new Controls();
		controls.addControlListener( app );
		controls.start();
		app.execute();
	}
	
	private void execute()
	{
		board = new Board( 15 );
		board.addActionListener( this );
		SwingUtilities.invokeLater( board );
	}
	
	public void actionPerformed( ActionEvent evt )
	{
		Cell	cell	= (Cell)evt.getSource();
		System.out.println( cell );
		cell.toggleAlive();
		board.setCell( cell );
		board.refresh();
	}
	
	public void controlActivated( ActionEvent evt )
	{
		nextState( board.getCells() );
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
}

