package cartesian_plane;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

/**
 * An object of this class is responsible for drawing lines
 * on the Cartesian plane.
 * 
 * @see LineClass
 */
public class LinePainter
{
    /**
     * Graphics context to draw with.
     */
    private transient Graphics2D    gtx;
    /**
     * Length of a unit, in pixels.
     */
    private transient double        unitLength;
    /**
     * Area in which lines are to be drawn.
     */
    private transient Rectangle2D   rect;
    /**
     * Left side of the drawing area;
     * derived from {@linkplain #rect}
     * and declared here for convenience.
     */
    private transient double        rectXco;
    /**
     * Top of the drawing area;
     * derived from {@linkplain #rect}
     * and declared here for convenience.
     */
    private transient double        rectYco;
    /**
     * X-coordinate of the center of the drawing area;
     * derived from {@linkplain #rect}
     * and declared here for convenience.
     */
    private transient double        centerXco;
    /**
     * Y-coordinate of the center of the drawing area;
     * derived from {@linkplain #rect}
     * and declared here for convenience.
     */
    private transient double        centerYco;
    /**
     * X-coordinate that defines the right the drawing area;
     * derived from {@linkplain #rect}
     * and declared here for convenience.
     */
    private transient double        maxXco;
    /**
     * Y-coordinate that defines the bottom drawing area;
     * derived from {@linkplain #rect}
     * and declared here for convenience.
     */
    private transient double        maxYco;
    /**
     * Width of the drawing area;
     * derived from {@linkplain #rect}
     * and declared here for convenience.
     */
    private transient double        width;
    /**
     * Height of the drawing area;
     * derived from {@linkplain #rect}
     * and declared here for convenience.
     */
    private transient double        height;
    /**
     * Spacing between lines;
     * derived in {@linkplain #paint(LineClass)}
     * and declared here for convenience.
     */
    private transient double        spacing;
    /**
     * Font for drawing text.
     */
    private Font                    textFont;
    /**
     * Font-render context for drawing text.
     */
    private FontRenderContext       textFRC;


    /**
     * Constructor.
     * Sets the drawing parameters for calls to the paint method.
     * Typically, a new object of this type is required
     * every time the client's paint or paintComponent method is invoked.
     * 
     * @param gtx           graphics context to draw with
     * @param rect          area in which to draw
     * @param unitLength    pixels per unit
     */
    public LinePainter( 
        Graphics2D gtx, 
        Rectangle2D rect, 
        double unitLength    )
    {
        this.gtx = gtx;
        this.rect = rect;
        this.unitLength = unitLength;
        rectXco = rect.getX();
        rectYco = rect.getY();
        centerXco = rect.getCenterX();
        centerYco = rect.getCenterY();
        maxXco = rect.getMaxX();
        maxYco = rect.getMaxY();
        width = rect.getWidth();
        height = rect.getHeight();
    }

    /**
     * Paint the horizontal and vertical lines
     * for the given type of line.
     * 
     * @param type  the given type of line
     */
    public void paint( LineClass type )
    {
        if ( type.isDraw() )
        {
            double  perUnit = type.getPerUnit();
            if ( perUnit < 0 )
                spacing = Math.max( width, height );
            else
                spacing = unitLength / perUnit;
            gtx.setStroke( type.getStroke() );
            gtx.setColor( type.getColor() );
            paintHorizontalLines( type );
            paintVerticalLines( type );
        }
    }
    
    /**
     * Draw the text on the given tick marks.
     * 
     * @param type  encapsulation of tick marks to draw on
     */
    public void paintText( LineClass type )
    {
        textFont = gtx.getFont();
        textFRC = gtx.getFontRenderContext();
        spacing = unitLength / type.getPerUnit();
        paintHorizontalText( type );
        paintVerticalText( type );
    }

    /**
     * Paint the horizontal lines
     * for the given type of line.
     * 
     * @param type  the given type of line
     */
    private void paintHorizontalLines( LineClass type )
    {
        double  length      = type.getLength();
        double  xco1        = 0;
        double  xco2        = 0;
        if ( length < 0 )
        {
            xco1 = rectXco;
            xco2 = maxXco;
        }
        else
        {
            double  offset  = length / 2;
            xco1 = centerXco - offset;
            xco2 = centerXco + offset;
        }
        Line2D.Double       line    = new Line2D.Double( xco1, 0, xco2, 0 );
        Stream.of( true, false ).forEach( b -> {
            Iterator<Double>    iter    = new XYIterator( false, b );
            while ( iter.hasNext() )
            {
                double  yco = iter.next();
                line.y1 = yco;
                line.y2 = yco;
                gtx.draw( line );
            }
        });
    }

    /**
     * Paint the text on the horizontal lines
     * for the given type of line.
     * 
     * @param type  the given type of line
     */
    private void paintHorizontalText( LineClass type )
    {
        double      textOffset  = textFont.getSize() / 2;
        double      length      = type.getLength();
        TextLayout  testLayout  = 
            new TextLayout( "1.0", textFont, textFRC );
        double      yOffset     = testLayout.getBounds().getHeight() / 2;

        double  valIncr = 1 / type.getPerUnit();
        float   xco     = (float)(centerXco + length / 2 + textOffset);
        Stream.of( true, false ).forEach( b -> {
            double  incr    = b ? valIncr : -valIncr;
            double  val     = incr;
            Iterator<Double>    iter    = new XYIterator( false, b );
            // discard origin
            iter.next();
            while ( iter.hasNext() )
            {
                float       yco     = (float)(iter.next() + yOffset);
                String      text    = String.format( "%3.2f", val );
                TextLayout  layout  = new TextLayout( text, textFont, textFRC );
                layout.draw( gtx, xco, yco );
                val += incr;
            }
        });
    }
    
    /**
     * Paint the vertical lines
     * for the given type of line.
     * 
     * @param type  the given type of line
     */
    private void paintVerticalLines( LineClass type )
    {
        double  length  = type.getLength();
        double  yco1    = 0;
        double  yco2    = 0;
        if ( length < 0 )
        {
            yco1 = rectYco;
            yco2 = maxYco;
        }
        else
        {
            double  offset  = length / 2;
            yco1 = centerYco - offset;
            yco2 = centerYco + offset;
        }
        Line2D.Double   line    = new Line2D.Double( 0, yco1, 0, yco2 );
        Stream.of( true, false ).forEach( b -> {
            Iterator<Double>    iter    = new XYIterator( true, b );
            while ( iter.hasNext() )
            {
                double  xco = iter.next();
                line.x1 = xco;
                line.x2 = xco;
                gtx.draw( line );
            }
        });
    }

    /**
     * Paint the text on the vertical lines
     * for the given type of line.
     * 
     * @param type  the given type of line
     */
    private void paintVerticalText( LineClass type )
    {
        double      textOffset  = textFont.getSize() / 2;
        double      length      = type.getLength();
        TextLayout  testLayout  = 
            new TextLayout( "1.0", textFont, textFRC );
        double      yOffset     = 
            length / 2 + testLayout.getBounds().getHeight() + textOffset;

        double      valIncr     = 1 / type.getPerUnit();
        float       yco         = (float)(centerYco + yOffset);
        Stream.of( true, false ).forEach( b -> {
            double              incr    = b ? -valIncr : valIncr;
            double              val     = incr;
            Iterator<Double>    iter    = new XYIterator( true, b );
            // discard origin
            iter.next();
            while ( iter.hasNext() )
            {
                String      text    = String.format( "%3.2f", val );
                TextLayout  layout  = new TextLayout( text, textFont, textFRC );
                double      xOffset = layout.getBounds().getWidth() / 2;
                float       xco     = (float)(iter.next() - xOffset);
                layout.draw( gtx, xco, yco );
                val += incr;
            }
        });
    }
    
    /**
     * Iterates through line coordinates,
     * either forward or backward.
     */
    public class XYIterator implements Iterator<Double>
    {
        /**
         * Iterating backward, if true.
         */
        private final boolean   negative;
        /**
         * First coordinate in the iteration
         */
        private final double    start;
        /**
         * Last coordinate in the iteration
         */
        private final double    end;
        /**
         * Offset to next coordinate in the iteration
         */
        private final double    incr;
        /**
         * Next coordinate in the iteration.
         */
        private double          next;
        
        /**
         * Constructor.
         * 
         * @param vertical  true if generating coordinates for vertical lines.
         * @param negative  true if iterating backward.
         */
        public XYIterator( boolean vertical, boolean negative )
        {
            this.negative = negative;
            incr = negative ? -spacing : spacing;
            start = vertical ? rectXco : rectYco;
            if ( vertical )
                end = negative ? rectXco : maxXco;
            else
                end = negative ? rectYco : maxYco;
            next = vertical ? centerXco : centerYco;
        }
        
        @Override
        public boolean hasNext()
        {
            boolean hasNext = negative ? next >= start : next < end;
            return hasNext;
        }

        @Override
        public Double next()
        {
            if ( !hasNext() )
                throw new NoSuchElementException();
            double  rval    = next;
            next += incr;
            return rval;
        }
        
    }
}
