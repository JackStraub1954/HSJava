package com.judahstutorials.javaintro.tools;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.net.URL;

/**
 * Encapsulates an image which may be drawn by a Turtle.
 * The source of the image can be a file or a URL.
 */
public class ImageObject implements IPaintMe
{
	/**
	 * x-coordinate of the upper-left corner of the image
	 */
	private	int	           xco         = 0;
    /**
     * y-coordinate of the upper-left corner of the image
     */
	private	int	           yco         = 0;
	/**
	 * Image to display.
	 */
	private	Image          image       = null;
	/**
	 * Image observer; may be null.
	 */
	private	ImageObserver  observer    = null;

	/** 
	 * Required method to redraw an image when necessary.
	 *
     *  @param	graphics	graphics context to use for drawing.
	 */
	public void paintMe( Graphics2D graphics )
	{
		graphics.drawImage( image, xco, yco, observer );
	}

	/**
	 *  Creates an image from a file.
	 *
	 *  @param	path	= image file pathname
	 *  @param	xco		= X coordinate to draw at
	 *  @param	yco		= Y coordinate to draw at
	 *  @param obs		= image observer to use for drawing;
	 *                     may be null
	 */
	public ImageObject( String path, int xco, int yco, ImageObserver obs )
	{
		this.xco = xco;
		this.yco = yco;
		observer = obs;
		image = Toolkit.getDefaultToolkit().getImage( path );
	}

	/**
	 *  Creates an image from a URL.
	 *
	 *  @param path    = image file pathname
	 *  @param xco     = X coordinate to draw at
	 *  @param yco     = Y coordinate to draw at
	 *  @param obs     = image observer to use for drawing
	 */
	public ImageObject( URL path, int xco, int yco, ImageObserver obs )
	{
		this.xco = xco;
		this.yco = yco;
		observer = obs;
		image = Toolkit.getDefaultToolkit().getImage( path );
	}
}
