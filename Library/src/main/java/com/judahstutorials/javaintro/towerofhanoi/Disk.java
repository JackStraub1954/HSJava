package com.judahstutorials.javaintro.towerofhanoi;

import java.awt.Color;

/**
 * Encapsulates the properties of a disk
 * in the Tower of Hanoi project.
 */
public class Disk
{
    /**
     * The width of the disk.
     */
    private final double    width;
    /**
     * The color of the disk.
     */
    private final Color     color;
    
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
}
