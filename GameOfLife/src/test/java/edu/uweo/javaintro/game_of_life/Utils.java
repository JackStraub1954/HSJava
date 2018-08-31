package edu.uweo.javaintro.game_of_life;

import static org.junit.Assert.fail;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.function.Predicate;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Utils
{
    public static Cell northCell( int row, int col, boolean alive )
    {
        Cell    cell    = new Cell( row - 1, col, alive );
        return cell;
    }
    
    public static Cell nEastCell( int row, int col, boolean alive )
    {
        Cell    cell    = new Cell( row - 1, col + 1, alive );
        return cell;
    }
    
    public static Cell eastCell( int row, int col, boolean alive )
    {
        Cell    cell    = new Cell( row, col + 1, alive );
        return cell;
    }
    
    public static Cell sEastCell( int row, int col, boolean alive )
    {
        Cell    cell    = new Cell( row + 1, col + 1, alive );
        return cell;
    }
    
    public static Cell southCell( int row, int col, boolean alive )
    {
        Cell    cell    = new Cell( row + 1, col, alive );
        return cell;
    }
    
    public static Cell sWestCell( int row, int col, boolean alive )
    {
        Cell    cell    = new Cell( row + 1, col - 1, alive );
        return cell;
    }
    
    public static Cell westCell( int row, int col, boolean alive )
    {
        Cell    cell    = new Cell( row, col - 1, alive );
        return cell;
    }
    
    public static Cell nWestCell( int row, int col, boolean alive )
    {
        Cell    cell    = new Cell( row - 1, col - 1, alive );
        return cell;
    }

    public static void pause()
    {
    	JOptionPane.showMessageDialog( null, "Paused..." );
    }
    
    public static void pause( int millis )
    {
        try
        {
            Thread.sleep( millis );
        }
        catch ( InterruptedException exc )
        {
            // don't care
        }
    }
    
//    private static EventQueue   eventQueue;
//    public static void pause( int millis )
//    {
//        Toolkit toolKit = Toolkit.getDefaultToolkit();
//        try
//        {
//            do
//            {
//                EventQueue.invokeAndWait( () -> eventQueue = toolKit.getSystemEventQueue() );
//            } while ( eventQueue.peekEvent() != null );
//        }
//        catch ( InvocationTargetException exc )
//        {
//            
//        }
//        catch ( InterruptedException exc )
//        {
//            // don't care
//        }
//    }
    
    private static Robot  robot;
    static
    {
        try
        {
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
    public static void clickMouse( Point point )
    {
        robot.mouseMove( point.x, point.y );
        robot.mousePress( InputEvent.BUTTON1_DOWN_MASK );
        robot.mouseRelease( InputEvent.BUTTON1_DOWN_MASK );
    }
    
    public static void dumpFrames()
    {
        Frame[] frames  = Frame.getFrames();
        for ( Frame frame : frames )
        {
            System.out.println( frame.getClass().getSimpleName() );
            if ( frame instanceof JFrame )
                dumpPane( ((JFrame)frame).getContentPane(), 2 );
        }
    }
    
    public static void waitForEventQueue()
    {
        try
        {
            EventQueue.invokeAndWait( () -> {} );
        }
        catch ( Exception exc )
        {
            exc.printStackTrace();
        }
    }
    
    private static void dumpPane( Container pane, int indent )
    {
        String      spaces  = "                              ";
        Component[] children    = pane.getComponents();
        for ( Component child : children )
        {
            StringBuilder bldr    = new StringBuilder();
            bldr.append( spaces.substring( 0, indent ) );
            bldr.append( child.getClass().getSimpleName() ).append( " " );
            if ( child instanceof AbstractButton )
                bldr.append( ((AbstractButton)child).getText() );
            System.out.println( bldr );
            if  ( child instanceof Container )
                dumpPane( (Container)child, indent + 2 );
        }
    }
    
    public static JFrame findBoardFrame()
    {
        JFrame  frame   = null;
        Frame[] frames  = Frame.getFrames();
        
        for ( int inx = 0 ; inx < frames.length && frame == null ; ++inx )
        {
            Frame   temp    = frames[inx];
            if ( temp instanceof JFrame && temp.isVisible() )
                frame = (JFrame)temp;
        }
        
        return frame;
    }
    
    public static Component findComponent( Predicate<Component> pred )
    {
        Component   comp    = null;
        Frame[] frames  = Frame.getFrames();
        
        int len = frames.length;
        for ( int inx = 0 ; inx < len && comp == null ; ++inx )
        {
            Frame   frame   = frames[inx];
            // If Frame is not displayable, don't search it. It has
            // most likely been disposed.
            if ( frame instanceof JFrame && frame.isDisplayable() )
            {
                comp = findComponent( frames[inx], pred );
            }
        }
        
        return comp;
    }
    
    private static Component 
    findComponent( Container container, Predicate<Component> pred )
    {
        Component       comp        = null;
        Component[]     components  = container.getComponents();
        int len = components.length;
        for ( int inx = 0 ; inx < len && comp == null ; ++inx )
        {
            Component   temp    = components[inx];
            if ( pred.test( temp ) )
                comp = temp;
            else if ( temp instanceof Container )
                comp = findComponent( (Container)temp, pred );
            else
                ;
        }
        
        return comp;
    }
}
