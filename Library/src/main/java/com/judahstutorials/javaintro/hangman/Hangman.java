package com.judahstutorials.javaintro.hangman;

public class Hangman
{
    private final   WordSelector    selector;
    private         String          secretWord;
    private         String          guess;
    private         boolean         inProgress;
    
    public Hangman()
    {
        selector = new WordSelector();
    }
    
    public void play()
    {
        secretWord = selector.select();
        inProgress  = secretWord != null;
        while ( inProgress )
        {
            guess = selector.getGuess();
            inProgress = showStatus();
        }
    }
    
    public boolean showStatus()
    {
        boolean rcode   = false;
        if ( secretWord.equals( guess ) )
            rcode = true;
        else if ( guess.length() == 1 && secretWord.contains( guess ) )
            rcode = true;
        else
            rcode = false;
        return rcode;
    }
}
