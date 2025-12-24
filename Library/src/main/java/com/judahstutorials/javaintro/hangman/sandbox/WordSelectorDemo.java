package com.judahstutorials.javaintro.hangman.sandbox;

import java.util.Arrays;

import com.judahstutorials.javaintro.hangman.GuessManager;
import com.judahstutorials.javaintro.hangman.WordSelector;

/**
 * This is a simple application 
 * that exercises the {@link WordSelector} class.
 * Allows the operator to select a word
 * and then prints it.
 */
public class WordSelectorDemo
{
     /**
      * Principal entry point.
      * 
     * @param args  command line arguements; not used
     */
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
    
    /**
     * Default constructor; not used.
     */
    public WordSelectorDemo()
    {
        // not used
    }
}
