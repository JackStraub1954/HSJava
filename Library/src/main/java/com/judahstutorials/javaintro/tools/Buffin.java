package com.judahstutorials.javaintro.tools;import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileReader;

/**
 * This class is based on a Sun OS utility that is no longer available.
 * When the textbook refers to the original facility,
 * this facility can be substituted.
 * This facility is not quite equivalent to the original,
 * but for our purposes it will do.
 */
public class Buffin
{
    /**
     * Input stream created below,
     * and returned to the user.
     */
    private final BufferedReader    bufReader;
    
    /**
     * Default constructor;
     * attaches a BufferedReader to stdin.
     */
    public Buffin()
    {
        InputStreamReader   inReader    = new InputStreamReader( System.in );
        bufReader = new BufferedReader( inReader );
    }
    
    /**
     * Opens the file at the given path
     * and attaches a BufferedReaer to it.
     * If an I/O error occurs
     * the program is exited.
     * 
     * @param path  the given path
     */
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
    
    /**
     * Gets the next line from the input stream
     * attached to the encapsulated BufferedReader.
     * In keeping with accepted convention,
     * if the input stream has been exhausted
     * null is returned;
     * attempts to access the stream
     * after EOF has been detected
     * will result in an I/O error,
     * and the program will be exited.
     * 
     * @return  the next line from the input stream
     *          or null if the end of the stream has been reached.
     */
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
    
    /**
     * Closes the encapsulated input stream.
     */
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
