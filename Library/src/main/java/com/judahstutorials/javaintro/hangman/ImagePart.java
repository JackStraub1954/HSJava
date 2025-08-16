package com.judahstutorials.javaintro.hangman;

import java.util.Arrays;

public class ImagePart
{
    private final int       row;
    private final int       col;
    private final char[][]  image;
    private String          name    = "";
    
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
    
    public char[][] getImage()
    {
        return image;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName( String name )
    {
        this.name = name;
    }
    
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

    private void tooManyRows( ImagePart that )
    {
        int             srcRows = that.image.length + that.row;
        StringBuilder   bldr    = new StringBuilder();
        bldr.append( " Too many rows in source " ).append( that.name )
            .append( ": Destination rows: " ).append( image.length )
            .append( ", Source rows: " ).append( srcRows );
        throw new IllegalArgumentException( bldr.toString() );
    }
    
    private void rowTooLong( int dest, int src, ImagePart that )
    {
        int thisLength  = image[dest].length;
        int thatLength  = that.image[src].length + that.col;
        StringBuilder   bldr    = new StringBuilder();
        bldr.append( "Row too long, source=" ).append( that.name )
            .append( ": Destination row/length: " )
                .append( dest ).append( "/" ).append( thisLength )
                .append( ": Source row/length: " )
            .append( src ).append( "/" ).append( thatLength );
        throw new IllegalArgumentException( bldr.toString() );
    }
    
    private void badMerge(
        int thisRow, 
        int thisCol, 
        int thatRow, 
        int thatCol, 
        ImagePart that
    )
    {
        char[][]    thatImage   = that.image;
        String      destChar    = String.valueOf( image[thisRow][thisCol] );
        String      srcChar     = 
            String.valueOf( thatImage[thatRow][thatCol] );
        if ( destChar.charAt( 0 ) == ' ' )
            destChar = "[space]";
        if ( srcChar.charAt( 0 ) == ' ' )
            srcChar = "[space]";
        StringBuilder   bldr    = new StringBuilder();
        bldr.append( "Illegal substitution, source=" ).append( that.name )
            .append( ": Destination( " )
                .append( thisRow ).append( "," ).append( thisCol )
                    .append( ")=" ).append( destChar ).append( ", " )
            .append( " Source( " )
                .append( thatRow ).append( "," ).append( thatCol )
                    .append( ")=" ).append( destChar );
        throw new IllegalArgumentException( bldr.toString() );
    }
}
