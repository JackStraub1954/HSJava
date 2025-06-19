/**
 * 
 */
package com.judahstutorials.javaintro.cartesian_plane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.JPanel;

/**
 * Encapsulates the Cartesian plane.
 */
public class Plane extends JPanel
{
    /**
     * If true, labels will be drawn on the major ticks
     * of the x- and y-axes.
     */
    private boolean             drawText    = true;
    /**
     * The font family to use when drawing text on the plane.
     */
    private String              fontFamily  = Font.MONOSPACED;
    /**
     * The font size to use when drawing text on the plane.
     */
    private int                 fontSize    = 10;
    /**
     * The font style to use when drawing text on the plane.
     */
    private int                 fontStyle   = Font.PLAIN;
    /**
     * The color to use when drawing text.
     */
    private Color               textColor   = Color.BLACK;
    /**
     * The diameter of a point drawn on the plane.
     */
    private double              pointDiam   = 5;
    /**
     * The color of a point drawn on the plane.
     */
    private Color               pointColor  = Color.RED;
    /**
     * Background color for this window.
     */
    private Color               bgColor     = new Color( .8f, .8f, .8f );
    /**
     * Pitch (the rectangular area the grid is drawn on) color for this window.
     */
    private Color               pitchColor  = new Color( .9f, .9f, .9f );
    /**
     * Margin on all sides of drawing area; pixels.
     */
    private int                 margin      = 25;
    /**
     * Pixels-per-unit.
     */
    private double              unitLength  = 75;
    /**
     * Rectangle that encloses the drawing area.
     * Must be updated every time paintComponent is called.
     */
    private Rectangle2D         rect;
    /**
     * Map of line-type to LineClass. This specific type of map preserves
     * iteration order; iterating over the members of the map will occur
     * in the same order that keys are added to the map.
     */
    private final LineClassMap  linkedMap   = new LineClassMap();
    /**
     * Collection of all points to draw on the plane.
     */
    private final List<Point2D> allPoints   = new ArrayList<>();
    
    /**
     * Default constructor.
     * Performs all object initialization.
     */
    public Plane()
    {
        double  minorTickLen    = 8;
        float   minorTickWidth  = 1;
        // Initialize the map first. Initialization occurs in reverse
        // order of the expected width of a line: gridline, minor tick,
        // major tick, axis. When drawn in this order, wider lines will
        // always overwrite thinner lines.
        LineClass   lineClass   = new LineClass( LineClass.GRIDLINE );
        lineClass.setColor( new Color( .75f, .75f, .75f ) );
        linkedMap.put( lineClass );
        
        lineClass   = new LineClass( LineClass.MINOR_TICK );
        lineClass.setLength( minorTickLen );
        lineClass.setWidth( minorTickWidth );
        lineClass.setPerUnit( 8 );
        linkedMap.put( lineClass );
        
        lineClass   = new LineClass( LineClass.MAJOR_TICK );
        lineClass.setLength( 2 * minorTickLen );
        lineClass.setPerUnit( 2 );
        lineClass.setWidth( minorTickWidth + 1 );
        linkedMap.put( lineClass );
        
        lineClass   = new LineClass( LineClass.AXIS );
        lineClass.setPerUnit( -1 );
        lineClass.setWidth( minorTickWidth + 1 );
        linkedMap.put( lineClass );
        
        Toolkit     toolkit     = Toolkit.getDefaultToolkit();
        Dimension   screenSize  = toolkit.getScreenSize();
        int         prefWidth   = (int)(screenSize.width / 4. * 3.);
        int         prefHeight  = (int)(screenSize.height / 4. * 3.);
        
        Dimension   prefSize    = new Dimension( prefWidth, prefHeight );
        setPreferredSize( prefSize );
    }
    
    /**
     * Add a point to draw on the Cartesian plane.
     *  
     * @param xco   the x-coordinate of the point
     * @param yco   the y-coordinate of the point
     */
    public void addPoint( double xco, double yco )
    {
        allPoints.add( new Point2D.Double( xco, yco ) );
    }
    
    /**
     * Empty the list of points to draw.
     */
    public void clear()
    {
        allPoints.clear();
    }
    
    @Override
    public void paintComponent( Graphics graphics )
    {
        Graphics2D  gtx         = (Graphics2D)graphics.create();
        int         width       = getWidth();
        int         height      = getHeight();
        int         rectWidth   = width - 2 * margin;
        int         rectHeight  = height - 2 * margin;
        rect = new Rectangle2D.Double( margin, margin, rectWidth, rectHeight );
        
        gtx.setColor( bgColor );
        gtx.fillRect( 0, 0, width, height );
        gtx.setColor( pitchColor );
        gtx.fill( rect );
        
        gtx.setClip( rect );
        LinePainter painter = new LinePainter( gtx, rect, unitLength );
        for ( LineClass lineClass : linkedMap )
            painter.paint( lineClass );
        
        drawAllPoints( gtx );
        
        if ( drawText )
        {
            Font    font    = new Font( fontFamily, fontStyle, fontSize );
            gtx.setFont( font );
            gtx.setColor( textColor );
            painter.paintText( linkedMap.get( LineClass.MAJOR_TICK ) );
        }
        
        gtx.dispose();
    }
    
    /**
     * Gets the font family used to draw text.
     * 
     * @return the fontFamily used to draw text
     */
    public String getFontFamily()
    {
        return fontFamily;
    }
    
    /**
     * Returns the value that determines whether or not
     * labels are drawn on the major ticks
     * of the x- and y-axes.
     * 
     * @return  
     *      true, if labels are drawn on the major ticks
     *      of the x- and y-axes
     */
    public boolean getDrawText()
    {
        return drawText;
    }
    
    /**
     * Sets the value that determines whether or not
     * labels are drawn on the major ticks
     * of the x- and y-axes.
     * 
     * @param drawText  true, to draw labels on the x- and y-axes
     */
    public void setDrawText( boolean drawText )
    {
        this.drawText = drawText;
    }

    /**
     * Sets the font family used to draw text.
     * Not all font families are available on all platforms.
     * The following are guaranteed to be available:
     * <ul>
     *      <li>Font.MONOSPACED</li>
     *      <li>Font.SANS_SERIF</li>
     *      <li>Font.SERIF</li>
     * </ul>
     * 
     * @param fontFamily the fontFamily to set
     */
    public void setFontFamily(String fontFamily)
    {
        this.fontFamily = fontFamily;
    }

    /**
     * Gets the size of the font used to draw text.
     * 
     * @return the font size
     */
    public int getFontSize()
    {
        return fontSize;
    }

    /**
     * Sets the size of the font used to draw text.
     * 
     * @param fontSize the size to set
     */
    public void setFontSize(int fontSize)
    {
        this.fontSize = fontSize;
    }

    /**
     * Gets the style of the font used to draw text
     * 
     * @return the fontStyle
     */
    public int getFontStyle()
    {
        return fontStyle;
    }

    /**
     * Sets the style of the font used to draw text
     * (Font.BOLD, Font.ITALIC, Font.PLAIN).
     * 
     * @param fontStyle the fontStyle to set
     */
    public void setFontStyle(int fontStyle)
    {
        this.fontStyle = fontStyle;
    }

    /**
     * Gets the color used to draw text
     * 
     * @return the text color
     */
    public Color getTextColor()
    {
        return textColor;
    }

    /**
     * Sets the color used to draw text
     * 
     * @param textColor the color to set
     */
    public void setTextColor(Color textColor)
    {
        this.textColor = textColor;
    }

    /**
     * Gets the diameter of the circle
     * that represents a point drawn on the plane.
     * 
     * @return the diameter of the circle that represents a point on the plane
     */
    public double getPointDiam()
    {
        return pointDiam;
    }

    /**
     * Sets the diameter of the circle
     * that represents a point drawn on the plane.
     * 
     * @param pointDiam the diameter to set
     */
    public void setPointDiam(double pointDiam)
    {
        this.pointDiam = pointDiam;
    }

    /**
     * Gets the color of the circle
     * that represents a point drawn on the plane.
     * 
     * @return the color of the circle that represents a point on the plane
     */
    public Color getPointColor()
    {
        return pointColor;
    }

    /**
     * Sets the color of the circle
     * that represents a point drawn on the plane.
     * 
     * @param pointColor the color to set
     */
    public void setPointColor(Color pointColor)
    {
        this.pointColor = pointColor;
    }

    /**
     * Gets the background color of this window.
     * 
     * @return the background color of this window
     */
    public Color getBgColor()
    {
        return bgColor;
    }

    /**
     * Sets the background color of this window.
     * 
     * @param bgColor the color to set
     */
    public void setBgColor(Color bgColor)
    {
        this.bgColor = bgColor;
    }

    /**
     * Gets the color of pitch
     * (the rectangular area that contains the grid).
     * 
     * @return the color of the pitch
     */
    public Color getPitchColor()
    {
        return pitchColor;
    }

    /**
     * Sets the color of pitch
     * (the rectangular area that contains the grid).
     * 
     * @param pitchColor the color to set
     */
    public void setPitchColor(Color pitchColor)
    {
        this.pitchColor = pitchColor;
    }

    /**
     * Gets the margin for all sides of this window.
     * 
     * @return the margin for all sides of this window
     */
    public int getMargin()
    {
        return margin;
    }

    /**
     * Sets the margin for all sides of this window.
     * 
     * @param margin the margin to set
     */
    public void setMargin(int margin)
    {
        this.margin = margin;
    }

    /**
     * Gets the unit length (pixels-per-unit)
     * of the encapsulated grid.
     * 
     * @return the unit length
     */
    public double getUnitLength()
    {
        return unitLength;
    }

    /**
     * Sets the unit length (pixels-per-unit)
     * of the encapsulated grid.
     * 
     * @param unitLength the unit length to set
     */
    public void setUnitLength(double unitLength)
    {
        this.unitLength = unitLength;
    }
    
    /**
     * Gets the color used to draw the lines of the given class.
     * 
     * @param lineClass the given class of line
     * 
     * @return  the color used to draw the lines of the given class
     * 
     * @throws NoSuchElementException   if the given class of line is invalid
     * 
     * @see LineClass
     */
    public Color getColor( int lineClass )
    {
        LineClass   lines   = linkedMap.get( lineClass );
        if ( lines == null )
            throw new NoSuchElementException();
        Color       color   = lines.getColor();
        return color;
    }
    
    /**
     * Sets the color used to draw the lines of the given class.
     * 
     * @param lineClass the given class of line
     * @param color     the color to set
     * 
     * @throws NoSuchElementException   if the given class of line is invalid
     * 
     * @see LineClass
     */
    public void setColor( int lineClass, Color color )
    {
        LineClass   lines   = linkedMap.get( lineClass );
        if ( lines == null )
            throw new NoSuchElementException();
        lines.setColor( color );
    }
    
    /**
     * Gets the length of the lines in the given class.
     * A length of -1 means the line is effectively infinite
     * (it spans the length of width of the rectangle
     * that encloses the grid).
     * 
     * @param lineClass the given class of line
     * 
     * @return  the line length
     * 
     * @throws NoSuchElementException   if the given class of line is invalid
     * 
     * @see LineClass
     */
    public double getLength( int lineClass )
    {
        LineClass   lines   = linkedMap.get( lineClass );
        if ( lines == null )
            throw new NoSuchElementException();
        double      length  = lines.getLength();
        return length;
    }
    
    /**
     * Sets the length of the lines in the given class.
     * A length of -1 means the line is effectively infinite
     * (it spans the length of width of the rectangle
     * that encloses the grid).
     * Axes and gridlines should always have a length of -1
     * 
     * @param lineClass the given class of line
     * @param length    the length to set
     * 
     * @throws NoSuchElementException   if the given class of line is invalid
     * 
     * @see LineClass
     */
    public void setLength( int lineClass, double length )
    {
        LineClass   lines   = linkedMap.get( lineClass );
        if ( lines == null )
            throw new NoSuchElementException();
        lines.setLength( length );
    }
    
    /**
     * Gets the width of the lines in the given class.
     * 
     * @param lineClass the given class of line
     * 
     * @return  the line width
     * 
     * @throws NoSuchElementException   if the given class of line is invalid
     * 
     * @see LineClass
     */
    public double getWidth( int lineClass )
    {
        LineClass   lines   = linkedMap.get( lineClass );
        if ( lines == null )
            throw new NoSuchElementException();
        double      width   = lines.getWidth();
        return width;
    }
    
    /**
     * Sets the width of the lines in the given class.
     * 
     * @param lineClass the given class of line
     * @param width     the width to set
     * 
     * @throws NoSuchElementException   if the given class of line is invalid
     * 
     * @see LineClass
     */
    public void setWidth( int lineClass, double width )
    {
        LineClass   lines   = linkedMap.get( lineClass );
        if ( lines == null )
            throw new NoSuchElementException();
        lines.setWidth( (float)width );
    }
    /**
     * Gets the per-unit value of the lines in the given class
     * (the number of lines draw for each unit).
     * 
     * @param lineClass the given class of line
     * 
     * @return  the line per-unit value
     * 
     * @throws NoSuchElementException   if the given class of line is invalid
     * 
     * @see LineClass
     */
    public int getPerUnit( int lineClass )
    {
        LineClass   lines   = linkedMap.get( lineClass );
        if ( lines == null )
            throw new NoSuchElementException();
        int         perUnit = lines.getPerUnit();
        return perUnit;
    }
    
    /**
     * Sets the per-unit value of the lines in the given class.
     * 
     * @param lineClass the given class of line
     * @param perUnit   the lines per unit
     * 
     * @throws NoSuchElementException   if the given class of line is invalid
     * 
     * @see LineClass
     */
    public void setPerUnit( int lineClass, int perUnit )
    {
        LineClass   lines   = linkedMap.get( lineClass );
        if ( lines == null )
            throw new NoSuchElementException();
        lines.setPerUnit( perUnit );
    }
    
    /**
     * Returns a <strong>copy</strong> of the list of all points.
     * This method is mainly useful for testing.
     * 
     * @return  a copy of the list of all points
     */
    public List<Point2D> getPoints()
    {
        List<Point2D>   copy    = Collections.unmodifiableList( allPoints );
        return copy;
    }

    /**
     * Draw all the points assigned to the current grid.
     * 
     * @param gtx   the graphics context for drawing the points
     */
    private void drawAllPoints( Graphics2D gtx )
    {
        gtx.setColor( pointColor );
        double              centerXco   = rect.getCenterX();
        double              centerYco   = rect.getCenterY();
        Ellipse2D.Double    point       =
            new Ellipse2D.Double( 0, 0, pointDiam, pointDiam );
        double              halfOffset  = pointDiam / 2;
        double              xOffset     = centerXco - halfOffset;
        double              yOffset     = centerYco + halfOffset;
        for ( Point2D next : allPoints )
        {
            double  xco = 
                centerXco + next.getX() * unitLength - halfOffset;
            double  yco = centerYco - next.getY() * unitLength - halfOffset;
            point.x = xco;
            point.y = yco;
            gtx.fill( point );
        };
    }
    
    /**
     * Map of line class types to line classes.
     * Declared here for the purpose of simplifying code.
     * It is a subclass of LinkedHashMap which preserves iteration order.
     */
    private static class LineClassMap
        extends LinkedHashMap<Integer, LineClass>
        implements Iterable<LineClass>
    {
        /**
         * Default serial version UID.
         */
        private static final long serialVersionUID = 1L;
        
        /**
         * Default constructor; not used.
         */
        private LineClassMap()
        {
            // not used
        }

        /**
         * Add an object to the map.
         * The key to the object is contained in the object.
         * 
         * @param lineClass the object to add
         */
        public void put( LineClass lineClass )
        {
            put( lineClass.getType(), lineClass );
        }

        @Override
        public Iterator<LineClass> iterator()
        {
            Iterator<LineClass> iterator    = values().iterator();
            return iterator;
        }
    }

}
