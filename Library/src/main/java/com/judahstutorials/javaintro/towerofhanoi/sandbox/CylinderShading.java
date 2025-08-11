package com.judahstutorials.javaintro.towerofhanoi.sandbox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * This application is used to experiment
 * with a means to shade a cylinder.
 * A rectangle representing the cylinder
 * is filled with a background (a dark color),
 * then divided into smaller rectangles (overlays)
 * filled with the cylinder's designated color
 * (an argb value).
 * The alpha value of each overlay's color
 * increases from 0 on the left
 * to 1 on the right.
 */
public class CylinderShading
{    
    
    /**
     * Default constructor; not used.
     */
    private CylinderShading()
    {
        // not used
    }
    
    /**
     * Application entry point.
     * 
     * @param args  command-line arguments; not used.
     */
    public static void main( String[] args )
    {
        CylinderShading app     = new CylinderShading();
        SwingUtilities.invokeLater( () -> app.build() );
    }
    
    /**
     * Constructs the application GUI.
     */
    private void build()
    {
        JFrame      frame   = new JFrame( "Cylinder Shading" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        JPanel      pane    = new JPanel( new BorderLayout() );
        pane.add( new Canvas(), BorderLayout.CENTER );
        frame.setContentPane( pane );
        frame.pack();
        frame.setLocation( 200, 200 );
        frame.setVisible( true );
    }
    
    /**
     * Encapsulates the window in which the cylinder
     * is drawn and shaded.
     */
    @SuppressWarnings("serial")
    private static class Canvas extends JPanel
    {
        /**
         * The initial width of the window.
         */
        private final int           winWidth        = 200;
        /**
         * The initial height of the window.
         */
        private final int           winHeight       = 300;
        /**
         * The window's background color.
         */
        private final Color         winBackground   = 
            new Color( 200, 200, 200 );
        /**
         * The width of the cylinder.
         */
        private final double        cylWidth        = 30;
        /**
         * The height of the cylinder.
         */
        private final double        cylHeight       = 120;
        /**
         * The x-coordinate of the upper-left corner
         * of the cylinder's bounding rectangle.
         */
        private final double        cylXco          = 
            winWidth / 2 - cylWidth / 2;
        /**
         * The y-coordinate of the upper-left corner
         * of the cylinder's bounding rectangle.
         */
        private final double        cylYco          = 
            winHeight / 2 - cylHeight / 2;
        /**
         * The cylinder's background color.
         */
        private final Color         cylBackground   = Color.DARK_GRAY;
        /**
         * The cylinder's bounding rectangle.
         */
        private final Rectangle2D   cylFrame        =
            new Rectangle2D.Double( cylXco, cylYco, cylWidth, cylHeight );
        /**
         * The number of rectangle's into which the cylinder is divided.
         */
        private final double        shaderSegs      = 20;
        /**
         * The width of the rectangle into which the cylinder is divided.
         */
        private final double        shaderWidth     = cylWidth / shaderSegs;
        /**
         * The cylinder's designated color.
         */
        private final int           shaderRGB       = 0x00FF00;
        /**
         * Bounding rectangle for drawing the cylinder;
         * properties are updated as needed.
         */
        private final Rectangle2D   shaderSeg       =
            new Rectangle2D.Double( 0, 0, shaderWidth, cylHeight );
        
        /**
         * Constructor.
         * Established the initial size of the window.
         */
        public Canvas()
        {
            Dimension   dim     = new Dimension( winWidth, winHeight );
            setPreferredSize( dim );
        }
        
        @Override
        public void paintComponent( Graphics graphics )
        {
            super.paintComponent( graphics );
            Graphics2D  gtx = (Graphics2D)graphics;
            gtx.setColor( winBackground );
            gtx.fillRect( 0, 0, getWidth(), getHeight() );
            
            gtx.setColor( cylBackground );
            gtx.fill( cylFrame );
            
            double  shaderIncr  = 255. / shaderSegs;
            for ( int inx = 0 ; inx < shaderSegs ; ++inx )
            {
                double  xco     = cylXco + inx * shaderWidth;
                int     iAlpha  = (int)(255 - inx * shaderIncr );
                int     argb    = shaderRGB | (iAlpha << 24);
                System.out.println( Integer.toHexString( argb ) );
                Color   color   = new Color( argb, true );
                gtx.setColor( color );
                shaderSeg.setRect( xco, cylYco, shaderWidth, cylHeight );
                gtx.fill( shaderSeg );
            }
        }
    }
}
