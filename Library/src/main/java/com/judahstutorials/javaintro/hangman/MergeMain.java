package com.judahstutorials.javaintro.hangman;

import java.util.Arrays;

public class MergeMain
{
    private final WordSelector  wordSelector    = new WordSelector();
    private final char[][]      feedback        = new char[2][];
    
    public static void main(String[] args)
    {
        

    }

    private void execute()
    {
    }
    
    public void selectHiddenWord()
    {
        String  selection   = wordSelector.select();
        if ( selection == null )
            System.exit( 0 );
        if ( selection != null )
        {
            int         wordLen     = selection.length();
            int         rowLen      = 4 * wordLen;
            feedback[0] = new char[rowLen];
            feedback[1] = new char[rowLen];
            Arrays.fill( feedback[0], ' ' );
            int         inx         = 0;
            while ( inx < rowLen )
            {
                feedback[1][inx++] = ' ';
                feedback[1][inx++] = '_';
                feedback[1][inx++] = '_';
                feedback[1][inx++] = '_';
            }
        }
    }
}
