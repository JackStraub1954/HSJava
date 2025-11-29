package com.judahstutorials.javaintro.hangman;

import java.util.HashMap;

/**
 * Application to demonstrate 
 * the "escape sequence" image assembly technique
 * used in the sample code.
 * Represent each part of the final figure
 * (e.g. "gallows," "head," "left arm")
 * as a two-dimensional array of chars
 * and row/column properties
 * that describe the position of the part
 * on the screen, for example:
 * <pre>
 * int      nooseRow = 0;
 * int      nooseCol = 5;
 * String[] noose = {
 *   //       1    1
 *   //  5----0----5---
 *      "+",  // 10
 *      "|",  // 1
 *      "o",  // 2
 *  };</pre>
 * <p>
 * Across multiple steps,
 * use ANSI escape sequences to position the cursor
 * to the given row and column,
 * then print the encapsulated characters.
 * <p>
 * Note:
 * I tried improving the display using Unicode characters
 * from the box-drawing set.
 * This effort met with mixed results
 * and I disabled it.
 */
public class TextArtwork
{
    /**
     * Map of Unicode characters found on a keyboard to Unicode
     * characters from the box-drawing set.
     * 
     * @see AltChar
     */
    private static final AltChar    altCharMap  = new AltChar();
    /**
     * Top-margin of the final figure; specified in rows.
     */
    private static final int        topMargin   = 5;
    /**
     * Left-margin of the final figure; specified in columns.
     */
    private static final int        leftMargin  = 15;
    /**
     * Escape sequence to clear the screen.
     */
    private static final String     clear       = "\u001b[2J";
    /**
     * Format string for deriving the escape sequence to position
     * the cursor at a given row/column.
     * @see #print(ImagePart)
     */
    private static final String     position    = "\u001b[%d;%dH";
    /*
    private static final String[]   finalImage    =
    {
     //            1    1
     //  0----5----0----5---
        "     +------------+",  // 0
        "     |            |",  // 1
        "     o            |",  // 2
        "    / \           |",  // 3
        "   /   \          |",  // 4
        "  | x x |         |",  // 5
        "  |  ^  |         |",  // 6
        "   \ O /          |",  // 7
        "    \ /           |",  // 8
        "     o            |",  // 9
        "    /o\           |",  // 10
        "   / o \          |",  // 11
        "  /  o  \         |",  // 12
        "     o            |",  // 13
        "     o            |",  // 14
        "    / \           |",  // 15
        "   /   \          |",  // 16
        "  /     \         |",  // 17
        " /       \        |",  // 18
        "                  |",  // 19
        "                  |",  // 20
    };
    */

    /**
     * Row at which to begin drawing the gallows.
     */
    private static final int        gallowsRow  = 0;
    /**
     * Column at which to begin drawing the gallows.
     */
    private static final int        gallowsCol  = 0;
    /**
     * Number of the step in which the noose is drawn.
     */
    private static final int        gallowsInx  = 0;
    /**
     * Array to describe the gallows.
     */
    private static final String[]   gallows     =
    {
     //            1    1
     //  0----5----0----5---
        "      ------------+",
        "                  |",
        "                  |",
        "                  |",
        "                  |",
        "                  |",
        "                  |",
        "                  |",
        "                  |",
        "                  |",
        "                  |",
        "                  |",
        "                  |",
        "                  |",
        "                  |",
        "                  |",
        "                  |",
        "                  |",
        "                  |",
        "                  |",
        "                  |",
    };

    /**
     * Row at which to begin drawing the noose.
     */
    private static final int        nooseRow    = 0;
    /**
     * Column at which to begin drawing the noose.
     */
    private static final int        nooseCol    = 5;
    /**
     * Number of the step in which the noose
     * is drawn on the screen.
     */
    private static final int        nooseInx    = 1;
    /**
     * Array that describes the noose in the final image.
     */
    private static final String[]   noose       =
    {
     //       1    1
     //  5----0----5---
        "+",  // 10
        "|",  // 1
        "o",  // 2
    };

    /**
     * Row at which to begin drawing the head.
     */
    private static final int        headRow     = 3;
    /**
     * Column at which to begin drawing the head.
     */
    private static final int        headCol     = 2;
    /**
     * Number of the step in which the head is drawn.
     */
    private static final int        headInx     = 2;
    /**
     * Array that describes the head in the final image.
     */
    private static final String[]   head        =
    {
     //          1    1
     //  2--5----0----5--- 
        "  / \\  ",  // 3
        " /   \\ ",  // 4
        "| x x | ",   // 5
        "|  ^  | ",   // 6
        " \\ O / ",  // 7
        "  \\ /  ",  // 8
    };
    
    /**
     * Row at which to begin drawing the body.
     */
    private static final int        bodyRow     = 9;
    /**
     * Column at which to begin drawing the body.
     */
    private static final int        bodyCol     = 5;
    /**
     * Number of the step in which the body is drawn
     */
    private static final int        bodyInx     = 3;
    /**
     * Array that describes the body in the final image.
     */
    private static final String[]   body        =
    {
     //       1    1
     //  5----0----5---
        "o",  // 9
        "o",  // 10
        "o",  // 11 
        "o",  // 12
        "o",  // 13
        "o",  // 14
    };
    
    /**
     * Row at which to begin drawing the left arm.
     */
    private static final int        leftArmRow  = 10;
    /**
     * Column at which to begin drawing the left arm.
     */
    private static final int        leftArmCol  = 2;
    /**
     * Number of the step in which the left arm is drawn.
     */
    private static final int        leftArmInx  = 4;
    /**
     * Array that describes the left arm in the final image.
     */
    private static final String[]   leftArm     =
    {
     //          1    1
     //  2--5----0----5---
        "  /",  // 10
        " /",   //  11
        "/",    //   12
    };
    
    /**
     * Row at which to begin drawing the right arm.
     */
    private static final int        rightArmRow = 10;
    /**
     * Column at which to begin drawing the right arm.
     */
    private static final int        rightArmCol = 6;
    /**
     * Number of the step in which the right arm is drawn.
     */
    private static final int        rightArmInx = 5;
    /**
     * Array that describes the right arm in the final image.
     */
    private static final String[]   rightArm    =
    {
     //      1    1
     //  6 ---0----5---
        "\\",    // 10
        " \\",   // 11
        "  \\",  // 12
    };
    
    /**
     * Row at which to begin drawing the left leg.
     */
    private static final int        leftLegRow  = 15;
    /**
     * Column at which to begin drawing the left leg.
     */
    private static final int        leftLegCol  = 1;
    /**
     * Number of the step in which the left leg is drawn.
     */
    private static final int        leftLegInx  = 6;
    /**
     * Array that describes the left leg in the final image.
     */
    private static final String[]   leftLeg     =
    {
     //           1    1
     //  1---5----0----5---
        "   /",  // 15
        "  /",   // 16
        " /",    // 17
        "/",     // 18
    };

    /**
     * Row at which to begin drawing the right leg.
     */
    private static final int        rightLegRow = 15;
    /**
     * Column at which to begin drawing the right leg.
     */
    private static final int        rightLegCol = 6;
    /**
     * Number of the step in which the right leg is drawn.
     */
    private static final int        rightLegInx = 7;
    /**
     * Array that describes the right leg in the final image.
     */
    private static final String[]   rightLeg    =
    {
     //      1    1
     //  6 ---0----5---
        "\\ ",   // 15
        " \\",   // 16
        "  \\",  // 17
        "   \\", // 18
    };
    
    /**
     * Number of the last step in the process.
     */
    private static final int    done            = 8;
    
    private int     next        = 0;
    
    /**
     * Default constructor; not used.
     */
    private TextArtwork()
    {
        // not used
    }
    
    public boolean isComplete()
    {
        boolean complete    = next >= done;
        return complete;
    }
    
    public ImagePart nextPart()
    {
        ImagePart   nextPart    = getPart( next++ );
        return nextPart;
    }
    
    public ImagePart getPart( int partNum )
    {
        ImagePart   nextPart    = null;
        switch ( partNum )
        {
        case gallowsInx:
            nextPart = new ImagePart( gallows, gallowsRow, gallowsCol );
            nextPart.setName( "gallows" );
            break;
        case nooseInx:
            nextPart = new ImagePart( noose, nooseRow, nooseCol );
            nextPart.setName( "noose" );
            break;
        case headInx:
            nextPart = new ImagePart( head, headRow, headCol );
            nextPart.setName( "head" );
            break;
        case bodyInx:
            nextPart = new ImagePart( body, bodyRow, bodyCol );
            nextPart.setName( "body" );
            break;
        case leftArmInx:
            nextPart = new ImagePart( leftArm, leftArmRow, leftArmCol );
            nextPart.setName( "leftArm" );
            break;
        case rightArmInx:
            nextPart = new ImagePart( rightArm, rightArmRow, rightArmCol );
            nextPart.setName( "rightArm" );
            break;
        case leftLegInx:
            nextPart = new ImagePart( leftLeg, leftLegRow, leftLegCol );
            nextPart.setName( "leftLeg" );
            break;
        case rightLegInx:
            nextPart = new ImagePart( rightLeg, rightLegRow, rightLegCol );
            nextPart.setName( "rightLeg" );
            break;
        }
        
        return nextPart;
    }
    
    /**
     * In the array encapsulated in the given ImagePart,
     * attempt to improve the drawing by substituting
     * Unicode box-drawing characters
     * for keyboard characters
     * initially used to format the character array.
     * 
     * @param part  the given ImagePart
     * 
     * @see AltChar
     */
    @SuppressWarnings("unused")
    private static void refine( ImagePart part )
    {
        char[][]    image   = part.getImage();
        for ( char[] row : image )
        {
            for ( int inx = 0 ; inx < row.length ; ++inx )
                row[inx] = altCharMap.get( row[inx] );
        }
    }

    /**
     * Draw the given ImagePart on the screen.
     * 
     * @param part  the given ImagePart
     */
    private static void print( ImagePart part )
    {
        char[][]    image   = part.getImage();
        int         col     = part.getColumn();
        int         row     = part.getRow();
        for ( int inx = 0 ; inx < image.length ; ++inx )
        {
            setCursor( row++, col );
            System.out.print( image[inx] );
        }
    }
    
    /**
     * Clear the screen.
     */
    private static void clearScreen()
    {
        System.out.print( clear );
    }
    
    /**
     * Position the cursor 
     * at the given row and column coordinates
     * on the screen.
     * 
     * @param row   the given row-coordinate
     * @param col   the given column-coordinate
     */
    private static void setCursor( int row, int col )
    {
        int     xco     = col + leftMargin;
        int     yco     = row + topMargin;
        String  command = String.format( position, yco, xco );
        System.out.print( command );
    }
    
    /**
     * Encapsulate a table
     * that maps a keyboard character
     * to a Unicode character
     * from the box-drawing set.
     */
    @SuppressWarnings("serial")
    private static class AltChar extends HashMap<Character,Character>
    {
        /**
         * The Unicode character for the upper side of a box.
         */
        private static final char   boxTop      = '\u2500';
        /**
         * The Unicode character for the left or right side of a box.
         */
        private static final char   boxSide     = '\u2502';
        /**
         * The Unicode character for the upper-left corner of a box.
         */
        private static final char   ulCorner    = '\u250c';
        /**
         * The Unicode character for the upper-right corner of a box.
         */
        private static final char   urCorner    = '\u2510';
        /**
         * The Unicode character for a right slant.
         */
        private static final char   rightSlant  = '\u2571';
        /**
         * The Unicode character for a left slant.
         */
        private static final char   leftSlant   = '\u2572';
        
        /**
         * Constructor.
         * Fully initialize the encapsulated map.
         */
        public AltChar()
        {
            put( '-', boxTop );
            put( '|', boxSide );
            put( '+', ulCorner );
            put( '%', urCorner );
            put( '/', rightSlant );
            put( '\\', leftSlant );
        }
        
        /**
         * Gets the character to substitute for the given character.
         * If none, returns the given character.
         * 
         * @param key   the given character
         * 
         * @return  character to substitute for the given character,
         *          or the given character if no substitute is found.
         */
        public Character get( Character key )
        {
            Character   charOut = super.get( key );
            if ( charOut == null )
                charOut = key;
            return charOut;
        }
    }
}
