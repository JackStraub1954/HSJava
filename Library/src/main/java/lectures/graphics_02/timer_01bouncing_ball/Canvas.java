package lectures.graphics_02.timer_01bouncing_ball;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * This is version 2.1 of a class that will be used
 * to demonstrate simple graphics.
 * This version demonstrates how to set a timer
 * that will cause the repaint method to be called 
 * repeatedly at some interval.
 * Each time the timer fires it will change the horizontal
 * position of a ball.
 * When the ball reaches one side of the window
 * it will reverse direction.
 * 
 * @author Jack Straub
 *
 */
@SuppressWarnings("serial")
public class Canvas extends JPanel
{
	/** The initial width of the window, in pixels. */
    private int         initWidth   = 400;
    /** The initial height of the window, in pixels. */
    private int         initHeight  = 300;
    
    /** The graphics context; set every time paintComponent is invoked */
    private Graphics2D  gtx         = null;
    /** 
     * The current width of the Canvas; 
     * set every time paintComponent is invoked
     */
    private int         currWidth   = 0;
    /** 
     * The current height of the Canvas; 
     * set every time paintComponent is invoked
     */
    private int         currHeight  = 0;
    /** Background color of the Canvas */
    private Color       bgColor         = new Color( 0xe52b50);
    
    /** The diameter of the bouncing ball */
    private int         ballDiameter    = 50;
    /** The current x coordinate of the bouncing ball */
    private int         ballXco         = 0;
    /** The current y coordinate of the bouncing ball */
    private int         ballYco         = 0;
    /** true if the ball is moving right, false if it's moving left */
    private boolean     ballMovingEast  = true;
    
    /**
     * This timer will cause this Canvas's repaint method
     * to be invoked every n milliseconds
     */
    private AnimationTimer  timer;
    
    /**
     * Constructor. Sets the initial size of the window,
     * and starts the animation timer.
     */
    public Canvas()
    {
    	// The initial width and height of a window is set using 
    	// a Dimension object.
        Dimension   size    = new Dimension( initWidth, initHeight );
        
        // IMPORTANT: set the size of the window using
        // setPreferredSize. Remember that the actual size of the
        // window may be different after being displayed.
        this.setPreferredSize( size );
        
        // Give the ball an initial position at the center of the
        // window, base on the window's preferred size (remember that
        // the window manager doesn't have to honor the preferred size,
        // so the position may not be perfect).
        ballXco = initWidth / 2 - ballDiameter / 2;
        ballYco = initHeight / 2 - ballDiameter / 2;
        
        // Start the timer which will cause this Canvas's repaint method
        // to be called every 10 milliseconds.
        timer = new AnimationTimer( this, "BouncingBall", 10 );
        timer.start();
    }
    
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
        
        // Change the y coordinate of the ball so that it is positioned
        // vertically in the window; this is only necessary if the 
        // operator has resized the window
        ballYco = currHeight / 2 - ballDiameter / 2;
        
        /* 
         * Reposition the ball either further east, or further west
         * as required. If the ball is at the horizontal boundary of
         * the window, change the direction of the ball before repositioning.
         */
        if ( ballMovingEast )
        {
            if ( ballXco + ballDiameter >= currWidth )
                ballMovingEast = false;
        }
        else
        {
            if ( ballXco <= 0 )
                ballMovingEast = true;
        }
        ballXco = ballMovingEast ? ballXco + 2 : ballXco - 2;
        
        // if the new position of the ball extends beyond the limits
        // of the window, tweak the position so that the ball so that
        // it meets the limit exactly
        if ( ballXco < 0 )
            ballXco = 0;
        else if ( ballXco + ballDiameter > currWidth )
            ballXco = currWidth - ballDiameter;
        else
            ;
        gtx.setColor( Color.CYAN );
        gtx.fillOval( ballXco, ballYco, ballDiameter, ballDiameter );
        gtx.setColor( Color.BLACK );
        gtx.drawOval( ballXco, ballYco, ballDiameter, ballDiameter );
        
        // boilerplate; facilitate garbage collection of graphics context
        gtx.dispose();
        // end boilerplate
    }
}
