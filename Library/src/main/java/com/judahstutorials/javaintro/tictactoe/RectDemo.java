package com.judahstutorials.javaintro.tictactoe;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.judahstutorials.javaintro.tools.GFrame;
import com.judahstutorials.javaintro.tools.GUser;

/**
 * This is a simple application that demonstrates
 * how to interact with the operator 
 * in a GFrame application.
 * The operator is prompted to enter an number,
 * then the application is filled with
 * the given number of rectangles.
 */
public class RectDemo extends GUser
{
    /**
     * Object to create and interface with the application GUI.
     */
    private final GFrame    frame       = GFrame.getGFrame( this, 300, 200 );
    /**
     * Number of rectangles to draw in the application window.
     */
    private int             numRects    = 0;
    
    /**
     * Default constructor; not used.
     */
    private RectDemo()
    {
        // not used
    }
    
    public void paint( Graphics2D gtx, JPanel panel )
    {
        int width   = frame.getWidth();
        int height  = frame.getHeight();
        gtx.setColor( Color.LIGHT_GRAY );
        gtx.fillRect( 0,  0, width, height );
        
        gtx.setColor( Color.BLACK );
        for ( int inx = 0 ; inx < numRects ; ++inx )
        {
            int offset  = 15 * inx;
            gtx.fillRect( offset, offset, 10, 10 );
        }
    }
    
    /**
     * Application entry point.
     * 
     * @param args  command-line arguments; not used
     */
    public static void main( String[] args )
    {
       RectDemo     user = new RectDemo();
       String       prompt  = "How many rectangles?";
       String       input   = user.frame.getInput(prompt);
       while ( input != null )
       {
          user.numRects = Integer.parseInt( input );
          user.frame.repaint();
          input = user.frame.getInput( prompt );
       }
    }
}
