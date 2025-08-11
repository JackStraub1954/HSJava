package com.judahstutorials.javaintro.towerofhanoi;

import java.awt.Color;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

/**
 * Encapsulates the properties of the rods and disks
 * in the Tower of Hanoi.
 */
public class Tower
{
    /**
     * Default constructor.
     * Made private to prevent instantiation.
     */
    private Tower()
    {
    }
    
    /**
     * Shadow color to simulate 3D images.
     */
    private static final Color  shadowColor = new Color( .35f, .35f, .35f );
    
    /**
     * Positions for creating linear-gradient paint.
     * @see #composePaint(Color, Rectangle2D)
     * @see #keyColors
     */
    private static final float[]    keyFrame    = { 0f, .1f, 1f };
    
    /**
     * Colors for creating linear-gradient paint;
     * the color in the center of the array
     * is changed each time a new Paint is constructed.
     * @see #composePaint(Color, Rectangle2D)
     * @see #keyFrame
     */
    private static final Color[]    keyColors   = 
        { shadowColor, null, shadowColor };
    
    /**
     * The color brown.
     */
    private static final Color  lightBrown      = new Color( 0xd3b683 );
    /**
     * The color of a plinth, and the rod atop the plinth.
     */
    private static Color    plinthColor         = lightBrown;
    /**
     * The color of the edge of a component in the assembly.
     */
    private static Color    edgeColor           = Color.BLACK;
    
    /**
     * The number of rods in the assembly.
     */
    private static int      numRods             = 3;
    /**
     * The width of a rod in the assembly.
     */
    private static double   rodWidth            = 10;
    /**
     * The height of a plinth or disk; calculated as a percentage
     * of the rod width.
     */
    private static double   componentHeight     = rodWidth * 1;
    /**
     * The width of a plinth; calculated as a percentage
     * of the rod width.
     */
    private static double   plinthWidth         = rodWidth * 20;
    /**
     * The left and right margins around the edges of the Pitch,
     * and between plinths.
     */
    private static double   margin              = 10;
    /** The margin at the top of the Pitch. */
    private static double   topMargin           = 10 * margin;
    /** The margin at the bottom of the Pitch. */
    private static double   bottomMargin        = margin;
    /**
     * The number of disks in play.
     */
    private static int      numDisks            = 6;
    /**
     * Disk width multiplier;
     * used to determine the width of the largest disk
     * as a percentage of the width of a plinth.
     */
    private static double   diskWidthXier       = .9;
    /**
     * The width of the largest disk in play.
     */
    private static double   maxDiskWidth        = plinthWidth * diskWidthXier;
    
    /**
     * Circular queue of disk colors.
     */
    private static Color[]  diskColors          = 
    {
        Color.RED,
        Color.MAGENTA,
        Color.BLUE,
        Color.CYAN,
        Color.GREEN,
        Color.YELLOW,
    };
    
    /**
     * Index of the next color in the circular disk color queue.
     */
    private static int      currDiskColor       = 0;
    
    /**
     * Returns the width of the largest disk in play.
     * 
     * @return  the width of the largest disk in play
     */
    public static double getMaxDiskWidth()
    {
        return maxDiskWidth;
    }
    
    /**
     * Returns the next color in the circular color queue.
     * 
     * @return  the next color in the circular color queue
     */
    public static Color getNextDiskColor()
    {
        Color   color   = diskColors[currDiskColor++];
        if ( currDiskColor == diskColors.length )
            currDiskColor = 0;
        return color;
    }
    
    /**
     * Change the number of disks in play
     * to the given value.
     * This must happen before calling Tower.play.
     * <p>
     * If you increase the number of disks
     * by more than one or two beyond the default,
     * the smallest ones will likely
     * become too small to see.
     * You can potentially fix this
     * by changing the width of the plinth
     * (the base that the rod sits on)
     * and the disk multiplier,
     * which calculates the width of the largest disk
     * as a percentage of the width of the plinth.
     * <b>Changing the width of the plinth
     * and/or the disk multiplier
     * should be considered experimental features.</b>
     * 
     * @param numDisks  the given value
     */
    public static void setNumDisks( int numDisks )
    {
        Tower.numDisks = numDisks;
    }

    /**
     * Returns the number of disks in play.
     * 
     * @return  the number of disks in play
     */
    public static int getNumDisks()
    {
        return numDisks;
    }
    
    /**
     * Get the width of a disk
     * given its initial position on the first rod.
     * The width of disk 0,
     * at the bottom of the rod,
     * will have the maximum value.
     * The widths of disks become proportionately smaller
     * as their position progress
     * toward the top of the rod.
     * 
     * @param pos   initial position of the disk on the first rod
     * 
     * @return  
     *      the width of a disk 
     *      based on its initial position on the first rod
     */
    public static double getDiskWidth( int pos )
    {
        double  plinthWidth     = getPlinthWidth();
        double  maxDiskWidth    = getMaxDiskWidth();
        double  pixelDiff       = plinthWidth - maxDiskWidth;
        double  width           = plinthWidth - (pos + 1) * pixelDiff;
        return width;
    }
    
    /**
     * Creates the GUI for this application.
     *  
     * @return  the Pitch object encapsulated in the GUI
     */
    public static Pitch play()
    {
        Frame   frame   = makeFrame();
        return frame.getPitch();
    }
    
    /**
     * Gets the x-coordinate of a rod
     * at the given position.
     * 
     * @param pos   the given position
     * 
     * @return  the x-coordinate of a rod at the given position
     */
    public static double getRodXco( int pos )
    {
        double  plinthXco   = getPlinthXco( pos );
        double  plinthWidth = getPlinthWidth();
        double  rodWidth    = getRodWidth();
        double  xco         = plinthXco +  plinthWidth / 2 - rodWidth / 2; 
        return xco;
    }

    /**
     * Get the y-coordinate of a rod.
     * 
     * @return  the y-coordinate of a rod
     */
    public static double getRodYco()
    {
        return topMargin;//margin + 2 * componentHeight;
    }
    
    /**
     * Gets the height of a rod.
     * The height is calculated so that it rises above
     * the maximum number of disks by one disk-height.
     * 
     * @return  the height of a rod
     */
    public static double getRodHeight()
    {
        double  height  = (numDisks + 1) * componentHeight;
        return height;
    }
    
    /**
     * Gets the color of an edge of a component.
     * 
     * @return  the color of an edge of a component
     */
    public static Color getEdgeColor()
    {
        return edgeColor;
    }
    
    /**
     * Gets the disk width multiplier.
     * This is a value less than 1
     * that determines the width
     * of the largest disk in play.
     * 
     * @return  the disk width multiplier
     */
    public double getDiskWidthXier()
    {
        return diskWidthXier;
    }
    
    /**
     * Sets the disk width multiplier.
     * This is a value less than 1
     * that determines the width
     * of the largest disk in play.
     * <b>This feature should be considered experimental.</b>
     * 
     * @param xier   the new multiplier
     */
    public void setDiskWidthXier( double xier )
    {
        diskWidthXier = xier;
    }

    /**
     * Gets the color of the plinth/rod assembly.
     * 
     * @return  the color of the plinth/rod assembly
     */
    public static Color getPlinthColor()
    {
        return plinthColor;
    }

    /**
     * Returns the width of a plinth
     * (the base that the rod stands on).
     * 
     * @return the width of a plinth
     */
    public static double getPlinthWidth()
    {
        return plinthWidth;
    }

    /**
     * Sets the width of a plinth
     * to the given value,
     * and recalculates the maximum width of a disk.
     * <b>This feature should be considered experimental.</b>
     * 
     * @param   width   the given value
     */
    public static void setPlinthWidth( double width )
    {
        plinthWidth = width;
        maxDiskWidth = plinthWidth * diskWidthXier;
    }
    
    /**
     * Gets the x-coordinate of the plinth at the given position.
     * 
     * @param pos   the given position
     * 
     * @return  the x-coordinate of the plinth at the given position
     */
    public static double getPlinthXco( int pos )
    {
        double  xco = (getPlinthWidth() + margin) * pos + margin;
        return xco;
    }

    /**
     * Gets the y-coordinate of a plinth.
     * 
     * @return  the y-coordinate of a plinth
     */
    public static double getPlinthYco()
    {
        double  yco = getRodYco() + getRodHeight();
        return yco;
    }
    
    /**
     * Gets the number of rods on the pitch.
     * 
     * @return  the number of rods on the pitch
     */
    public static int getNumRods()
    {
        return numRods;
    }
    
    /**
     * Returns the height of a component,
     * where the component is a disk or a plinth.
     * @return the height of a component
     */
    public static double getComponentHeight()
    {
        return componentHeight;
    }
    
    /**
     * Returns the margin to use 
     * for the left and right edges of the pitch,
     * and for the distance between plinths.
     * 
     * @return  the margin used to separate components of the GUI
     */
    public static double getMargin()
    {
        return  margin;
    }
    
    /**
     * Returns the margin to use 
     * for the top of the pitch.
     * 
     * @return  the margin to use for the top of the pitch
     */
    public static double getTopMargin()
    {
        return  topMargin;
    }
    
    /**
     * Returns the margin to use 
     * for the bottom of the pitch.
     * 
     * @return  the margin to use for the bottom of the pitch
     */
    public static double getBottomMargin()
    {
        return  bottomMargin;
    }
    
    /**
     * Get the width of a rod.
     * 
     * @return the width of a rod
     */
    public static double getRodWidth()
    {
        return rodWidth;
    }
    
    /**
     * Given the width of a disk
     * and the index of a rod,
     * determine the x-coordinate of the disk on the rod.
     * 
     * @param disk  the given disk
     * @param rod   the given rod
     * 
     * @return  the x-coordinate of the given disk on the given rod
     */
    public static double getDiskXco( Disk disk, int rod )
    {
        double  center  = getPlinthXco( rod ) + getPlinthWidth() / 2;
        double  xco     = center - disk.getWidth() / 2;
        return xco;
    }
    
    /**
     * Given the position of a disk on a rod,
     * where 0 is the bottom of the rod,
     * determine the y-coordinate of the disk.
     * 
     * @param pos   the given position
     * 
     * @return  the y-coordinate of the disk at the given position
     */
    public static double getDiskYco( int pos )
    {
        double  height      = getComponentHeight();
        double  plinthYco   = getPlinthYco();
        double  yco         = plinthYco - (pos + 1) * height;
        return yco;
    }
    
    /**
     * Composes a gradient Paint object for shading a given rectangle
     * with the given primary color.
     * 
     * @param color the given primary color
     * @param rect  the given rectangle
     * 
     * @return  the composed Paint object
     */
    public static Paint composePaint( Color color, Rectangle2D rect )
    {
        keyColors[1] = color;
        Point2D left    = new Point2D.Double( rect.getX(), rect.getY() );
        Point2D right   = 
            new Point2D.Double( rect.getMaxX(), rect.getMaxY() );
        LinearGradientPaint paint =
            new LinearGradientPaint( 
                left,
                right,
                keyFrame,
                keyColors
            );
        return paint;
    }
    
    /**
     * Creates an application Frame.
     * Returns only after the Frame is fully
     * instantiated and configured.
     * 
     * @return  the instantiated Frame
     */
    private static Frame makeFrame()
    {
        try
        {
            SwingUtilities.invokeAndWait( () -> Frame.getFrame() );
        }
        catch ( InterruptedException | InvocationTargetException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
        return Frame.getFrame();
    }
}
