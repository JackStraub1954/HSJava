package edu.uweo.javaintro.ball_world_app.take02;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * First take at creating my own BallWorld.
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
    public static final int     DEF_WIDTH           = 400;
    /** The default initial height of the window, in pixels. */
    public static final int     DEF_HEIGHT          = 300;
    /** The default background color of the window */
    public static final Color   DEF_BG_COLOR        = new Color( 0xe52b50 );
    /** The default edge color of the window */
    public static final Color   DEF_EDGE_COLOR      = new Color( 0x52be80  );;
    /** The default edge width of the window, in pixels */
    public static final int     DEF_EDGE_WIDTH      = 10;
    /** The default timer interval, in milliseconds */
    public static final long    DEF_TIMER_DELTA     = 10;

    /** Background color of the window */
    private Color   bgColor;    

    /**
     * This timer will cause the Canvas's repaint method
     * to be invoked every two seconds.
     */
    private AnimationTimer      timer;
    
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
    
    /**
     * Encapsulation of the bouncing ball.
     */
    private Ball        ball        = new Ball();
    
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
    	    DEF_TIMER_DELTA
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
            DEF_TIMER_DELTA
        );
    }
    
    /**
     * Constructor. Sets the initial size of the window,
     * and starts the animation timer.
     * The the refresh rate 
     * is specified by the caller;
     * all other properties default.
     * 
     * @param   timerDelta  the timer timeout interval
     */
    public BallField( long timerDelta )
    {
        this(
            DEF_WIDTH,
            DEF_HEIGHT,
            DEF_BG_COLOR,
            DEF_EDGE_COLOR,
            DEF_EDGE_WIDTH,
            timerDelta
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
     * @param   timerDelta  the refresh rate
     */
    public BallField( int width, int height, long timerDelta )
    {
        this(
            width,
            height,
            DEF_BG_COLOR,
            DEF_EDGE_COLOR,
            DEF_EDGE_WIDTH,
            timerDelta
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
     * @param timerDelta    window refresh rate
     */
    public BallField( 
        int     width,
        int     height,
        Color   bgColor,
        Color   edgeColor,
        int     edgeWidth,
        long    timerDelta
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
        
        // Start the timer which will cause this Canvas's repaint method
        // to be called every 10 milliseconds.
        timer = new AnimationTimer( this, TIMER_NAME, timerDelta );
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
        
        /* 
         * Reposition the ball.
         */
        ball.intersect( this );
        ball.update();
        ball.redraw( gtx, currWidth, currHeight );
        
        // boilerplate; facilitate garbage collection of graphics context
        gtx.dispose();
        // end boilerplate
    }

    /**
     * Gets the background color of this window.
     * 
     * @return the the background color of this window.
     */
    public Color getBgColor()
    {
        return bgColor;
    }

    /**
     * Sets the background color of this window.
     * 
     * @param bgColor the new background color for this window
     */
    public void setBgColor(Color bgColor)
    {
        this.bgColor = bgColor;
    }

    /**
     * Gets the timer refresh rate.
     * 
     * @return the timer refresh rate
     */
    public long getTimerDelta()
    {
        long    timerDelta  = timer.getDelay();
        return timerDelta;
    }

    /**
     * Sets the timer refresh rate.
     * 
     * @param timerDelta    the new timer refresh rate
     */
    public void setTimerDelta( long timerDelta )
    {
        timer.stop();
        timer = new AnimationTimer( this, TIMER_NAME, timerDelta );
    }
}
