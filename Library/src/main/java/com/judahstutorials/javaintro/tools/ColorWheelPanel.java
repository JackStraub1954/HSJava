package com.judahstutorials.javaintro.tools;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Encapsulates an application for displaying a 
 * {@link ColorWheel}.
 */
public class ColorWheelPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private static final    double  degreeIncr      = 2;
    private static final    double  side            = 512;
    private static final    int     margin          = 10;
    private static final    long    pauseTime       = 10;
    private static ColorWheelPanel  panel;
    
    private final ColorManager  colorMgr    = new ColorManager();
    private double              start       = 0;
    private Arc2D               arc         =
        new Arc2D.Double( margin, margin, side, side, start, degreeIncr, Arc2D.PIE );
    
    /**
     * Application entry point.
     *
     * @param args command line arguments, not used
     *
    */
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater( () -> {
            JFrame      frame       = new JFrame( "Color Wheel" );
            frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
            panel = new ColorWheelPanel();
            frame.setContentPane( panel );
            frame.pack();
            frame.setLocation( 200, 150 );
            frame.setVisible( true );
        });
        while ( true )
        {
            pause();
            SwingUtilities.invokeLater( () -> panel.repaint() );
        }
    }
    
    /**
     * Constructor.
     * Sets the preferred size of the encapsulating GUI.
     */
    public ColorWheelPanel()
    {
        int         dim             = (int)(side + 2 * margin );
        Dimension   prefferedSize   = new Dimension( dim, dim );
        setPreferredSize( prefferedSize );
    }
    
    @Override
    public void paintComponent( Graphics graphics )
    {
        Graphics2D  gtx = (Graphics2D)graphics.create();
        gtx.setColor( colorMgr.nextColor() );
        gtx.setRenderingHint( 
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        
        arc.setAngleStart( start );
        gtx.fill( arc );
        start += degreeIncr;
        if ( start > 360 )
            start = 0;
        gtx.dispose();
    }
    
    /**
     * Put the application thread to sleep for a little while
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
     * Encapsulates the logic to increment/decrement the value of a color.
     * It does this by representing a color in the HSB model.
     * To change the value of a color:
     * <ol>
     *      <li>
     *          Increment or decrement the saturation.
     *          If the saturation goes out of bounds:
     *      </li>
     *      <li>
     *          Return the saturation to its base value
     *          (0 for incrementing, 1 for decrementing)
     *          and increment or decrement the brightness.
     *          If the brightness goes out of bounds:
     *      </li>
     *      <li>
     *          Return the brightness to its base value
     *          (0 for incrementing, 1 for decrementing)
     *          and increment or decrement the hue.
     *          If the hue goes out of bounds:
     *      </li>
     *      <li>
     *          Return the hue to its base value
     *          (0 for incrementing, 1 for decrementing)
     *          and increment or decrement the hue.
     *          Reverse the direction of change.
     *      </li>
     * </ol>
     */
    private static class ColorManager
    {
        /**
         * Hue of the current color.
         */
        private float   hue         = 0;
        /**
         * Saturation of the current color.
         */
        private float   saturation  = 0;
        /**
         * Brightness of the current color.
         */
        private float   brightness  = 0;
        /**
         * Value by which to increment/decrement a color component.
         */
        private float   incr        = .05f;
        /**
         * Direction of change: positive value to increment,
         * negative value to decrement.
         */
        private int     direction   = 1;
        
        /**
         * Gets the next color in the sequence.
         * 
         * @return  the next color in the sequence
         */        
        public Color nextColor()
        {
            if ( direction == 1 )
                forward();
            else
                back();
            Color   color   = 
                Color.getHSBColor( hue, saturation, brightness );
            return color;
        }
        
        /**
         * Increment the color value.
         */
        private void forward()
        {
            brightness += incr;
            if ( brightness > 1 )
            {
                brightness = 0;
                saturation += incr;
                if ( saturation >= 1 )
                {
                    saturation = 0;
                    hue += incr;
                    if ( hue > 1 )
                    {
                        hue = 1;
                        saturation = 1;
                        brightness = 1;
                        direction = -1;
                    }
                }
            }
        }
        
        /**
         * Decrement the color value.
         */
        private void back()
        {
            brightness -= incr;
            if ( brightness < 0 )
            {
                brightness = 1;
                saturation -= incr;
                if ( saturation < 0 )
                {
                    saturation = 1;
                    hue -= incr;
                    if ( hue <= 0 )
                    {
                        hue = 0;
                        brightness = 0;
                        saturation = 0;
                        direction = 1;
                    }
                }
            }
        }
    }
}
