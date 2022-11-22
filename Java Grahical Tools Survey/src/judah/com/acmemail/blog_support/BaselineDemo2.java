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
public class BaselineDemo2 extends JPanel
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
        BaselineDemo2   demo    = new BaselineDemo2( 400, 500 );
        Root            root    = new Root( demo );
        root.start();
    }
    
    public BaselineDemo2( int width, int height )
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
        float       leading     = fontMetrics.getLeading();
        
        float       rectXco     = xco + (float)strRect.getX();
        float       rectYco     = yco + (float)strRect.getY() + leading;
        float       rectWidth   = (float)strRect.getWidth();
        float       rectHeight  = (float)strRect.getHeight() - leading;
        
        strRect.setRect( rectXco, rectYco, rectWidth, rectHeight );
        gtx.setColor( Color.BLACK );
        gtx.setStroke( new BasicStroke( 1 ) );
        gtx.drawString( str, xco, yco );
        
        gtx.setColor( Color.RED );
        gtx.draw( strRect );
    }
}
