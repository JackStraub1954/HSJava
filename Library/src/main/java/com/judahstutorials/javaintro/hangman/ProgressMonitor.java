package com.judahstutorials.javaintro.hangman;

public class ProgressMonitor
{
    private static final int    maxTries    = 5;
    private int                 triesLeft   = maxTries;
    
    public ProgressMonitor()
    {
        // TODO Auto-generated constructor stub
    }

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
//        for ( char ccc : assembledChars )
        for ( int inx = 0 ; inx < assembledChars.length ; ++inx )
        {
            char    ccc     = assembledChars[inx];
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
