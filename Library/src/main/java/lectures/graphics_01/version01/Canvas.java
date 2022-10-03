package lectures.graphics_01.version01;

import java.awt.Dimension;

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
    private int		initWidth   	= 800;
    /** The initial height of the window, in pixels. */
    private int     initHeight  	= 500;
    
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
}
