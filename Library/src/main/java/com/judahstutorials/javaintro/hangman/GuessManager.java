package com.judahstutorials.javaintro.hangman;

import java.util.Arrays;

import javax.swing.JOptionPane;

/**
 * Encapsulates the logic that prompts the player
 * to make a guess,
 * which letters have been successfully guessed,
 * and whether the player has guessed the word.
 * Successful guesses are monitored via a char array
 * in which unguessed letters are represented by 0
 * and guessed letters represent themselves.
 */
public class GuessManager
{
    /** The string that's been selected for the player to guess. */
    private final String    selectedStr;
    /** The selected string converted to a char array. */
    private final char[]    selectedChars;
    /** Array filled with characters correctly guessed by the player. */
    private final char[]    assembledChars;
    /** The last guess made by the player. */
    private String          lastGuess           = "";
    /** The last result maintained by processing the player's guess. */
    private boolean         lastResult          = false;

    /**
     * Constructor.
     * 
     * @param selectedStr   
     *      the string that's been selected for the player to guess.
     */
    public GuessManager( String selectedStr )
    {
        this.selectedStr = selectedStr;
        selectedChars = selectedStr.toCharArray();
        assembledChars = new char[selectedChars.length];
        Arrays.fill( assembledChars, (char)0 );
    }

    /**
     * Compare a guess made by the player with the selected string.
     * If the guess consists of multiple characters:
     * <p style="padding-left=2em;">
     * The guess will be compared to the selected string.
     * If they are equal true will be returned,
     * else false will be returned.
     * <p>
     * If the guess consists of a single character:
     * <p style="padding-left=2em;">
     * The guess will be compared to the each character
     * in the selected string.
     * If a pair of characters match,
     * the corresponding element of the assembled string
     * will be updated with the guessed character.
     * True will be returned if the guessed character
     * matches any character in the selected string,
     * otherwise false will be returned.
     * 
     * @param guess the player's guess
     * 
     * @return  true if the player's guess was valid
     */
    public boolean processGuess( String guess )
    {
        lastGuess = guess;
        boolean rval    = false;
        int     len     = guess.length();
        if ( len == 0 )
            throw new Malfunction( "Cannot process an empty string" );
        if ( len > 1 )
        {
            if ( rval = selectedStr.equals( guess ) )
                System.arraycopy( 
                    selectedChars, 
                    0, 
                    assembledChars, 
                    0, 
                    selectedChars.length
                );
        }
        else
        {
            char    guessedChar = guess.charAt( 0 );
            for ( int inx = 0 ; inx < selectedChars.length ; ++inx )
            {
                if ( guessedChar == selectedChars[inx] )
                {
                    rval = true;
                    assembledChars[inx] = guessedChar;
                }
            }
        }
        lastResult = rval;
        return rval;
    }
    
    /**
     * Returns true if the player
     * has correctly guessed all the characters
     * in the selected string.
     * 
     * @return
     *      true if the player has correctly guessed all the characters
     *      in the selected string
     */
    public boolean isWin()
    {
        int     compare = Arrays.compare( selectedChars, assembledChars );
        boolean win     = compare == 0;
        return win;
    }

    /**
     * Gets the string that the player is trying to guess.
     * 
     * @return the string the player is trying to guess
     */
    public String getSelectedStr()
    {
        return selectedStr;
    }

    /**
     * Gets a copy of the array of chars
     * that the player has correctly guessed.
     * Elements of the array corresponding to 
     * correctly guessed characters
     * are filled with that character.
     * Elements of the array that have not been guessed
     * are filled with the nul character (0).
     * 
     * @return
     *      a copy of the array of chars
     *      that the player has correctly guessed
     */
    public char[] getAssembledChars()
    {
        int     len     = assembledChars.length;
        char[]  copy    = Arrays.copyOf( assembledChars, len );
        return copy;
    }

    /**
     * Gets the last guess passed to
     * {@linkplain #processGuess(String)}.
     * 
     * @return the last guess passed to processGuess
     */
    public String getLastGuess()
    {
        return lastGuess;
    }

    /**
     * Gets the last result obtained by calling
     * {@linkplain #processGuess(String)}.
     * 
     * @return the last result obtained by calling processGuess
     */
    public boolean isLastResult()
    {
        return lastResult;
    }
    
    /**
     * Convenient utility to prompt the player for a guess.
     * If the player enters an empty string
     * the prompt is repeated.
     * The guess is trimmed and converted to upper case.
     * The player may cancel the operation
     * in which case null is returned.
     * 
     * @return  the player's guess, or null if the operation is canceled.
     */
    public static String getGuess()
    {
        String  prompt  = "Enter your guess";
        String  guess   = "";
        while ( guess != null && guess.isEmpty() )
            guess = JOptionPane.showInputDialog( prompt );
        if ( guess != null )
            guess = guess.trim().toUpperCase();
        return guess;
    }
}
