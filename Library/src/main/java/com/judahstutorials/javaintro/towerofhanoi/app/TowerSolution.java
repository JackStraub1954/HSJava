package com.judahstutorials.javaintro.towerofhanoi.app;

import com.judahstutorials.javaintro.towerofhanoi.*;
public class TowerSolution
{
    private Pitch pitch;
    public static void main(String[] args)
    {
        Pitch           pitch   = Tower.play();
        TowerSolution   demo    = new TowerSolution();
        demo.execute( pitch );
    }

    public void execute( Pitch pitch )
    {
        this.pitch = pitch;
        int numDisks    = Tower.getNumDisks();
        moveDisk( numDisks, 0, 2, 1 );
    }
    
    private void  
    moveDisk( int num, int fromRod, int toRod, int auxRod )
    {
        if ( num > 0 )
        {
            moveDisk(num - 1, fromRod, auxRod, toRod);
            Disk    disk    = pitch.pop( fromRod );
            pitch.push( disk, toRod );
            printFeedback( num, fromRod, toRod, auxRod );
            pitch.repaint();
            Pitch.pause( 500 );
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