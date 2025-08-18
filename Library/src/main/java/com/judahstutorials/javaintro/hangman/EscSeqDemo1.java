package com.judahstutorials.javaintro.hangman;

/**
 * Application to demonstrate how escape sequences
 * can used to clear the display,
 * and position the cursor at a given position
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
        System.out.print( "\u001b[2J" );
        System.out.print( "\u001b[10;15H" );
        System.out.print( "World" );
        System.out.print( "\u001b[9;15H" );
        System.out.print( "Hello" );
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
