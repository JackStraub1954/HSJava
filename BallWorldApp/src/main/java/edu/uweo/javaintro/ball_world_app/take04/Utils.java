/**
 * 
 */
package edu.uweo.javaintro.ball_world_app.take04;

/**
 * This class contains miscellaneous utilities
 * that are convenient for use throughout the application.
 * 
 * @author Jack Straub
 */
public class Utils
{
    /**
     * Dummy default constructor to prevent
     * instantiation of this class.
     */
    private Utils()
    {
    }
    
    /**
     * Convenience method to put this thread to sleep for the
     * given number of milliseconds.
     * The heart of the operation is "Thread.sleep(),"
     * which is actually pretty simple.
     * The minor complication is that the sleep method
     * might throw an InterruptedException which then
     * has to be caught.
     * If an InterruptedException is thrown
     * it can be safely ignored.
     * 
     * This relatively simple logic is encapsulated here
     * just to make the source logic easier to read.
     * 
     * @param   delay   the given number of milliseconds
     */
    public static void pause( long delay )
    {
        try
        {
            Thread.sleep( delay );
        }
        catch ( InterruptedException exc )
        {
            // Don't really care if an InterruptedExeption
            // is thrown, so do nothing.
        }
    }
}
