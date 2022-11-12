package judah.com.mailco.sandbox;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class GridIterator2
{
    public static final int HORIZONTAL  = 0;
    public static final int VERTICAL    = 1;
    
    private final double    firstXco;
    private final double    firstYco;
    private final double    lastXco;
    private final double    lastYco;
    private final double    centerXco;
    private final double    centerYco;
    private final double    distance;
    
    public GridIterator2( Rectangle2D rect, double distance )
    {
        firstXco = rect.getMinX();
        firstYco = rect.getMinY();
        lastXco = rect.getMaxX();
        lastYco = rect.getMaxY();
        centerXco = rect.getCenterX();
        centerYco = rect.getCenterY();
        this.distance = distance;
    }
    
    public GridLines getGridLines( double lineLen, int orientation )
    {
        Iterator<Line2D>    iter    = getLineIterator( lineLen, orientation );
        GridLines           lines   = new GridLines( iter );
        return lines;
    }
    
    public Iterator<Line2D> getLineIterator( double lineLen, int orientation )
    {
        Iterator<Line2D>    iter    =   orientation == HORIZONTAL ? 
            new HorizontalIterator( lineLen ) :
            new VerticalIterator( lineLen );
        return iter;
    }
    
    private class HorizontalIterator implements Iterator<Line2D>
    {
        private double          nextYco;
        private final Line2D    line2D;
        private final double    xco1;
        private final double    xco2;
        
        public HorizontalIterator( double lineLen )
        {
            if ( lineLen > 0 )
            {
                xco1 = centerXco - lineLen / 2;
                xco2 = xco1 + lineLen;
            }
            else
            {
                xco1 = firstXco;
                xco2 = lastXco;
            }
            double  segLen = (lastYco - firstYco) / 2;
            
            int numParts    = (int)(segLen / distance);
            nextYco = centerYco - numParts * distance;
            
            line2D = new Line2D.Double( xco1, 0, xco2, 0 );
        }
        
        public boolean hasNext()
        {
            boolean hasNext = nextYco < lastYco;
            return hasNext;
        }
        
        public Line2D next()
        {
            if ( nextYco >= lastYco )
                throw new NoSuchElementException();
            line2D.setLine( xco1, nextYco, xco2, nextYco );
            nextYco += distance;
            return line2D;
        }
    }
    
    private class VerticalIterator implements Iterator<Line2D>
    {
        private double          nextXco;
        private final Line2D    line2D;
        private final double    yco1;
        private final double    yco2;
        
        public VerticalIterator( double lineLen )
        {
            if ( lineLen > 0 )
            {
                yco1 = centerYco - lineLen / 2;
                yco2 = yco1 + lineLen;
            }
            else
            {
                yco1 = firstYco;
                yco2 = lastYco;
            }
            double  segLen = (lastXco - firstXco) / 2;
            
            int numParts    = (int)(segLen / distance);
            nextXco = centerXco - numParts * distance;
            
            line2D = new Line2D.Double( 0, yco1, 0, yco2 );
        }
        
        public boolean hasNext()
        {
            boolean hasNext = nextXco < lastXco;
            return hasNext;
        }
        
        public Line2D next()
        {
            if ( nextXco >= lastXco )
                throw new NoSuchElementException();
            line2D.setLine( nextXco, yco1, nextXco, yco2 );
            nextXco += distance;
            return line2D;
        }
    }
}
