package com.judahstutorials.javaintro.tools;import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileReader;

public class Buffin
{
    private final BufferedReader    bufReader;
    public Buffin()
    {
        InputStreamReader   inReader    = new InputStreamReader( System.in );
        bufReader = new BufferedReader( inReader );
    }
    
    public Buffin( String path )
    {
        BufferedReader  temp = null;
        try
        {
            FileReader  fileReader  = new FileReader( path );
            temp = new BufferedReader( fileReader );
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
        bufReader = temp;
    }
    
    public String nextLine()
    {
        String  input   = null;
        try
        {
            input = bufReader.readLine();
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
        return input;
    }
    
    public void close()
    {
        if ( bufReader != null )
        {
            try
            {
                bufReader.close();
            }
            catch ( IOException exc )
            {
                exc.printStackTrace();
            }
        }
    }
}
