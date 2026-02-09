package judah.com.mailco.sandbox;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

import javax.swing.JPanel;

import judah.com.mailco.graph_utils.Root;

@SuppressWarnings("serial")
public class LineIteratorDemo extends JPanel
{
    public static void main(String[] args)
    {
        LineIteratorDemo    demo    = new LineIteratorDemo();
        Root                root    = new Root( demo );
        root.start();
    }

    public LineIteratorDemo()
    {
        Dimension   preferredSize   = new Dimension( 500, 600 );
        setPreferredSize( preferredSize );
    }
    
    @Override
    public void paintComponent( Graphics graphics )
    {
        Graphics2D  gtx = (Graphics2D)graphics;
        gtx.setColor( Color.WHITE );
        gtx.fillRect( 0, 0, getWidth(), getHeight() );
        
        Rectangle2D rect    = new Rectangle2D.Double( 50, 50, 400, 500 );
        gtx.setColor( Color.BLACK );
        gtx.draw( rect );
        
        double  distance    = 35.7;
        gtx.setColor( Color.RED );
        GridIterator        lineIter    = new GridIterator( rect, distance );
        Iterator<Line2D>    iter        = 
            lineIter.getLineIterator( -1, GridIterator.HORIZONTAL );
        while ( iter.hasNext() )
        {
            Line2D  line    = iter.next();
            gtx.draw( line );
        }
        gtx.setColor( Color.GREEN );
        iter = lineIter.getLineIterator( -1, GridIterator.VERTICAL );
        while ( iter.hasNext() )
        {
            Line2D  line    = iter.next();
            gtx.draw( line );
        }
        
        lineIter = new GridIterator( rect, distance / 5 );
        gtx.setColor( Color.BLACK );
        gtx.setStroke( new BasicStroke( 3 ) );
        iter = lineIter.getLineIterator( 5, GridIterator.HORIZONTAL );
        while ( iter.hasNext() )
        {
            Line2D  line    = iter.next();
            gtx.draw( line );
        }
        iter = lineIter.getLineIterator( 5, GridIterator.VERTICAL );
        while ( iter.hasNext() )
        {
            Line2D  line    = iter.next();
            gtx.draw( line );
        }
    }
}
