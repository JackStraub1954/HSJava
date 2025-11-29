package com.judahstutorials.javaintro.hangman;

public class WordSelectorDemo
{
     public static void main( String[] args )
     {
         WordSelector   selector    = new WordSelector();
         String         word        = selector.select();
         boolean        done        = false;
         
         System.out.println( word );
         while ( !done )
         {
             System.out.println( "=================" );
             String guess   = selector.getGuess();
             if ( guess == null )
                 done = true;
             else
             {
                 String chars   = String.valueOf( selector.getAssembledChars() );
                 System.out.println( "Guess: " + guess );
                 System.out.println( "Chars: " + chars );
             }
         }
     }
}
