package com.judahstutorials.javaintro.tools;

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
 * Application to demonstrate use of the HSB color model,
 * and Java utilities that employ the HSB color model.
 * Displays the ColorWheel animation in one window,
 * and the color spectrum in another.
 */
public class Hues
{
    /**
     * Milliseconds to pause between drawing a frame
     * of the ColorWheel animation.
     */
    private static final    long    pauseTime       = 10;
    /**
     * Encapsulation of the ColorWheel animation.
     */
    private static ColorWheelPanel colorWheel;
    
    /**
     * Application entry point.
     * 
     * @param args  command-line arguments, not used
     */
    public static void main( String[] args )
    {
        Hues    app     = new Hues();
        SwingUtilities.invokeLater( () -> app.buildGUI() );
        while ( true )
        {
            pause();
            SwingUtilities.invokeLater( () -> colorWheel.repaint() );
        }
    }
    
    /**
     * Default constructor, not used.
     */
    private Hues()
    {
        // not used
    }
    
    /**
     * Builds the application GUI.
     */
    private void buildGUI()
    {
        JFrame  frame   = new JFrame( "Hues Panel" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        JPanel  pane    = new JPanel( new BorderLayout() );
        colorWheel = new ColorWheelPanel();
        pane.add( colorWheel, BorderLayout.CENTER );
        pane.add( new HuesPanel(), BorderLayout.SOUTH );
        
        frame.setContentPane( pane );
        frame.setLocation( 200, 100 );
        frame.pack();
        frame.setVisible( true );
    }
    
    /**
     * Pause the application for the purpose
     * of animating the color where.
     */
    private static void pause()
    {
        try
        {
            Thread.sleep( pauseTime );
        }
        catch ( InterruptedException exc )
        {
            // ignore
        }
    }

    /**
     * Encapsulates the window in which
     * the color spectrum is displayed.
     */
    private static class HuesPanel extends JPanel
    {
        /**
         * Default serial version UID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * Constructor;
         * sets the initial size of the window.
         */
        public HuesPanel()
        {
            setPreferredSize( new Dimension( 256, 50 ) );
        }
        
        @Override
        public void paintComponent( Graphics graphics )
        {
            Graphics2D  gtx     = (Graphics2D)graphics;
            
            int         height  = getHeight();
            int         width   = getWidth();
            double      incr    = 1.0 / (width / 2f);
            float       hue     = 0;
            System.out.println( width + ", " + incr );
            for ( int xco = 0 ; xco < width ; xco += 2 )
            {
                Color   color   = Color.getHSBColor( hue, 1, 1 );
                System.out.println( xco + ", " + hue );
                hue += incr;
                gtx.setColor( color );
                Rectangle2D rect    = 
                    new Rectangle2D.Double( xco, 0, 2, height );
                gtx.fill( rect );
            }
        }
    }
}
