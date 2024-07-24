package com.judahstutorials.javaintro.tools;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Hues
{
    private static final    long    pauseTime       = 10;
    private static ColorWheelPanel colorWheel;
    
    public static void main( String[] args )
    {
        Hues    app     = new Hues();
        SwingUtilities.invokeLater( () -> app.buildGUI() );
        while ( true )
        {
            pause();
            SwingUtilities.invokeLater( () -> colorWheel.repaint() );
        }
    }
    
    private void buildGUI()
    {
        JFrame  frame   = new JFrame( "Hues Panel" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        JPanel  pane    = new JPanel( new BorderLayout() );
        colorWheel = new ColorWheelPanel();
        pane.add( colorWheel, BorderLayout.CENTER );
        pane.add( new HuesPanel(), BorderLayout.SOUTH );
        
        frame.setContentPane( pane );
        frame.setLocation( 200, 100 );
        frame.pack();
        frame.setVisible( true );
    }
    
    private static void pause()
    {
        try
        {
            Thread.sleep( pauseTime );
        }
        catch ( InterruptedException exc )
        {
            // ignore
        }
    }

    private static class HuesPanel extends JPanel
    {
        private static final long serialVersionUID = 1L;

        public HuesPanel()
        {
            setPreferredSize( new Dimension( 256, 50 ) );
        }
        
        @Override
        public void paintComponent( Graphics graphics )
        {
            Graphics2D  gtx     = (Graphics2D)graphics.create();
            
            int         height  = getHeight();
            int         width   = getWidth();
            double      incr    = 1.0 / (width / 2f);
            float       hue     = 0;
            System.out.println( width + ", " + incr );
            for ( int xco = 0 ; xco < width ; xco += 2 )
            {
                Color   color   = Color.getHSBColor( hue, 1, 1 );
                System.out.println( xco + ", " + hue );
                hue += incr;
                gtx.setColor( color );
                Rectangle2D rect    = 
                    new Rectangle2D.Double( xco, 0, 2, height );
                gtx.fill( rect );
            }
            
            gtx.dispose();
        }
    }
}
