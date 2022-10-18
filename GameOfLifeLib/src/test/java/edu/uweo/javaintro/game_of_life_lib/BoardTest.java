package edu.uweo.javaintro.game_of_life_lib;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.function.Predicate;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.uweo.javaintro.game_of_life_lib.Board;
import edu.uweo.javaintro.game_of_life_lib.Cell;
import edu.uweo.javaintro.game_of_life_lib.Properties;

public class BoardTest
{
    private static final int    DEFAULT_PAUSE   = 500;
    private Board   board;
    
    @BeforeEach
    public void setUp()
    {
        Properties.reset();
        board = new Board();
    }
    
    @AfterEach
    public void tearDown()
    {
        board.close();
        Utils.pause( 500 );
//        Utils.waitForEventQueue();
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
            Utils.clickMouse( point );
            board.refresh();
        }
        
        Utils.pause( DEFAULT_PAUSE );
//        Utils.waitForEventQueue();
        boolean[][] cells   = board.getCells();
        
        // Is number of rows correct?
        assertEquals( gridSide, cells.length );
        for ( int inx = 0 ; inx < limit ; ++inx )
            for ( int jnx = 0 ; jnx < limit ; ++jnx )
            {
                // is length of row correct?
                assertEquals( gridSide, cells[inx].length );
                if ( inx == jnx )
                    assertTrue( cells[inx][jnx], "" + inx + ", " + jnx );
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
    public void testAddRemoveActionListener()
    {
        board.start();
        
        int     row     = 10;
        int     col     = 20;
        Cell    cell    = new Cell( row, col );
        Point   cellPos = getCellPosition( board, cell );
        
        ClickMonitor    clicker = new ClickMonitor();
        board.addActionListener( clicker );
        Utils.clickMouse( cellPos );
        Utils.pause( DEFAULT_PAUSE );
//        Utils.waitForEventQueue();
        assertTrue( clicker.clicked );
        
        clicker.clicked = false;
        board.removeActionListener( clicker );
        Utils.clickMouse( cellPos );
        assertFalse( clicker.clicked );
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
        board.start();
        
        int         row     = 5;
        int         col     = 5;
        
        boolean[][] cells   = board.getCells();
        assertFalse( cells[row][col] );
        
        Cell    cell    = new Cell( row, col, true );
        board.setCell( cell );
        cells   = board.getCells();
        assertTrue( cells[row][col] );
        
        board.refresh();
        Utils.pause( DEFAULT_PAUSE );
//        Utils.waitForEventQueue();
        validateImage( cells );
    }
    
    @Test
    public void noBorderTest1()
    {
        Properties.USE_BORDER.setProperty( false );
        System.out.println( Properties.USE_BORDER.getProperty() );

        board = new Board();
        
        board.addActionListener( e ->
            { Cell cell = (Cell)e.getSource();
              cell.setAlive( true );
              board.setCell( cell );
            }
        );
        board.start();
        
        Point   point   = getCellPosition( board, new Cell( 0, 0 ) );
        Utils.clickMouse( point );
        Utils.pause( DEFAULT_PAUSE );
//        Utils.waitForEventQueue();
        boolean[][] state   = board.getCells();
        assertTrue( state[0][0] );
    }
    
    @Test
    public void noBorderTest3()
    {
        Properties.BORDER_WIDTH.setProperty( 0 );

        board = new Board();
        
        board.addActionListener( e ->
            { Cell cell = (Cell)e.getSource();
              cell.setAlive( true );
              board.setCell( cell );
            }
        );
        board.start();
        
        Point   point   = getCellPosition( board, new Cell( 0, 0 ) );
        Utils.clickMouse( point );
        Utils.pause( DEFAULT_PAUSE );
//        Utils.waitForEventQueue();
        boolean[][] state   = board.getCells();
        assertTrue( state[0][0] );
    }
    
    @Test
    public void noBorderTest2()
    {
        Properties.BORDER_COLOR.setProperty( null );

        board = new Board();
        
        board.addActionListener( e ->
            { Cell cell = (Cell)e.getSource();
              cell.setAlive( true );
              board.setCell( cell );
            }
        );
        board.start();
        Utils.waitForEventQueue();
        
//        Utils.pause( DEFAULT_PAUSE );
        Point   point   = getCellPosition( board, new Cell( 0, 0 ) );
        Utils.clickMouse( point );
        Utils.pause( DEFAULT_PAUSE );
        board.refresh();
//        Utils.waitForEventQueue();
        Utils.pause( DEFAULT_PAUSE );
        boolean[][] state   = board.getCells();
        assertTrue( state[0][0] );
    }
    
    @Test
    public void clearTest()
    {
        board.start();
        for ( int inx = 0 ; inx < 10 ; ++inx )
            board.setCell( new Cell( inx, inx, true ) );
        
        board.refresh();
        Utils.pause( DEFAULT_PAUSE );
//        Utils.waitForEventQueue();
        validateImage();
        
        board.clear();
        board.refresh();
        Utils.pause( DEFAULT_PAUSE );
//        Utils.waitForEventQueue();
        
        boolean[][] cells   = board.getCells();
        for ( int inx = 0 ; inx < cells.length ; ++inx )
            for ( int jnx = 0 ; jnx < cells[inx].length ; ++jnx )
                assertFalse( cells[inx][jnx] );
        validateImage();
    }
    
    @Test
    public void testSetCellGoWrong1()
    {
		Class<IndexOutOfBoundsException>	clazz	= IndexOutOfBoundsException.class;
        int inx = board.getCells().length;
        assertThrows( clazz, () -> board.setCell( new Cell( inx, 0 ) ) );
    }
    
    @Test
    public void testSetCellGoWrong2()
    {
		Class<IndexOutOfBoundsException>	clazz	= IndexOutOfBoundsException.class;
        int inx = board.getCells().length;
        assertThrows( clazz, () -> board.setCell( new Cell( 0, inx) ) );
    }
    
    @Test
    public void testSetCellGoWrong3()
    {
		Class<IndexOutOfBoundsException>	clazz	= IndexOutOfBoundsException.class;
        assertThrows( clazz, () -> board.setCell( new Cell( -1, 0 ) ) );
    }
    
    @Test
    public void testSetCellGoWrong4()
    {
		Class<IndexOutOfBoundsException>	clazz	= IndexOutOfBoundsException.class;
        assertThrows( clazz, () -> board.setCell( new Cell( 0, -1 ) ) );
    }
    
    @Test
    public void testSetCellsBooleanArrayGoWrong1()
    {
        int         len     = (Integer)Properties.GRID_SIDE.getProperty();
        boolean[][] arr     = new boolean[len + 1][len];
        board.setCells( arr );
    }
    
    @Test
    public void testSetCellsBooleanArrayGoWrong2()
    {
		Class<IllegalArgumentException>	clazz	= IllegalArgumentException.class;
        int         len     = (Integer)Properties.GRID_SIDE.getProperty();
        boolean[][] arr     = new boolean[len][len + 1];
        assertThrows( clazz, () -> board.setCells( arr ) );
    }

    @Test
    public void testSetCellsCellArray()
    {
        int         start       = 10;
        int         limit       = 10;
        boolean[][] cellState   = board.getCells();
        Cell[]      cells   = new Cell[limit];
        for ( int inx = 0 ; inx < cells.length ; ++inx )
        {
            int cellInx = inx + start;
            assertFalse( cellState[cellInx][cellInx] );
            cells[inx] = new Cell( start + inx, start + inx, true );
        }
        
        board.start();
        board.setCells( cells );
        board.refresh();
        cellState = board.getCells();
        for ( int inx = 0 ; inx < cells.length ; ++inx )
        {
            int cellInx = inx + start;
            assertTrue( cellState[cellInx][cellInx] );
        }
        
        Utils.pause( DEFAULT_PAUSE );
//        Utils.waitForEventQueue();
        this.validateImage( cellState );
    }

    @Test
    public void testRefresh()
    {
        board.start();
        
        for ( int inx = 0 ; inx < 10 ; ++inx )
            board.setCell( new Cell( inx, inx, true ) );
        board.refresh();
        Utils.pause( DEFAULT_PAUSE );
//        Utils.waitForEventQueue();
        validateImage();
        
        for ( int inx = 5 ; inx < 15 ; ++inx )
            board.setCell( new Cell( inx + 5, inx, true ) );
        board.refresh();
        Utils.pause( DEFAULT_PAUSE );
        validateImage();
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
                    assertTrue( cells[inx][jnx], "" + inx + ", " + jnx );
                else
                    assertFalse( cells[inx][jnx] );
            }
    }
    
    @Test
    public void testBackgroundNull()
    {
        Properties.BACKGROUND_COLOR.setProperty( null );
        board.start();
        Utils.pause( DEFAULT_PAUSE );
//        Utils.waitForEventQueue();
    }
    
    @Test
    public void testResize()
    {
        board.start();
        
        JFrame  frame   = Utils.findBoardFrame();
        assertNotNull( frame );
        
        int origWidth   = frame.getWidth();
        int origHeight  = frame.getHeight();
        int newWidth    = origWidth + 100;
        int newHeight   = origHeight + 100;
        System.out.printf( "%d,  %d, %d, %d %n", origWidth, origHeight,
                newWidth, newHeight);
        frame.setSize( new Dimension( newWidth, origHeight ) );
        board.refresh();
        Utils.pause( DEFAULT_PAUSE );
        frame.setSize( new Dimension( origWidth, newHeight ) );
        board.refresh();
        Utils.pause( DEFAULT_PAUSE );
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
    
    private void validateImage()
    {
        validateImage( board.getCells() );
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
        boolean     useGrid = (Boolean)Properties.USE_BORDER.getProperty();
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
    
    private class ClickMonitor implements ActionListener
    {
        public boolean clicked  = false;
        
        public void actionPerformed( ActionEvent evt )
        {
            clicked = true;
        }
    }
}
