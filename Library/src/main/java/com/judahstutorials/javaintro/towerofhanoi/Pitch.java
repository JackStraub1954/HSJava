package com.judahstutorials.javaintro.towerofhanoi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.swing.JPanel;

/**
 * This class encapsulates the GUI for the Tower of Hanoi project,
 * and provides all facilities
 * for dynamically manipulating the GUI.
 */
public class Pitch extends JPanel
{
    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = 7816937742943245165L;
    
    /**
     * Rendering hints for improving the appearance of the graphics.
     */
    private static final    RenderingHints renderingHints   = 
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
     * The number of rods contained in the GUI.
     */
    private final int           numRods     = Tower.getNumRods();
    /**
     * All the rods contained in the GUI.
     */
    private final Rod[]         rods        = new Rod[numRods];

    /**
     * Extra shapes to be drawn with each repaint;
     * explicitly added to support animations.
     */
    private final List<Disk>   auxDisks     = new ArrayList<>();
    
    /**
     * Constructor.
     * Fully initializes and configures this portion
     * of the application GUI.
     */
    public Pitch()
    {
        double  rodHeight       = Tower.getRodHeight();
        double  rodYco          = Tower.getRodYco();
        double  plinthHeight    = Tower.getComponentHeight();
        double  margin          = Tower.getMargin();
        int     height          = 
            (int)(rodYco + rodHeight + plinthHeight + 2 * margin + .5);
        
        double  plinthWidth     = Tower.getPlinthWidth();
        int     numRods         = Tower.getNumRods();
        int     width           = 
            (int)(numRods * plinthWidth + (numRods + 1) * margin + .5);
        
        Dimension   preferredSize   = new Dimension( width, height );
        setPreferredSize( preferredSize );
        
        for ( int inx = 0 ; inx < numRods ; ++inx )
            rods[inx] = new Rod();
        
        int         numDisks    = Tower.getNumDisks();
        Rod         rod0        = rods[0];
        for ( int inx = 0 ; inx < numDisks ; ++inx )
        {
            Color   diskColor   = Tower.getNextDiskColor();
            double  diskWidth   = Tower.getDiskWidth( inx );
            Disk    disk        = new Disk( diskWidth, diskColor );
            rod0.push( disk );
        };
    }
    
    @Override
    public void paintComponent( Graphics graphics )
    {
        super.paintComponent( graphics );
        Graphics2D  gtx     = (Graphics2D)graphics;
        gtx.setRenderingHints( renderingHints );
        
        double  rodYco          = Tower.getRodYco();
        double  plinthYco       = Tower.getPlinthYco();
        double  rodWidth        = Tower.getRodWidth();
        double  rodHeight       = Tower.getRodHeight();
        double  plinthWidth     = Tower.getPlinthWidth();
        double  plinthHeight    = Tower.getComponentHeight();
        int     numRods         = Tower.getNumRods();
        Color   plinthColor     = Tower.getPlinthColor();
        Color   edgeColor       = Tower.getEdgeColor();
        
        // Draw the plinths and rods for the GUI.
        Rectangle2D.Double  rod     = 
            new Rectangle2D.Double( 0, rodYco, rodWidth, rodHeight );
        Rectangle2D.Double  plinth  = 
            new Rectangle2D.Double( 0, plinthYco, plinthWidth, plinthHeight );
        for ( int inx = 0 ; inx < numRods ; ++inx )
        {
            rod.x = Tower.getRodXco( inx );
            plinth.x = Tower.getPlinthXco( inx );
            gtx.setColor( plinthColor );
            RodShader.fill( rod.getX(), gtx );
            PlinthShader.fill( plinth.x, gtx );
            gtx.setColor( edgeColor );
            gtx.draw( rod );
            gtx.draw( plinth );
        }
        
        // Draw the disks on the rods.
        IntStream.iterate( 0, i -> i < numRods , i -> i + 1 )
            .forEach( i -> drawDisks( gtx, i ) );
        
        for ( Disk disk : auxDisks )
            disk.draw( gtx );
    }
    
    /**
     * Pushes the given disk onto the rod
     * with the given index number.
     * 
     * @param disk      the given disk
     * @param rodNum    the given index number
     */
    public void push( Disk disk, int rodNum )
    {
        rods[rodNum].push( disk );
    }
    
    /**
     * Removes a disk from the top of the rod
     * with the given index number.
     * Returns null if the rod is empty.
     * 
     * @param rodNum the given index number
     * 
     * @return  the disk from the top of the rod, or null if the rod is empty
     */
    public Disk pop( int rodNum )
    {
        Disk    disk    = rods[rodNum].pop();
        return disk;
    }
    
    /**
     * Gets the disk at the top of the rod
     * with the given index number
     * without removing it.
     * Returns null if the rod is empty.
     * 
     * @param rodNum  the given index number
     * 
     * @return  the disk from the top of the rod, or null if the rod is empty
     */
    public Disk peek( int rodNum )
    {
        Disk    disk    = rods[rodNum].peek();
        return disk;
    }
    
    /**
     * Returns true if the rod with the given index number
     * is empty.
     * 
     * @param rodNum    the given index number
     * 
     * @return  true if the rod with the given index number is empty
     */
    public boolean isEmpty( int rodNum )
    {
        boolean isEmpty = rods[rodNum].isEmpty();
        return isEmpty;
    }
    
    /**
     * Gets the count of disks
     * currently push on to the given rod.
     * 
     * @param rodNum    the given rod
     * 
     * @return  the number of disks on the given rod
     */
    public int getDiskCount( int rodNum )
    {
        int count   = rods[rodNum].getDiskCount();
        return count;
    }

    /**
     * Pauses the application for the given number of milliseconds.
     * @param millis    the given number of milliseconds
     */
    public static void pause( long millis )
    {
        try
        {
            Thread.sleep( millis );
        }
        catch ( InterruptedException exc )
        {
            // ignore
        }
    }
    
    /**
     * Add the given disk to the auxiliary disks list.
     * This method is explicitly intended to support animation.
     *  
     * @param disk the given disk
     */
    public void addAuxDisk( Disk disk )
    {
        auxDisks.add( disk );
    }
    
    /**
     * Removes the given disk from the auxiliary disks list.
     * This method is explicitly intended to support animation.
     *  
     * @param disk the given disk
     */
    public void removeAuxDisk( Disk disk )
    {
        auxDisks.remove( disk );
    }
    
    /**
     * Draws the disks assigned to the rod
     * with the given index number.
     * 
     * @param gtx       graphics context for drawing
     * @param rodNum    the given index number
     */
    private void drawDisks( Graphics2D gtx, int rodNum )
    {
        Rod     rod         = rods[rodNum];
        int     diskNum     = 0;
        for ( Disk disk : rod )
        {
            double      xco     = Tower.getDiskXco( disk, rodNum );
            double      yco     = Tower.getDiskYco( diskNum );
            disk.setLocation( xco, yco );
            disk.draw( gtx );
            ++diskNum;
        }
    }
    
    /**
     * Encapsulates the logic to draw a shaded rod.
     * The logic is managed via a dedicated class
     * in order to collect the details of the operation
     * in a single, isolated place.
     * <p>
     * The rod is represented as a rectangle
     * and shaded to give the appearance of a cylinder.
     * The rectangle is drawn in a dark color,
     * then overlaid from left to right
     * with a sequence of rectangles
     * drawn in the designated color of the rod
     * (see {@linkplain Tower#getPlinthColor()}).
     * The alpha values of the color
     * of the overlaid recangles
     * begins at 0
     * and gradually increases to 1.
     */
    private static class RodShader
    {
        /**
         * The cylinder width.
         */
        private static final double        cylWidth        = 
            Tower.getRodWidth();
        /**
         * The cylinder height.
         */
        private static final double        cylHeight       = 
            Tower.getRodHeight();
        /**
         * The y-coordinate of the cylinder
         * (the upper-left corner of the bounding rectangle).
         */
        private static final double        cylYco          = 
            Tower.getRodYco();
        /**
         * Underlying color of the cylinder.
         */
        private static final Color         cylBackground    = Color.DARK_GRAY;
        /**
         * Representation of the bounding rectangle of the cylinder;
         * declared as a class variable so that it only
         * has to be instantiated once.
         * The properties of the rectangle are adjusted as needed.
         */
        private static final Rectangle2D   rectFrame         = 
            new Rectangle2D.Double();
        /**
         * The number of shaded rectangles used to overlay
         * the cylinder's bounds.
         */
        private static final double        shaderSegs       = 20;
        /**
         * The width of a shaded rectangle used to overlay
         * the cylinder's bounds.
         */
        private static final double        shaderWidth      = 
            cylWidth / shaderSegs;
        /**
         * The integer value of the designated color
         * of a rod.
         */
        private static final int           shaderRGB        = 
            Tower.getPlinthColor().getRGB() & 0xffffff;
        /**
         * The amount by which to increment the value of the alpha
         * component of the color of sequential overlays.
         */
        private static final double         alphaIncr       = 
            255. / shaderSegs;
        
        /**
         * Draws a rod shaded to give the appearance
         * of a cylinder.
         * 
         * @param xco   the x-coordinate of the rod
         * @param gtx   the graphics context to use for drawing.
         */
        public static void fill( double xco, Graphics2D gtx )
        {
            gtx.setColor( cylBackground );
            rectFrame.setRect( xco, cylYco, cylWidth, cylHeight );
            gtx.fill( rectFrame );
            
            for ( int inx = 0 ; inx < shaderSegs ; ++inx )
            {
                int     iAlpha  = (int)(255 - inx * alphaIncr );
                int     rgba    = shaderRGB | (iAlpha << 24);
                Color   color   = new Color( rgba, true );
                gtx.setColor( color );
                double  cylXco  = xco + inx * shaderWidth;
                System.out.println( cylXco );
                System.out.println( Integer.toHexString( rgba ) );
                rectFrame.setRect( cylXco, cylYco, shaderWidth, cylHeight );
                gtx.fill( rectFrame );
            }
        }
    }
    
    private static class PlinthShader
    {
        /**
         * The plinth width.
         */
        private static final double        plinthWidth      = 
            Tower.getPlinthWidth();
        /**
         * The plinth height.
         */
        private static final double        plinthHeight     = 
            Tower.getComponentHeight();
        /**
         * The y-coordinate of the plinth
         * (the upper-left corner of the bounding rectangle).
         */
        private static final double        plinthYco        = 
            Tower.getPlinthYco();
        /**
         * Underlying color of the plinth.
         */
        private static final Color         plinthBackground = Color.DARK_GRAY;
        /**
         * Representation of the bounding rectangle of the cylinder;
         * declared as a class variable so that it only
         * has to be instantiated once.
         * The properties of the rectangle are adjusted as needed.
         */
        private static final Rectangle2D   plinthFrame      = 
            new Rectangle2D.Double();
        private static final Point2D        left            = 
            new Point2D.Double( 0, plinthYco );
        private static final Point2D        right           = 
            new Point2D.Double( 0, plinthYco );
        
        /**
         * The designated color of a plinth.
         */
        private static final Color          plinthColor     = 
            Tower.getPlinthColor();
        
        public static void fill( double xco, Graphics2D gtx )
        {
            left.setLocation( xco, plinthYco );
            right.setLocation( xco + plinthWidth, plinthYco + plinthHeight );
            GradientPaint   paint   = 
                new GradientPaint(
                    left,
                    plinthColor,
                    right,
                    plinthBackground,
                    false
                );
            gtx.setPaint( paint );
            plinthFrame.setFrame( xco, plinthYco, plinthWidth, plinthHeight );
            gtx.fill( plinthFrame );
        }
    }
}
