package judah.com.mailco.plane;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

/**
 * This is version 1 of a class that will be used
 * to demonstrate simple graphics.
 * This version is limited to setting the initial size
 * of the window.
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
    private double      gridLineWidth    = 1;
    
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
    }
    
    @Override
    public void paintComponent( Graphics graphics )
    {
        // Begin boilerplate
        super.paintComponent( graphics );
        gtx = (Graphics2D)graphics.create();
        currWidth = getWidth();
        currHeight = getHeight();
        gtx.setColor( bgColor );
        gtx.fillRect( 0, 0, currWidth, currHeight );
        // End boiler plate
        
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
        
        // begin boilerplate
        gtx.dispose();
        // end boilerplate
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
        double  centerX = currWidth / 2.0;
        double  centerY = currHeight / 2.0;
        double  firstX  = 0;
        double  lastX   = currWidth;
        double  firstY  = 0;
        double  lastY   = currHeight;
        Line2D.Double   line    = new Line2D.Double();
        
        // Draw vertical lines to the right of the y-axis
        line.y1 = firstY;
        line.y2 = lastY;
        for ( double xco = centerX ; xco < lastX ; xco += distance )
        {
            line.x1 = xco;
            line.x2 = xco;
            gtx.draw( line );
        }
        
        // Draw vertical lines to the left of the y-axis
        for ( double xco = centerX ; xco > firstX ; xco  -= distance )
        {
            line.x1 = xco;
            line.x2 = xco;
            gtx.draw( line );
        }

        line.x1 = firstX;
        line.x2 = lastX;
        // Draw horizontal lines above the y-axis
        for ( double yco = centerY ; yco > firstY ; yco -= distance )
        {
            line.y1 = yco;
            line.y2 = yco;
            gtx.draw( line );
        }
        
        // Draw horizontal lines below the y-axis
        for ( double yco = centerY ; yco < lastY  ; yco += distance )
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
        double  centerX = currWidth / 2.0;
        double  centerY = currHeight / 2.0;
        double  firstX  = 0;
        double  lastX   = currWidth;
        double  firstY  = 0;
        double  lastY   = currHeight;
        Line2D.Double   line    = new Line2D.Double();
        
        // Draw vertical tics to the right of the y-axis
        line.y1 = centerY - len;
        line.y2 = centerY + len;
        for ( double xco = centerX ; xco < lastX ; xco += distance )
        {
            line.x1 = xco;
            line.x2 = xco;
            gtx.draw( line );
        }
        
        // Draw vertical lines to the left of the y-axis
        for ( double xco = centerX ; xco > firstX ; xco  -= distance )
        {
            line.x1 = xco;
            line.x2 = xco;
            gtx.draw( line );
        }

        line.x1 = centerX - len;
        line.x2 = centerX + len;
        // Draw horizontal tics above the y-axis
        for ( double yco = centerY ; yco > distance ; yco -= distance )
        {
            line.y1 = yco;
            line.y2 = yco;
            gtx.draw( line );
        }
        
        // Draw horizontal tics below the y-axis
        for ( double yco = centerY ; yco <= lastY  ; yco += distance )
        {
            line.y1 = yco;
            line.y2 = yco;
            gtx.draw( line );
        }
    }
}
