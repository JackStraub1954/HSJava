package com.judahstutorials.javaintro.towerofhanoi.sandbox;

import com.judahstutorials.javaintro.towerofhanoi.Pitch;
import com.judahstutorials.javaintro.towerofhanoi.Tower;
import com.judahstutorials.javaintro.towerofhanoi.app.TowerSolution;
import com.judahstutorials.javaintro.towerofhanoi.utils.Animator;

public class AnimationDemo
{

    public AnimationDemo()
    {
        // TODO Auto-generated constructor stub
    }

    private Pitch pitch;
    public static void main(String[] args)
    {
        Pitch           pitch   = Tower.play();
        AnimationDemo   demo    = new AnimationDemo();
        demo.execute( pitch );
    }

    public void execute( Pitch pitch )
    {
        this.pitch = pitch;
        Animator    animator    = new Animator( pitch, 0, 2 );
        animator.animate();
        animator    = new Animator( pitch, 0, 1 );
        animator.animate();
        animator    = new Animator( pitch, 2, 1 );
        animator.animate();
    }
}
