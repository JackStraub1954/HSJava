/**
 * 
 */
package cartesian_plane;

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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.JPanel;

/**
 * Encapsulates the Cartesian plane.
 */
public class Plane extends JPanel
{
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
    private Color               bgColor     = new Color( .9f, .9f, .9f );
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
        
        LinePainter painter = new LinePainter( gtx, rect, unitLength );
        for ( LineClass lineClass : linkedMap )
            painter.paint( lineClass );
        
        drawAllPoints( gtx );
        
        Font    font    = new Font( fontFamily, fontStyle, fontSize );
        gtx.setFont( font );
        gtx.setColor( textColor );
        painter.paintText( linkedMap.get( LineClass.MAJOR_TICK ) );
        
        gtx.dispose();
    }
    
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
