package com.judahstutorials.javaintro.hangman.sandbox;

import java.util.Arrays;

import com.judahstutorials.javaintro.hangman.ImagePart;

/**
 * This application verifies that the error processing logic
 * in the {@linkplain ImagePart} class works as expected.
 * <p>
 * If this demo executes correctly
 * you will see an error message in the console,
 * but no GUI.
 */
public class MergeDemo1
{
    /**
     * The initializer for the ImagePart
     * that will be the destination of a merge operation.
     */
    private static final String[]   mergeDest   =
    {
     //            1    1
     //  0----5----0----5---
        "                  |", // 0
        "     |            |", // 1
        "     o            |", // 2
        "    * *           |", // 3
        "   *   *          |", // 4
    };

    /**
     * The row at which we will attempt to merge an ImagePart
     * that contains a row which is too long for the destination.
     */
    private static final int        tooManyColsRow  = 1;
    /**
     * The column at which we will attempt to merge an ImagePart
     * that contains a row which is too long for the destination.
     */
    private static final int        tooManyColsCol  = 2;
    /**
     * Initializer for an ImagePart which will contain a row
     * that is too long to merge with the destination ImagePart.
     */
    private static final String[]   tooManyCols     =
    {
     //          1    1
     //  ---5----0----5---
        "                 ",  // 1
        "                  ", // 2 *** too many cols
        "                 ",  // 3
        "                 ",  // 4
    };

    /**
     * The row at which we will attempt to merge an ImagePart
     * that contains too many rows.
     */
    private static final int        tooManyRowsRow  = 2;
    /**
     * Initializer for an ImagePart which will contain too many rows
     * to be merged with the destination ImagePart.
     */
    private static final String[]   tooManyRows     =
    {
     //            1    1
     //  0----5----0----5---
        "                   ", // 2
        "                   ", // 3
        "                   ", // 4
        "                   ", // 5
    };
    
    /**
     * The row at which we will attempt to merge an ImagePart
     * that contains an invalid character.
     */
    private static final int        badMergeRow =   2;
    /**
     * The column at which we will attempt to merge an ImagePart
     * that contains an invalid character.
     */
    private static final int        badMergeCol =   3;
    /**
     * Initializer for an ImagePart that contains characters
     * that cannot be properly merged with the destination ImagePart.
     */
    private static final String[]   badMerge    =
    {
        //            1    1
        //  --5----0----5---
        "               |", // 2
        " *             |", // 3
        "               |", // 4
    };

    /**
     * Application entry point.
     * 
     * @param args  command-line arguments; not used.
     */
    public static void main(String[] args)
    {
        ImagePart   src     = new ImagePart( tooManyRows, tooManyRowsRow, 0 );
        tryMerge( src, "tooManyRows" );
        
        src = new ImagePart( tooManyCols, tooManyColsRow, tooManyColsCol );
        tryMerge( src, "tooManyCols" );
        
        src = new ImagePart( badMerge, badMergeRow, badMergeCol );
        tryMerge( src, "tooManyCols" );
    }
    
    /**
     * Default constructor; not used.
     */
    private MergeDemo1()
    {
        // not used
    }
    
    /**
     * Attempt to merge an incompatible ImagePart
     * with the destination ImagePart.
     * This should raise an IllegalArgumentException
     * which will be caught, reported, then ignored.
     * If the exception is not throw
     * a diagnostic will be reported.
     * 
     * @param src   the incompatible ImagePart
     * @param name  the name of the incompatible ImagePart
     */
    private static void tryMerge( ImagePart src, String name )
    {
        ImagePart   dest    = copyDest();
        src.setName( name );
        try
        {
            dest.merge( src );
            System.out.println( src.getName() + " didn't fail" );
        }
        catch ( IllegalArgumentException exc )
        {
            System.out.println( exc.getMessage() );
        }
    }

    /**
     * Utility to make a copy of the destination ImagePart.
     * 
     * @return  a copy of the destination ImagePart
     */
    private static ImagePart copyDest()
    {
        String[]    strArr  = Arrays.copyOf( mergeDest, mergeDest.length );
        ImagePart   copy    = new ImagePart( strArr, 0, 0 );
        copy.setName( "Destination" );
        return copy;
    }
}
