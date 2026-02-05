package edu.uweo.javaintro.game_of_life_lib;

import static org.junit.Assert.assertEquals;

import javax.swing.SwingUtilities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import app.Neighborhood;

public class NeighborhoodCountTest
{
    private Board   board;
    private int     lastCell;
    
    @Before
    public void setUp() throws Exception
    {
        board = new Board( 40 );
        SwingUtilities.invokeLater( board );
        lastCell = board.getSide() - 1;
    }
    
    @After
    public void tearDown()
    {
        board.close();
    }

    @Test
    public void testNorth()
    {
        int     row     = 0;
        int     col     = 10;
        
        Cell[]  cells   = new Cell[5];
        cells[0] = Utils.eastCell( row, col, true );
        cells[1] = Utils.sEastCell( row, col, true );
        cells[2] = Utils.southCell( row, col, true );
        cells[3] = Utils.sWestCell( row, col, true );
        cells[4] = Utils.westCell( row, col, true );
        validateLivingCount( row, col, cells );
    }

    @Test
    public void testNEast()
    {
        int     row     = 0;
        int     col     = lastCell;
        Cell[]  cells   = new Cell[3];
        cells[0] = Utils.southCell( row, col, true );
        cells[1] = Utils.sWestCell( row, col, true );
        cells[2] = Utils.westCell( row, col, true );
        validateLivingCount( row, col, cells );
    }

    @Test
    public void testEast()
    {
        int     row     = 10;
        int     col     = lastCell;
        Cell[]  cells   = new Cell[5];
        cells[0] = Utils.northCell( row, col, true );
        cells[1] = Utils.southCell( row, col, true );
        cells[2] = Utils.sWestCell( row, col, true );
        cells[3] = Utils.westCell( row, col, true );
        cells[4] = Utils.nWestCell( row, col, true );
        validateLivingCount( row, col, cells );
    }

    @Test
    public void testSEast()
    {
        int     row     = lastCell;
        int     col     = lastCell;
        Cell[]  cells   = new Cell[3];
        cells[0] = Utils.northCell( row, col, true );
        cells[1] = Utils.westCell( row, col, true );
        cells[2] = Utils.nWestCell( row, col, true );
        validateLivingCount( row, col, cells );
    }

    @Test
    public void testSouth()
    {
        int     row     = lastCell;
        int     col     = 10;
        Cell[]  cells   = new Cell[5];
        cells[0] = Utils.northCell( row, col, true );
        cells[1] = Utils.nEastCell( row, col, true );
        cells[2] = Utils.eastCell( row, col, true );
        cells[3] = Utils.westCell( row, col, true );
        cells[4] = Utils.nWestCell( row, col, true );
        validateLivingCount( row, col, cells );
    }

    @Test
    public void testSWest()
    {
        int     row     = lastCell;
        int     col     = 0;
        Cell[]  cells   = new Cell[3];
        cells[0] = Utils.northCell( row, col, true );
        cells[1] = Utils.nEastCell( row, col, true );
        cells[2] = Utils.eastCell( row, col, true );
        validateLivingCount( row, col, cells );
    }

    @Test
    public void testWest()
    {
        int     row     = 10;
        int     col     = 0;
        Cell[]  cells   = new Cell[5];
        cells[0] = Utils.northCell( row, col, true );
        cells[1] = Utils.nEastCell( row, col, true );
        cells[2] = Utils.eastCell( row, col, true );
        cells[3] = Utils.sEastCell( row, col, true );
        cells[4] = Utils.southCell( row, col, true );
        validateLivingCount( row, col, cells );
    }

    @Test
    public void testNWest()
    {
        int     row     = 0;
        int     col     = 0;
        Cell[]  cells   = new Cell[3];
        cells[0] = Utils.eastCell( row, col, true );
        cells[1] = Utils.sEastCell( row, col, true );
        cells[2] = Utils.southCell( row, col, true );
        validateLivingCount( row, col, cells );
    }

    private void validateLivingCount( int row, int col, Cell[] cells )
    {    
        int             expCount    = cells.length;
        Neighborhood    neigh       = new Neighborhood();
        board.setCells( cells );
        board.refresh();
        
        for ( Cell cell : cells )
        {
            //Utils.pause();
            neigh.reset( row, col, board.getCells() );
            int     actCount    = neigh.getLivingCellCount();
            assertEquals( expCount--, actCount );
            cell.setAlive( false );
            board.setCell( cell );
            board.refresh();
        }
        neigh.reset( row, col, board.getCells() );
        assertEquals( 0, neigh.getLivingCellCount() );
    }
}
