package com.judahstutorials.javaintro.towerofhanoi.app;

import com.judahstutorials.javaintro.towerofhanoi.Disk;
import com.judahstutorials.javaintro.towerofhanoi.Pitch;
import com.judahstutorials.javaintro.towerofhanoi.Tower;

/**
 * <p>
 * Application that demonstrates how to create and manipulate
 * the Tower of Hanoi GUI.
 * It does not implement a solution
 * to the Tower of Hanoi puzzle.
 * Via the <i>main</i> method,
 * which instantiates and displays the GUI,
 * and the <i>execute</i> method,
 * which executes the application logic
 * It demonstrates how to use
 * the following components
 * of the Tower API.
 * </p>
 * <h2>The Tower Class</h2>
 * <p>
 * This class encapsulates the Tower of Hanoi game.
 * It has one important method:
 * </p>
 * <ul>
 *      <li>
 *          <strong>public static Pitch play()</strong><br>
 *          The <em>play</em> method
 *          creates and displays the game GUI.
 *          It returns a <em>Pitch</em> object
 *          which provides access to 
 *          the game's rods and disks.
 *      </li>
 * </ul>
 * <h2>The Pitch Class</h2>
 * <p>
 * This class provides acess to the rods and disks
 * of the Tower of Hanoi game.
 * The game's three rods are numbered,
 * from left to right,
 * 0, 1, and 2.
 * At the initiation of the game,
 * all the disks are stacked on rod 0.
 * The disks are numbered,
 * from top to bottom,
 * 0 through <em>n - 1,</em>
 * where <em>n</em> is the number of disks
 * allocated to the game.
 * Its most important methods are:
 * </p>
 * <ul>
 *      <li>
 *          <strong>public Disk pop( int rodNum )</strong><br>
 *          <strong>public void push( Disk disk, int rodNum )</strong><br>
 *          The <em>pop</em> method removes the disk
 *          from the top of the rod with the given number;
 *          the <em>push</em> method adds the given disk
 *          to the top of the rod with the given number.
 *          To move a disk from rod 0 to rod 2,
 *          you would use logic like this:<br>
 *          <span style="
 *              font-family: 'Lucida Console', 'Courier New', monospace;
 *          ">
 *              &nbsp;&nbsp;&nbsp;&nbsp;Disk    disk    = pitch.pop( 0 );<br>
 *              &nbsp;&nbsp;&nbsp;&nbsp;pitch.push( disk, 2 );
 *          </span>
 *      </li>
 *      <li>
 *          <strong>public void repaint()</strong><br>
 *          <strong>public static void pause( long millis )</strong><br>
 *          The <em>repaint</em> method 
 *          prompts Java to redraw the GUI;
 *          you will likely want to call this method
 *          after popping or pushing a disk.
 *          The <em>pause</em> method
 *          halts execution of your program
 *          for the given number of milliseconds.
 *          You can perform a simple animation
 *          of the movement of a disk
 *          from rod 0 to rod 2 like this:<br>
 *          <span style="
 *              font-family: 'Lucida Console', 'Courier New', monospace;
 *          ">
 *              &nbsp;&nbsp;&nbsp;&nbsp;Disk    disk    = pitch.pop( 0 );<br>
 *              &nbsp;&nbsp;&nbsp;&nbsp;pitch.repaint();<br>
 *              &nbsp;&nbsp;&nbsp;&nbsp;Pitch.pause( 1000 );<br>
 *              &nbsp;&nbsp;&nbsp;&nbsp;pitch.push( disk, 2 );
 *          </span>
 *      </li>
 * </ul>
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
