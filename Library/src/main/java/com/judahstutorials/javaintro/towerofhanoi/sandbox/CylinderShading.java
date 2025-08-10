package com.judahstutorials.javaintro.towerofhanoi.sandbox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class CylinderShading
{    
    public static void main( String[] args )
    {
        CylinderShading app     = new CylinderShading();
        SwingUtilities.invokeLater( () -> app.build() );
    }
    
    private void build()
    {
        JFrame      frame   = new JFrame( "Cylinder Shading" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        JPanel      pane    = new JPanel( new BorderLayout() );
        pane.add( new Canvas(), BorderLayout.CENTER );
        frame.setContentPane( pane );
        frame.pack();
        frame.setLocation( 200, 200 );
        frame.setVisible( true );
    }
    
    @SuppressWarnings("serial")
    private static class Canvas extends JPanel
    {
        private final int           winWidth        = 200;
        private final int           winHeight       = 300;
        private final Color         winBackground   = 
            new Color( 200, 200, 200 );
        private final double        cylWidth        = 30;
        private final double        cylHeight       = 120;
        private final double        cylXco          = 
            winWidth / 2 - cylWidth / 2;
        private final double        cylYco          = 
            winHeight / 2 - cylHeight / 2;
        private final Color         cylBackground   = Color.DARK_GRAY;
        private final Rectangle2D   cylFrame        =
            new Rectangle2D.Double( cylXco, cylYco, cylWidth, cylHeight );
        private final double        shaderSegs      = 20;
        private final double        shaderWidth     = cylWidth / shaderSegs;
        private final int           shaderRGB       = 0x00FF00;
        private final Rectangle2D   shaderSeg       =
            new Rectangle2D.Double( 0, 0, shaderWidth, cylHeight );
        
        public Canvas()
        {
            Dimension   dim     = new Dimension( winWidth, winHeight );
            setPreferredSize( dim );
        }
        
        @Override
        public void paintComponent( Graphics graphics )
        {
            super.paintComponent( graphics );
            Graphics2D  gtx = (Graphics2D)graphics;
            gtx.setColor( winBackground );
            gtx.fillRect( 0, 0, getWidth(), getHeight() );
            
            gtx.setColor( cylBackground );
            gtx.fill( cylFrame );
            
            double  shaderIncr  = 255. / shaderSegs;
            for ( int inx = 0 ; inx < shaderSegs ; ++inx )
            {
                double  xco     = cylXco + inx * shaderWidth;
                int     iAlpha  = (int)(255 - inx * shaderIncr );
                int     rgba    = shaderRGB | (iAlpha << 24);
                System.out.println( Integer.toHexString( rgba ) );
                Color   color   = new Color( rgba, true );
                gtx.setColor( color );
                shaderSeg.setRect( xco, cylYco, shaderWidth, cylHeight );
                gtx.fill( shaderSeg );
            }
        }
    }
}
