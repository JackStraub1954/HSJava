package edu.uweo.javaintro.game_of_life;

import static org.junit.Assert.*;

import org.junit.Test;

public class NeighborhoodMiscTest
{
    @Test(expected = IndexOutOfBoundsException.class) 
    public void testInvalidRow1()
    {
        int             len     = 10;
        boolean[][]     cells   = new boolean[len][len];
        Neighborhood    neigh   = new Neighborhood( -1, 0, cells );
    }
    
    @Test(expected = IndexOutOfBoundsException.class) 
    public void testInvalidRow2()
    {
        int             len     = 10;
        boolean[][]     cells   = new boolean[len][len];
        Neighborhood    neigh   = new Neighborhood( len, 0, cells );
    }
    @Test(expected = IndexOutOfBoundsException.class) 
    public void testInvalidCol1()
    {
        int             len     = 10;
        boolean[][]     cells   = new boolean[len][len];
        Neighborhood    neigh   = new Neighborhood( 0, -1, cells );
    }
    
    @Test(expected = IndexOutOfBoundsException.class) 
    public void testInvalidCol2()
    {
        int             len     = 10;
        boolean[][]     cells   = new boolean[len][len];
        Neighborhood    neigh   = new Neighborhood( 0, len, cells );
    }
    
    @Test
    public void testMiscGetters()
    {
        int             len     = 10;
        int             row     = 5;
        int             col     = 6;
        boolean[][]     cells   = new boolean[len][len];
        Neighborhood    neigh   = new Neighborhood( row, col, cells );
        
        boolean[][]     actCells    = neigh.getCells();
        int             actRow      = neigh.getRow();
        int             actCol      = neigh.getCol();
        
        assertEquals( row, actRow );
        assertEquals( col, actCol );
        assertArrayEquals( cells, actCells );
    }
}
