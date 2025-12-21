package com.judahstutorials.javaintro.hangman;

import java.util.Arrays;

public class WordSelectorDemo
{
     public static void main( String[] args )
     {
         WordSelector   selector    = new WordSelector();
         String         word        = selector.select();
         GuessManager   guessMgr    = null;
         String         guess       = "";
         
         System.out.println( "Selected word:" + word );
         if ( word != null )
             guessMgr = new GuessManager( word );
         else
             guess = null;
         while ( guess != null )
         {
             System.out.println( "=================" );
             guess = GuessManager.getGuess();
             if ( guess != null )
             {
                 boolean    res     = guessMgr.processGuess( guess );
                 char[]     chars   = guessMgr.getAssembledChars();
                 System.out.println( "Guess: " + guess + "( "  + res + ")" );
                 System.out.println( "Chars: " + Arrays.toString( chars ) );
             }
         }
     }
}
