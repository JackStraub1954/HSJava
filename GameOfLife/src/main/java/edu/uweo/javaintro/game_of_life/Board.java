package edu.uweo.javaintro.game_of_life;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Board
	implements Runnable
{
	private JFrame		frame		= new JFrame( "Game of Life" );
	
	private boolean		useGrid		= true;
	private boolean		useBorder	= true;
	private Color		gridColor	= new Color( .8f, .8f, .8f );
	private Color		borderColor	= Color.BLUE;
	private Color		cellColor	= Color.BLACK;
	private int			cellSize	= 0;
	
	/**
	 * Determines the "width" of the border drawn around the
	 * outside of the board.
	 * 
	 * <div style = 
	 * 	"border-left: 5px solid black;
	 *   border-top:  5px solid black;
	 *   max-width: 5em;
	 *   height: 5em;"
	 * >
	 * <pre>   &#x2191;
	 * &#x2190; border
	 * </pre>
	 * </div>
	 */
	private int			borderWidth		= 5;
	private int			gridLineWidth	= 1;
	private int			gridSide		= 500;
	private int			minCellSize		= 10;
	private Color		background		= Color.WHITE;
	private Color		gridCellColor	= Color.BLACK;
	private boolean		interactive		= true;
	private boolean[][]	allCells		= new boolean[gridSide][gridSide];
	
	private List<ActionListener>	listeners	= new ArrayList<>();
	
	public void run()
	{
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setContentPane( new Canvas() );
		frame.pack();
		frame.setVisible( true );
	}
	
	public void addActionListener( ActionListener listener )
	{
		listeners.add( listener );
	}
	
	public void removeActionListener( ActionListener listener )
	{
		listeners.remove( listener );
	}
	
	public boolean[][] getCells()
	{
		boolean[][]	arr	= new boolean[allCells.length][];
		for ( int inx = 0 ; inx < arr.length ; ++inx )
			arr[inx] = Arrays.copyOf( allCells[inx], allCells[inx].length  );
		return arr;
	}
	
	public boolean getInteractive()
	{
		return interactive;
	}
	
	public void setInteractive( boolean interactive )
	{
		this.interactive = interactive;
	}
	
	public void setCell( Cell cell )
	{
		int	row	= cell.getRow();
		int col = cell.getCol();
		if ( row >= gridSide || row < 0 || col >= gridSide)
			throw new IndexOutOfBoundsException( cell.toString() );
		allCells[row][col] = cell.isAlive();
	}
	
	public void refresh()
	{
		frame.repaint();
	}
	
	@SuppressWarnings("serial")
    private class Canvas extends JPanel
	{
		private Stroke		borderStroke	=
			new BasicStroke( borderWidth ); 
		private Stroke		gridLineStroke	= 
			new BasicStroke( gridLineWidth );
		private Stroke		gridStroke		=
			new BasicStroke( gridLineWidth );
		private int			width;
		private int			height;
		private boolean		interactive		= true;
		
		public Canvas()
		{
			setPreferredSize( new Dimension( 500, 500 ) );
			addMouseListener( new MouseProcessor() );
		}
		
		@Override
		public void paintComponent( Graphics graphics )
		{
			super.paintComponent( graphics );
			
			Graphics2D	gtx	= (Graphics2D)graphics.create();
			
			width = getWidth();
			height = getHeight();
			double	base		= width < height ? width : height;
			cellSize = (int)Math.round( base / gridSide );
			if ( cellSize < minCellSize )
			{
				cellSize = minCellSize + gridLineWidth;
			}
			else
			{
				cellSize = (int)Math.round( cellSize + gridLineWidth );
			}
			
			paintBorder( gtx );
			paintGrid( gtx );
		}
		
		private void paintBorder( Graphics2D gtx)
		{
			if ( useBorder )
			{
				gtx.setStroke( borderStroke );
				gtx.setColor( borderColor );
				gtx.drawLine( 0, 0, width, 0 );
				gtx.drawLine( 0, height, width, height );
				gtx.drawLine( 0, 0, 0, height );
				gtx.drawLine( width, 0, width, height );
			}
		}
		
		private void paintGrid( Graphics2D gtx )
		{
			int	xco	= 0;
			int yco	= 0;
			gtx.setStroke( gridLineStroke );
			if ( useBorder )
			{
				xco += borderWidth;
				yco += borderWidth;
			}
			
			Rectangle2D	rect	= new Rectangle2D.Double();
			for ( int inx = 0 ; inx < gridSide ; ++inx )
			{
				for ( int jnx = 0 ; jnx < gridSide ; ++jnx )
				{
					int	col	= xco + jnx * cellSize;
					rect.setRect( col , yco, cellSize, cellSize );
					if ( useGrid )
					{
						gtx.setColor( gridColor);
						gtx.draw( rect );
					}
					if ( allCells[inx][jnx] )
					{
						gtx.setColor( gridCellColor );
						gtx.fill( rect );
					}
				}
				yco += cellSize;
			}
		}
	}
	
	private class MouseProcessor implements MouseListener
	{

		@Override
		public void mouseClicked( MouseEvent evt )
		{
			if ( interactive )
			{
				int	xco	= evt.getX();
				int yco = evt.getY();
				if ( useBorder )
				{
					xco -= borderWidth;
					yco -= borderWidth;
				}
				
				int 	row			= (int)(yco + .5) / cellSize;
				int 	col 		= (int)(xco + .5) / cellSize;
				boolean	alive		= allCells[row][col];
				Cell	cell		= new Cell( row, col, alive );
				int		ident		= evt.getID();
				int		modifiers	= evt.getModifiers();
				ActionEvent	event	= 
					new ActionEvent( cell, ident, "", modifiers );
				for ( ActionListener listener : listeners )
					listener.actionPerformed( event );
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
}
