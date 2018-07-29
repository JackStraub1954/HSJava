package edu.uweo.javaintro.game_of_life;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
