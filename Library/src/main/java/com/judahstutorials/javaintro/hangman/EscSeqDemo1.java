package com.judahstutorials.javaintro.hangman;

/**
 * Application to demonstrate how escape sequences
 * can used to clear the display,
 * and position the cursor at a given (column,row) position
 * on the display.
 */
public class EscSeqDemo1
{
    /**
     * Application entry point.
     *  
     * @param args  command-line arguments; not used
     */
    public static void main(String[] args)
    {
        // Clear the screen
        System.out.print( "\u001b[2J" );
        // Position the cursor at row 10, column 15
        System.out.print( "\u001b[10;15H" );
        // Display "World"
        System.out.print( "World" );
        // Position the cursor at row 9, column 15
        System.out.print( "\u001b[9;15H" );
        // Display "Hello"
        System.out.print( "Hello" );
        // Position the cursor at row 24, column 0
        System.out.print( "\u001b[24;0H" );
    }
    
    /**
     * Default constructor; not used.
     */
    private EscSeqDemo1()
    {
        // not used
    }
}
