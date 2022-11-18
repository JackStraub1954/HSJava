package judah.com.acmemail.blog_support;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class FigureGenerator_010 implements Runnable
{

    public static void main(String[] args)
    {
        FigureGenerator_010 figure  = new FigureGenerator_010();
        SwingUtilities.invokeLater( figure );
    }

    public void run()
    {
        int     width   = 400;
        int     height  = 500;
        int     mini    = 75;
        
        JFrame  frame       = new JFrame( "Figure 0010" );
        JPanel  contentPane = new JPanel( new BorderLayout() );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        JPanel  centerPanel = 
            new MiscPanel( 
                "Center panel",
                Color.CYAN,
                width,
                height
            );
        JPanel  northPanel = 
            new MiscPanel( 
                "North panel",
                Color.GREEN,
                width,
                mini
            );
        JPanel  westPanel = 
            new MiscPanel( 
                "West panel",
                Color.ORANGE,
                mini,
                height
            );
        contentPane.add( centerPanel, BorderLayout.CENTER );
        contentPane.add( northPanel, BorderLayout.NORTH );
        contentPane.add( westPanel, BorderLayout.WEST );
        frame.setContentPane( contentPane );
        frame.pack();
        frame.setVisible( true );
}
    
    private class MiscPanel extends JPanel
    {
        private final String    comment;
        private final Color     color;
        public MiscPanel( String comment, Color color, int width, int height )
        {
            this.comment = comment;
            this.color = color;
            Dimension   size    = new Dimension( width, height );
            setPreferredSize( size );
        }
        
        @Override
        public void paintComponent( Graphics graphics )
        {
            int         currWidth   = getWidth();
            int         currHeight  = getHeight();
            Graphics2D  gtx         = (Graphics2D)graphics.create();
            graphics.setColor( color );
            graphics.fillRect( 0, 0, currWidth, currHeight );
            
            FontMetrics metrics     = gtx.getFontMetrics();
            Rectangle2D strRect     = metrics.getStringBounds( comment, gtx );
            int         strWidth    = (int)strRect.getWidth();
            int         strHeight   = (int)strRect.getHeight();
            int         strXco      = (currWidth - strWidth) / 2;
            int         strYco      = (int)(currHeight * .75);
            gtx.setColor( Color.BLACK );
            gtx.drawString( comment, strXco, strYco );
        }
    }
}
