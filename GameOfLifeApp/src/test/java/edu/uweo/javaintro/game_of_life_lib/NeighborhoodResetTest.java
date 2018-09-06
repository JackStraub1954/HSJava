package edu.uweo.javaintro.game_of_life_lib;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

import javax.swing.SwingUtilities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import app.Neighborhood;
import edu.uweo.javaintro.game_of_life_lib.Board;
import edu.uweo.javaintro.game_of_life_lib.Cell;

public class NeighborhoodResetTest
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
        
        Neighborhood    neigh   = new Neighborhood( row, col, board.getCells() );
        boolean[]       result  = neigh.getNeighbors();
        for ( boolean alive : result )
            assertFalse( alive );
        
        Cell[]  cells   = new Cell[5];
        cells[0] = Utils.eastCell( row, col, true );
        cells[1] = Utils.sEastCell( row, col, true );
        cells[2] = Utils.southCell( row, col, true );
        cells[3] = Utils.sWestCell( row, col, true );
        cells[4] = Utils.westCell( row, col, true );
        board.setCells( cells );
        board.refresh();
        Utils.pause();

        boolean expected[]  = 
        {
            false, // north
            false, // nEast
            true,  // east
            true,  // sEast
            true,  // south
            true,  // sWest
            true,  // west
            false, // nWest
        };
        neigh.reset( row, col, board.getCells() );
        boolean actual[]    = neigh.getNeighbors();
        assertArrayEquals( expected, actual );
    }

    @Test
    public void testNEast()
    {
        int     row     = 0;
        int     col     = lastCell;
        
        Neighborhood    neigh   = new Neighborhood( row, col, board.getCells() );
        boolean[]       result  = neigh.getNeighbors();
        for ( boolean alive : result )
            assertFalse( alive );
        
        Cell[]  cells   = new Cell[3];
        cells[0] = Utils.southCell( row, col, true );
        cells[1] = Utils.sWestCell( row, col, true );
        cells[2] = Utils.westCell( row, col, true );
        board.setCells( cells );
        board.refresh();
//        Utils.pause();

        boolean expected[]  = 
        {
            false, // north
            false, // nEast
            false, // east
            false, // sEast
            true,  // south
            true,  // sWest
            true,  // west
            false, // nWest
        };
        neigh.reset( row, col, board.getCells() );
        boolean actual[]    = neigh.getNeighbors();
        assertArrayEquals( expected, actual );
    }

    @Test
    public void testEast()
    {
        int     row     = 10;
        int     col     = lastCell;
        
        Neighborhood    neigh   = new Neighborhood( row, col, board.getCells() );
        boolean[]       result  = neigh.getNeighbors();
        for ( boolean alive : result )
            assertFalse( alive );
        
        Cell[]  cells   = new Cell[5];
        cells[0] = Utils.northCell( row, col, true );
        cells[1] = Utils.southCell( row, col, true );
        cells[2] = Utils.sWestCell( row, col, true );
        cells[3] = Utils.westCell( row, col, true );
        cells[4] = Utils.nWestCell( row, col, true );
        board.setCells( cells );
        board.refresh();
//        Utils.pause();

        boolean expected[]  = 
        {
            true,  // north
            false, // nEast
            false, // east
            false, // sEast
            true,  // south
            true,  // sWest
            true,  // west
            true,  // nWest
        };
        neigh.reset( row, col, board.getCells() );
        boolean actual[]    = neigh.getNeighbors();
        assertArrayEquals( expected, actual );
    }

    @Test
    public void testSEast()
    {
        int     row     = lastCell;
        int     col     = lastCell;
        
        Neighborhood    neigh   = new Neighborhood( row, col, board.getCells() );
        boolean[]       result  = neigh.getNeighbors();
        for ( boolean alive : result )
            assertFalse( alive );
        
        Cell[]  cells   = new Cell[3];
        cells[0] = Utils.northCell( row, col, true );
        cells[1] = Utils.westCell( row, col, true );
        cells[2] = Utils.nWestCell( row, col, true );
        board.setCells( cells );
        board.refresh();
//        Utils.pause();

        boolean expected[]  = 
        {
            true,  // north
            false, // nEast
            false, // east
            false, // sEast
            false, // south
            false, // sWest
            true,  // west
            true,  // nWest
        };
        neigh.reset( row, col, board.getCells() );
        boolean actual[]    = neigh.getNeighbors();
        assertArrayEquals( expected, actual );
    }

    @Test
    public void testSouth()
    {
        int     row     = lastCell;
        int     col     = 10;
        
        Neighborhood    neigh   = new Neighborhood( row, col, board.getCells() );
        boolean[]       result  = neigh.getNeighbors();
        for ( boolean alive : result )
            assertFalse( alive );
        
        Cell[]  cells   = new Cell[5];
        cells[0] = Utils.northCell( row, col, true );
        cells[1] = Utils.nEastCell( row, col, true );
        cells[2] = Utils.eastCell( row, col, true );
        cells[3] = Utils.westCell( row, col, true );
        cells[4] = Utils.nWestCell( row, col, true );
        board.setCells( cells );
        board.refresh();
//        Utils.pause();

        boolean expected[]  = 
        {
            true,  // north
            true,  // nEast
            true,  // east
            false, // sEast
            false, // south
            false, // sWest
            true,  // west
            true,  // nWest
        };
        neigh.reset( row, col, board.getCells() );
        boolean actual[]    = neigh.getNeighbors();
        assertArrayEquals( expected, actual );
    }

    @Test
    public void testSWest()
    {
        int     row     = lastCell;
        int     col     = 0;
        
        Neighborhood    neigh   = new Neighborhood( row, col, board.getCells() );
        boolean[]       result  = neigh.getNeighbors();
        for ( boolean alive : result )
            assertFalse( alive );
        
        Cell[]  cells   = new Cell[3];
        cells[0] = Utils.northCell( row, col, true );
        cells[1] = Utils.nEastCell( row, col, true );
        cells[2] = Utils.eastCell( row, col, true );
        board.setCells( cells );
        board.refresh();
//        Utils.pause();

        boolean expected[]  = 
        {
            true,  // north
            true,  // nEast
            true,  // east
            false, // sEast
            false, // south
            false, // sWest
            false, // west
            false, // nWest
        };
        neigh.reset( row, col, board.getCells() );
        boolean actual[]    = neigh.getNeighbors();
        assertArrayEquals( expected, actual );
    }

    @Test
    public void testWest()
    {
        int     row     = 10;
        int     col     = 0;
        
        Neighborhood    neigh   = new Neighborhood( row, col, board.getCells() );
        boolean[]       result  = neigh.getNeighbors();
        for ( boolean alive : result )
            assertFalse( alive );
        
        Cell[]  cells   = new Cell[5];
        cells[0] = Utils.northCell( row, col, true );
        cells[1] = Utils.nEastCell( row, col, true );
        cells[2] = Utils.eastCell( row, col, true );
        cells[3] = Utils.sEastCell( row, col, true );
        cells[4] = Utils.southCell( row, col, true );
        board.setCells( cells );
        board.refresh();
//        Utils.pause();

        boolean expected[]  = 
        {
            true,  // north
            true,  // nEast
            true,  // east
            true,  // sEast
            true,  // south
            false, // sWest
            false, // west
            false, // nWest
        };
        neigh.reset( row, col, board.getCells() );
        boolean actual[]    = neigh.getNeighbors();
        assertArrayEquals( expected, actual );
    }

    @Test
    public void testNWest()
    {
        int     row     = 0;
        int     col     = 0;
        
        Neighborhood    neigh   = new Neighborhood( row, col, board.getCells() );
        boolean[]       result  = neigh.getNeighbors();
        for ( boolean alive : result )
            assertFalse( alive );
        
        Cell[]  cells   = new Cell[3];
        cells[0] = Utils.eastCell( row, col, true );
        cells[1] = Utils.sEastCell( row, col, true );
        cells[2] = Utils.southCell( row, col, true );
        board.setCells( cells );
        board.refresh();
//        Utils.pause();

        boolean expected[]  = 
        {
            false, // north
            false, // nEast
            true,  // east
            true,  // sEast
            true,  // south
            false, // sWest
            false, // west
            false, // nWest
        };
        neigh.reset( row, col, board.getCells() );
        boolean actual[]    = neigh.getNeighbors();
        assertArrayEquals( expected, actual );
    }    

    @Test
    public void testInterior()
    {
        int     row     = 10;
        int     col     = 10;
        
        Neighborhood    neigh   = new Neighborhood( row, col, board.getCells() );
        boolean[]       result  = neigh.getNeighbors();
        for ( boolean alive : result )
            assertFalse( alive );
        
        Cell[]  cells   = new Cell[8];
        cells[0] = Utils.northCell( row, col, true );
        cells[1] = Utils.nEastCell( row, col, true );
        cells[2] = Utils.eastCell( row, col, true );
        cells[3] = Utils.sEastCell( row, col, true );
        cells[4] = Utils.southCell( row, col, true );
        cells[5] = Utils.sWestCell( row, col, true );
        cells[6] = Utils.westCell( row, col, true );
        cells[7] = Utils.nWestCell( row, col, true );
        board.setCells( cells );
        board.refresh();
//        Utils.pause();

        boolean expected[]  = 
        {
            true,  // north
            true,  // nEast
            true,  // east
            true,  // sEast
            true,  // south
            true,  // sWest
            true,  // west
            true,  // nWest
        };
        neigh.reset( row, col, board.getCells() );
        boolean actual[]    = neigh.getNeighbors();
        assertArrayEquals( expected, actual );
    }
}
