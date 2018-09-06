package edu.uweo.javaintro.game_of_life_lib;

import java.util.Objects;

/**
 * An object of this class encapsulates the properties of a cell
 * on the game board's grid.
 * The properties are the position of the cell (specified as row/column)
 * and the cell's state, alive or dead.
 */
public class Cell
{
    
    /** The row containing the cell. */
    private int     row;
    
    /** The column containing the cell. */
    private int     col;
    
    /** True if this cell is alive. */
    private boolean    alive;
    
    /**
     * Instantiates a new cell using the given row and column.
     * The state of the cell is explicitly set to dead.
     *
     * @param row the given row
     * @param col the given column
     */
    public Cell( int row, int col )
    {
        this( row, col, false );
    }
    
    /**
     * Instantiates a new cell using the given row, column and state.
     *
     * @param row      the given row
     * @param col      the given col
     * @param state    the given state
     */
    public Cell( int row, int col, boolean state )
    {
        this.row = row;
        this.col = col;
        this.alive = state;
    }
    
    /**
     * Copy constructor. Creates a new Cell with the
     * same properties as a given cell.
     *
     * @param that the given cell
     */
    public Cell( Cell that )
    {
        this.row = that.row;
        this.col = that.col;
        this.alive = that.alive;
    }

    /**
     * Gets the row of this cell.
     *
     * @return the row
     */
    public int getRow()
    {
        return row;
    }

    /**
     * Sets the row of this cell.
     *
     * @param row the new row
     */
    public void setRow(int row)
    {
        this.row = row;
    }

    /**
     * Gets the column of this cell.
     *
     * @return the column of this cell
     */
    public int getCol()
    {
        return col;
    }

    /**
     * Sets the column of this cell.
     *
     * @param col the new col
     */
    public void setCol(int col)
    {
        this.col = col;
    }

    /**
     * Checks if this cell is alive.
     *
     * @return true, if cell is alive
     */
    public boolean isAlive()
    {
        return alive;
    }

    /**
     * Sets the state of this cell. <em>True</em>
     * to make the cell alive, false to make it dead.
     *
     * @param alive the new alive
     */
    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }
    
    /**
     * Toggles the state of this cell. 
     * Alive cells become dead, dead cells become alive.
     */
    public void toggleAlive()
    {
        alive = !alive;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        int    hash    = Objects.hash( row, col, alive );
        return hash;
    }
    
    /**
     * Compares two cells for equality.
     * Two cells are equal if the have the same
     * row, column and state.
     */
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
                this.col == that.col
                && this.row == that.row
                && this.alive == that.alive;
        }
        
        return rval;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder    bldr    = new StringBuilder();
        bldr.append( "row=").append(row)
            .append( ",col=").append( col )
            .append( ",alive=" ).append( alive );
        return bldr.toString();
    }
}
