package edu.uweo.javaintro.game_of_life;

import java.util.Objects;

public class Cell
{
	private int 	row;
	private int 	col;
	private boolean	alive;
	
	public Cell( int row, int col )
	{
		this( row, col, false );
	}
	
	public Cell( int row, int col, boolean val )
	{
		this.row = row;
		this.col = col;
		this.alive = val;
	}
	
	public Cell( Cell that )
	{
		this.row = that.row;
		this.col = that.col;
		this.alive = that.alive;
	}

	public int getRow()
	{
		return row;
	}

	public void setRow(int row)
	{
		this.row = row;
	}

	public int getCol()
	{
		return col;
	}

	public void setCol(int col)
	{
		this.col = col;
	}

	public boolean isAlive()
	{
		return alive;
	}

	public void setAlive(boolean alive)
	{
		this.alive = alive;
	}
	
	public void toggleAlive()
	{
		alive = !alive;
	}
	
	@Override
	public int hashCode()
	{
	    int    hash    = Objects.hash( row, col, alive );
	    return hash;
	}
	
	@Override
	public boolean equals( Object obj )
	{
	    boolean    rval    = false;
	    
	    if ( obj == this )
	        rval = true;
	    else if ( obj == null )
	        rval = false;
	    else if ( !(obj instanceof Cell) )
	        rval = false;
	    else
	    {
	        Cell   that    = (Cell)obj;
	        rval = 
	            this.col == that.col &&
	            this.row == that.row &&
	            this.alive == that.alive;
	    }
	    
	    return rval;
	}
	
	@Override
	public String toString()
	{
		StringBuilder	bldr	= new StringBuilder();
		bldr.append( "row=").append(row)
		    .append( ",col=").append( col )
		    .append( ",alive=" ).append( alive );
		return bldr.toString();
	}
}
