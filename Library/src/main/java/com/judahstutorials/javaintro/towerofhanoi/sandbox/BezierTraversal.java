package com.judahstutorials.javaintro.towerofhanoi.sandbox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Application to demonstrate how to render a Bezier curve
 * using a parametric equation.
 * The curve is generated as a sequence of point.
 * Each point is represented by a 2x2 bounding rectangle.
 */
public class BezierTraversal
{
    /**
     * The x-coordinate of the start point of the curve.
     */
    private static final double xco0    = 400;
    /**
     * The y-coordinate of the start point of the curve.
     */
    private static final double yco0    = 100;
    /**
     * The x-coordinate of the curve's control point.
     */
    private static final double xco1    = 250;
    /**
     * The y-coordinate of the curve's control point.
     */
    private static final double yco1    = 0;
    /**
     * The x-coordinate of the end point of the curve.
     */
    private static final double xco2    = 150;
    /**
     * The y-coordinate of the start point of the curve.
     */
    private static final double yco2    = yco0;
    /**
     * The amount by which to increment the t-parameter
     * when stepping through the parametric equation.
     */
    private static final double incr    = .005;
    
    /**
     * Default constructor, not used.
     */
    private BezierTraversal()
    {
        // not used
    }
    
    /**
     * Application entry point.
     * 
     * @param args  command-line arguments; not used
     */
    public static void main( String[] args )
    {
        BezierTraversal app     = new BezierTraversal();
        SwingUtilities.invokeLater( () -> app.build() );
    }
    
    /**
     * Given the value of the t-parameter
     * calculate a point on the curve.
     * 
     * @param tee   the value of the t-parameter
     * 
     * @return  the calculated point
     */
    private static Point2D getPointOnCurve( double tee )
    {
        double x = 
            (1 - tee) * (1 - tee) * xco0 
            + 2 * (1 - tee) * tee * xco1 
            + tee * tee * xco2;
        double y = 
            (1 - tee) * (1 - tee) * yco0 
            + 2 * (1 - tee) * tee * yco1 
            + tee * tee * yco2;
        return new Point2D.Double(x, y);
    }
    
    /**
     * Constructs the application GUI.
     */
    private void build()
    {
        JFrame      frame   = new JFrame( "Bezier Traversal" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        JPanel      pane    = new JPanel( new BorderLayout() );
        pane.add( new Canvas(), BorderLayout.CENTER );
        frame.setContentPane( pane );
        frame.pack();
        frame.setLocation( 200, 200 );
        frame.setVisible( true );
    }
    
    /**
     * Window in which the Bezier curve is drawn.
     */
    @SuppressWarnings("serial")
    private static class Canvas extends JPanel
    {
        /**
         * Used to draw a point; properties are updated as needed.
         */
        private final Ellipse2D     circle  = new Ellipse2D.Double();
        /**
         * Frame used to update the properties of the circle's
         * bounding rectangle.
         */
        private final Rectangle2D   frame   = new Rectangle2D.Double();
        
        /**
         * Constructor.
         * Determines the initial size of the window.
         */
        public Canvas()
        {
            Dimension   dim     = new Dimension( 500, 300 );
            setPreferredSize( dim );
        }
        
        @Override
        public void paintComponent( Graphics graphics )
        {
            super.paintComponent( graphics );
            Graphics2D  gtx = (Graphics2D)graphics;
            for ( double tee = 0 ; tee <= 1 ; tee += incr )
            {
                Point2D corner  = getPointOnCurve( tee );
                frame.setRect( corner.getX(), corner.getY(), 2, 2 );
                circle.setFrame( frame );
                gtx.setColor( Color.BLACK );
                gtx.fill( circle );
                gtx.drawLine( (int)(xco2 - 50), (int)yco0, (int)(xco0 + 50), (int)yco0 );
                gtx.drawLine( (int)xco1, 0, (int)xco1, 200 );
            }
        }
    }
}
