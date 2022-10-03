package lectures.graphics_01.version03;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

/**
 * This is version 1 of a class that will be used
 * to demonstrate simple graphics.
 * This version is limited to setting the initial size
 * of the window.
 * 
 * @author Jack Straub
 *
 */
public class Canvas extends JPanel
{
	/** The initial width of the window, in pixels. */
    private int		   initWidth   	= 800;
    /** The initial height of the window, in pixels. */
    private int         initHeight  = 500;
    
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
    private Color       bgColor     = new Color( 0xe52b50);
    
    /**
     * Constructor. Sets the initial size of the window. 
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
    }
    
    @Override
    public void paintComponent( Graphics graphics )
    {
        super.paintComponent( graphics );
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
