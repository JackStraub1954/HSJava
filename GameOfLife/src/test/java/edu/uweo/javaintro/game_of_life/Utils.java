package edu.uweo.javaintro.game_of_life;

import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.util.function.Predicate;

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
    
    public static Component findComponent( Predicate<Component> pred )
    {
        Component   comp    = null;
        Frame[] frames  = Frame.getFrames();
        
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
            /// recurse
        }
        
        return comp;
    }
}
