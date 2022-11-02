package edu.uweo.javaintro.ball_world_app.take04;

import java.awt.BorderLayout;

/**
 * This is the class that kicks off our graphics project.
 * 
 * @author Jack Straub
 *
 */
public class Main
{
	/** The object we'll be drawing to. */
    private static BallField    canvas;
    /** GUI to control the animation. */
    private static ControlPanel	controlPanel;
    /** The frame that encloses the project's GUI. */
    private static Root      root;
    
    /**
     * Main method to start the application.
     * 
     * @param args	Command line arguments; not used
     */
    public static void main(String[] args)
    {
    	/* Instantiate the drawing area. */
        canvas = new BallField();
        /* Instantiate the application frame. */
        root = new Root( canvas );
        /* Instantiate the control panel */
        controlPanel = new ControlPanel( canvas );
        /* Add control panel to root frame */
        root.addPanel( controlPanel, BorderLayout.NORTH );
        
        
        /** Display the application frame. */
        root.start();
    }

}
