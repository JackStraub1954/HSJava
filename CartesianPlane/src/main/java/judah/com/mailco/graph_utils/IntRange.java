package judah.com.mailco.graph_utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class is a special case of Range&lt;T&gt;,
 * where T is type Integer.
 * It includes an iterator
 * that traverses the range by ones,
 * from lowerLimit to upperLimit.
 * 
 * @author Jack Straub
 *
 *@see Range
 */
public class IntRange extends Range<Integer> implements Iterable<Integer>
{
    /**
     * Constructor.
     * Traverses this range by ones,
     * for lower-limit to upper-limit, inclusive.
     * 
     * @param lowerLimit    lower-limit of this range
     * @param upperLimit    upper-limit of this range
     */
    public IntRange( int lowerLimit, int  upperLimit )
    {
        super( lowerLimit, upperLimit );
    }

    @Override
    public Iterator<Integer> iterator()
    {
        IntIterator iter    = new IntIterator();
        return iter;
    }

    private class IntIterator implements Iterator<Integer>
    {
        private final int   last    = (int)getUpperLimit();
        private int         next    = (int)getLowerLimit();
        @Override
        public boolean hasNext()
        {
            boolean hasNext = next <= last;
            return hasNext;
        }

        @Override
        public Integer next()
        {
            if ( next > last )
            {
                final String    msg = "limits of range exceeded";
                throw new NoSuchElementException( msg );
            }
            return next++;
        }
    }
}
