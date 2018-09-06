package app;

/**
 * An instance of this class represents the neighborhood of a given
 * cell in Conway's Game of Life. <em>Neighborhood</em> is defined as the
 * cell and its eight peripheral neighbors. <br>
 * 
 * <table style = "margin-left: auto; margin-right: auto">
 * <caption style = "caption-side: bottom; font-size: 80%;">The Neighborhood of a Cell</caption>
 * <tr>
 * <td style = "border: 2px solid black; width: 20px; 
        height: 20px; background-color: gray;">&nbsp;</td>
 * <td style = "border: 2px solid black; width: 20px; 
        height: 20px; background-color: gray;">&nbsp;</td>
 * <td style = "border: 2px solid black; width: 20px; 
        height: 20px; background-color: gray;">&nbsp;</td>
 * </tr>
 * <tr>
 * <td style = "border: 2px solid black; width: 20px; 
        height: 20px; background-color: gray;">&nbsp;</td>
 * <td style = "border: 2px solid black; width: 20px; 
        height: 20px; background-color: lightGray;">&nbsp;</td>
 * <td style = "border: 2px solid black; width: 20px; 
        height: 20px; background-color: gray;">&nbsp;</td>
 * </tr>
 * <tr>
 * <td style = "border: 2px solid black; width: 20px; 
        height: 20px; background-color: gray;">&nbsp;</td>
 * <td style = "border: 2px solid black; width: 20px; 
        height: 20px; background-color: gray;">&nbsp;</td>
 * <td style = "border: 2px solid black; width: 20px; 
        height: 20px; background-color: gray;">&nbsp;</td>
 * </tr>
 * </table>
 * 
 * Individual neighbors are referred to as (clockwise, from upper left) 
 * <em>northwest (NW), north (N), northeast (NE), east (E), southeast (SE), 
 * south (S), southwest (SW) </em>and<em> west (W).</em> Life and death of a cell
 * are determined by the original rules as specified by Conway:
 * <ol>
 * <li>Any live cell with fewer than two live neighbors dies, as if by under population.</li>
 * <li>Any live cell with two or three live neighbors lives on to the next generation.</li>
 * <li>Any live cell with more than three live neighbors dies, as if by overpopulation.</li>
 * <li>
 * Any dead cell with exactly three live neighbors becomes a live cell,
 * as if by reproduction.
 * </li>
 * </ol>
 * In addition:
 * <ol>
 * <li>
 * Each new generation of cells is computed simultaneously
 * (also as specified by Conway).
 * That is to say, if cells A and B are adjacent, 
 * the new state of A is dependent on 
 * the current state of cell B, and the new state of B is 
 * is dependent on the current state of cell A.
 * </li>
 * <li>
 * The size of the board is finite (contrary to Conway's original
 * design).
 * </li>
 * <li>
 * Any cell outside of the bounds of the board is considered dead.
 * </li>
 * <li>
 * The state of the board is represented by a two-dimensional
 * array of Boolean values; a cell with a state of <em>false</em>
 * is considered dead, and a cell with a state of <em>true</em>
 * is considered alive.
 * </li>
 * </ol>
 * @see <a href=https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life>
 *     <em>Conway's Game of Life,</em> on Wikipedia.
 * </a>
 */
public class Neighborhood
{
    private int         row;
    private int         col;
    private boolean[][] cells;
    private boolean[]   neighbors   = new boolean[8];
    
    /**
     * Instantiates a new neighborhood.
     * 
     * @see #reset(int, int, boolean[][])
     */
    public Neighborhood()
    {
    }

    /**
     * Instantiates a neighborhood for a given cell.
     *
     * @param row   The row of the given cell.
     * @param col   The column of the given cell.       
     * @param cells Array specifying the state of every cell on the board.
     * 
     * @throws IndexOutOfBoundsException if the given
     *         row/column pair is not on the board.
     * 
     * @see #reset(int, int, boolean[][])
     */
    public Neighborhood( int row, int col, boolean[][] cells )
    {
        reset( row, col, cells );
    }
    
    /**
     * Changes this neighborhood to reflect the neighborhood
     * of a different cell.
     *
     * @param row   The row of the new cell.
     * @param col   The column of the new cell.
     * @param cells Array specifying the state of every cell on the board.
     * 
     * @throws IndexOutOfBoundsException if the given
     *         row/column pair is not on the board.
     */
    public void reset( int row, int col, boolean[][] cells )
        throws IndexOutOfBoundsException
    {
        this.cells = cells;
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
        if ( row < 0 || row >= len )
            throw new IndexOutOfBoundsException( "row = " + row );
        if ( col < 0 || col >= len )
            throw new IndexOutOfBoundsException( "col = " + col );
        
        int  lastRow = len - 1;
        int  lastCol = len - 1;
                
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
                nEast = false;
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
                nEast = cells[lastRow - 1][col + 1];
                east = cells[lastRow][col + 1];
                west = false;
                nWest = false;
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
                nWest = cells[lastRow - 1][col - 1];
            }
        }
        else if ( col == 0 )
        {
            north = cells[row - 1][0];
            nEast = cells[row - 1][1];
            east = cells[row][1];
            sEast = cells[row + 1][1];
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
        else
        {
            north = cells[row - 1][col];
            nEast = cells[row - 1][col + 1];
            east = cells[row][col + 1];
            sEast = cells[row + 1][col + 1];
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
    
    /**
     * Gets the number of live neighbors in this neighborhood.
     *
     * @return the number of live neighbors in this neighborhood
     */
    public int getLivingCellCount()
    {
        int count   = 0;
        int limit   = neighbors.length;
        for ( int inx = 0 ; inx < limit ; ++inx )
            count += neighbors[inx] ? 1 : 0;
        return count;
    }

    /**
     * Gets the row of the cell at the center
     * of this neighborhood.
     *
     * @return the row of the cell at the center of this neighborhood.
     */
    public int getRow()
    {
        return row;
    }

    /**
     * Gets the column of the cell at the center
     * of this neighborhood..
     *
     * @return the column of the cell at the center of this neighborhood
     */
    public int getCol()
    {
        return col;
    }

    /**
     * Gets the array representing the state of the whole board.
     *
     * @return the array representing the state of the whole board
     */
    public boolean[][] getCells()
    {
        return cells;
    }

    /**
     * Gets an array representing the state
     * of each of the eight neighbors of the cell at the center
     * of this neighborhood.
     *
     * @return the neighbors
     */
    public boolean[] getNeighbors()
    {
        return neighbors;
    }
}
