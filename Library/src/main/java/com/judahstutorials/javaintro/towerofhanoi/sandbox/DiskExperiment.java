package com.judahstutorials.javaintro.towerofhanoi.sandbox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Application to experiment with a means
 * to draw a rectangle
 * with the edge-on appearance of a disk.
 */
public class DiskExperiment
{
    /**
     * Application entry point.
     * 
     * @param args  command-line arguments; not used
     */
    public static void main( String[] args )
    {
        DiskExperiment  app = new DiskExperiment();
        SwingUtilities.invokeLater( () -> app.build() );
    }
    
    /**
     * Default constructor; not used.
     */
    private DiskExperiment()
    {
        // not used
    }

    /**
     * Constructs the application GUI.
     */
    private void build()
    {
        JFrame      frame           = new JFrame( "Disk Colors" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        JPanel      contentPane     = new JPanel( new BorderLayout() );
        contentPane.add( new Sandbox(), BorderLayout.CENTER );
        frame.setContentPane( contentPane );
        frame.setLocation( 300, 300 );
        frame.pack();
        frame.setVisible( true );
    }
    
    /**
     * Encapsulates the window
     * in which the disk is drawn.
     */
    private class Sandbox extends JPanel
    {
        /**
         * Default serial version UID
         */
        private static final long serialVersionUID = 1L;

        /**
         * Constructor;
         * establishes the initial size of the window.
         */
        public Sandbox()
        {
            Dimension   dim     = new Dimension( 300, 300 );
            setPreferredSize( dim );
        }
        
        @Override
        public void paintComponent( Graphics graphics )
        {
            super.paintComponent( graphics );
            Graphics2D  gtx     = (Graphics2D)graphics;
            gtx.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
            );
            gtx.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY
            );
            gtx.setRenderingHint(
                RenderingHints.KEY_COLOR_RENDERING,
                RenderingHints.VALUE_COLOR_RENDER_QUALITY
            );
            gtx.setRenderingHint(
                RenderingHints.KEY_DITHERING,
                RenderingHints.VALUE_DITHER_ENABLE
            );
            
            int         width   = getWidth();
            int         height  = getHeight();
            gtx.setColor( new Color( .85f, .85f, .85f ) );
            gtx.fillRect( 0, 0, width, height );
            
            double      diskWidth   = .75 * width;
            double      diskHeight  = 20;
            double      xco         = (width - diskWidth) / 2;
            double      yco         = (height - diskHeight) / 2;
            Color       centerColor = Color.yellow;
            Color       sideColor   = Color.BLACK;// Color( 0xEEDC82 );
//            Rectangle2D disk        = 
//                new Rectangle2D.Double( xco, yco, diskWidth, diskHeight );
            double      dither      = diskHeight * .9;
            RoundRectangle2D disk        = 
                new RoundRectangle2D.Double( 
                    xco, 
                    yco, 
                    diskWidth, 
                    diskHeight, 
                    dither, 
                    dither 
                );
            Point2D     left        = new Point2D.Double( 0, 0 );
            Point2D     right       = new Point2D.Double( width, height );
            LinearGradientPaint paint   =
                new LinearGradientPaint( 
                    left,
                    right,
                    new float[] { 0f, .5f, 1f },
                    new Color[] { sideColor, centerColor, sideColor }
                );
            gtx.setPaint( paint );
            gtx.fill( disk );
            gtx.setColor( Color.BLACK );
            gtx.draw( disk );
        }
    }
}
