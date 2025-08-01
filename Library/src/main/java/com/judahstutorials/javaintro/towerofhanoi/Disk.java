package com.judahstutorials.javaintro.towerofhanoi;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Encapsulates the properties of a disk
 * in the Tower of Hanoi project.
 */
public class Disk
{
    /**
     * Rendering hints for drawing and shading a disk;
     * improve the appearance of the corners
     * on the RoundRectangle,
     * and improve color combinations
     * in shading.
     * Speed is sacrificed for increased quality.
     */
    private static final RenderingHints renderingHints  =
        new RenderingHints(
            RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON
        );
    static
    {
        renderingHints.put(
            RenderingHints.KEY_RENDERING,
            RenderingHints.VALUE_RENDER_QUALITY
        );
        renderingHints.put(
            RenderingHints.KEY_COLOR_RENDERING,
            RenderingHints.VALUE_COLOR_RENDER_QUALITY
        );
        renderingHints.put(
            RenderingHints.KEY_DITHERING,
            RenderingHints.VALUE_DITHER_ENABLE
        );
    }
    /**
     * Shadow color to simulate 3D disk image.
     */
    private static final Color  shadowColor = new Color( .35f, .35f, .35f );
    
    /**
     * Factor for calculating the arc arguments for a RoundRectangle
     * from the rectangles height: 
     * arc = Tower.getComponentHeight() * arcXier.
     */
    private static final double     arcXier = .9;
    
    /**
     * The width of the disk.
     */
    private final double            width;
    /**
     * The height of the disk.
     */
    private final double            height;
    /**
     * The x-coordinate of the disk.
     */
    private  double                 xco;
    /**
     * The y-coordinate of the disk.
     */
    private  double                 yco;
    /**
     * The color of the disk.
     */
    private final Color             color;
    
    /**
     * The shape representing this Disk.
     */
    private final Rectangle2D       bounds  = new Rectangle2D.Double();
    
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
        height = Tower.getComponentHeight();
        this.color = color;
        bounds.setRect( 0, 0, width, height );
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
     * Get the rectangular bounds of this disk
     * 
     * @return  the rectangular bounds this Disk
     */
    public Rectangle2D getBounds()
    {
        bounds.setRect( xco, yco, width, height );
        return bounds;
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
        this.xco = xco;
        this.yco = yco;
    }
    
    /**
     * Renders this disk.
     * The shape is filled using a paint object,
     * and its edge is drawn using the color
     * specified by the Tower class.
     * 
     * @param gtx   the graphics context to draw with
     * 
     * @see Disk#getPaint()
     */
    public void draw( Graphics2D gtx )
    {
        Paint               paint   = getPaint();
        double              arc     = arcXier * height;
        RoundRectangle2D    rect    = 
            new RoundRectangle2D.Double( xco, yco, width, height, arc, arc );
        gtx.setPaint( paint );
        gtx.fill( rect );
        gtx.setColor( Tower.getEdgeColor() );
        gtx.draw( rect );

    }
    
    /**
     * Constructs the Paint object 
     * used to fill this disk.
     * 
     * @return  a Paint object to use while filling this disk
     */
    private Paint getPaint()
    {
        Color       color       = getColor();
        Point2D left    = new Point2D.Double( xco, yco );
        Point2D right   = new Point2D.Double( xco + width, yco + height );
        LinearGradientPaint paint =
            new LinearGradientPaint( 
                left,
                right,
                new float[] { 0f, .1f, 1f },
                new Color[] { shadowColor, color, shadowColor }
            );
        return paint;
    }
}
