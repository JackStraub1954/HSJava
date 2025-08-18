package com.judahstutorials.javaintro.hangman;

import java.util.Arrays;

/**
 * An instance of this class
 * encapsulates a part of the Hangman image,
 * for example,
 * the left arm or the right leg.
 * It contains a utility 
 * that will merge the encapsulated array
 * into a larger array, for example:
 * <pre>    finalImage.merge( rightArm )</pre>
 * <p>
 * The contents of the encapsulated array
 * are provided as an array of Strings.
 * Internally,
 * the array of Strings is converted
 * to a two-dimensional array of chars;
 * see {@linkplain #getImage()}.
 * <p>
 * Example:<br>
 * Suppose this ImagePart looks like this:
 * <pre>
 *                   1
 *         0    5    0
 *       0 -----------+
 *       1            |
 *       2            |
 *       3            |
 *       4            |
 *       5            |
 *       6 -----------+</pre>
 * <p>
 * And an ImagePart to be merged 
 * has a row property of 3,
 * a column property of 2,
 * and looks like this:
 * <pre>
 *     ^     
 *   &lt;   >
 *     v</pre>
 * <p>
 * Then the merged image will look like this:
 * <pre>
 *                   1
 *         0    5    0
 *       0 -----------+
 *       1            |
 *       2            |
 *       3    ^       |
 *       4  &lt;   >     |
 *       5    v       |
 *       6 -----------+</pre>

 * @see #merge(ImagePart)
 */
public class ImagePart
{
    /**
     * The row at which this image part
     * is to be merged into another image part.
     */
    private final int       row;
    /**
     * The column at which this image part
     * is to be merged into another image part.
     */
    private final int       col;
    /**
     * The array to be merged into another image part.
     */
    private final char[][]  image;
    /**
     * The name of this part;
     * only used in diagnostics.
     * See, for example, {@linkplain #rowTooLong(int, int, ImagePart)}.
     * 
     * @see #setName(String)
     */
    private String          name    = "";
    
    /**
     * Constructor.
     * All properties of the constructed instance
     * are initialized <em>except</em> for the name.
     * The caller provides an array of Strings
     * which is converted to a 2-D array of chars.
     * 
     * @param imageStrs the given array of Strings
     * @param row       the row at which this image part
     *                  is to be merged into another
     * @param col       the column at which this image part
     *                  is to be merged into another
     */
    public ImagePart( String[] imageStrs, int row, int col )
    {
        this.row = row;
        this.col = col;
        int     numStrs = imageStrs.length;
        image = new char[numStrs][];
        for ( int inx = 0 ; inx < numStrs ; ++inx )
        {
            char[]  chars       = imageStrs[inx].toCharArray();
            int     numChars    = chars.length;
            image[inx] = Arrays.copyOf( chars, numChars );
        }
    }
    
    /**
     * Merge a given ImagePart into this ImagePart.
     * This operation is very picky.
     * Spaces in the given ImagePart are ignored.
     * Non-spaces in the given ImagePart
     * can only be merged with spaces
     * in this ImagePart;
     * a violation will raise an IllegalArgument exception.
     * If the number of a row in the given ImagePart
     * exceeds the number of rows in this ImagePart
     * an IllegalArgument exception will be raised.
     * If a row in the given ImagePart
     * is longer than the corresponding row
     * of this ImagePart
     * an IllegalArgument exception will be raised.
     * 
     * @param toMerge   the given ImagePart
     * 
     * @throws  IllegalArgumentException
     *          if the geometry of the given ImagePart
     *          exceeds that of this ImagePart,
     *          or if the merge would overwrite
     *          a non-space character in this ImagePart
     */
    public void merge( ImagePart toMerge )
    {
        char[][]    thatImage   = toMerge.image;
        int         numRows     = thatImage.length + toMerge.row;
        if ( numRows > image.length )
            tooManyRows( toMerge );
        for ( int thisInx = toMerge.row, thatInx = 0 ; 
              thatInx < thatImage.length ;
              ++thisInx, ++thatInx
            )
        {
            mergeRow( thisInx, thatInx, toMerge );
        }
    }
    
    /**
     * Returns the 2-D array of chars
     * encapsulated in this ImagePart.
     * @return  the 2-D array of chars encapsulated in this ImagePart
     */
    public char[][] getImage()
    {
        return image;
    }
    
    /**
     * Returns the name of this ImagePart.
     * 
     * @return the name of this ImagePart
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Sets the name of this ImagePart
     * to the given value.
     * 
     * @param name  the given value
     */
    public void setName( String name )
    {
        this.name = name;
    }
    
    /**
     * Gets the starting row for this ImagePart.
     * 
     * @return the starting row for this ImagePart
     */
    public int getRow()
    {
        return row;
    }
    
    /**
     * Gets the starting row for this ImagePart.
     * 
     * @return the starting column for this ImagePart
     */
    public int getColumn()
    {
        return col;
    }
    
    /**
     * Merges a row from the given ImagePart
     * into the corresponding row of this ImagePart.
     * 
     * @param thisInx   the index of the row in this ImagePart to merge with
     * @param thatInx   the index of the row in the given ImagePart to merge
     * @param toMerge   the given ImagePart.
     * 
     * @throws  IllegalArgumentException
     *          if the geometry of the given ImagePart
     *          exceeds that of this ImagePart,
     *          or if the merge would overwrite
     *          a non-space character in this ImagePart
     */
    private void mergeRow( int thisInx, int thatInx, ImagePart toMerge )
    {
        char[][]    thatImage   = toMerge.image;
        char[]      thisRow     = image[thisInx];
        char[]      thatRow     = thatImage[thatInx];
        int         numCols     = thatRow.length + toMerge.col;
        if ( numCols > thisRow.length )
            rowTooLong( thisInx, thatInx, toMerge );
        for ( int thisJnx = toMerge.col, thatJnx = 0 ; 
            thatJnx < thatRow.length ;
            ++thisJnx, ++thatJnx
          )
        {
            if ( thatRow[thatJnx] != ' ' )
            {
                if ( thisRow[thisJnx] != ' ' )
                    badMerge( thisInx, thisJnx, thatInx, thatJnx, toMerge );
                thisRow[thisJnx] = thatRow[thatJnx];
            }
        }
    }

    /**
     * Formats a diagnostic message indicating
     * that there were too many rows in the given ImagePart,
     * and throws an IllegalArgumentException
     * encapsulating the diagnostic message.
     * 
     * @param source  the given ImagePart
     * 
     * @throws  IllegalArgumentException unconditionally
     */
    private void tooManyRows( ImagePart source )
    {
        int             sourceRows  = source.image.length + source.row;
        StringBuilder   bldr        = new StringBuilder();
        bldr.append( " Too many rows in source " ).append( source.name )
            .append( ": Destination rows: " ).append( image.length )
            .append( ", Source rows: " ).append( sourceRows );
        throw new IllegalArgumentException( bldr.toString() );
    }
    
    /**
     * Formats a diagnostic message indicating
     * that the length of a row in the given ImagePart
     * exceeds the length of the corresponding row
     * in this ImagePart,
     * and throws an IllegalArgumentException
     * encapsulating the diagnostic message.
     * 
     * @param dest      the number of the row in this ImagePart
     *                  to be merged with
     * @param source    the number of the row in the given ImagePart
     *                  to merge
     * @param that      the given ImagePart
     * 
     * @throws  IllegalArgumentException unconditionally
     */
    private void rowTooLong( int dest, int source, ImagePart that )
    {
        int thisLength  = image[dest].length;
        int thatLength  = that.image[source].length + that.col;
        StringBuilder   bldr    = new StringBuilder();
        bldr.append( "Row too long, source=" ).append( that.name )
            .append( ": Destination row/length: " )
                .append( dest ).append( "/" ).append( thisLength )
                .append( ": Source row/length: " )
            .append( source ).append( "/" ).append( thatLength );
        throw new IllegalArgumentException( bldr.toString() );
    }
    
    /**
     * Formats a diagnostic message indicating
     * that merging the given ImagePart
     * with this ImagePart
     * would overwrite a non-space character
     * in this ImagePart.
     * exceeds the length of the corresponding row
     * in this ImagePart,
     * and throws an IllegalArgumentException
     * encapsulating the diagnostic message.
     * 
     * @param destRow   the number of the row in this ImagePart
     *                  to be merged with
     * @param destCol   the number of the column in this ImagePart
     *                  to be merged with
     * @param sourceRow   the number of the row in the given ImagePart
     *                  to merge
     * @param sourceCol   the number of the column in the given ImagePart
     *                  to merge
     * @param source      the given ImagePart
     * 
     * @throws  IllegalArgumentException unconditionally
     */
    private void badMerge(
        int destRow, 
        int destCol, 
        int sourceRow, 
        int sourceCol, 
        ImagePart source
    )
    {
        char[][]    thatImage   = source.image;
        String      destChar    = String.valueOf( image[destRow][destCol] );
        String      sourceChar  = 
            String.valueOf( thatImage[sourceRow][sourceCol] );
        if ( destChar.charAt( 0 ) == ' ' )
            destChar = "[space]";
        if ( sourceChar.charAt( 0 ) == ' ' )
            sourceChar = "[space]";
        StringBuilder   bldr    = new StringBuilder();
        bldr.append( "Illegal substitution, source=" ).append( source.name )
            .append( ": Destination( " )
                .append( destRow ).append( "," ).append( destCol )
                    .append( ")=" ).append( destChar ).append( ", " )
            .append( " Source( " )
                .append( sourceRow ).append( "," ).append( sourceCol )
                    .append( ")=" ).append( destChar );
        throw new IllegalArgumentException( bldr.toString() );
    }
}
