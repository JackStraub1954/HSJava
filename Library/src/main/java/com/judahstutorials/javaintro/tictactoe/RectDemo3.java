package com.judahstutorials.javaintro.tictactoe;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.judahstutorials.javaintro.tools.GFrame;
import com.judahstutorials.javaintro.tools.GUser;

public class RectDemo3 extends GUser
{
    private final GFrame    frame       = GFrame.getGFrame( this, 300, 200 );
    public void paint( Graphics2D gtx, JPanel panel )
    {
        int width   = frame.getWidth();
        int height  = frame.getHeight();
        gtx.setColor( Color.LIGHT_GRAY );
        gtx.fillRect( 0,  0, width, height );
        
        int margin      = 20;
        int rWidth      = width - 2 * margin;
        int rHeight     = height - 2 * margin;
        gtx.setColor( Color.GREEN );
        gtx.drawRect( margin, margin, rWidth, rHeight );
        gtx.setColor( Color.RED );
        gtx.fillRect( margin, margin, rWidth, rHeight );
    }
    public static void main( String[] args )
    {
       RectDemo3     user = new RectDemo3();
    }
}
