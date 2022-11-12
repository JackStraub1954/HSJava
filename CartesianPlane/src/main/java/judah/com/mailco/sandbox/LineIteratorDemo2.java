package judah.com.mailco.sandbox;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import judah.com.mailco.graph_utils.Root;

public class LineIteratorDemo2 extends JPanel
{
    public static void main(String[] args)
    {
        LineIteratorDemo2   demo    = new LineIteratorDemo2();
        Root                root    = new Root( demo );
        root.start();
    }

    public LineIteratorDemo2()
    {
        Dimension   preferredSize   = new Dimension( 500, 600 );
        setPreferredSize( preferredSize );
    }
    
    @Override
    public void paintComponent( Graphics graphics )
    {
        Graphics2D  gtx = (Graphics2D)graphics.create();
        gtx.setColor( Color.WHITE );
        gtx.fillRect( 0, 0, getWidth(), getHeight() );
        
        Rectangle2D rect    = new Rectangle2D.Double( 50, 50, 400, 500 );
        gtx.setColor( Color.BLACK );
        gtx.draw( rect );
        
        double  distance    = 35.7;
        
        // Draw the horizontal grid lines
        GridIterator        lineIter    = new GridIterator( rect, distance );
        gtx.setColor( Color.RED );
        GridLines   lines   = 
            lineIter.getGridLines( -1, GridIterator.HORIZONTAL );
        for ( Line2D line : lines )
            gtx.draw( line );
        
        // Draw the vertical grid lines
        gtx.setColor( Color.GREEN );
        lines = lineIter.getGridLines( -1, GridIterator.VERTICAL );
        for ( Line2D line : lines )
            gtx.draw( line );
        
        // Draw the horizontal tic marks
        gtx.setColor( Color.BLACK );
        gtx.setStroke( new BasicStroke( 3 ) );
        lines = lineIter.getGridLines( 5, GridIterator.HORIZONTAL );
        for ( Line2D line : lines )
            gtx.draw( line );
        
        // Draw the vertical tic marks
        lines = lineIter.getGridLines( 5, GridIterator.VERTICAL );
        for ( Line2D line : lines )
            gtx.draw( line );
    }
}
