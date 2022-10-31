package edu.uweo.javaintro.ball_world_app.take03;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * Re-tool earlier code.
 * The update logic (which updates the position and direction of the balls)
 * needs to be separated from the repaint logic.
 * In particular, each update needs to consume
 * a full time step.
 * TODO need to understand this better
 * 
 * Adapted from code by Chua Hock-Chuan (ehchua@ntu.edu.sg).
 *
 * 
 * @author Jack Straub
 *@see  <a href="https://www3.ntu.edu.sg/home/ehchua/programming/java/J8a_GameIntro-BouncingBalls.html">
 *          The World Of Bouncing Balls
 *      </a>
 *      by Chua Hock-Chuan (ehchua@ntu.edu.sg)
 *
 */
@SuppressWarnings("serial")
public class BallField extends JPanel
{
    /** The name of the timer that drives the animation in this window. */
    public static final String  TIMER_NAME          = 
        "BallField Animation Timer";
    
    /** The default initial width of the window, in pixels. */
    public static final int     DEF_WIDTH           = 300;
    /** The default initial height of the window, in pixels. */
    public static final int     DEF_HEIGHT          = 200;
    /** The default background color of the window */
    public static final Color   DEF_BG_COLOR        = new Color( 0xe52b50 );
    /** The default edge color of the window */
    public static final Color   DEF_EDGE_COLOR      = new Color( 0x52be80  );
    // TODO fix the problem with the edge width. Right now the ball slides
    // underneath the border; temporary fix: set edge width to 0.
    /** The default edge width of the window, in pixels */
    public static final int     DEF_EDGE_WIDTH      = 0;
    /** 
     * Default update rate, frames per second.
     * TODO justify this value
     * @see #updateRate
     */
    public static final int     DEF_UPDATE_RATE     = 30;
    /** 
     * Default epsilon time.
     * TODO justify this value
     * @see #epsilonTime
     */
    public static final double  DEF_EPSILON_TIME    = .001;
    
    /**
     * Update rate, in frames-per-second.
     * Determines how quickly the animation timer fires. 
     */
    private int     updateRate      = DEF_UPDATE_RATE;
    
    /**  
     * The time-step is considered fully consumed
     * when the "time left" falls below this value.
     */
    private double  epsilonTime     = DEF_EPSILON_TIME;

    /** Background color of the window */
    private Color   bgColor;    

    /**
     * This timer will cause the Canvas's repaint method
     * to be invoked every <em>updateRate</em>
     * times per second.
     */
    private FrameTimer  timer;
    
    /** 
     * The graphics context; set every time paintComponent is invoked.
     * It's an instance variable to make it convenient for use
     * by helper methods employed by paintComponent.
     */
    private Graphics2D  gtx         = null;
    /** 
     * The current width of the Canvas; 
     * set every time paintComponent is invoked.
     * It's an instance variable to make it convenient for use
     * by helper methods employed by paintComponent.
     */
    private int         currWidth   = 0;
    /** 
     * The current height of the Canvas; 
     * set every time paintComponent is invoked.
     * It's an instance variable to make it convenient for use
     * by helper methods employed by paintComponent.
     */
    private int         currHeight  = 0;
    
    /** The single bouncing ball in this stage of the tutorial. */
    private Ball        ball        = null;
    
    /**
     * Default constructor. Sets the initial size of the window,
     * and starts the animation timer.
     * All window properties default.
     */
    public BallField()
    {
        this(
            DEF_WIDTH,
            DEF_HEIGHT,
            DEF_BG_COLOR,
            DEF_EDGE_COLOR,
            DEF_EDGE_WIDTH,
            DEF_UPDATE_RATE
        );
    }
    
    /**
     * Constructor. Sets the initial size of the window,
     * and starts the animation timer.
     * The size of the window is specified by the caller;
     * all other properties default.
     * 
     * @param   width   the initial width of the window
     * @param   height  the initial height of the window
     */
    public BallField( int width, int height )
    {
        this(
            width,
            height,
            DEF_BG_COLOR,
            DEF_EDGE_COLOR,
            DEF_EDGE_WIDTH,
            DEF_UPDATE_RATE
        );
    }
    
    /**
     * Constructor. Sets the initial size of the window,
     * and starts the animation timer.
     * The the refresh rate 
     * is specified by the caller;
     * all other properties default.
     * 
     * @param   updateRate  the timer timeout interval
     */
    public BallField( int updateRate )
    {
        this(
            DEF_WIDTH,
            DEF_HEIGHT,
            DEF_BG_COLOR,
            DEF_EDGE_COLOR,
            DEF_EDGE_WIDTH,
            updateRate
        );
    }
    
    /**
     * Constructor. Sets the initial size of the window,
     * and starts the animation timer.
     * The size of the window 
     * and refresh rate
     * are specified by the caller;
     * all other properties default.
     * 
     * @param   width       the initial width of the window
     * @param   height      the initial height of the window
     * @param   updateRate  the refresh rate
     */
    public BallField( int width, int height, int updateRate )
    {
        this(
            width,
            height,
            DEF_BG_COLOR,
            DEF_EDGE_COLOR,
            DEF_EDGE_WIDTH,
            updateRate
        );
    }
    
    /**
     * Constructor.
     * Sets all properties of the window,
     * including the initial size,
     * and starts the window timer.
     * All window properties are provided
     * by the caller.
     * 
     * @param width         initial width of the window
     * @param height        initial height of the window
     * @param bgColor       background color of the window
     * @param edgeColor     edge color of the window
     * @param edgeWidth     edge width of the window
     * @param updateRate    window refresh rate
     */
    public BallField( 
        int     width,
        int     height,
        Color   bgColor,
        Color   edgeColor,
        int     edgeWidth,
        int     updateRate
    )
    {
        // The initial width and height of a window is set using 
        // a Dimension object.
        Dimension   size    = new Dimension( width, height );
        
        // IMPORTANT: set the size of the window using
        // setPreferredSize. Remember that the actual size of the
        // window may be different after being displayed.
        this.setPreferredSize( size );
        
        this.bgColor = bgColor;
        
        Border  border  = 
            BorderFactory.createLineBorder( edgeColor, edgeWidth );
        setBorder( border );
        
        this.updateRate = updateRate;
        
        // Generate a random starting position for the ball.
        Random  randy   = new Random();
        int     radius  = (int)(Ball.DEF_RADIUS + .5);
        int     diam    = radius * 2;
        int     xco     = randy.nextInt( width - diam ) + radius;
        int     yco     = randy.nextInt( height - diam ) + radius;
        ball = new Ball();
        ball.setBallXco( xco );
        ball.setBallYco( yco );
        System.out.println( xco + "," + yco );
        // TODO give the ball a random slope
        
        // Start the timer which will cause this Canvas's repaint method
        // to be called every 10 milliseconds.
        timer = new FrameTimer( this, TIMER_NAME, updateRate );
        timer.start();
    }
    
    /**
     * Redraws the canvas every time invoked.
     * This entails filling the window background, 
     * calculating the new position of the bouncing ball
     * and drawing the bouncing ball at the new position.
     * 
     * @param   graphics    the graphics context
     */
    @Override
    public void paintComponent( Graphics graphics )
    {
        // boilerplate...
        // ... execute superclass processing
        // ... make a copy of the graphics context
        // ... establish the current size of the window
        // ... fill the window with the background color
        super.paintComponent( graphics );
        
        gtx = (Graphics2D)graphics.create();
        currWidth = getWidth();
        currHeight = getHeight();
        gtx.setColor( bgColor );
        gtx.fillRect( 0, 0, currWidth, currHeight );
        // end boilerplate
        
        ball.redraw( gtx );
        
        // boilerplate; facilitate garbage collection of graphics context
        gtx.dispose();
        // end boilerplate
    }

    /** 
     * One game time-step. 
     * Update the game objects, with proper collision detection and response.
     * 
     * TODO this logic needs to be better understood
     */
    public void gameUpdate()
    {
       float timeLeft = 1.0f;  // 100% of one time-step left to be exceuted
       
       // loop until a full time-step is consumed
       do
       {
           // Need to find the earliest collision time among all objects
           double   earliestCollisionTime   = timeLeft;
           ball.intersect( this, timeLeft );
           double   testCollisionTime       = ball.getEarliestCollisionTime();
           if ( testCollisionTime < earliestCollisionTime )
               earliestCollisionTime = testCollisionTime;
           
           // update all objects for earliest collision time;
           // at this stage of the tutorial there is only one such object.
           ball.update( earliestCollisionTime );
           
           
           //////////////////////////
           //
           // TESTING ONLY
           // Show the new collision position, but only if the earliest
           // collision time exceeds a small threshold.
           //
           if ( earliestCollisionTime < .05 )
               repaint();
           //
           // END TESTING ONLY
           //
           //////////////////////////
           
           // Consume a fractional proportion of the time-step.
           // TODO need to better understand this;
           // "1000" = 1 second, in milliseconds
           long sleepDelta  = (long)(1000 / updateRate * earliestCollisionTime);
           Utils.pause(sleepDelta);
           
           // update proportion of time-step consumed
           timeLeft -= earliestCollisionTime;
       } while ( timeLeft > epsilonTime );
    }

}
