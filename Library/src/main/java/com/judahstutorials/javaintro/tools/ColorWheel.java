package com.judahstutorials.javaintro.tools;

import java.awt.Color;

/**
 * Application that demonstrates
 * 'use of the HSB color model.
 */
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
    
    /**
     * Pauses the application for the purpose of animation.
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
        
        /**
         * Decrement the color value.
         */
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
