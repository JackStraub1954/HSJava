package judah.com.mailco.graph_utils;

/**
 * This is a simple implementation of a class
 * that provides the limits for some range of numbers.
 * The numbers may be any numeric type.
 * Whether the limits are inclusive or exclusive
 * is context-dependent.
 * 
 * @author Jack Straub
 *
 * @param <T>   the type of the limits of this range;
 *              must extends Number
 */
public class Range<T extends Number>
{
    /** The lower limit of this range.  */
    private final T lowerLimit;
    /** The upper limit of this range.  */
    private final T upperLimit;
    
    /**
     * Constructor.
     * Establish the limits of this range.
     * 
     * @param lowerLimit    the lower limit of this range
     * @param upperLimit    the upper limit of this range
     */
    public Range( T lowerLimit, T upperLimit )
    {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    /**
     * Get the lower limit of this range.
     * 
     * @return the lower limit of this range
     */
    public T getLowerLimit()
    {
        return lowerLimit;
    }

    /**
     * Get the upper limit of this range.
     * 
     * @return the upper limit of this range
     */
    public T getUpperLimit()
    {
        return upperLimit;
    }
    
    @Override
    public String toString()
    {
        StringBuilder   bldr    = new StringBuilder();
        bldr.append( "lowerLimit=" ).append( lowerLimit )
            .append( ",upperLint=" ).append(upperLimit);
        return bldr.toString();
    }
}
