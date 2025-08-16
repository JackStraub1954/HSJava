package com.judahstutorials.javaintro.hangman;

import javax.swing.JOptionPane;

public class MergeDemo2
{
    private static final String[]   finalImage    =
    {
     //            1    1
     //  0----5----0----5---
        "     +------------+",   // 0
        "     |            |",   // 1
        "     o            |",   // 2
        "    / \\           |",  // 3
        "   /   \\          |",  // 4
        "  | x x |         |",   // 5
        "  |  ^  |         |",   // 6
        "   \\ O /          |",  // 7
        "    \\ /           |",  // 8
        "     o            |",   // 9
        "    /o\\           |",  // 10
        "   / o \\          |",  // 11
        "  /  o  \\         |",  // 12
        "     o            |",   // 13
        "     o            |",   // 14
        "    / \\           |",  // 15
        "   /   \\          |",  // 16
        "  /     \\         |",  // 17
        " /       \\        |",  // 18
        "                  |",   // 19
        "                  |",   // 20
    };

    private static final int        gallowsInx  = 0;
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

    private static final int        nooseInx    = 1;
    private static final int        nooseRow    = 0;
    private static final int        nooseCol    = 0;
    private static final String[]   noose       =
    {
     //            1    1
     //  0----5----0----5---
        "     +",  // 10
        "     |",  // 1
        "     o",  // 2
    };

    private static final int        headRow     = 3;
    private static final int        headCol     = 0;
    private static final int        headInx     = 2;
    private static final String[]   head        =
    {
     //            1    1
     //  0----5----0----5---
        "    / \\  ",  // 3
        "   /   \\ ",  // 4
        "  | x x | ",   // 5
        "  |  ^  | ",   // 6
        "   \\ O / ",  // 7
        "    \\ /  ",  // 8
    };
    
    private static final int        bodyRow     = 9;
    private static final int        bodyCol     = 0;
    private static final int        bodyInx     = 3;
    private static final String[]   body        =
    {
     //            1    1
     //  0----5----0----5---
        "     o",  // 9
        "     o",  // 10
        "     o",  // 11 
        "     o",  // 12
        "     o",  // 13
        "     o",  // 14
    };
    
    private static final int        leftArmRow  = 10;
    private static final int        leftArmCol  = 0;
    private static final int        leftArmInx  = 4;
    private static final String[]   leftArm     =
    {
     //            1    1
     //  0----5----0----5---
        "    /",  // 10
        "   /",  //  11
        "  /",  //   12
    };
    
    private static final int        rightArmRow = 10;
    private static final int        rightArmCol = 0;
    private static final int        rightArmInx = 5;
    private static final String[]   rightArm    =
    {
     //            1    1
     //  0----5----0----5---
        "      \\",    // 10
        "       \\",   // 11
        "        \\",  // 12
    };
    
    private static final int        leftLegRow  = 15;
    private static final int        leftLegCol  = 0;
    private static final int        leftLegInx  = 6;
    private static final String[]   leftLeg     =
    {
     //            1    1
     //  0----5----0----5---
        "    /",  // 15
        "   /",   // 16
        "  /",    // 17
        " /",     // 18
    };

    private static final int        rightLegRow = 15;
    private static final int        rightLegCol = 0;
    private static final int        rightLegInx = 7;
    private static final String[]   rightLeg    =
    {
     //            1    1
     //  0----5----0----5---
        "      \\ ",  // 15
        "       \\",  // 16
        "        \\",  // 17
        "         \\",  // 18
    };
    
    private static final int    done            = 8;

    public static void main(String[] args)
    {
        ImagePart   dest        = new ImagePart( gallows, 0, 0 );
        ImagePart   nextPart    = null;
        int         next        = nooseInx;
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

    private static void print( ImagePart part )
    {
        char[][]    image   = part.getImage();
        System.out.println( "\033[H\033[2J" );
        for ( int inx = 0 ; inx < image.length ; ++inx )
            System.out.println( image[inx] );
    }
}
