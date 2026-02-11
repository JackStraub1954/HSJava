package edu.uweo.javaintro.game_of_life_lib;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import app.Neighborhood;

public class NeighborhoodMiscTest
{
    @Test(expected = IndexOutOfBoundsException.class) 
    public void testInvalidRow1()
    {
    }
    
    @Test(expected = IndexOutOfBoundsException.class) 
    public void testInvalidRow2()
    {
    }
    @Test(expected = IndexOutOfBoundsException.class) 
    public void testInvalidCol1()
    {
    }
    
    @Test(expected = IndexOutOfBoundsException.class) 
    public void testInvalidCol2()
    {
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
