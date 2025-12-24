package com.judahstutorials.javaintro.hangman;

import javax.swing.JOptionPane;

/**
 * Final implementation of Hangman
 * that uses escape sequences and Unicode characters
 * to produce the artwork.
 */
public class EscapedHangman
{
    /** For selecting the secret word. */
    private final WordSelector      selector;
    /** The secret word. */
    private final String            secretWord;
    /** The guess manager. */
    private final GuessManager      guessMgr;
    /** For producing artwork. */
    private final EscapedArtwork    artwork;

    /** Set to true when the game is over. */
    private boolean             inProgress  = false;
    
    /**
     * Principal entry point.
     * 
     * @param args  command line arguments; not used
     */
    public static void main( String[] args )
    {
        new EscapedHangman().play();
    }
    
    /**
     * Constructor.
     * Prepares the game to be played.
     */
    public EscapedHangman()
    {
        selector = new WordSelector();
        secretWord = selector.select();
        if ( secretWord == null )
        {
            guessMgr = null;
            artwork = null;
            inProgress = false;
        }
        else
        {
            guessMgr = new GuessManager( secretWord );
            char[]  assembledChars  = guessMgr.getAssembledChars();
            artwork = new EscapedArtwork();
            artwork.update( false, assembledChars );
            inProgress = true;
        }
    }
    
    /**
     * Begins game play.
     * The operator may have canceled the game
     * when selecting the secret word,
     * in which case the game will silently terminate.
     */
    public void play()
    {
        JOptionPane.showMessageDialog( null, secretWord );
        if ( secretWord != null )
            playGame();
    }
    
    /**
     * Plays the game,
     * prompting the player for input
     * until the game is won, lost, or canceled.
     */
    private void playGame()
    {
        boolean isCanceled  = false;
        boolean win = false;
        while ( inProgress && !win )
        {
            String  guess   = GuessManager.getGuess();
            if ( guess == null )
            {
                inProgress = false;
                isCanceled = true;
            }
            else
            {
                 boolean    status          = guessMgr.processGuess( guess );
                 char[]     assembledChars  = guessMgr.getAssembledChars();
                 inProgress = !artwork.update( status, assembledChars );
                 win = guessMgr.isWin();
            }
        }
        
        // Display final status unless the player canceled play.
        // If play was canceled, exit silently.
        if ( !isCanceled )
        {
            String  feedback    = win ? "You Win" : "You Lose";
            JOptionPane.showMessageDialog( null, feedback );
        }
    }
}
