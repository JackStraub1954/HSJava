package lectures.graphics_01.version01;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Canvas extends JPanel
{
    private int		initWidth   	= 800;
    private int     initHeight  	= 500;
    public Canvas()
    {
        Dimension   size    = new Dimension( initWidth, initHeight );
        this.setPreferredSize( size );
    }
}
