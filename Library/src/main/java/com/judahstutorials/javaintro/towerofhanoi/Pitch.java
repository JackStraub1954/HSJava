package com.judahstutorials.javaintro.towerofhanoi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
            gtx.fill( rod );
            gtx.fill( plinth );
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
}
