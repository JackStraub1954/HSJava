package com.judahstutorials.javaintro.hangman.sandbox;

/**
 * This class is used to demonstrate
 * a very simple Hangman implementation.
 * It produces no artwork,
 * and uses a counter to determine
 * when the player has made too many mistakes.
 * It prints out the contents 
 * of an "assembledChars" array,
 * in which guessed characters represent themselves,
 * and unguessed characters are represented
 * by the nul character.
 * <p>
 * Using this application
 * we show how the core of a Hangman implementation
 * can be constructed,
 * interacting with the WordSelector and GuessManager classes,
 * and using this class
 * as a stand-in for a class
 * that produces artwork.
 * It's also a trial run
 * of the logic to print the content
 * of an <em>assembledChars</em> array
 * that will eventually be incorporated
 * into the classes that produce artwork.
 * 
 * @see SimpleHangman
 */
public class ProgressMonitor
{
    /**
     *  The maximum number of mistakes that a player can make 
     *  before the game is declared lost.
     */
    private static final int    maxTries    = 5;
    /**
     * The adjusted number of mistakes a player has left before losing
     * a game; decremented every time the player guesses incorrectly.
     */
    private int                 triesLeft   = maxTries;
    
    /**
     * Default constructor; not used.
     */
    public ProgressMonitor()
    {
        // not used
    }

    /**
     * Updates the progress of the game,
     * monitoring the number of mistakes made by the player,
     * and printing the assembledChars feedback.
     * The return value indicates whether the game
     * should be allowed to continue
     * (i.e., the player has not made the maximum number of mistakes).
     * 
     * @param status            true for a correct guess
     * @param assembledChars    reflection of the letters that the player
     *                          has so far guessed correctly
     * @return  true if the game should be allowed to continue
     */
    public boolean update( boolean status, char[] assembledChars )
    {
        if ( status )
        {
            System.out.print( "Correct: " );
        }
        else
        {
            System.out.print( "Incorrect: " );
            --triesLeft;
        }
        for ( char ccc : assembledChars )
        {
            char    toPrint = ccc;
            if ( ccc == 0 )
                toPrint = '_';
            System.out.print( "" + toPrint + ' ' );
        }
        System.out.println();
        boolean inProgress  = triesLeft > 0;
        return inProgress;
    }
}
