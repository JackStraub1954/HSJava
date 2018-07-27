package edu.uweo.javaintro.game_of_life;

public class Neighborhood
{
    private int         row;
    private int         col;
    private boolean[][] cells;
    private boolean[]   neighbors   = new boolean[8];
    
    public Neighborhood()
    {
        reset( 0, 0 );
    }

    public Neighborhood( int row, int col, boolean[][] cells )
    {
        this.cells = cells;
        reset( row, col );
    }
    
    public void reset( int row, int col )
    {
        this.row = row;
        this.col = col;
        
        boolean north   = false;
        boolean nEast   = false;
        boolean east    = false;
        boolean sEast   = false;
        boolean south   = false;
        boolean sWest   = false;
        boolean west    = false;
        boolean nWest   = false;

        int   len     = cells.length;
        if ( row < 0  || row >= len )
            throw new IndexOutOfBoundsException( "row = " + row );
        if ( col < 0 || col >= len )
            throw new IndexOutOfBoundsException( "col = " + row );
        
        int  lastRow = len -1;
        int  lastCol = len -1;
                
        if ( row == 0 )
        {
            north = false;
            nEast = false;
            // east
            // sEast
            south = cells[1][col];
            //sWest
            // west
            nWest = false;
            if ( col == 0 )
            {
                east = cells[0][col + 1];
                sEast = cells[1][col + 1];
                sWest = false;
                west = false;
            }
            else if ( col == lastCol )
            {
                east = false;
                sEast = false;
                sWest = cells[1][col - 1];
                west = cells[0][col - 1];
            }
            else
            {
                east = cells[0][col - 1];
                sEast = cells[1][col + 1];
                sWest = cells[1][col - 1];
                west = cells[0][col + 1];
            }
        }
        else if ( row == lastRow )
        {
            north = cells[lastRow - 1][col];
            //nEast
            //east
            sEast = false;
            south = false;
            sWest = false;
            // west
            // nWest
            if ( col == 0 )
            {
                west = false;
                nWest = false;
                nEast = cells[lastRow - 1][col + 1];
                east = cells[lastRow][col + 1];
            }
            else if ( col == lastCol )
            {
                nEast = false;
                east = false;
                nWest = cells[lastRow - 1][col - 1];
                west = cells[lastRow][col - 1];
            }
            else
            {
                nEast = cells[lastRow - 1][col + 1];
                east = cells[lastRow][col + 1];
                west = cells[lastRow][col - 1];
            }
        }
        else if ( col == 0 )
        {
            north = cells[row - 1][0];
            nEast = cells[row - 1][1];
            east = cells[1][row];
            sEast = cells[1][row + 1];
            south = cells[row + 1][0];
            sWest = false;
            west = false;
            nWest = false;
        }
        else if ( col == lastCol )
        {
            north = cells[row - 1][lastCol];
            nEast = false;
            east = false;
            sEast = false;
            south = cells[row + 1][col];
            sWest = cells[row + 1][col - 1];
            west = cells[row][col - 1];
            nWest = cells[row - 1][col - 1];
        }
        
        neighbors[0] = north;
        neighbors[1] = nEast;
        neighbors[2] = east;
        neighbors[3] = sEast;
        neighbors[4] = south;
        neighbors[5] = sWest;
        neighbors[6] = west;
        neighbors[7] = nWest;
    }
    
    public int getLivingCellCount()
    {
        int count   = 0;
        int limit   = neighbors.length;
        for ( int inx = 0 ; inx < limit ; ++inx )
            count += neighbors[inx] ? 1 : 0;
        return count;
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public boolean[][] getCells()
    {
        return cells;
    }

    public boolean[] getNeighbors()
    {
        return neighbors;
    }

}
