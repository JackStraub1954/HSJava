package lectures.graphics_01.version01;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Canvas extends JPanel
{
    public Canvas()
    {
        int         width   = 800;
        int         height  = 500;
        Dimension   size    = new Dimension( width, height );
        this.setPreferredSize( size );
    }
}
