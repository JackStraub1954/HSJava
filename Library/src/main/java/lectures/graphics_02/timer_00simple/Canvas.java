package lectures.graphics_02.timer_00simple;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.time.LocalTime;

import javax.swing.JPanel;

/**
 * This is version 2 of a class that will be used
 * to demonstrate simple graphics.
 * This version demonstrates how to set a timer
 * that will cause the repaint method to be called 
 * repeatedly at some interval.
 * The timer is instantiated and started in the constructor.
 * 
 * @author Jack Straub
 *
 */
public class Canvas extends JPanel
{
	/** The initial width of the window, in pixels. */
    private final int   initWidth   	= 800;
    /** The initial height of the window, in pixels. */
    private final int   initHeight      = 500;
    /** Background color of the Canvas */
    private final Color bgColor         = new Color( 0xe52b50);    
    /**
     * This timer will cause the Canvas's repaint method
     * to be invoked every two seconds.
     */
    private final       AnimationTimer  timer;
    
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
     * Constructor. Sets the initial size of the window,
     * and starts the animation timer.
     */
    public Canvas()
    {
    	// The initial width and height of a window is set using 
    	// a Dimension object.
        Dimension   size    = new Dimension( initWidth, initHeight );
        
        // Start the timer which will cause this Canvas's repaint method
        // to be called every two seconds (i.e. 2000 milliseconds).
        timer = new AnimationTimer( this, "CanvasRepainter", 2000 );
        timer.start();
        
        // IMPORTANT: set the size of the window using
        // setPreferredSize. Remember that the actual size of the
        // window may be different after being displayed.
        this.setPreferredSize( size );
    }
    
    @Override
    public void paintComponent( Graphics graphics )
    {
        super.paintComponent( graphics );
        
        // This line of code merely demonstrates that
        // the associated timer is firing every two seconds
        // as expected. Presumably you will replace
        // this line of code with the logic that will 
        // control the next frame of the animation.
        System.out.println( "paintComponent: " + LocalTime.now() );
        
        gtx = (Graphics2D)graphics.create();
        currWidth = getWidth();
        currHeight = getHeight();
        gtx.setColor( bgColor );
        gtx.fillRect( 0, 0, currWidth, currHeight );
        
    /* 
     * Make a rectangle 3/4 the current size of the Canvas;
     * center it in the window
     */
    Color       rFill   = Color.YELLOW;
    Color       rEdge   = Color.BLACK;
    int         rWidth  = 3 * currWidth / 4;
    int         rHeight = 3 * currHeight / 4;
    int         rXco    = (currWidth - rWidth) / 2;
    int         rYco    = (currHeight - rHeight) / 2;
    Rectangle   rect    = new Rectangle( rXco, rYco, rWidth, rHeight );
    gtx.setColor( rFill );
    gtx.fill( rect );
    gtx.setColor( rEdge );
    gtx.draw( rect );
        
        gtx.dispose();
    }
}
