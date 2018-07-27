package edu.uweo.javaintro.game_of_life;

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
		board.setCell( new Cell( 0, 20, true ) );
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
	
//	public static class Neighborhood
//	{
//		public Neighborhood data = new Neighborhood();
//
//        public Neighborhood( int row, int col, boolean[][] cells  )
//		{
//		}
//		
}

