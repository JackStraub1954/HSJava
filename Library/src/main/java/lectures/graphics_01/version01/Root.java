package lectures.graphics_01.version01;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Root implements Runnable
{
    private JFrame  frame       = null;
    private JPanel  contentPane = null;
    private JPanel  userPanel   = null;
    
    public Root( JPanel userPanel )
    {
        this.userPanel = userPanel;
    }
    
    public void start()
    {
        SwingUtilities.invokeLater( this );
    }
    
    public void run()
    {
        frame = new JFrame( "Graphics Frame" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        BorderLayout    layout  = new BorderLayout();
        contentPane = new JPanel( layout );
        contentPane.add( userPanel, BorderLayout.CENTER );
        frame.setContentPane( contentPane );
        frame.pack();
        frame.setVisible( true );
    }
}
