package com.judahstutorials.javaintro.towerofhanoi.app;

import com.judahstutorials.javaintro.towerofhanoi.Pitch;
import com.judahstutorials.javaintro.towerofhanoi.Tower;
import com.judahstutorials.javaintro.towerofhanoi.utils.Animator;

/**
 * This application shows how to use the Animator class.
 * It animates the transfer of the first two disks
 * from the first rod to the second rod.
 * It is not a solution to the Tower of Hanoi puzzle.
 * 
 * @see Animator
 * @see #execute(Pitch)
 */
public class AnimationDemo
{

    /**
     * Default constructor, not used.
     */
    public AnimationDemo()
    {
        // not used
    }

    /**
     * Application entry point.
     * 
     * @param args  command-line arguments, not used
     */
    public static void main(String[] args)
    {
        Pitch           pitch   = Tower.play();
        AnimationDemo   demo    = new AnimationDemo();
        demo.execute( pitch );
    }

    /**
     * This method shows how to instantiate and activate an Animator.
     * It is not a solution to the Tower of Hanoi puzzle;
     * it just moves a couple of disks around.
     * 
     * @param pitch the Pitch where the animation is to take place
     */
    public void execute( Pitch pitch )
    {
        // Instantiate an Animator to move a disk
        // from rod 0 to rod 2.
        Animator    animator    = new Animator( pitch, 0, 2 );
        animator.animate();

        // Instantiate an Animator to move a disk
        // from rod 0 to rod 1.
        animator    = new Animator( pitch, 0, 1 );
        animator.animate();

        // Instantiate an Animator to move a disk
        // from rod 2 to rod 1.
        animator    = new Animator( pitch, 2, 1 );
        animator.animate();
    }
}
