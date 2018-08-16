package edu.uweo.javaintro.game_of_life;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.util.function.Predicate;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

public class BoardTest
{
    private Robot   robot;
    private Board   board;
    
    @Before
    public void setUp()
    {
        try
        {
            board = new Board();
            
            robot = new Robot();
            robot.setAutoWaitForIdle( true );
//            robot.setAutoDelay( 50 );
        }
        catch ( AWTException exc )
        {
            exc.printStackTrace();
            fail( exc.getMessage() );
        }
    }
    
    @Test
    public void testBoard()
    {
        board.addActionListener( e ->
            { Cell cell = (Cell)e.getSource();
              cell.setAlive( true );
              board.setCell( cell );
            }
        );
        
        int gridSide    = (Integer)Properties.GRID_SIDE.getProperty();
        int limit       = 10;
        
        board.start();
        for ( int inx = 0 ; inx < limit ; ++inx )
        {
            Cell    cell    = new Cell( inx, inx, true );
            Point   point   = getCellPosition( board, cell );
            click( point );
            board.refresh();
        }
        
        Utils.pause( 250 );
        boolean[][] cells   = board.getCells();
        
        // Is number of rows correct?
        assertEquals( gridSide, cells.length );
        for ( int inx = 0 ; inx < limit ; ++inx )
            for ( int jnx = 0 ; jnx < limit ; ++jnx )
            {
                // is length of row correct?
                assertEquals( gridSide, cells[inx].length );
                if ( inx == jnx )
                    assertTrue( "" + inx + ", " + jnx, cells[inx][jnx] );
                else
                    assertFalse( cells[inx][jnx] );
            }
        
        validateImage( cells );
    }

    @Test
    public void testBoardInt()
    {
        int         gridSide    = 75;
        Board       board       = new Board( gridSide );
        boolean[][] cells       = board.getCells();
        assert( cells.length == gridSide );
        for ( int inx = 0 ; inx < gridSide ; ++inx )
            assertEquals( cells[inx].length, gridSide );
    }

    @Test
    public void testRun()
    {
        //fail("Not yet implemented");
    }

    @Test
    public void testAddActionListener()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testRemoveActionListener()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testSetGetCells()
    {
        board.start();
        
        int         gridSide    = (Integer)Properties.GRID_SIDE.getProperty();
        boolean[][] act         = board.getCells();
        assertEquals( gridSide, act.length );
        for ( int inx = 0 ; inx < gridSide ; ++inx )
        {
            assertEquals( act[inx].length, gridSide );
            for ( int jnx = 0 ; jnx < gridSide ; ++jnx )
                assertFalse( act[inx][jnx] );
            
        }
        boolean[][] exp         = new boolean[gridSide][gridSide];
        boolean     val         = true;
        for ( int inx = 0 ; inx < gridSide ; ++inx )
        {
            for ( int jnx = 0 ; jnx < gridSide ; ++jnx )
                exp[inx][jnx] = val;
            val = !val;
        }
        
        board.setCells( exp );
        act = board.getCells();
        for ( int inx = 0 ; inx < gridSide ; ++inx )
            for ( int jnx = 0 ; jnx < gridSide ; ++jnx )
                assertEquals( exp[inx][jnx], act[inx][jnx] );
//        Utils.pause();
    }

    @Test
    public void testGetSide()
    {
        int exp = (Integer)Properties.GRID_SIDE.getProperty();
        int act = board.getSide();
        assertEquals( exp, act );
        
        exp /= 2;
        Properties.GRID_SIDE.setProperty( exp );
        board = new Board();
        act = board.getSide();
        assertEquals( exp, act );
    }

    @Test
    public void testSetCell()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testSetCellsCellArray()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testSetCellsBooleanArrayArray()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testRefresh()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testClear()
    {
        board.start();
        
        int     gridSide    = (Integer)Properties.GRID_SIDE.getProperty();
        for ( int inx = 0 ; inx < gridSide ; ++inx )
        {
            Cell    cell    = new Cell( inx, inx, true );
            board.setCell( cell );
        }
        
        boolean[][] cells   = board.getCells();
        for ( int inx = 0 ; inx < gridSide ; ++inx )
            for ( int jnx = 0 ; jnx < gridSide ; ++jnx )
            {
                if ( inx == jnx )
                    assertTrue( "" + inx + ", " + jnx, cells[inx][jnx] );
                else
                    assertFalse( cells[inx][jnx] );
            }
    }
    
    private JPanel getCanvas()
    {
        Predicate<Component>    pred    = 
            c -> c instanceof JPanel &&
            Board.CANVAS_NAME.equals( c.getName() );
        JPanel  panel   = (JPanel)Utils.findComponent( pred );
        assertNotNull( panel );
        
        return panel;
    }
    
    private void validateImage( boolean[][] cells )
    {
        BufferedImage   image       = getCanvasSnapshot();
        int             side        = board.getCellSide();
        Point           topLeft     = new Point( side / 2, side / 2 );
        Object          borderColor = Properties.BORDER_COLOR.getProperty();
        int             borderWidth = 
            (Integer)Properties.BORDER_WIDTH.getProperty();
        Color           reset       = 
            (Color)Properties.BACKGROUND_COLOR.getProperty();
        Color           set         = 
                (Color)Properties.CELL_COLOR.getProperty();
        
        if ( borderColor != null && borderWidth > 0 )
        {
            topLeft.x += borderWidth;
            topLeft.y += borderWidth;
        }
        
        // Canvas is assumed to be square
        int     limit   = cells.length;
        for ( int row = 0 ; row < limit ; ++row )
            for ( int col = 0 ; col < limit ; ++col )
            {
                boolean alive       = cells[row][col];
                int     xco         = topLeft.x + row * side;
                int     yco         = topLeft.y + col * side;
                Color   expColor    = alive ? set : reset;
                int     rgb         = image.getRGB( xco, yco );
                Color   actColor    = new Color( rgb );
                assertEquals( expColor, actColor );
            }
    }
    
    private BufferedImage getCanvasSnapshot()
    {
        JPanel          canvas  = getCanvas();
        int             width   = canvas.getWidth();
        int             height  = canvas.getHeight();
        int             type    = BufferedImage.TYPE_INT_RGB;
        BufferedImage   image   = new BufferedImage( width, height, type );
        canvas.paint( image.getGraphics() );
        
        return image;
    }
    
    private void click( Point point )
    {
        robot.mouseMove( point.x, point.y );
        robot.mousePress( InputEvent.BUTTON1_DOWN_MASK );
        robot.mouseRelease( InputEvent.BUTTON1_DOWN_MASK );
    }
    
    // Returns the point at the center of a cell
    private Point getCellPosition( Board board, Cell cell )
    {
        Point   pos     = getCellOrigin( board );
        int     side    = board.getCellSide();
        pos.x += side * cell.getCol();
        pos.y += side * cell.getRow();
        return pos;
    }

    private Point getCellOrigin( Board board )
    {
        Component   canvas  = getCanvas();
        boolean     useGrid = (Boolean)Properties.USE_GRID.getProperty();
        Color       color   = (Color)Properties.BORDER_COLOR.getProperty();
        Point       nEast   = canvas.getLocationOnScreen();

        // nEast = upper-left corner of Canvas.
        // If necessary, skip of border.
        if ( useGrid && color != null )
        {
            int borderWidth = (Integer)Properties.BORDER_WIDTH.getProperty();
            nEast.x += borderWidth;
            nEast.y += borderWidth;
        }
        
        // move nEast to middle of cell.
        int offset  = board.getCellSide() / 2;
        nEast.x += offset;
        nEast.y += offset;
        
        return nEast;
    }
}
