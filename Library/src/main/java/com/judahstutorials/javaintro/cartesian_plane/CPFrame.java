package com.judahstutorials.javaintro.cartesian_plane;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Encapsulates the application frame for the Cartesian plane application.
 */
public class CPFrame
{
    /**
     * The title of the application frame.
     */
    private static final String title   = "Cartesian Plane";
    /**
     * The frame that encapsulates the application GUI.
     */
    private final JFrame    jFrame  = new JFrame( title );
    /**
     * The encapsulated Cartesian plane.
     */
    private final Plane     plane   = new Plane();
    
    /**
     * The singleton for this class.
     */
    private static CPFrame  cpFrame = null;
    
    /**
     * Creates and makes visible the GUI,
     * and returns the encapsulated Plane object.
     * 
     * @return  the encapsulated Plane object
     */
    public static Plane getPlane()
    {
        if ( cpFrame == null )
        {
            try
            {
                SwingUtilities.invokeAndWait( () -> cpFrame = new CPFrame() );
            }
            catch ( InterruptedException | InvocationTargetException exc )
            {
                exc.printStackTrace();
                System.exit( 1 );
            }
        }
        return cpFrame.plane;
    }
    
    /**
     * Constructor.
     * Fully configures the application GUI and makes it visible.
     */
    public CPFrame()
    {
        jFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        jFrame.setContentPane( plane );
        jFrame.pack();
        
        Toolkit     toolkit     = Toolkit.getDefaultToolkit();
        Dimension   screenSize  = toolkit.getScreenSize();
        Dimension   windowSize  = jFrame.getPreferredSize();
        
        int         xco        = (screenSize.width - windowSize.width) / 2;
        int         yco        = (screenSize.height - windowSize.height) / 2;
        jFrame.setLocation( xco, yco );
        jFrame.setVisible( true );
    }

}
