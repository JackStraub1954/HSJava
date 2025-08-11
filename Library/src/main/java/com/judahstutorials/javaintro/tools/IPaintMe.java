package com.judahstutorials.javaintro.tools;

import java.awt.Graphics2D;

/**
 * Interface to use when using an ImageObject.
 * 
 * @see ImageObject
 */
public interface IPaintMe
{
	/**
	 * Method to display an encapsulated image.
	 * 
	 * @param graphics graphics context for drawing the encapsulated image
	 */
	public void paintMe( Graphics2D graphics );
}
