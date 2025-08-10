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

public class BezierTraversal
{
    private static final double xco0    = 400;
    private static final double yco0    = 100;
    private static final double xco1    = 250;
    private static final double yco1    = 0;
    private static final double xco2    = 150;
    private static final double yco2    = yco0;
    private static final double incr    = .005;
    
    public static void main( String[] args )
    {
        BezierTraversal app     = new BezierTraversal();
        SwingUtilities.invokeLater( () -> app.build() );
    }
    
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
    
    @SuppressWarnings("serial")
    private static class Canvas extends JPanel
    {
        private final Ellipse2D     circle  = new Ellipse2D.Double();
        private final Rectangle2D   frame   = new Rectangle2D.Double();
        
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
