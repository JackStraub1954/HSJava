package com.judahstutorials.javaintro.hangman;

public class TextLayout
{
    private static final String[]   rows    =
    {
     //            1    1
     //  0----5----0----5---
        "     +------------+",
        "     |            |",
        "     o            |",
        "    / \\           |",
        "   /   \\          |",
        "  | x x |         |",
        "  |  ^  |         |",
        "   \\ O /          |",
        "    \\ /           |",
        "     o            |",
        "    /o\\           |",
        "   / o \\          |",
        "  /  o  \\         |",
        "     o            |",
        "     o            |",
        "    / \\           |",
        "   /   \\          |",
        "  /     \\         |",
        " /       \\        |",
        "                  |",
        "                  |",
    };
    
    public static void main( String[] args )
    {
        for ( String row : rows )
            System.out.println( row );
        System.out.println();
    }
}
