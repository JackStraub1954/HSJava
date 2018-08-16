package edu.uweo.javaintro.game_of_life;

import static org.junit.Assert.fail;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.util.function.Predicate;

import org.junit.Test;

public class BoardTest
{
    @Test
    public void testBoard()
    {
        Board   board   = new Board();
        board.addActionListener( ( ActionEvent e ) ->
            { Cell cell = (Cell)e.getSource();
              cell.setAlive( true );
              board.setCell( cell );
            }
        );
        board.addActionListener( e -> System.out.println( e.getSource() ) );
        startBoard( board );
        for ( int inx = 0 ; inx < 10 ; ++inx )
        {
            Cell    cell    = new Cell( inx, inx, true );
            Point   point   = getCellPosition( board, cell );
            click( point );
        }
        
        Utils.pause( 250 );
        board.refresh();
        Utils.pause();
    }

    @Test
    public void testBoardInt()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testRun()
    {
        fail("Not yet implemented");
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
    public void testGetCells()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testGetSide()
    {
        fail("Not yet implemented");
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
    public void testClose()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testClear()
    {
        fail("Not yet implemented");
    }
    
    private void startBoard( Board board )
    {
        board.start();
        Utils.pause( 500 );
    }
    
    private void click( Point point )
    {
        try
        {
            Robot   robot   = new Robot();
            robot.mouseMove( point.x, point.y );
            robot.mousePress( InputEvent.BUTTON1_DOWN_MASK, 125 );
            robot.mouseRelease( InputEvent.BUTTON1_DOWN_MASK );
        }
        catch ( AWTException exc )
        {
            exc.printStackTrace();
        }
    }
    
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
        Predicate<Component>    pred    = 
            c -> Board.CANVAS_NAME.equals( c.getName() );
        Component               canvas  = Utils.findComponent( pred );
        Point                   nEast   = canvas.getLocationOnScreen();
        boolean                 useGrid = 
            (Boolean)Properties.USE_GRID.getProperty();
        Color                   color   = 
            (Color)Properties.BORDER_COLOR.getProperty();
        if ( useGrid && color != null )
        {
            int borderWidth = (Integer)Properties.BORDER_WIDTH.getProperty();
            nEast.x += borderWidth;
            nEast.y += borderWidth;
        }
        
        int offset  = board.getCellSide() / 2;
        nEast.x += offset;
        nEast.y += offset;
        
        return nEast;
    }
}
