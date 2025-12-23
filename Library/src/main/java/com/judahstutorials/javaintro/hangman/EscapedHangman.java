package com.judahstutorials.javaintro.hangman;

public class EscapedHangman
{
    private final WordSelector      selector;
    private final String            secretWord;
    private final GuessManager      guessMgr;
    private final ProgressMonitor   monitor;

    private boolean             inProgress  = false;
    
    public static void main( String[] args )
    {
        new EscapedHangman().play();
    }
    
    public EscapedHangman()
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
    
    public void play()
    {
        System.out.println( secretWord );
        if ( secretWord != null )
            play( secretWord );
    }
    
    private void play( String toGuess )
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
