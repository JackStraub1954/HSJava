package judah.com.mailco.plane;

import java.awt.geom.Point2D;

/**
 * Encapsulation of a point in the Cartesian plane.
 * 
 * @author jstra
 */
public class XYPair extends Point2D.Double
{
    // Generated serial version UID.
    private static final long serialVersionUID = 366343181074838192L;
    
    private boolean hidden;
    
    /**
     * Default constructor. Sets the (x, y) coordinates to (0, 0),
     * and the hidden property to false.
     */
    public XYPair()
    {
        this( 0, 0, false );
    }
    
    /**
     * Constructor. Sets the (x, y) coordinates to the given 
     * values, and sets the hidden property to false.
     * 
     * @param xco   The given x-coordinate
     * @param yco   The given y-coordinate
     */
    public XYPair( double xco, double yco )
    {
        this( xco, yco, false );
    }
    
    /**
     * Constructor. Sets the (x, y) coordinates and the 
     * hidden property to the given coordinates.
     * 
     * @param xco       The given x-coordinate
     * @param yco       The given y-coordinate
     * @param hidden    The value of the hidden property.
     */
    public XYPair( double xco, double yco, boolean hidden )
    {
        super( xco, yco );
        this.hidden = hidden;
    }

    /**
     * Returns the value of the hidden property.
     * @return The value of the hidden property
     */
    public boolean isHidden()
    {
        return hidden;
    }

    /**
     * Sets the value of the hidden property to the given value.
     * 
     * @param hidden    The given value of the hidden property.
     */
    public void setHidden( boolean hidden )
    {
        this.hidden = hidden;
    }
}
