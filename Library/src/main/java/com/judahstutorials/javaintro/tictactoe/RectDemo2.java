package com.judahstutorials.javaintro.tictactoe;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.judahstutorials.javaintro.tools.GFrame;
import com.judahstutorials.javaintro.tools.GUser;

public class RectDemo2 extends GUser
{
    private final GFrame    frame       = GFrame.getGFrame( this, 300, 200 );
    private final List<Shape>   shapes  = new ArrayList<>();
    public void paint( Graphics2D gtx, JPanel panel )
    {
        int width   = frame.getWidth();
        int height  = frame.getHeight();
        gtx.setColor( Color.LIGHT_GRAY );
        gtx.fillRect( 0,  0, width, height );
        
        int margin      = 20;
        int rWidth      = width - 2 * margin;
        int rHeight     = height - 2 * margin;
        Rectangle   rect    = new Rectangle( margin, margin, rWidth, rHeight );
        gtx.setColor( Color.GREEN );
        gtx.draw( rect );
        gtx.setColor( Color.RED );
        gtx.fill( rect );
        
        Ellipse2D oval    = new Ellipse2D.Double( margin, margin, rWidth, rHeight );
        gtx.setColor( Color.BLUE );
        gtx.fill( oval );
        gtx.setColor( Color.MAGENTA );
        gtx.draw( oval );
        
        for ( Shape shape : shapes )
            gtx.fill( shape );
    }
    public static void main( String[] args )
    {
       RectDemo2     user = new RectDemo2();
    }
}
