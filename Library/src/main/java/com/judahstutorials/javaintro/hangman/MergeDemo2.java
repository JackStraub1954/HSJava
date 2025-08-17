package com.judahstutorials.javaintro.hangman;

import javax.swing.JOptionPane;

/**
 * Application to demonstrate the image assembly technique
 * used in the sample code.
 * Start with a two-dimensional array of chars
 * which will eventually hold the final figure.
 * Initialize the starter array with
 * the horizontal and vertical parts of the gallows;
 * all other elements of the array are 
 * initialized to spaces.
 * For each remaining part of the image
 * make a two-dimensional array of chars
 * that hold the characters that describe that part.
 * Across multiple steps,
 * gradually merge each individual part
 * into the array that holds the final figure.
 */
public class MergeDemo2
{
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

//    private static final int        gallowsInx  = 0;
//    private static final int        gallowsRow  = 0;
//    private static final int        gallowsCol  = 0;
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
     * Row at which to merge the noose
     * into the final image.
     */
    private static final int        nooseRow    = 0;
    /**
     * Column at which to merge the noose
     * into the final image.
     */
    private static final int        nooseCol    = 5;
    /**
     * Number of the step in which the noose
     * is merged into the final image.
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
     * Row at which to merge the head
     * into the final image.
     */
    private static final int        headRow     = 3;
    /**
     * Column at which to merge the head
     * into the final image.
     */
    private static final int        headCol     = 2;
    /**
     * Number of the step in which the head
     * is merged into the final image.
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
     * Row at which to merge the body
     * into the final image.
     */
    private static final int        bodyRow     = 9;
    /**
     * Column at which to merge the body
     * into the final image.
     */
    private static final int        bodyCol     = 5;
    /**
     * Number of the step in which the body
     * is merged into the final image.
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
     * Row at which to merge the left arm
     * into the final image.
     */
    private static final int        leftArmRow  = 10;
    /**
     * Column at which to merge the left arm
     * into the final image.
     */
    private static final int        leftArmCol  = 2;
    /**
     * Number of the step in which the left arm
     * is merged into the final image.
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
     * Row at which to merge the right arm
     * into the final image.
     */
    private static final int        rightArmRow = 10;
    /**
     * Column at which to merge the right arm
     * into the final image.
     */
    private static final int        rightArmCol = 6;
    /**
     * Number of the step in which the right arm
     * is merged into the final image.
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
     * Row at which to merge the left leg
     * into the final image.
     */
    private static final int        leftLegRow  = 15;
    /**
     * Column at which to merge the left leg
     * into the final image.
     */
    private static final int        leftLegCol  = 1;
    /**
     * Number of the step in which the left leg
     * is merged into the final image.
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
     * Row at which to merge the right leg
     * into the final image.
     */
    private static final int        rightLegRow = 15;
    /**
     * Column at which to merge the right leg
     * into the final image.
     */
    private static final int        rightLegCol = 6;
    /**
     * Number of the step in which the right leg
     * is merged into the final image.
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
    
    /**
     * Default constructor; not used.
     */
    private MergeDemo2()
    {
        // not used
    }

    /**
     * Application entry point.
     * 
     * @param args  command-line arguments; not used
     */
    public static void main(String[] args)
    {
        ImagePart   dest        = new ImagePart( gallows, 0, 0 );
        ImagePart   nextPart    = null;
        int         next        = nooseInx;
        print( dest );
        while ( next < done )
        {
            JOptionPane.showMessageDialog( null, "Next" );
            switch ( next )
            {
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
            dest.merge( nextPart );
            print( dest );
            ++next;
        }
    }

    /**
     * Draw the image that has been so far assembled
     * on the console.
     * 
     * @param part  the image to draw
     */
    private static void print( ImagePart part )
    {
        char[][]    image   = part.getImage();
        System.out.println( "\033[H\033[2J" );
        for ( int inx = 0 ; inx < image.length ; ++inx )
            System.out.println( image[inx] );
    }
}
