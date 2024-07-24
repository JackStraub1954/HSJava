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
    
    private static class ColorManager
    {
        private float   hue         = 0;
        private float   saturation  = 0;
        private float   brightness  = 0;
        private float   incr        = .1f;
        private int     direction   = 1;
        
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
