package com.judahstutorials.javaintro.tools;

import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.net.URL;

public class ImageObject implements IPaintMe
{
	private	int	xco_					= 0;
	private	int	yco_					= 0;
	private	Image			image_		= null;
	private	ImageObserver	observer_	= null;

	/** Required method to redraw an image when necessary.
	 *
     *  @param	graphics	graphics context to use for drawing.
	 */
	public void paintMe( Graphics2D graphics )
	{
		graphics.drawImage( image_, xco_, yco_, observer_ );
	}

	/**
	 *  Creates an image from a file.
	 *
	 *  @param	path	= image file pathname
	 *  @param	xco		= X coordinate to draw at
	 *  @param	yco		= Y coordinate to draw at
	 *  @param	obs		= image observer to use for drawing
	 */
	public ImageObject( String path, int xco, int yco, ImageObserver obs )
	{
		xco_		= xco;
		yco_		= yco;
		observer_	= obs;
		image_		= Toolkit.getDefaultToolkit().getImage( path );
	}

	/**
	 *  Creates an image from a URL.
	 *
	 *  @param	path	= image file pathname
	 *  @param	xco		= X coordinate to draw at
	 *  @param	yco		= Y coordinate to draw at
	 *  @param	obs		= image observer to use for drawing
	 */
	public ImageObject( URL path, int xco, int yco, ImageObserver obs )
	{
		xco_		= xco;
		yco_		= yco;
		observer_	= obs;
		image_		= Toolkit.getDefaultToolkit().getImage( path );
	}
}
