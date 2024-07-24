package com.judahstutorials.javaintro.tools;

import java.awt.Color;

public class ColorWheel
{
    private static final    double   degreeIncr     = .2;
    private static final    long     pauseTime      = 1;
    /**
     * Application entry point.
     *
     * @param args command line arguments, not used
     *
    */
    public static void main(String[] args)
    {
        ColorManager    colors  = new ColorManager();
        double          degrees = 0;
        double          length  = 256;
        while ( true )
        {
            Turtle  pokey   = new Turtle();
            Color   color   = colors.nextColor();
            pokey.switchTo( color );
            pokey.paint( degrees, length );
            degrees += degreeIncr;
            if ( degrees > 360 )
                degrees = 0;
            pause();
        }
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
        private float   incr        = .05f;
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
            saturation += incr;
            if ( saturation > 1 )
            {
                saturation = 0;
                brightness += incr;
                if ( brightness > 1 )
                {
                    brightness = 0;
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
            saturation -= incr;
            if ( saturation < 0 )
            {
                saturation = 1;
                brightness -= incr;
                if ( brightness < 0 )
                {
                    brightness = 1;
                    hue -= incr;
                    if ( hue < 0 )
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
