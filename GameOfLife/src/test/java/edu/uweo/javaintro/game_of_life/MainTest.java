package edu.uweo.javaintro.game_of_life;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.SwingUtilities;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest
{
    private Board   board;
    
    @Before
    public void setUp() throws Exception
    {
        board = new Board();
        SwingUtilities.invokeLater( board );
    }

    @Test
    public void testNorthNeighborhood()
    {
        int     col     = 20;
        int     row     = 0;
        
        Neighborhood    neigh   = new Neighborhood( row, col, board.getCells() );
        boolean[]       result  = neigh.getNeighbors();
        for ( boolean alive : result )
            assertFalse( alive );
        
        Cell[]  cells   = new Cell[5];
        cells[0] = new Cell( row, col + 1, true );      // East
        cells[1] = new Cell( row + 1, col + 1, true );  // Southeast
        cells[2] = new Cell( row + 1, col, true );      // South
        cells[3] = new Cell( row + 1, col - 1, true );  // Southwest
        cells[4] = new Cell( row, col - 1, true );     // West
        board.setCells( cells );
        board.setCell( cells[0] );
        board.refresh();
        
        neigh = new Neighborhood( row, col, board.getCells() );
        result = neigh.getNeighbors();
        for ( int inx = 0 ; inx < result.length ; ++inx )
            assertTrue( "at " + inx, result[inx] );
        
//        pause();
    }

    private void pause()
    {
        InputStreamReader   reader      = new InputStreamReader( System.in );
        BufferedReader      bufReader   = new BufferedReader( reader );
        try
        {
            bufReader.readLine();
        }
        catch ( IOException exc )
        {
            System.err.println( exc.getMessage() );
            exc.printStackTrace();
        }
    }
}
