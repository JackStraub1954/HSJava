package cartesian_plane;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.util.Iterator;

/**
 * Encapsulates the properties of a class of line
 * to be drawn on the Cartesian plane.
 * Examples include axes, gridlines, and ticks.
 */
public class LineClass
{
    /**
     * Describes the x- and y-axes.
     */
    public static final int AXIS        = 0;
    /**
     * Describes the major ticks.
     */
    public static final int MAJOR_TICK  = 1;
    /**
     * Describes the minor ticks;
     */
    public static final int MINOR_TICK  = 2;
    /**
     * Describes the grid lines;
     */
    public static final int GRIDLINE    = 3;
    
    /**
     * The type of line encapsulated here.
     */
    private final int       type;
    
    /**
     * Color of the line.
     */
    private Color   color       = Color.black;
    /**
     * Length of the line in pixels; -1 means infinite (span the visible area).
     */
    private double  length      = -1;
    /**
     * Number of lines per unit.
     */
    private double  perUnit     = 1;
    /**
     * Width of the line.
     */
    private float   width       = 1f;    
    /**
     * If true, this type of line will be drawn on the Cartesian plane.
     */
    private boolean draw        = true;
    /**
     * The Stroke for this line.
     * To change the Stroke, change the line width.
     */
    private Stroke  stroke      = new BasicStroke( width );
    
    /**
     * Constructor.
     * Sets the type of line encapsulated in this object.
     * Properties default as follows:
     * <ul>
     * <li>Color: black</li>
     * <li>Width: 1</li>
     * <li>Length: -1 (infinite)</li>
     * <li>Instances-per-unit: 1</li>
     * <li>Draw: true</li>
     * </ul>
     * 
     * @param type  type of line encapsulated in this object
     */
    public LineClass( int type )
    {
        this.type = type;
    }

    /**
     * Gets the color of this type of line.
     * 
     * @return the color of this type of line
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * Sets the color of this type of line
     * to the given value.
     * 
     * @param color the given value
     */
    public void setColor(Color color)
    {
        this.color = color;
    }

    /**
     * Gets the length of this type of line.
     * 
     * @return the length of this type of line
     */
    public double getLength()
    {
        return length;
    }

    /**
     * Sets the length of this type of line
     * to the given value.
     * 
     * @param length the length to set
     */
    public void setLength(double length)
    {
        this.length = length;
    }

    /**
     * Gets the instances-per-unit of this type of line.
     * 
     * @return the instances-per-unit of this type of line
     */
    public double getPerUnit()
    {
        return perUnit;
    }

    /**
     * Sets the instances-per-unit of this type of line
     * to the given value.
     * 
     * @param perUnit the perUnit to set
     */
    public void setPerUnit(double perUnit)
    {
        this.perUnit = perUnit;
    }

    /**
     * Gets the width of this type of line.
     * 
     * @return the width of this type of line
     */
    public float getWidth()
    {
        return width;
    }

    /**
     * Sets the width of this type of line
     * to the given value.
     * 
     * @param width the width to set
     */
    public void setWidth(float width)
    {
        this.width = width;
        stroke = new BasicStroke( width );
    }

    /**
     * Gets the draw property for this type of line.
     * 
     * @return the draw property for this type of line
     */
    public boolean isDraw()
    {
        return draw;
    }

    /**
     * Sets the draw property for this type of line.
     * If true, this type of line will be drawn
     * every time the GUI is refreshed,
     * otherwise it will not be drawn.
     * 
     * @param draw true to draw this type of line when the GUI is refreshed
     */
    public void setDraw(boolean draw)
    {
        this.draw = draw;
    }

    /**
     * @return the type
     */
    public int getType()
    {
        return type;
    }
    
    /**
     * Gets the Stroke object for this line.
     * 
     * @return  the Stroke for this line
     */
    public Stroke getStroke()
    {
        return stroke;
    }
}
