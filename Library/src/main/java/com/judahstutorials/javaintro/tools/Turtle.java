package com.judahstutorials.javaintro.tools;

// <pre>
/*  Copy this file in its entirety to a file named Turtle.java.
 *  Compile the Turtlet class and then compile this class, before trying to
 *  compile any program that uses Turtles.
 *  This class draws to an Image object and lets the frame's paint method
 *  show the Image whenever the frame repaints itself. It is for
 *  Turtle commands that are given in or from a main application. */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import javax.swing.SwingUtilities;

/**
 * Encapsulation of commands that drive
 * the collection of geometric figures
 * in an application window.
 */
public class Turtle extends Turtlet
{
	/**
	 * JFrame comprising the application window.
	 */
	private static TurtleWorld theWorld = null;


	/** 
	 * Write words without changing the Turtle's state.
	 * 
	 * @param  message the words to write
	 */
	public void say (String message)
	{	
	    super.say (message);
		theWorld.repaint();
	}	//======================


	/** 
	 * Make a circle of the given radius, Turtle at center. 
	 * The state of the Turtle is not changed.
	 * 
	 * @param radius   the radius of the circle to draw
	 */
	public void swingAround (double radius)
	{	
	    super.swingAround (radius);
		theWorld.repaint();
	}	//======================

	/** 
	 * Draws a circular arc inside a square of side 2 * radius.
	 * The beginning of the arc is the Turtle's current heading; the end
	 * of the arc is the current heading + degrees. The Turtle is rotated
	 * by degrees.
	 * 
	 * @param  radius  the radius of the circular arc
	 * @param  degrees the degrees to rotate Turtle
	 */
	public void paintCircularArc( double radius, double degrees )
	{
		super.paintCircularArc( radius, degrees );
		theWorld.repaint();
	} //========================

	/** 
	 * Draws an elliptical arc inside a rectangle.
	 * The beginning of the arc is the Turtle's current heading; the end
	 * of the arc is the current heading + degrees. The Turtle is rotated
	 * by degrees.
	 *
	 * @param	width	the width of the rectangle
	 * @param	height	the height of the rectangle
	 * @param	degrees to rotate Turtle through
	 */
	public void paintArc( double width, double height, double degrees )
    {
        super.paintArc( width, height, degrees );
        theWorld.repaint();
    }

	/** 
	 * Fills an elliptical arc inside a rectangle.
	 * The beginning of the arc is the Turtle's current heading; the end
	 * of the arc is the current heading + degrees. The Turtle is rotated
	 * by degrees.
	 *
	 * @param	width	the width of the rectangle
	 * @param	height	the height of the rectangle
	 * @param	degrees	degrees to rotate Turtlet through
	 */
	public void fillArc( double width, double height, double degrees )
    {
        super.fillArc( width, height, degrees );
        theWorld.repaint();
    }

	/** 
	 * Fill a circle of the given radius, Turtle at center.
	 * The state of the Turtle is unchanged.
	 * 
	 * @param  radius  the given radius
	 */
	public void fillCircle (double radius)
	{	
	    super.fillCircle (radius);
		theWorld.repaint();
	}	//======================


	/** 
	 * Rotate by the given number of degrees; 
	 * move by the given number steps.
	 * Positive degrees turn left,
	 * negative degrees turn right.
	 * Positive steps move forward.
	 * negative steps move backward.
	 * 
	 * @param  degrees the given number of degrees
	 * @param  steps   the given number of steps
	 */
	public Turtlet move (double degrees, double steps)
	{	
	    return super.move (degrees, steps);
	}	//======================


    /** 
     * Rotate by the given number of degrees; 
     * move by the given number steps,
     * draw a line over the traversed pixels.
     * Positive degrees turn left,
     * negative degrees turn right.
     * Positive steps move forward.
     * negative steps move backward.
     * 
     * @param  degrees the given number of degrees
     * @param  steps   the given number of steps
     */
	public Turtlet paint (double degrees, double steps)
	{	
	    super.paint (degrees, steps);
		theWorld.repaint();
		return this;
	}	//======================


	/** 
	 * Fill a rectangle of the given width and height, Turtle at center. 
	 * The state of the Turtle is unchanged.
	 * 
	 * @param  width   the given width
	 * @param  height  the given height
	 */

	public void fillBox (double width, double height)
	{	
	    super.fillBox (width, height);
		theWorld.repaint();
	}	//======================

	/** 
	 * Create an image from a file and draw it at the Turtle's
	 * current position. The postion and orientation of the Turtle
	 * remain unchanged.
	 *
	 * @param	path file from which to obtain the image data
	 * 
	 * @return	reference to stored image. This only needs to be saved
	 *          if at some future time the image is to be deleted from
	 *          the canvas.
	 *
	 * @see #removePaintMe( IPaintMe )
	 */
    public IPaintMe drawImage( String path )
    {
        IPaintMe	paintMe	= drawImage( path, theWorld );
		theWorld.repaint();
		return paintMe;
    }

	/** Create an image from a file and draw it at the Turtle's
	 *  current position. The position and orientation of the Turtle
	 *  remain unchanged.
	 *  <p>
	 *  Example:
	 *  <pre>
public static void main( String[] args )
{
    URL	path = Turtlet.getURL( "http://faculty.washington.edu/jstraub/flowers.jpg" );
    if ( path != null )
    {
        Turtle	pokey	= new Turtle();
        pokey.drawImage( path );
    }
}</pre>
	 *
	 *  @param	path    file from which to obtain the image data
	 *  
	 *  @return	reference to stored image. This only needs to be saved
	 *          if at some future time the image is to be deleted from
	 *          the canvas.
	 *
	 *  @see #removePaintMe( IPaintMe )
	 *  @see Turtlet#getURL( String )
	 */
    public IPaintMe drawImage( URL path )
    {
        IPaintMe	paintMe	= drawImage( path, theWorld );
		theWorld.repaint();
		return paintMe;
    }

	/** 
	 * Returns an appropriate image observer the the Turtles' canvas.
	 * An ImageObserver is used for performing Image creation and
	 * manipulation.
	 *
	 * @return Image observer
	 *
	 * @see #getGraphics
	 * @see java.awt.Image
	 */
	public static ImageObserver getImageObserver()
	{
		return theWorld;
	}

	/** 
	 * Create a turtle at the center of the default JFrame.
	 */
	public Turtle()
	{	
	    this (false, 760, 600);  // special case of the constructor below
	}	//======================


	/** 
	 * If makeNewWorld is true, make an additional JFrame of the given
	 * width and height.  Create a turtle at the center of the JFrame.
	 * 
	 * @param  makeNewWorld    true to make an additional JFrame
	 * @param  width           the width of the additional JFrame
	 * @param  height          the height of the additional JFrame
	 */
	public Turtle(boolean makeNewWorld, int width, int height)
	{	super (makePage (makeNewWorld, width, height),
			           width / 2, height / 2);
	}	//======================


	/** 
	 * If necessary, make a new JFrame to be populated with Turtles.
	 * Only done as a separate method because super() has to be
	 * the first statement in the any constructor.
	 * 
	 * @param   makeNewWorld    true to create a JFrame
	 * @param  w   the width of the new window
	 * @param  h   the height of the new window
	 * 
	 * @return the graphics context from the new window
	 */
	private static Graphics makePage (boolean makeNewWorld, int w, int h)
	{	if (theWorld == null || makeNewWorld)
	    {
    	    try
    	    {
    	        SwingUtilities.invokeAndWait(() ->
    			    theWorld = new TurtleWorld (w, h)
			    );
    	    }
    	    catch ( InvocationTargetException | InterruptedException exc )
    	    {
    	        exc.printStackTrace();
    	        System.exit( 1 );
    	    }
	    }
		return theWorld.getPage();
	}	//======================


    /** 
     * A TurtleWorld is a JFrame on which an Image object is drawn each time
     *  the JFrame is repainted.  Each Turtle draws on that Image object.
     */
    private static class TurtleWorld extends javax.swing.JFrame
    {
        /**
         * Serial version UID.
         */
        private static final long serialVersionUID = 0x10L;
    
    	/**
    	 * Margins.
    	 */
    	private static final int EDGE = 3, TOP = 30;  // around the JFrame
    	/**
    	 * The Image object that Turtles draw on.
    	 */
    	private Image itsPicture;
    	/**
    	 * The graphics context for drawing 
    	 * on the encapsulated image object.
    	 */
    	private Graphics itsPage;
    
    	/**
    	 *  Constructor.
    	 *  Creates and configures the encapsulated JFrame.
    	 *  
    	 * @param width    the width of the window
    	 * @param height   the height of the window
    	 */
    	public TurtleWorld (int width, int height)
    	{	super ("Turtle Drawings");  // set the title for the frame
    		setDefaultCloseOperation (EXIT_ON_CLOSE); // no WindowListener
    		setSize (width + 2 * EDGE, height + TOP + EDGE);
    		toFront();  // put this frame in front of the BlueJ window
    		setVisible (true);  // cause a call to paint
    		begin (width, height);
    	}	//======================
    
    
    	/**
    	 * Initializes the encapsulated image object.
    	 * 
    	 * @param width    the width of the image object
    	 * @param height   the height of the image object
    	 */
    	private void begin (int width, int height)
    	{	itsPicture = new java.awt.image.BufferedImage (width, height,
    			           java.awt.image.BufferedImage.TYPE_INT_RGB);
    		itsPage = itsPicture.getGraphics();
    		itsPage.setColor (Color.white);
    		itsPage.fillRect (0, 0, width, height);
    		itsPage.setColor (Color.black);
    	}	//======================
    
    
    	/**
    	 * Gets the graphics context associated with
    	 * the encapsulated image object.
    	 * 
    	 * @return the graphics context for the encapsulated image object
    	 */
    	public Graphics getPage()
    	{	
    	    return itsPage; // itsPicture.getGraphics(); => NO COLORS
    	}	//======================
    
    	@Override
    	public void paint (Graphics g)
    	{	if (itsPicture != null)
    			g.drawImage (itsPicture, EDGE, TOP, this);
    		Turtlet.paintPaintmes();
    	}	//======================
    
    }

}
