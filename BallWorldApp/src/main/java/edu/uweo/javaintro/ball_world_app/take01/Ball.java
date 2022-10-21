/**
 * 
 */
package edu.uweo.javaintro.ball_world_app.take01;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Arc2D;

/**
 * Encapsulates a ball and its physics in Ball World.
 * Adapted from code by Chua Hock-Chuan (ehchua@ntu.edu.sg).
 * 
 * @author Jack Straub
 *
 *@see  <a href="https://www3.ntu.edu.sg/home/ehchua/programming/java/J8a_GameIntro-BouncingBalls.html">
 *          The World Of Bouncing Balls
 *      </a>
 *      by Chua Hock-Chuan (ehchua@ntu.edu.sg)
 *    
 */
public class Ball
{
    /** The default fill color of the bouncing ball */
    public static final Color   DEF_FILL_COLOR  = Color.BLUE;
    /** The default fill color of the bouncing ball */
    public static final Color   DEF_EDGE_COLOR  = Color.BLACK;
    /**  Default edge width of the bouncing ball */
    public static final double  DEF_EDGE_WIDTH  = 3;
    /** Default diameter of the bouncing ball (width and height) */
    public static final double  DEF_DIAMETER    = 50;
    /** Default x-coordinate increment when the ball moves */
    public static final double  DEF_X_DELTA     = 6;
    /** Default x-coordinate increment when the ball moves */
    public static final double  DEF_Y_DELTA     = 4;
    /** Default x coordinate of the bouncing ball */
    public static final double  DEF_XCO         = 0;
    /** Default y coordinate of the bouncing ball */
    public static final double  DEF_YCO         = 0;
    
    
    /** The fill color of the bouncing ball */
    private Color       ballFillColor;
    /** The edge color of the bouncing ball */
    private Color       ballEdgeColor;
    /** Stroke (mainly width) if the edge of the bouncing ball */
    private BasicStroke ballEdgeStroke;
    /** Diameter of the bouncing ball (width and height) */
    private double      ballDiameter;
    /** x-coordinate increment when the ball moves */
    private double      ballXDelta;
    /** x-coordinate increment when the ball moves */
    private double      ballYDelta;
    /** x coordinate of the bouncing ball */
    private double      ballXco;
    /** y coordinate of the bouncing ball */
    private double      ballYco;
    
    /** 
     * Encapsulates the geometry of the bouncing ball.
     * The x and y coordinates will change each time
     * the ball is redrawn. 
     * The width and height are the same, indicating
     * that the arc will be circular.
     * The starting angle is 0 radians, and the extent
     * is 2 * PI radians indicating the arc will
     * be a full circle.
     * The type of the closure is OPEN; note that closure
     * is only important if the extent of the arc is 
     * less than a full circle.
     */
    private Arc2D.Double    bouncingBall    = 
        new Arc2D.Double( 
            0,              // x-coordinate
            0,              // y-coordinate
            ballDiameter,   // width
            ballDiameter,   // height
            0,              // start angle
            360,            // extent
            Arc2D.OPEN      // closure
        );
    
    /**
     * Default constructor. All properties default.
     */
    public Ball()
    {
        this(
            DEF_DIAMETER,
            DEF_EDGE_WIDTH,
            DEF_FILL_COLOR,
            DEF_EDGE_COLOR,
            DEF_XCO,
            DEF_YCO,
            DEF_X_DELTA,
            DEF_Y_DELTA
        );
    }
    
    /**
     * Constructor. 
     * Sets the diameter of this ball;
     * all other properties default.
     * 
     * @param diameter  diameter of this ball
     */
    public Ball( double diameter )
    {
        this(
            diameter,
            DEF_EDGE_WIDTH,
            DEF_FILL_COLOR,
            DEF_EDGE_COLOR,
            DEF_XCO,
            DEF_YCO,
            DEF_X_DELTA,
            DEF_Y_DELTA
        );
        bouncingBall    = 
            new Arc2D.Double( 
                ballXco,        // x-coordinate
                ballYco,        // y-coordinate
                ballDiameter,   // width
                ballDiameter,   // height
                0,              // start angle
                360,            // extent
                Arc2D.OPEN      // closure
            );
    }
    
    /**
     * Constructor. 
     * Sets the diameter and colors of this ball;
     * all other properties default.
     * 
     * @param diameter  diameter of this ball
     * @param fillColor fill color of this  ball
     * @param edgeColor edge color of this ball
     */
    public Ball( double diameter, Color fillColor, Color edgeColor )
    {
        this(
            diameter,
            DEF_EDGE_WIDTH,
            fillColor,
            edgeColor,
            DEF_XCO,
            DEF_YCO,
            DEF_X_DELTA,
            DEF_Y_DELTA
        );
    }
    
    /**
     * Constructor
     * 
     * @param diameter      diameter of this ball
     * @param edgeWidth     edge width of this ball
     * @param fillColor     fill color of this ball
     * @param edgeColor     edge color of this ball
     * @param xco           x-coordinate of this ball
     * @param yco           y-coordinate of this ball
     * @param xDelta        horizontal speed of this ball
     * @param yDelta        vertical speed of this ball
     */
    public Ball( 
        double diameter, 
        double edgeWidth,
        Color  fillColor,
        Color  edgeColor,
        double xco,
        double yco,
        double xDelta,
        double yDelta
    )
    {
        this.ballDiameter = diameter;
        this.ballEdgeStroke = new BasicStroke( (float)edgeWidth );
        this.ballFillColor = fillColor;
        this.ballEdgeColor = edgeColor;
        this.ballXco = xco;
        this.ballYco = yco;
        this.ballXDelta = xDelta;
        this.ballYDelta = yDelta;
    }
    
    /**
     * Updates this ball's position and redraws the ball.
     * 
     * @param gtx           graphics context for drawing
     * @param currWidth     the width of the bounding rectangle
     *                      containing the ball
     * @param currHeight    the height of the bounding rectangle
     *                      containing the ball
     */
    public void redraw( Graphics2D gtx, int currWidth, int currHeight )
    {
        // Save original gtx parameters overwritten below
        Color   saveColor   = gtx.getColor();
        Stroke  saveStroke  = gtx.getStroke();
        // end save gtx parameters
        
        // Reposition the ball. If the ball is at a boundary of
        // the window, change the direction of the ball before repositioning.
       if ( ballXco + ballDiameter >= currWidth || ballXco <= 0 )
           ballXDelta *= -1;
       if ( ballYco + ballDiameter >= currHeight || ballYco <= 0 )
           ballYDelta *= -1;
       ballXco += ballXDelta;
       ballYco += ballYDelta;
       
       // if the new position of the ball extends beyond the limits
       // of the window, tweak the position so that the ball so that
       // it meets the limit exactly
       if ( ballXco < 0 )
           ballXco = 0;
       else if ( ballXco + ballDiameter > currWidth )
           ballXco = currWidth - ballDiameter;
       else
           ;
       
       if ( ballYco < 0 )
           ballYco = 0;
       else if ( ballYco + ballDiameter > currHeight )
           ballYco = currHeight - ballDiameter;
       else
           ;
       bouncingBall.x = ballXco;
       bouncingBall.y = ballYco;
       gtx.setColor( ballFillColor );
       gtx.fill( bouncingBall );
       gtx.setColor( ballEdgeColor );
       gtx.setStroke( ballEdgeStroke );
       gtx.draw( bouncingBall );
        
        // Restore overwritten gtx parameters
        gtx.setColor( saveColor );
        gtx.setStroke( saveStroke );
        // end restore gtx parameters
    }

    /**
     * Gets this ball's fill color.
     * 
     * @return the ball's fill color.
     */
    public Color getBallFillColor()
    {
        return ballFillColor;
    }

    /**
     * Sets this ball's fill color.
     * @param ballFillColor the ball's fill color
     */
    public void setBallFillColor(Color ballFillColor)
    {
        this.ballFillColor = ballFillColor;
    }

    /**
     * Gets this ball's edge color.
     * @return the ballEdgeColor
     */
    public Color getBallEdgeColor()
    {
        return ballEdgeColor;
    }

    /**
     * Sets this ball's edge color.
     * @param ballEdgeColor the ball's edge color
     */
    public void setBallEdgeColor(Color ballEdgeColor)
    {
        this.ballEdgeColor = ballEdgeColor;
    }

    /**
     * Gets this ball's edge stroke.
     * @return the ballEdgeStroke
     */
    public double getBallEdgeWidth()
    {
        double  width   = ballEdgeStroke.getLineWidth();
        return width;
    }

    /**
     * Sets this ball's stroke.
     * @param ballEdgeStroke the ball's stroke
     */
    public void setBallEdgeWidth(double width)
    {
        this.ballEdgeStroke = new BasicStroke( (float)width );
    }

    /**
     * Gets this ball's diameter.
     * @return the ballDiameter
     */
    public double getBallDiameter()
    {
        return ballDiameter;
    }

    /**
     * Sets this ball's diameter.
     * @param ballDiameter the diameter of the ball
     */
    public void setBallDiameter(double ballDiameter)
    {
        this.ballDiameter = ballDiameter;
    }

    /**
     * Gets this ball's x-coordinate.
     * This is a read-only property.
     * @return the ballXco
     */
    public double getBallXco()
    {
        return ballXco;
    }

    /**
     * Gets this ball's y-coordinate.
     * This is a read-only property.
     * @return the ballYco
     */
    public double getBallYco()
    {
        return ballYco;
    }

    /**
     * Gets this ball's horizontal speed
     * (the amount that the ball's x-coordinate changes
     * when the ball's position is updated).
     * 
     * @return the ballXDelta this ball's horizontal speed
     */
    public double getBallXDelta()
    {
        // get the delta, NOT the direction
        double  delta   = Math.abs( ballXDelta );
        return delta;
    }

    /**
     * Sets this ball's horizontal speed
     * (the amount that the ball's x-coordinate changes
     * when the ball's position is updated).
     * 
     * @param ballYDelta the new vertical speed
     */
    public void setBallXDelta(double delta)
    {
        // set the delta, NOT the direction
        double  dir = Math.signum( ballXDelta );
        this.ballXDelta = dir * delta;
    }

    /**
     * Gets this ball's vertical speed
     * (the amount that the ball's y-coordinate changes
     * when the ball's position is updated).
     * 
     * @return the ballXDelta this ball's horizontal speed
     */
    public double getBallYDelta()
    {
        // return the delta, NOT the direction
        double  delta   = Math.abs( ballYDelta );
        return delta;
    }

    /**
     * Sets this ball's vertical speed
     * (the amount that the ball's y-coordinate changes
     * when the ball's position is updated).
     * 
     * @param ballYDelta the new vertical speed
     */
    public void setBallYDelta(double delta)
    {
        // change the delta, NOT the direction
        double  dir = Math.signum( ballYDelta );
        this.ballYDelta = dir * delta;
    }
}
