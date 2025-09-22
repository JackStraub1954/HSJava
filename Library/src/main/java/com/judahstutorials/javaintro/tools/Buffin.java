package com.judahstutorials.javaintro.tools;import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

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
     * Opens the given URL
     * and attaches a BufferedReaer to it.
     * If an I/O error occurs
     * the program is exited.
     * Here's an example that opens a web page:
     * <pre>
     *     URL    url    = Buffin.getURL( "https://kexp.org/" );
     *     Buffin buffin = new Buffin( url );
     * </pre>
     * 
     * @param path  the URL to read from
     * 
     * @see #getURL(String)
     */
    public Buffin( URL path )
    {
        BufferedReader  temp = null;
        try
        {
            InputStream         inStream    = path.openStream();
            InputStreamReader   inReader    = 
                new InputStreamReader( inStream );
            temp = new BufferedReader( inReader );
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
        bufReader = temp;
    }
    
    /**
     * Converts a properly formatted string to a URL.
     * Examples of URLs include web page addresses,
     * for example:
     * <ul>
     * <li>https://kexp.org/</li>
     * <li>https://kcls.org/</li>
     * <li>https://judahstutorials.com/Classes/JavaClass/</li>
     * </ul>
     * <p>
     * This URL refers to a picture on a web page:
     * <pre>
     *     https://stsci-opo.org/STScI-01K0W7F90FHKBCJQ6734GXG00N.png
     * </pre>
     * <p>
     * A URL can refer to resources other than web pages.
     * For more information see:
     * <a href="https://developer.mozilla.org/en-US/docs/Learn_web_development/Howto/Web_mechanics/What_is_a_URL">
     *     What is a URL?
     * </a>
     * on the Mozilla web site.
     * 
     * @param urlStr    a properly formatted URL
     * 
     * @return  the converted URL, or null if the conversion fails
     */
    public static URL getURL( String urlStr )
    {
        URL url = null;
        try
        {
            URI uri = new URI( urlStr );
            url = uri.toURL();
        }
        catch ( URISyntaxException | MalformedURLException exc )
        {
            exc.printStackTrace();
        }
        return url;
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
