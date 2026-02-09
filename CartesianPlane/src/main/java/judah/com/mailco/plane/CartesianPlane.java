package judah.com.mailco.plane;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

/**
 * This is the next version of a class that will be used
 * to display a Cartesian plane.
 * Emphasis is on labeling the tic marks.
 * 
 * I think I'll try dividing the main JPanel
 * into three separate panels, 
 * for the main display (axes/grid lines/tic marks),
 * the y-coordinate labels and the x-coordinate labels.
 * Making the main display its own JPanel 
 * should make it easier to calculate the center coordinates
 * and the bounds.
 * 
 * @author Jack Straub
 *
 */
public class CartesianPlane extends JPanel
{
	/** The initial width of the window, in pixels. */
    private int         initWidth           = 815;
    /** The initial height of the window, in pixels. */
    private int         initHeight          = 515;
    /** Background color of the Canvas */
    private Color       bgColor             = new Color( .9f, .9f, .9f );
    /** The color of the major tic marks */
    private Color       ticMajorColor       = new Color( .5f, .5f, .5f );
    /** The color of the minor tic marks */
    private Color       ticMinorColor       = new Color( .75f, .75f, .75f );
    /** The color of the x- and y-axes */
    private Color       axisColor           = new Color( 0f, 0f, 0f );
    /** The color of the grid lines */
    private Color       gridLineColor       = new Color( .7f, .7f, .7f );
    /** The color of the labels */
    private Color       fontColor           = new Color( 0f, 0f, 0f );
    /** Distance between minor tic marks */
    private double      ticMinorDist        = 15;
    /** Distance between major tic marks */
    private double      ticMajorDist        = 5 * ticMinorDist;
    /** Distance between grid marks */
    private double      gridLineDist        = ticMajorDist;
    /** Length of a minor tic mark*/
    private double      ticMinorLen         = 5;
    /** Length of a minor tic mark*/
    private double      ticMajorLen         = 1.5 * ticMinorLen;
    /** Width of a minor tic mark*/
    private double      ticMinorWidth       = ticMinorLen / 2 + 1;
    /** Width of a major tic mark*/
    private double      ticMajorWidth       = ticMinorWidth;
    /** The width of the x- and y-axes */
    private double      axisWidth           = ticMajorWidth;
    /** The color of the grid lines */
    private double      gridLineWidth       = 1;
    
    /** True to display a legend, false to leave it off. */
    private boolean     showLegend          = true;
    /**
     * Width of the legend in pixels;
     * may be negative, in which case the width is calculated 
     * from the font size and the font units.
     * @see #fontSize
     * @see #fontUnits
     * @see #vertPanelWidth
     */
    private double      legendPixels        = -1;
    /**
     * Width of the legend.
     * This is equal to legendPixels, unless legendPixels is negative,
     * in which case the width is calculated from the font.
     * @see #fontSize
     * @see #fontUnits
     * @see #legendPixels
     * @see #fontDecimals
     */
    private double      vertPanelWidth      = 0;
    /** Height of the horizontal panel. */
    private double      horPanelHeight      = 0;
    /** The name of the font used for the legend */
    private String      fontName            = "fixed";
    /** 
     * Units for calculating font size; "em" (case-insensitive) is
     * interpreted as EMS, anything else is interpreted as points.
     * @see #fontSize
     */
    private String      fontUnits           = "em";
    /** 
     * Size of the font in the given units.
     * @see #fontUnits
     */
    private int         fontSize            = 10;
    /** Number of decimal points to use in legend numbers. */
    private int         fontDecimals        = 2;
    /** 
     * Number of places consumed by a number in the legend,
     * including the decimal point.
     */
    private int         fontFieldWidth      = 3;
    /** 
     * Font style. Specified by one of the constants
     * in the font class. 
     * The default is Font.PLAIN.
     */
    private int         fontStyle           = Font.PLAIN;
    /** The font to use to draw grid labels. */
    private Font        font                = null;
    
    /** The graphics context; set every time paintComponent is invoked */
    private Graphics2D  gtx         = null;
    /** 
     * The current width of the Canvas; 
     * set every time paintComponent is invoked
     */
    private int         currWidth   = 0;
    /** 
     * The current height of the Canvas; 
     * set every time paintComponent is invoked
     */
    private int         currHeight  = 0;
    
    //////////////////////////////////////////////////////////////////
    // Rectangular specs for the panel to display y-coordinate labels.
    // Dynamically generated with each pass through paintComponent
    //////////////////////////////////////////////////////////////////
    /** X-coordinate of the upper left corner of the vertical panel. */
    private double      yLabelXco       = 0;
    /** Y-coordinate of the upper left corner of the vertical panel. */
    private double      yLabelYco       = 0;
    /** Width of the vertical panel. */
    private double      yLabelWidth     = 0;
    /** Height of the vertical panel. */
    private double      yLabelHeight    = 0;
    
    //////////////////////////////////////////////////////////////////
    // Rectangular specs for the panel to display x-coordinate labels.
    // Dynamically generated with each pass through paintComponent
    //////////////////////////////////////////////////////////////////
    /** X-coordinate of the upper left corner of the horizontal panel. */
    private double      xLabelXco       = 0;
    /** Y-coordinate of the upper left corner of the horizontal panel. */
    private double      xLabelYco       = 0;
    /** Width of the horizontal panel. */
    private double      xLabelWidth     = 0;
    /** Height of the horizontal panel. */
    private double      xLabelHeight    = 0;
    
    //////////////////////////////////////////////////////////////////
    // Rectangular specs for the panel to display grid.
    // Dynamically generated with each pass through paintComponent
    //////////////////////////////////////////////////////////////////
    /** X-coordinate of the upper left corner of the grid panel. */
    private double      gridXco         = 0;
    /** Y-coordinate of the upper left corner of the grid panel. */
    private double      gridYco         = 0;
    /** Width of the grid panel. */
    private double      gridWidth       = 0;
    /** Height of the grid panel. */
    private double      gridHeight      = 0;
    
    //////////////////////////////////////////////////////////////////
    // Center coordinates of the grid panel;
    // the origin, in this version of the implementation.
    //////////////////////////////////////////////////////////////////
    /** X-coordinate of the center of the grid panel. */
    private double      centerXco      = 0;
    /** Y-coordinate of the center of the grid panel. */
    private double      centerYco      = 0;

    
    /**
     * Constructor. Sets the initial size of the window. 
     */
    public CartesianPlane()
    {
    	// The initial width and height of a window is set using 
    	// a Dimension object.
        Dimension   size    = new Dimension( initWidth, initHeight );
        
        // IMPORTANT: set the size of the window using
        // setPreferredSize. Remember that the actual size of the
        // window may be different after being displayed.
        this.setPreferredSize( size );
        
        // Set the font to be used in the legend.
        font    = new Font( fontName, fontStyle, fontSize );
    }
    
    @Override
    public void paintComponent( Graphics graphics )
    {
        // Begin boilerplate
        super.paintComponent( graphics );
        gtx = (Graphics2D)graphics;
        currWidth = getWidth();
        currHeight = getHeight();
        gtx.setColor( bgColor );
        gtx.fillRect( 0, 0, currWidth, currHeight );
        // End boiler plate
        
        gtx.setFont( font );
        configurePanels();
        drawGridLines( gridLineDist, gridLineColor, gridLineWidth );
        drawTicMarks( 
            ticMinorDist, 
            ticMinorColor, 
            ticMinorWidth, 
            ticMinorLen
        );
        drawTicMarks( 
            ticMajorDist, 
            ticMajorColor, 
            ticMajorWidth, 
            ticMajorLen
        );
        drawAxes();
        drawLabels( ticMajorDist );
        
        gtx.setColor( new Color( 1, 0, 0, .5f ) );
        Rectangle2D rect = new Rectangle2D.Double( yLabelXco, yLabelYco, yLabelWidth, yLabelHeight );
        gtx.fill( rect );
        gtx.setColor( new Color( 0, 1, 0, .5f ) );
        rect = new Rectangle2D.Double( xLabelXco, xLabelYco, xLabelWidth, xLabelHeight );
        gtx.fill( rect );
        gtx.setColor( Color.BLACK );
    }
    
    /**
     * Draw the horizontal and vertical grid lines.
     * 
     * @param distance  distance between grid lines
     * @param color     grid line color
     * @param width     grid line width
     */
    private void drawGridLines( double distance, Color color, double width )
    {
        gtx.setColor( color );
        gtx.setStroke( new BasicStroke( (int)width ) );
        double  firstX  = gridXco;
        double  lastX   = currWidth;
        double  firstY  = gridYco;
        double  lastY   = currHeight;
        Line2D.Double   line    = new Line2D.Double();
        
        // Draw vertical lines to the right of the y-axis
        line.y1 = firstY;
        line.y2 = lastY;
        for ( double xco = centerXco ; xco < lastX ; xco += distance )
        {
            line.x1 = xco;
            line.x2 = xco;
            gtx.draw( line );
        }
        
        // Draw vertical lines to the left of the y-axis
        for ( double xco = centerXco ; xco > firstX ; xco  -= distance )
        {
            line.x1 = xco;
            line.x2 = xco;
            gtx.draw( line );
        }

        line.x1 = firstX;
        line.x2 = lastX;
        // Draw horizontal lines above the y-axis
        for ( double yco = centerYco ; yco > firstY ; yco -= distance )
        {
            line.y1 = yco;
            line.y2 = yco;
            gtx.draw( line );
        }
        
        // Draw horizontal lines below the y-axis
        for ( double yco = centerYco ; yco < lastY  ; yco += distance )
        {
            line.y1 = yco;
            line.y2 = yco;
            gtx.draw( line );
        }
    }
    
    /**
     * Draw the horizontal and vertical grid lines.
     * 
     * @param distance  distance between grid lines
     * @param color     grid line color
     * @param width     grid line width
     */
    private void 
    drawTicMarks( double distance, Color color, double width, double len )
    {
        gtx.setColor( color );
        gtx.setStroke( new BasicStroke( (int)width ) );
        double  firstX  = gridXco;
        double  lastX   = currWidth;
        double  firstY  = gridYco;
        double  lastY   = currHeight - xLabelHeight;
        Line2D.Double   line    = new Line2D.Double();
        
        // Draw vertical tics to the right of the y-axis
        line.y1 = centerYco - len;
        line.y2 = centerYco + len;
        for ( double xco = centerXco ; xco < lastX ; xco += distance )
        {
            line.x1 = xco;
            line.x2 = xco;
            gtx.draw( line );
        }
        
        // Draw vertical lines to the left of the y-axis
        for ( double xco = centerXco ; xco > firstX ; xco  -= distance )
        {
            line.x1 = xco;
            line.x2 = xco;
            gtx.draw( line );
        }

        line.x1 = centerXco - len;
        line.x2 = centerXco + len;
        // Draw horizontal lines above the x-axis
        for ( double yco = centerYco ; yco > firstY ; yco -= distance )
        {
            line.y1 = yco;
            line.y2 = yco;
            gtx.draw( line );
        }
        
        // Draw horizontal lines below the x-axis
        for ( double yco = centerYco ; yco < lastY  ; yco += distance )
        {
            line.y1 = yco;
            line.y2 = yco;
            gtx.draw( line );
        }
    }
    
    private void drawAxes()
    {
        gtx.setColor( axisColor );
        Line2D.Double   line    = new Line2D.Double();
        
        // x-axis
        line.x1 = gridXco;
        line.y1 = centerYco;
        line.x2 = gridXco + gridWidth;
        line.y2 = centerYco;
        gtx.draw( line );
        
        // y-axis
        line.x1 = centerXco;
        line.y1 = gridYco;
        line.x2 = centerXco;
        line.y2 = gridYco + gridHeight;
        gtx.draw( line );
    }
    private void drawLabels( double dist )
    {
        gtx.setColor( fontColor );
        double  firstX  = gridXco;
        double  lastX   = gridXco + gridWidth;
        double  firstY  = gridYco;
        double  lastY   = currHeight - xLabelHeight;
        
        FontMetrics metrics = gtx.getFontMetrics();
        
        // Draw y-axis labels above the x-axis in the vertical panel
        double  dLabel  = 0;
        for ( double yco = centerYco ; yco > firstY ; yco -= dist )
        {
            String  sLabel      = String.format( "%4.2f", dLabel );
            Rectangle2D rect    = metrics.getStringBounds( sLabel, gtx );
            double  xco         = yLabelXco + yLabelWidth - rect.getWidth();
            gtx.drawString( sLabel, (int)xco, (int)yco );
            dLabel += dist;
        }
        
    }
    
    private void configurePanels()
    {
        FontMetrics     metrics     = gtx.getFontMetrics();
        StringBuilder   bldr        = new StringBuilder();
        for ( int inx = 0 ; inx < fontFieldWidth ; ++inx )
            bldr.append( '9' );
        String          testStr     = bldr.toString();
        Rectangle2D     bounds      = metrics.getStringBounds( testStr, gtx );
        
        yLabelWidth = bounds.getWidth() * 1.5;
        xLabelHeight = bounds.getHeight() * 1.5;
        yLabelHeight = currHeight - xLabelHeight;
        xLabelWidth = currWidth - yLabelWidth;
        
        yLabelXco = 0;
        yLabelYco = 0;
        xLabelXco = yLabelWidth;
        xLabelYco = yLabelHeight;
        
        gridXco = yLabelWidth;
        gridYco = 0;
        gridWidth = currWidth - yLabelWidth;
        gridHeight = currHeight - xLabelHeight;
        
        centerXco = gridWidth / 2 + gridXco;
        centerYco = gridHeight / 2 + gridYco;
    }
}
