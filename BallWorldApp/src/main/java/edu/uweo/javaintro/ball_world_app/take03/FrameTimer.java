package edu.uweo.javaintro.ball_world_app.take03;

import javax.swing.JComponent;

/**
 * This is a simple example of running a timer 
 * <span style="text-decoration:line-through;">
 *      that fires every <em>n</em> milliseconds.
 * </span>
 * 
 * Change timer strategy to implement "update rate (fps)."
 * The update rate, in frames per second
 * is specified by the caller,
 * and used to determine the delta between timer firings.
 * Steps are taken to ensure that
 * <em>(TODO what is the best way to explain this?)</em>
 * a full frame is consumed 
 * "even after possibly multiple collisions."
 * (I suspect this will make more sense
 * after reaching the part of the tutorial
 * that introduces multiple-collision processing.)
 * 
 * <span style="text-decoration: line-through;">
 * Each time it fires it calls the <em>repaint</em> method
 * of a given JComponent (usually a JPanel).
 * </span>
 * 
 * The component has been changed explicitly to a BallField,
 * so that BallField.gameUpdate() can be invoked.
 * Should probably change the name of this class
 * to something more descriptive.
 * 
 * @author Jack Straub
 */
public class FrameTimer implements Runnable
{
    /** 
     * The JComponent who's repaint method is to be invoked.
     * Provided by the user in the constructor.
     */
    private final BallField    ballField;
    /**
     * The number of times, per second, that the timer should fire.
     */
    private final int           updateRate;
    /**
     * Thread to execute the timer. Created <em>but not started</em>
     * in the constructor.
     */
    private final Thread		timerThread;
    
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
     * @param ballField the component who's repaint method is
     *                  to be repeatedly invoked
     * @param name      name for this timer
     * @param delay     delay between firings, in milliseconds
     * 
     * @see #start()
     */
    public FrameTimer( BallField ballField, String name, int updateRate )
    {
        this.ballField  = ballField;
        this.updateRate = updateRate;
        timerThread = new Thread( this, name );
    }

    /**
     * Method that is invoked when the timer is started.
     */
    @Override
    public void run()
    {
        long    frameStartTime  = 0;
        long    timeConsumed    = 0;
        long    timeRemaining   = 0;
        
        while ( execute )
        {
            frameStartTime = System.currentTimeMillis();
            ballField.gameUpdate();
            ballField.repaint();
            
            timeConsumed = System.currentTimeMillis() - frameStartTime;
            timeRemaining = 1000 / updateRate - timeConsumed;
            // Set a minimum bound on time delta
            // TODO encapsulate "5" as a named constant
            if ( timeRemaining < 5 )
                timeRemaining = 5;
            Utils.pause( timeRemaining );
        }
    }
    
    /**
     * Begins execution of this timer.
     * The first execution of the timer will be scheduled
     * to run immediately.
     * Note that a timer CANNOT be started more than once.
     */
    public void start()
    {
    	// If the thread state is NEW the thread has not been started.
    	// If it's anything else, the thread has been started, and may
    	// not be started again.
    	Thread.State	state	= timerThread.getState();
        if ( state == Thread.State.NEW )
        	timerThread.start();
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
     * Gets the refresh rate of this timer.
     * @return  the refresh rate of this timer
     */
    public long getUpdateRate()
    {
        return updateRate;
    }
}
