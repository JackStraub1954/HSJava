package com.judahstutorials.javaintro.towerofhanoi;

/**
 * Application that demonstrates how to create and manipulate
 * the Tower of Hanoi GUI.
 */
public class TowerDemo
{
    /**
     * Application entry point.
     * 
     * @param args  command-line arguments; not used
     */
    public static void main(String[] args)
    {
        Pitch       pitch   = Tower.play();
        TowerDemo   demo    = new TowerDemo();
        demo.execute( pitch );
    }

    /**
     * Default constructor.
     * Present here so that it can be documented,
     * because "Javadoc expects a comment for every constructor, 
     * including the default one."
     */
    public TowerDemo()
    {
    }
    
    /**
     * Executes the logic of this application.
     * @param pitch the Pitch that encapsulates the GUI
     */
    public void execute( Pitch pitch )
    {
        long    pauseFor    = 1000;
        Pitch.pause( pauseFor );
        Disk    disk    = pitch.pop( 0 );
        pitch.repaint();
        Pitch.pause( pauseFor );
        pitch.push( disk, 1 );
        pitch.repaint();
        Pitch.pause( pauseFor );
        disk = pitch.pop( 0 );
        pitch.repaint();
        Pitch.pause( pauseFor );
        pitch.push( disk, 2 );
        pitch.repaint();
        Pitch.pause( pauseFor );
        disk = pitch.pop( 1 );
        pitch.repaint();
        Pitch.pause( pauseFor );
        pitch.push( disk, 2 );
        pitch.repaint();
    }
}
