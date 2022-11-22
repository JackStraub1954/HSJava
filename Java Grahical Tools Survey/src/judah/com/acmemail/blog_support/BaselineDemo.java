package judah.com.acmemail.blog_support;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BaselineDemo extends JPanel
{
    private final Color     bgColor     = new Color( .9f, .9f, .9f );
    
    private int             currWidth;
    private int             currHeight;
    private Graphics2D      gtx;
    private FontMetrics     fontMetrics;
    private Font            strFont;
    private Font            labelFont;
    
    public static void main( String[] args )
    {
        BaselineDemo    demo    = new BaselineDemo( 400, 500 );
        Root            root    = new Root( demo );
        root.start();
    }
    
    public BaselineDemo( int width, int height )
    {
        Dimension   dim = new Dimension( width, height );
        setPreferredSize( dim );
        strFont = new Font( "monospaced", Font.BOLD, 18 );
        labelFont = new Font( "dialog", Font.PLAIN, 12 );
    }
    
    @Override
    public void paintComponent( Graphics graphics )
    {
        // begin boilerplate
        super.paintComponent( graphics );
        currWidth = getWidth();
        currHeight = getHeight();
        gtx = (Graphics2D)graphics.create();
        gtx.setColor( bgColor );
        gtx.fillRect( 0,  0, currWidth, currHeight );
        // end boilerplate
        
        drawString( "Brique peter junquet", 30, 100 );

        // begin boilerplate
        gtx.dispose();
        // end boilerplate
    }
    
    private void drawString( String str, int xco, int yco )
    {
        gtx.setFont( strFont );
        fontMetrics = gtx.getFontMetrics();
        Rectangle2D strRect     = 
            fontMetrics.getStringBounds( str, gtx );
        float       strWidth    = (float)strRect.getWidth();
        float       strHeight   = (float)strRect.getHeight();
        float       leading     = fontMetrics.getLeading();
        
        String      strCoords   = String.format( "(x=%d,y=%d)", xco, yco );
        float       baseXco1    = xco - 20;
        float       baseXco2    = baseXco1 + strWidth + 40;
        float       rectXco     = xco + (float)strRect.getX();
        float       rectYco     = yco + (float)strRect.getY() + leading;
        float       rectWidth   = (float)strRect.getWidth();
        float       rectHeight  = (float)strRect.getHeight() - leading;
        
        strRect.setRect( rectXco, rectYco, rectWidth, rectHeight );
        
        Line2D      baseline    = 
            new Line2D.Float( baseXco1, yco, baseXco2, yco );
        Stroke      dashes  =
            new BasicStroke(
                1,
                BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_ROUND,
                1.0f,
                new float[] { 6 },
                5f
            );
        gtx.setColor( Color.BLACK );
        gtx.setStroke( new BasicStroke( 1 ) );
        gtx.drawString( str, xco, yco );
        gtx.draw( baseline );
        
        gtx.setColor( Color.RED );
        gtx.setStroke( dashes );
        gtx.draw( strRect );
    }
}
