package com.judahstutorials.javaintro.dawkinsweasel;

import java.util.Arrays;

/**
 * This is a sample program to implement the algorithm 
 * proposed by Richard Dawkins
 * for simulating evolution.
 * See the package description
 * which is linked at the top of this page.
 */
public class DawkinsWeasel
{
    /**
     * Number of children born in a generation.
     */
    private static final int        childrenPerGeneration   = 100;
    /**
     * How often a gene mutates.
     */
    private static final double     rateOfMutation          = .05;
    /**
     * Target string (METHINKS IT IS LIKE A WEASEL).
     */
    private static final String     target                  = 
        "METHINKS IT IS LIKE A WEASEL";
    /**
     * Target string expressed as a char array.
     */
    private static final char[]     targetArr               = 
        target.toCharArray();
    /**
     * Number of genes (the number of characters in the target string).
     */
    private static final int        numberOfGenes           = targetArr.length;
    
    /**
     * The child selected from the most recent generation.
     */
    private char[]  selected    = getStartingSequence();
    
    /**
     * Application entry point.
     * 
     * @param args  command-line arguments; not used
     */
    public static void main(String[] args)
    {
        DawkinsWeasel   game    = new DawkinsWeasel();
        game.execute();
    }
    
    /**
     * Default constructor.
     * It doesn't do any thing.
     * It is documented here because 
     * "... good documentation practice 
     * suggests providing a comment 
     * to explain its purpose (or lack thereof) 
     * in the context of the class's API."
     */
    public DawkinsWeasel()
    {
        
    }
    
    /**
     * Play the game, display the final result.
     * Print every tenth selected child.
     */
    private void execute()
    {
        int generation  = -1;
        while ( !Arrays.equals( selected, targetArr ) )
        {
            ++generation;
            selected = selectChild();
            if ( generation % 10 == 0 )
                printChild( generation, selected );
        }
        printChild( generation, selected );
    }
    
    /**
     * Generate a generation of children.
     * Selected the child of the generation
     * that most closely matches the desired result.
     * 
     * @return  the child of the generation
     *          that most closely matches the desired result
     */
    private char[] selectChild()
    {
        char[]  candidate   = new char[numberOfGenes];
        int     matches     = -1;
        for ( int inx = 0 ; inx < childrenPerGeneration ; ++inx )
        {
            char[]  child           = calculateChild();
            int     childMatches    = compare( child );
            if ( childMatches > matches )
            {
                candidate = child;
                matches = childMatches;
            }
        }
        return candidate;
    }
    
    /**
     * From the currently selected child
     * derive a new child with a percentage of its genes
     * mutated to a random value.
     * 
     * @return  the new child
     */
    private char[] calculateChild()
    {
        char[]  child   = Arrays.copyOf( selected, numberOfGenes );
        for ( int inx = 0 ; inx < numberOfGenes ; ++inx )
            if ( shouldMutate() )
                child[inx] = getRandomChar();
        return child;
    }
    
    /**
     * Decide whether a gene should mutate.
     * 
     * @return  true if a gene should mutate.
     */
    private boolean shouldMutate()
    {
        boolean mutate  = Math.random() < rateOfMutation;
        return mutate;
    }
    
    /**
     * Calculate the first child
     * as a sequence of randomly generated characters.
     * 
     * @return  a randomly generated child
     */
    private char[] getStartingSequence()
    {
        int     numChars    = target.length();
        char[]  sequence    = new char[numChars];
        for ( int inx = 0 ; inx < numChars ; ++inx )
            sequence[inx] = getRandomChar();
        return sequence;
    }
    
    /**
     * Compare a given child to the desired result.
     * Return the number of the child's genes
     * that match.
     * 
     * @param child the given child
     * 
     * @return  the number of the child's genes that match the desired result
     */
    private int compare( char[] child )
    {
        int matches = 0;
        for ( int inx = 0 ; inx < numberOfGenes ; ++inx )
            if ( child[inx] == targetArr[inx] )
                ++matches;
        return matches;
    }

    /**
     * Gets a randomly selected character that will be
     * either a capital letter or a space.
     * 
     * @return  randomly selected character that will be
     *          either a capital letter or a space
     */
    private char getRandomChar()
    {
        // get an integer between 0 and 26.
        int     num     = (int)(Math.random() * 27);
        
        // convert to a character; 0 maps to A, 25 maps to Z
        // and 26 maps to space.
        char    result  = num == 26 ? ' ' : (char)('A' + num );
        return result;
    }
    
    /**
     * Prints a diagnostic message indicating
     * the given child and the generation that it belongs to.
     * 
     * @param generation    the generation the given child belongs to
     * @param child         the given child
     */
    private void printChild( int generation, char[] child )
    {
        String  str         = new String( child );
        String  feedback    = 
            String.format( "Generation %3d: %s", generation, str );
        System.out.println( feedback );
    }
}
