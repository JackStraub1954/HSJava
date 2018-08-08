package edu.uweo.javaintro.game_of_life;

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
}
