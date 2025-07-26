package com.judahstutorials.javaintro.towerofhanoi;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * Encapsulates the properties of a disk
 * in the Tower of Hanoi project.
 */
public class Disk
{
    /**
     * The width of the disk.
     */
    private final double            width;
    /**
     * The color of the disk.
     */
    private final Color             color;
    
    /**
     * The shape representing this Disk.
     */
    private final Rectangle2D       shape   = new Rectangle2D.Double();
    
    /**
     * Constructor.
     * Initializes the width and color of a disk.
     *  
     * @param width the width of the disk
     * @param color the color of the disk
     */
    public Disk( double width, Color color)
    {
        this.width = width;
        this.color = color;
        shape.setRect( 0, 0, width, Tower.getComponentHeight() );
    }

    /**
     * Gets the width of this disk.
     * 
     * @return the width of this disk
     */
    public double getWidth()
    {
        return width;
    }

    /**
     * Gets the color of this disk.
     * 
     * @return the color of this disk
     */
    public Color getColor()
    {
        return color;
    }
    
    /**
     * Get a Shape representing this Disk
     * and located at the given coordinates.
     * 
     * @param xco   the given x-coordinate
     * @param yco   the given y-coordinate
     * 
     * @return  a Shape representing this Disk
     */
    public Rectangle2D getShape( double xco, double yco )
    {
        shape.setRect( xco, yco, width, Tower.getComponentHeight() );
        return shape;
    }
    
    /**
     * Get a Shape representing this Disk.
     * The coordinates of the Shape
     * are copied from the Shape's previous location.
     * 
     * @param xco   the given x-coordinate
     * @param yco   the given y-coordinate
     * 
     * @return  a Shape representing this Disk
     */
    public Rectangle2D getShape()
    {
        return shape;
    }
    
    /**
     * Sets the location of this Disk
     * to the given coordinates.
     * 
     * @param xco   the given x-coordinate
     * @param yco   the given y-coordinate
     */
    public void setLocation( double xco, double yco )
    {
        getShape( xco, yco );
    }
}
