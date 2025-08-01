package com.judahstutorials.javaintro.towerofhanoi;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Encapsulates the GUI for the Tower of Hanoi project.
 * It is implemented as a singleton.
 */
public class Frame
{
    /**
     * The title of the frame that encapsulates the GUI.
     */
    private static final String     title   = "Tower of Hanoi";
    /**
     * The frame that encapsulates the GUI.
     */
    private static final JFrame     jFrame  = new JFrame( title );
    /**
     * The main panel incorporated into the application frame.
     */
    private static final Pitch      pitch   = new Pitch();
    /**
     * The singleton for this class.
     */
    private static Frame            frame;
    
    /**
     * Gets the singleton for this class,
     * instantiating it if necessary.
     * @return  the singleton for this class
     */
    public static Frame getFrame()
    {
        if ( frame == null )
            frame = new Frame();
        return frame;
    }
    
    /**
     * Returns the Pitch embedded in the application frame.
     * 
     * @return  the Pitch embedded in the application frame
     */
    public Pitch getPitch()
    {
        return pitch;
    }
    
    /**
     * Constructor.
     * Fully initializes, configures, and makes visible
     * an object of this class.
     */
    Frame()
    {
        jFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        jFrame.setContentPane( pitch );
        jFrame.pack();
        
        Toolkit     toolkit     = Toolkit.getDefaultToolkit();
        Dimension   pitchDim    = pitch.getPreferredSize();
        Dimension   screenDim   = toolkit.getScreenSize();
        int         xco         = 
            (int)(screenDim.getWidth() / 2) - pitchDim.width / 2;
        int         yco         = 
            (int)(screenDim.getHeight() / 2) - pitchDim.height / 2;
        jFrame.setLocation( xco, yco );

        
        jFrame.setVisible( true );
    }
}
