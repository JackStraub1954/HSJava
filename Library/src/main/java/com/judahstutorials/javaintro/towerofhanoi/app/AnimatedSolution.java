package com.judahstutorials.javaintro.towerofhanoi.app;

import com.judahstutorials.javaintro.towerofhanoi.Pitch;
import com.judahstutorials.javaintro.towerofhanoi.Tower;
import com.judahstutorials.javaintro.towerofhanoi.utils.Animator;

/**
 * This is an application
 * that encapsulates a solution to the Tower of Hanoi puzzle
 * that employs the {@linkplain Animator} class
 * to animate the movement of disks
 * from one rod to another.
 * <p>
 * <b>Note:</b>
 * Students are encouraged to develop their own solution to the puzzle
 * before looking at the sample solution.
 * 
 * @see Animator
 */
public class AnimatedSolution
{
    /**
     * The Pitch that contains the puzzle graphics;
     */
    private Pitch pitch;
    
    /**
     * Application entry point.
     * 
     * @param args  Command-line arguments, not used.
     */
    public static void main(String[] args)
    {
        Pitch           pitch   = Tower.play();
        AnimatedSolution   demo    = new AnimatedSolution();
        demo.execute( pitch );
    }
    
    /**
     * Default constructor, not used.
     */
    public AnimatedSolution()
    {
        // not used
    }

    /**
     * This method is called to begin the solution.
     * 
     * @param pitch The Pitch on which the puzzle graphic is drawn.
     */
    public void execute( Pitch pitch )
    {
        this.pitch = pitch;
        int numDisks    = Tower.getNumDisks();
        moveDisk( numDisks, 0, 2, 1 );
    }
    
    /**
     * Recursive method to implement the puzzle solution.
     * 
     * @param num       the number of the disk to pop off the from rod
     * @param fromRod   the number of the rod to move from
     * @param toRod     the number of the rod to move to
     * @param auxRod    the number of the rod to use as an auxiliary
     * 
     * @see 
     *      <a href = "https://www.geeksforgeeks.org/dsa/c-program-for-tower-of-hanoi/">
     *          Program for Tower of Hanoi Algorithm
     *      </a>
     *      on the GeeksForGeeks website
     *      
     * @see
     *      <a href = "https://en.wikipedia.org/wiki/Tower_of_Hanoi">
     *          Tower of Hanoi
     *      </a>
     *      on Wikipedia
     */
    private void  
    moveDisk( int num, int fromRod, int toRod, int auxRod )
    {
        if ( num > 0 )
        {
            moveDisk(num - 1, fromRod, auxRod, toRod);
            Animator    animator    = new Animator( pitch, fromRod, toRod );
            animator.animate();
            printFeedback( num, fromRod, toRod, auxRod );
            pitch.repaint();
            moveDisk(num - 1, auxRod, toRod, fromRod);
        }
    }
    
    /**
     * Prints feedback regarding which disk (num)
     * to move from the source rod (fromRod)
     * to the destination rod (toRod).
     * <p>
     * This method is purely a visualization aid.
     * The encapsulated code could easily
     * be incorporated into the recursive method (moveDisk).
     * When stepping through the code, however,
     * we would then have to step through
     * the assembly of the feedback message.
     * As written,
     * printing the feedback in the recursive method
     * is a single line of code
     * and can be stepped-over.
     * 
     * @param num       the number of the disk to move
     * @param fromRod   the number of the rod to move from
     * @param toRod     the number of the rod to move to
     * @param auxRod    the number of the rod to use 
     *                  for the intermediate step
     */
    private void 
    printFeedback( int num, int fromRod, int toRod, int auxRod )
    {
        System.out.println(
            "Move disk " + num 
            + " from rod " + fromRod
            + " to rod " + toRod
        );
    }
} 