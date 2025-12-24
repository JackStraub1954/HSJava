package com.judahstutorials.javaintro.hangman.sandbox;

import com.judahstutorials.javaintro.hangman.GuessManager;
import com.judahstutorials.javaintro.hangman.WordSelector;

/**
 * A simple implementation of the Hangman game
 * that produces no artwork.
 * Any Hangman game can be written
 * using this class as a template,
 * and substituting a class that produced artwork
 * for {@link ProgressMonitor}.
 * 
 * @see ProgressMonitor
 */
public class SimpleHangman
{
    /** For selecting the secret word. */
    private final WordSelector      selector;
    /** The secret word. */
    private final String            secretWord;
    /** For GuessManager. */
    private final GuessManager      guessMgr;
    /** Stand-in for the class that produces artwork. */
    private final ProgressMonitor   monitor;

    /** Set to true when the game is over. */
    private boolean             inProgress  = false;
    
    /**
     * Application entry point.
     * 
     * @param args  command line arguments; not used
     */
    public static void main( String[] args )
    {
        new SimpleHangman().play();
    }
    
    /**
     * Constructor.
     * Sets the stage for playing the game.
     * 
     * @see #play()
     */
    public SimpleHangman()
    {
        selector = new WordSelector();
        monitor = new ProgressMonitor();
        secretWord = selector.select();
        if ( secretWord == null )
        {
            guessMgr = null;
            inProgress = false;
        }
        else
        {
            guessMgr = new GuessManager( secretWord );
            inProgress = true;
        }
    }
    
    /**
     * Called by the client to begin execution
     * of the game.
     * Silently exits if the operator
     * has canceled play.
     */
    public void play()
    {
        System.out.println( secretWord );
        if ( secretWord != null )
            playGame();
    }
    
    /**
     * Main driver for the game.
     * Prompts the player for input
     * until the game has been won, lost, or canceled.
     */
    private void playGame()
    {
        boolean win = false;
        while ( inProgress && !win )
        {
            String  guess   = GuessManager.getGuess();
            if ( guess == null )
                inProgress = false;
            else
            {
                 boolean    status          = guessMgr.processGuess( guess );
                 char[]     assembledChars  = guessMgr.getAssembledChars();
                 inProgress = monitor.update( status, assembledChars );
                 win = guessMgr.isWin();
            }
        }
    }
}
