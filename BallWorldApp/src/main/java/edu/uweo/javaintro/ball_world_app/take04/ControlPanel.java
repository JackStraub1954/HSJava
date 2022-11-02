package edu.uweo.javaintro.ball_world_app.take04;

import java.awt.event.ItemEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 * This class implements a control panel for a BallField.
 * @author Jack Straub
 * 
 * Adapted from code by Chua Hock-Chuan (ehchua@ntu.edu.sg).
 * 
 * @author Jack Straub
 * @see  <a href="https://www3.ntu.edu.sg/home/ehchua/programming/java/J8a_GameIntro-BouncingBalls.html">
 *          The World Of Bouncing Balls
 *      </a>
 *      by Chua Hock-Chuan (ehchua@ntu.edu.sg)
 */
@SuppressWarnings("serial")
public class ControlPanel extends JPanel
{
    /** The BallField under control; provided via constructor */
    private final BallField ballField;
    
    /**
     * Constructor.
     * 
     * @param ballField ballField to be controlled
     */
    public ControlPanel( BallField ballField )
    {
        this.ballField = ballField;
        
        // Check box to pause/resume animation
//        add( new JLabel( "Pause" ) );
        JCheckBox   checkBox= new JCheckBox( "Pause", false );
        checkBox.addItemListener( e -> pauseAnimation( e ) );
        
        // Determine the upper and lower bounds of the slider
        // that control the speed of the ball.
        // TODO justify these values
        int minSpeed    = 2;
        int maxSpeed    = 20;
        int ballSpeed   = (int)ballField.getBall().getSpeed();
        int horizontal  = JSlider.HORIZONTAL;
        add( new JLabel( "Speed" ) );
        JSlider slider  = 
            new JSlider( horizontal, minSpeed, maxSpeed, ballSpeed );
    }
    
    /**
     * Pause/resume the animation as indicated by
     * the state of the <em>pause</em> check box.
     * The state of the <em>pause</em> check box
     * is determined by a given ItemEvent
     * (presumably obtained when the state
     * of the check box changed).
     * 
     * @param evt   the given ItemEvent
     */
    private void pauseAnimation( ItemEvent evt )
    {
        int     state       = evt.getStateChange();
        boolean isPaused    = state == ItemEvent.SELECTED;
        ballField.setPaused( isPaused );
    }
}
