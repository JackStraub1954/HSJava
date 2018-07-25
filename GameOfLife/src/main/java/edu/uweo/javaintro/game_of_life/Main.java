package edu.uweo.javaintro.game_of_life;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

public class Main
	implements ActionListener, ControlListener
{
	private Board	board	= new Board();
	
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
		board = new Board();
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
		
		boolean[][]	cells	= board.getCells();
	}
	
	public void controlActivated( ActionEvent evt )
	{
		System.out.println( "activated" );
	}
	
	private class Neighborhood
	{
		private int		row;
		private int		col;
		private	boolean	north;
		private boolean nEast;
		private boolean	east;
		private boolean	sEast;
		private boolean south;
		private boolean sWest;
		private boolean west;
		private boolean nWest;
		
		private boolean[][]	cells	= board.getCells();
		
		public Neighborhood( int row, int col )
		{
			reset( row, col );
		}
		
		public void reset( int row, int col )
		{
		    int   len     = cells.length;
			if ( row < 0  || row >= len )
				throw new IndexOutOfBoundsException( "row = " + row );
			if ( col < 0 || col >= len )
				throw new IndexOutOfBoundsException( "col = " + row );
			
			Point[]  neighbors   = new Point[8];
			neighbors[0] = new Point( row - 1, col - 1 ); // nw
			neighbors[1] = new Point( row - 1, col ); // n
			neighbors[2] = new Point( row - 1, col + 1 ); // ne
			neighbors[3] = new Point( row, col + 1 ); // e
			neighbors[4] = new Point( row + 1, col + 1 );  //se
			neighbors[5] = new Point( row + 1, col ); // s
			neighbors[6] = new Point( row + 1, col - 1 ); // sw
			neighbors[7] = new Point( row, col - 1 ); // w
			
			if ( neighbors[0].x < 0 || neighbors[0].y < 0 )
			    nWest = false;
			else
			    nWest = cells[neighbors[0].x - 1][neighbors[0].y - 1];
		}
	}
}

