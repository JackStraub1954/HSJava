package com.judahstutorials.javaintro.tictactoe;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.judahstutorials.javaintro.tools.GFrame;
import com.judahstutorials.javaintro.tools.GUser;

public class RectDemo extends GUser
{
    private final GFrame    frame       = GFrame.getGFrame( this, 300, 200 );
    private int             numRects    = 0;
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
