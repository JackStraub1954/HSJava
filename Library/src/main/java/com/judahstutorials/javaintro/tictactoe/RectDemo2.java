package com.judahstutorials.javaintro.tictactoe;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import com.judahstutorials.javaintro.tools.GFrame;
import com.judahstutorials.javaintro.tools.GUser;

/**
 * This is a simple application that demonstrates
 * how to use a GFrame to open a window
 * and draw a rectangle and oval in it.
 */
public class RectDemo2 extends GUser
{
    /**
     * Object to create and interface with the application GUI.
     */
    private final GFrame    frame   = GFrame.getGFrame( this, 300, 200 );
    
    /**
     * Default constructor; not used.
     */
    private RectDemo2()
    {
        // not used
    }

    /**
     * Paint method required by abstract superclass GUser.
     * Draws a rectangle and an oval
     * in the application window.
     * 
     * @param   gtx     graphics context for drawing
     * @param   panel   JPanel containing the drawing surface
     */
    public void paint( Graphics2D gtx, JPanel panel )
    {
        int width   = frame.getWidth();
        int height  = frame.getHeight();
        gtx.setColor( Color.LIGHT_GRAY );
        gtx.fillRect( 0,  0, width, height );
        
        int         margin  = 20;
        int         rWidth  = width - 2 * margin;
        int         rHeight = height - 2 * margin;
        Rectangle   rect    = 
            new Rectangle( margin, margin, rWidth, rHeight );
        gtx.setColor( Color.GREEN );
        gtx.draw( rect );
        gtx.setColor( Color.RED );
        gtx.fill( rect );
        
        Ellipse2D   oval    = 
            new Ellipse2D.Double( margin, margin, rWidth, rHeight );
        gtx.setColor( Color.BLUE );
        gtx.fill( oval );
        gtx.setColor( Color.MAGENTA );
        gtx.draw( oval );
    }
    
    /**
     * Application entry point.
     * 
     * @param args  command-line arguments; not used
     */
    public static void main( String[] args )
    {
       new RectDemo2();
    }
}
