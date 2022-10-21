package edu.uweo.javaintro.ball_world_app.take01;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Arc2D;

import javax.swing.JPanel;

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
    
    /**
     * Encapsulation of the bouncing ball.
     */
    private Ball        ball        = new Ball();
    
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
        
        /* 
         * Reposition the ball.  If the ball is at a boundary of
         */
        ball.redraw( gtx, currWidth, currHeight );
        
        // boilerplate; facilitate garbage collection of graphics context
        gtx.dispose();
        // end boilerplate
    }
}
