package judah.com.mailco.sandbox;

import java.awt.geom.Line2D;
import java.util.Iterator;

public class GridLines implements Iterable<Line2D>
{
    private Iterator<Line2D> lineIterator;
    
    public GridLines( Iterator<Line2D> iter )
    {
        this.lineIterator = iter;
    }
    
    @Override
    public Iterator<Line2D> iterator()
    {
        return lineIterator;
    }

}
