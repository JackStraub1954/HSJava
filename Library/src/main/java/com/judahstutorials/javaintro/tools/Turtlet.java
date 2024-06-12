package com.judahstutorials.javaintro.tools;

// <pre>
// Copy this file in its entirety to a file named Turtlet.java.
// Compile it before compiling the Turtle.java file.
// You can put Turtle commands inside the paint method of an applet if
// you declare them as Turtlets rather than as Turtles using something like
// new Turtlet (page, 100, 150), where page is paint's Graphics parameter
// and you replace 100 by any x-coordinate and 150 by any y-coordinate to
// obtain the starting point of your Turtlet.

import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.net.URL;

public class Turtlet extends Object
{
    public static final double DEGREE = Math.PI / 180;
	public static final Color RED = Color.red, BLUE = Color.blue,
	               BLACK = Color.black,      GRAY = Color.gray,
	               YELLOW = Color.yellow,    PINK = Color.pink,
	               ORANGE = Color.orange,    GREEN = Color.green,
	               MAGENTA = Color.magenta,  WHITE = Color.white;
	private static Graphics2D thePage;
	private static final ArrayList<IPaintMe>	paintMes	= new ArrayList<>();
	//////////////////////////////////
	private double heading = 0;         // heading initially east
	private double xcor, ycor;          // current position of Turtle
	private Color  currColor;           // current color of Turtle
	private Color  xorColor;            // current XOR mode color of Turtle

    private final RHint[]     allRHints_  = new RHint[]
    {
        new RHint( RenderingHints.KEY_ALPHA_INTERPOLATION,
                    RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY
                  ),
        new RHint( RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
                  ),
        new RHint( RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY
                  )
    };

	/** Convenience method so that the student can obtain a URL
	 *  without having to deal with exceptions.
	 *
	 *  @param	path	URL, for example "http://server/picture.jpg";
	 *
	 *  @return	URL if resolved, otherwise null
	 *
	 *  @see java.net.URL
	 */
	public static URL getURL( String path )
	{
		URL	url;
        try
		{
			url = new URL( path );
		}
		catch ( MalformedURLException exc )
		{
			url = null;
		}

		return url;
	}

	/** Set the color for this turtle to the given color.
	 *
	 * @param	given	The color to switch to
	 *
	 * @return The previous color.
	 *
	 */

	public Color switchTo (Color given)
	{
		Color	oldColor	= currColor;
		currColor = given;
		return oldColor;
	}	//======================

	/** Set this Turtle's XOR mode.
	 *
	 *  @param	color	The color to use in the Turtle's XOR mode. To
	 *  				return to paint (normal) mode pass null;
	 *
	 *  @return	previous xor mode color.
	 *
	 *  @see java.awt.Graphics2D#setXORMode
	 *  @see java.awt.Graphics2D#setPaintMode
	 */
	public Color setXORMode( Color color )
	{
		Color	oldColor	= xorColor;
		xorColor = color;
		return oldColor;
	}

	/** Get the grapchics context used for drawing on the Turtles' canvas.
	 *  With this object you can draw anything that Java is capable of.
	 *
	 *  @return	Graphics2D object
	 *
	 *  @see java.awt.Graphics2D
	 */
	public static Graphics2D getGraphics()
	{
		return thePage;
	}

	/** Write words without changing the Turtle's state.
	 *  @param	message	words to write
	 */

	public void say (String message)
	{	setDrawingParams();
		thePage.drawString (message, round (xcor), round (ycor));
	}	//======================

	/** Add to the list of objects that need to be redrawn
	 *  each time the paint method is executed. Images, for example.
	 *
	 *  @param	paintMe	object to be repainted
	 *  @see #removePaintMe( IPaintMe )
	 */
	public static void addPaintMe( IPaintMe paintMe )
	{
		paintMes.add( paintMe );
	}

	/** Remove from the list of objects that need to be redrawn
	 *  each time the paint method is executed.
	 *
	 *  @param	paintMe	object to be removed
	 *  @see #addPaintMe( IPaintMe )
	 */
	public static void removePaintMe( IPaintMe paintMe )
	{
		paintMes.remove( paintMe );
	}

	/**
	 *  Repaints all the objects in the paintMe list.
	 */
	public static void paintPaintmes()
	{
		for ( IPaintMe	paintMe : paintMes )
		{
			paintMe.paintMe( thePage );
		}
	}

	/**
	 * Returns the list of objects in the paintMe list.
	 * @return	paintMe list as type ArrayList
	 */
	public static ArrayList<IPaintMe> getPaintMes()
	{
		return paintMes;
	}


	/** Supply the nearest int value to methods requiring ints. */

	private int round (double x)
	{	return (int) (x + 0.5);
	}	//======================

	private void setDrawingParams()
	{
		thePage.setColor( currColor );
		if ( xorColor != null )
			thePage.setXORMode( xorColor );
		else
			thePage.setPaintMode();
	}


	/** Make a circle of the given radius, Turtle at center.
     * @param radius The given radius.
     */
	public void swingAround (double radius)
	{	setDrawingParams();
		if (radius > 0.0)
		{	int rad = round (2 * radius);
			thePage.drawOval (round (xcor - radius),
				      round (ycor - radius), rad, rad);
		}
	}	//======================

	/** Draws a circular arc inside a square of side 2 * radius.
	 *  The beginning of the arc is the Turtle's current heading; the end
	 *  of the arc is the current heading + degrees. The Turtle is rotated
	 *  by degrees.
	 *
	 *  @param	radius	the radius of the circle
	 *  @param	degrees	degrees to rotate Turtlet through
	 */
	public void paintCircularArc( double radius, double degrees )
	{
		paintArc( 2 * radius, 2 * radius, degrees );
	}

	/** Draws an eliptical arc inside a rectangle.
	 *  The beginning of the arc is the Turtle's current heading; the end
	 *  of the arc is the current heading + degrees. The Turtle is rotated
	 *  by degrees.
	 *
	 *  @param	width	the width of the rectangle
	 *  @param	height	the height of the rectangle
	 *  @param	degrees	degrees to rotate Turtlet through
	 */
	public void paintArc( double width, double height, double degrees )
	{
		setDrawingParams();
		if ( width > 0.0 && height > 0.0 )
		{
			int	arcAngle	= round( degrees );
			int	xco  		= round( xcor - width / 2 );
			int yco  		= round( ycor - height / 2 );
			int	wid			= round( width );
			int hei			= round( height );
			int startAngle	= round( heading * (1/DEGREE ));
			thePage.drawArc( xco, yco, wid, hei, startAngle, arcAngle );
			heading += degrees * DEGREE;
		}
	}

	/** Fills an eliptical arc inside a rectangle.
	 *  The beginning of the arc is the Turtle's current heading; the end
	 *  of the arc is the current heading + degrees. The Turtle is rotated
	 *  by degrees.
	 *
	 *  @param	width	the width of the rectangle
	 *  @param	height	the height of the rectangle
	 *  @param	degrees	degrees to rotate Turtlet through
	 */
	public void fillArc( double width, double height, double degrees )
	{
		setDrawingParams();
		if ( width > 0.0 && height > 0.0 )
		{
			int	arcAngle	= round( degrees );
			int	xco  		= round( xcor - width / 2 );
			int yco  		= round( ycor - height / 2 );
			int	wid			= round( width );
			int hei			= round( height );
			int startAngle	= round( heading * (1/DEGREE) );
			thePage.fillArc( xco, yco, wid, hei, startAngle, arcAngle );
			heading += degrees * DEGREE;
		}
    }
	/** Fill a circle of the given radius, Turtle at center. */

    /**
     * Fill a circle of the given radius, Turtle at center.
     * @param radius The given radius
     */
    public void fillCircle (double radius)
	{	setDrawingParams();
		if (radius > 0.0)
		{	int rad = round (2 * radius);
			thePage.fillOval (round (xcor - radius),
				     round (ycor - radius), rad, rad);
		}
	}	//======================

	/** Create an image from a file and draw it at the Turtle's
	 *  current position. The postion and orientation of the Turtle
	 *  remain unchanged.
	 *
	 *  @param	path file from which to obtain the image data.
	 *  @param  obs  ImageObserver to manage the drawing.
	 *  @return	reference to stored image. This only needs to be saved
	 *          if at some future time the image is to be deleted from
	 *          the canvas.
	 *
	 * @see #removePaintMe( IPaintMe )
	 */
    public IPaintMe drawImage( String path, ImageObserver obs )
    {
        ImageObject img =
				new ImageObject( path, round( xcor ), round( ycor ), obs );
		addPaintMe( img );
        return img;
    }

	/** Create an image from a URL and draw it at the Turtle's
	 *  current position. The position and orientation of the Turtle
	 *  remain unchanged.
	 *
	 *  @param	path URL from which to obtain the image data.
	 *  @param  obs  ImageObserver to manage the drawing.
	 *  @return	reference to stored image. This only needs to be saved
	 *          if at some future time the image is to be deleted from
	 *          the canvas.
	 *
	 * @see #removePaintMe( IPaintMe )
	 */
    public IPaintMe drawImage( 	URL path, ImageObserver obs )
    {
        ImageObject img =
				new ImageObject( path, round( xcor ), round( ycor ), obs );
		addPaintMe( img );
        return img;
    }

// the Turtle class, completed

	/** Rotate left by left degrees; MOVE for forward steps.
     * @param left     The number of degrees to rotate.
     * @param forward  The number of pixels to move forward.
     * @return this
     */

	public Turtlet move (double left, double forward)
	{	heading += left * DEGREE;
		xcor += forward * Math.cos (heading);
		ycor -= forward * Math.sin (heading);
		return this;
	}	//======================


	/** Rotate left by left degrees; PAINT for forward steps.
     * @param left     The number of degrees to rotate.
     * @param forward  The number of pixels to move/paint.
     * @return this
     */
	public Turtlet paint (double left, double forward)
	{	setDrawingParams();
		heading += left * DEGREE;
		double x = xcor + forward * Math.cos (heading);
		double y = ycor - forward * Math.sin (heading);
		thePage.drawLine (round (xcor), round (ycor),
			            round (x), round (y));
		xcor = x;
		ycor = y;
		return this;
	}	//======================


	/** Fill a rectangle of the given width and height, Turtle at center.
     * @param width  The width of the box to fill.
     * @param height The height of the box to fill.
     */

	public void fillBox (double width, double height)
	{	setDrawingParams();
		if (width > 0.0 && height > 0.0)
		{	thePage.fillRect (round (xcor - width / 2),
				            round (ycor - height / 2),
				            round (width), round (height));
		}
	}	//======================


	/** Pause the animation for wait milliseconds.  Made a class method
	 *  so that an applet can pause an animation "between turtles".
     * @param wait The number of milliseconds to wait.
     */

	public static void sleep (int wait)
	{	try
		{	Thread.sleep (wait);
		}catch (InterruptedException ex)
		{}
	}	//======================


	/** Create a Turtlet on a given Component at a given starting position.
	 *  All Turtlets must be created from within the Component's paint()
	 *  method or from a method called by it.  All Turtles live in
	 *  the same world at any given time, so changing the page is analogous
	 *  to spaceshipping the entire Turtle population to a new world.
     * @param page     The page the Turtlet lives on.
     * @param xstart   The x-coordinate of the starting position.
     * @param ystart   The y-coordinate of the starting position.
     */

	public Turtlet (java.awt.Graphics page, double xstart, double ystart)
	{	if (page == null)
			throw new NullPointerException ("You did not give a "
				             + "world where turtles can live!");
		thePage = (Graphics2D)page;
		xcor = xstart;
		ycor = ystart;

        for ( RHint hint : allRHints_ )
            hint.apply( thePage );
	}	//======================
}

class RHint
{
    public RenderingHints.Key   key_;
    public Object               value_;

    public
    RHint( RenderingHints.Key key, Object value )
    {
        key_ = key;
        value_ = value;
    }

    public void apply( Graphics2D g2D )
    {
        g2D.setRenderingHint( key_, value_ );
    }
}
// </pre>

