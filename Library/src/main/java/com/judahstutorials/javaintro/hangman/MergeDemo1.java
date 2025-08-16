package com.judahstutorials.javaintro.hangman;

import java.util.Arrays;

public class MergeDemo1
{
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

    private static final int        tooManyColsRow  = 1;
    private static final int        tooManyColsCol  = 2;
    private static final String[]   tooManyCols     =
    {
     //            1    1
     //  ---5----0----5---
        "                 ",  // 1
        "                  ", // 2 *** too many cols
        "                 ",  // 3
        "                 ",  // 4
    };

    private static final int        tooManyRowsRow  = 2;
    private static final String[]   tooManyRows     =
    {
     //            1    1
     //  0----5----0----5---
        "                   ", // 2
        "                   ", // 3
        "                   ", // 4
        "                   ", // 5
    };
    
    private static final int        badMergeRow =   2;
    private static final int        badMergeCol =   3;
    private static final String[]   badMerge    =
    {
        //            1    1
        //  --5----0----5---
        "               |", // 2
        " *             |", // 3
        "               |", // 4
    };

    public static void main(String[] args)
    {
        ImagePart   src     = new ImagePart( tooManyRows, tooManyRowsRow, 0 );
        tryMerge( src, "TooManyRows" );
        
        src = new ImagePart( tooManyCols, tooManyColsRow, tooManyColsCol );
        tryMerge( src, "TooManyCols" );
        
        src = new ImagePart( badMerge, badMergeRow, badMergeCol );
        tryMerge( src, "TooManyCols" );
    }
    
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

    private static ImagePart copyDest()
    {
        String[]    strArr  = Arrays.copyOf( mergeDest, mergeDest.length );
        ImagePart   copy    = new ImagePart( strArr, 0, 0 );
        copy.setName( "Destination" );
        return copy;
    }
}
