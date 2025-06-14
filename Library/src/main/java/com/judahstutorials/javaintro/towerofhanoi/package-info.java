/**
 * This package supports GUI control
 * for the suggested Tower of Hanoi project.
 * The class <em>Disk</em> represents a disk on a rod.
 * You can access a disk at the top of a rod,
 * and push a disk onto the top of a rod.
 * The principal class of interest to the student
 * is <em>Pitch.</em>
 * This class contains the code to build and manipulate the GUI.
 * You get a <em>Pitch</em> object
 * by calling the class method <em>Tower.play()</em>.
 * Once you have it,
 * you can manipulate your GUI
 * using its methods,
 * the principal ones being:
 * <dl>
 *      <dt><strong>public Disk pop( int rodNum )</strong></dt>
 *      <dd>
 *          Remove the disk at the top of the rod
 *          numbered <em>rodNum</em>,
 *          where the first rod number is 0.
 *          Returns null if the rod is empty.
 *      </dd>
 *      <dt><strong>public Disk peek( int rodNum )</strong></dt>
 *      <dd>
 *          Get the disk at the top of the rod
 *          numbered <em>rodNum</em>
 *          without removing it.
 *          Returns null if the rod is empty.
 *      </dd>
 *      <dt><strong>public void push( Disk disk, int rodNum )</strong></dt>
 *      <dd>
 *          Push the given disk onto the top of the rod
 *          numbered <em>rodNum.</em>
 *      </dd>
 *      <dt><strong>public static void pause( long milliseconds )</strong></dt>
 *      <dd>
 *          Pause execution for the given number of milliseconds.
 *          This enables you to perform simple animations.
 *      </dd>
 *      <dt><strong>public void repaint()</strong></dt>
 *      <dd>
 *          After you make changes to the GUI
 *          you must call this method
 *          in order for the changes to be displayed.
 *      </dd>
 * </dl>
 * <p>
 * Following is the code from the application <em>TowerDemo</em>
 * which can be found in the <em>sandbox</em> package.
 * After creating a <em>Pitch,</em> this application:
 * <ol>
 *      <li>Pauses for one second.</li>
 *      <li>
 *          Removes the disk from the top of rod 0
 *          and pauses for one second.
 *      </li>
 *      <li>
 *          Puts the disk at the top of rod 1
 *          and pauses for one second.
 *      </li>
 *      <li>
 *          Removes the disk from the top of rod 0
 *          and pauses for one second.
 *      </li>
 *      <li>
 *          Puts the disk at the top of rod 2
 *          and pauses for one second.
 *      </li>
 *      <li>
 *          Removes the disk from the top of rod 1
 *          and pauses for one second.
 *      </li>
 *      <li>
 *          Puts the disk at the top of rod 2
 *          and pauses for one second.
 *      </li>
 * </ol>
 * <pre style="margin-left: 2em;">
public static void main(String[] args)
{
    Pitch       pitch   = Tower.play();
    TowerDemo   demo    = new TowerDemo();
    demo.execute( pitch );
}

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
}</pre>
<p>
 * You can use this facility to:
 * <ul>
 *      <li>
 *          Get input from the operator in the form of
 *          "move disk from rod x to rod y,"
 *          then animate the operation.
 *      </li>
 *      <li>
 *          Use a recursive algorithm to solve the puzzle,
 *          animating each step.
 *      </li>
 * </ul>
 * 
 * @see <a href="https://docs.google.com/document/d/1vLTVROytgzKH6WymdiRaTPkCXe_Zng10bQQRSQMuwLU/edit?usp=drive_link">
 *     Tower of Hanoi
 * </a>
 * in the Java class documentation
 * @see <a href="https://en.wikipedia.org/wiki/Tower_of_Hanoi">
 *     Tower of Hanoi
 * </a>
 * on Wikipedia
 */
package com.judahstutorials.javaintro.towerofhanoi;