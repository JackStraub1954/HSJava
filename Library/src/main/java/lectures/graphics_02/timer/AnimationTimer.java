package lectures.graphics_02.timer;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComponent;

/**
 * This is a simple example of running a timer that fires every
 * <em>n</em> milliseconds.
 * Each time it fires it calls the <em>repaint</em> method
 * of a given JComponent (usually a JPanel).
 * 
 * @author Jack Straub
 */
public class AnimationTimer extends TimerTask
{
    /** 
     * The JComponent who's repaint method is to be invoked.
     * Provided by the user in the constructor.
     */
    private final JComponent    component;
    /**
     * The length of time to wait between firings.
     * Provided by the user in the constructor.
     */
    private final long          delay;
    /** The Java utility that implements the timer. */
    private final Timer         timer;
    
    /** 
     * Controls operation of the timer.
     * While true, the timer will continue to fire as scheduled.
     * To set to false, call the stop() method.
     * @see #stop()
     */
    private boolean             execute     = true;
    
    /**
     * Constructor. Instantiates the timer that will control execution,
     * <em>but does not start the timer.</em>
     * To start the timer, call the <em>start</em> method.
     * Also establishes a name for this timer,
     * which may be useful for debugging purposes.
     * 
     * @param component the component who's repaint method is
     *                  to be repeatedly invoked
     * @param name      name for this timer
     * @param delay     delay between firings, in milliseconds
     * 
     * @see #start()
     */
    public AnimationTimer( JComponent component, String name, long delay )
    {
        this.component = component;
        this.delay = delay;
        timer = new Timer( name );
    }

    /**
     * Method that is invoked when the timer is startede.
     */
    @Override
    public void run()
    {
        while ( execute )
        {
            component.repaint();
            pause();
        }
    }
    
    /**
     * Begins execution of this timer.
     * The first execution of the timer will be scheduled
     * to run immediately.
     */
    public void start()
    {
        timer.schedule( this, 0 );
    }
    
    /**
     * Halts execution of this timer.
     * After the timer is halted, it cannot be restarted.
     */
    public void stop()
    {
        execute = false;
    }
    
    /**
     * Convenience method to put this thread to sleep for the
     * given number of milliseconds.
     * The heart of the operation is "Thread.sleep()."
     * The minor complication is that the sleep method
     * might throw an InterruptedException which then
     * has to be caught.
     * If an InterruptedException is thrown
     * it can be safely ignored.
     */
    private void pause()
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
