package lectures.graphics_02.timer_02bouncing_ball;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Arc2D;

import javax.swing.JPanel;

import lectures.graphics_02.timer_00simple.AnimationTimer;

/**
 * This is version 2.2 of a class that will be used
 * to demonstrate simple graphics.
 * This version is essentially the same as version 1,
 * but it does a better job of encapsulating parameters,
 * such as the ball fill and draw colors, 
 * width of the edge of the ball, speed of the timer 
 * and incremental ball repositioning.
 * 
 * @author Jack Straub
 *
 */
@SuppressWarnings("serial")
public class Canvas extends JPanel
{
    /** The initial width of the window, in pixels. */
    private final int   initWidth       = 400;
    /** The initial height of the window, in pixels. */
    private final int   initHeight      = 300;
    /** Background color of the Canvas */
    private final Color bgColor         = new Color( 0xe52b50);    
    /**
     * This timer will cause the Canvas's repaint method
     * to be invoked every two seconds.
     */
    private final AnimationTimer    timer;
    /** Interval between timer firing, in milliseconds */
    private final int               timerDelta  = 10;
    
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
    
    /** The fill color of the bouncing ball */
    private Color       ballFillColor   = Color.BLUE;
    /** The edge color of the bouncing ball */
    private Color       ballEdgeColor   = Color.BLACK;
    /** Stroke (mainly width) if the edge of the bouncing ball */
    private Stroke      ballEdgeStroke  = new BasicStroke( 2.5f );
    /** Diameter of the bouncing ball (width and height) */
    private double      ballDiameter    = 50;
    /** x coordinate of the bouncing ball */
    private double      ballXco         = 0;
    /** y coordinate of the bouncing ball */
    private double      ballYco         = 0;
    /** x-coordinate increment when the ball moves */
    private double      ballXDelta      = 2;
    /** true if the ball is moving east, false if it's moving west */
    private boolean     ballMovingEast  = true;
    
    /** 
     * Encapsulates the geometry of the bouncing ball.
     * The x and y coordinates will change each time
     * the ball is redrawn. 
     * The width and height are the same, indicating
     * that the arc will be circular.
     * The starting angle is 0 radians, and the extent
     * is 2 * PI radians indicating the arc will
     * be a full circle.
     * The type of the closure is OPEN; note that closure
     * is only important if the extent of the arc is 
     * less than a full circle.
     */
    private Arc2D.Double    bouncingBall    = 
        new Arc2D.Double( 
            0,              // x-coordinate
            0,              // y-coordinate
            ballDiameter,   // width
            ballDiameter,   // height
            0,              // start angle
            360,            // extent
            Arc2D.OPEN      // closure
        );
    
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
        timer = new AnimationTimer( this, "CanvasRepainter", timerDelta );
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
        ballXco += ballMovingEast ? ballXDelta : -ballXDelta;
        
        // if the new position of the ball extends beyond the limits
        // of the window, tweak the position so that the ball so that
        // it meets the limit exactly
        if ( ballXco < 0 )
            ballXco = 0;
        else if ( ballXco + ballDiameter > currWidth )
            ballXco = currWidth - ballDiameter;
        else
            ;
        bouncingBall.x = ballXco;
        bouncingBall.y = ballYco;
        gtx.setColor( ballFillColor );
        gtx.fill( bouncingBall );
        gtx.setColor( ballEdgeColor );
        gtx.setStroke( ballEdgeStroke );
        gtx.draw( bouncingBall );
        
        // boilerplate; facilitate garbage collection of graphics context
        gtx.dispose();
        // end boilerplate
    }
}
