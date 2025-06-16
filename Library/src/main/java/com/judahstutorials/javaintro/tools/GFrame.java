package com.judahstutorials.javaintro.tools;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * This class encapsulates a simple JFrame
 * to support the implementation of graphics applications.
 * To use it the programmer passes an object
 * that implements {@link GUser} to {@link #getGFrame(GUser)}.
 * The GFrame that it returns encapsulates a visible JFrame
 * that contains a JPanel.
 * The paint method of the incorporated JPanel
 * automatically calls the paint method
 * in the GUser implementation.
 * 
 * @see GUser
 * @see <a href="https://judahstutorials.com/Classes/JavaClass/mini-lessons/index.html">
 * GFrame Mini-lessons</a>
 */
public class GFrame
{
    /**
     * The application frame.
     */
    private final JFrame    frame;
    /**
     * The main application window.
     */
    private final IntPanel  panel;
    /**
     * The GUser assigned to this frame.
     */
    private final GUser     gUser;
    /**
     * Single instance of this class.
     */
    private static GFrame   gFrame;

    /**
     * Temporary variable for supporrting the use of lambdas.
     */
    private volatile String     tempString;
    
    /**
     * Returns a visible GFrame
     * containing a JPanel that the user
     * can draw in.
     * 
     * @param gUser	object to be notified when drawing is required
     * @return a visible GFrame with a JPanel the user can draw in
     * 
     *  @see GUser
     */
    public static GFrame getGFrame( GUser gUser )
    {
        GFrame gFrame  = getGFrame( gUser, 400, 400 );
        return gFrame;
    }
    
    /**
     * Returns a visible GFrame
     * with the given dimensions
     * containing a JPanel that the user
     * can draw in.
     * 
     * @param gUser		object to be notified when drawing is required
     * @param width		the given width of the window
     * @param height	the given height of the window
     * @return a visible GFrame with a JPanel the user can draw in
     * 
     *  @see GUser
     */
    public static GFrame getGFrame( GUser gUser, int width, int height )
    {
        if ( SwingUtilities.isEventDispatchThread() )
            gFrame = new GFrame( gUser, width, height );
        else
            invokeAndWait( () -> gFrame = new GFrame( gUser, width, height ) );
        return gFrame;
    }
    
    /**
     * Constructor.
     * Creates, configures, and makes visible
     * a JFrame containing a JPanel that the client
     * can in.
     * 
     * @param gUser		object to be notified when drawing is required
     * @param width		target width of the frame
     * @param height	target height of the frame
     */
    private GFrame( GUser gUser, int width, int height )
    {
        Dimension   dim = new Dimension( width, height );
        frame = new JFrame( "GFrame" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        panel = new IntPanel();
        this.gUser = gUser;
        panel.setPreferredSize( dim );
        frame.setContentPane( panel );
        frame.pack();
        frame.setVisible( true );
    }
    
    /**
     * Gets the current width of the encapsulated panel.
     * @return the width of the encapsulated panel
     */
    public int getWidth()
    {
        return panel.getWidth();
    }
    
    /**
     * Gets the current height of the encapsulated panel.
     * @return the height of the encapsulated panel
     */
    public int getHeight()
    {
        return panel.getHeight();
    }
    
    /**
     * Uses JOptionPane.showInputDialog
     * to obtain a string from the operator.
     * The advantage of this method
     * is that it ensures the input dialog
     * is posted on the EDT.
     * 
     * @param prompt    the prompt to display in the input dialog
     * 
     * @return  the string entered by the operator,
     *          or null if the operator canceled the operation
     */
    public String getInput( String prompt )
    {
        String  input   = getString( () ->
            JOptionPane.showInputDialog(
                null,
                prompt
            )   
        );
        return input;
    }
    
    /**
     * Invokes the encapsulated JPanel's paint method,
     * ensuring that the invocation occurs on the EDT.
     */
    public void repaint()
    {
        execEDT( () -> panel.repaint() );
    }
    
    /**
     * Invokes the given String Supplier,
     * ensuring that the invocation occurs on the EDT.
     * @param supplier  the given String Supplier
     * @return  the string obtained from the given Supplier
     */
    private String getString( Supplier<String> supplier )
    {
        execEDT( () -> tempString = supplier.get() ) ;
        return tempString;        
    }
    
    /**
     * Ensures that the given Runner is executed on the EDT.
     * This method can e called within or without
     * the context of the EDT.
     * 
     * @param runner    the given Runner
     */
    private void execEDT( Runnable runner )
    {
        if ( SwingUtilities.isEventDispatchThread() )
            runner.run();
        else
            invokeAndWait( runner );
    }
    
    /**
     * Invokes the given Runner in the context of the EDT.
     * This method must not be called from the EDT.
     * 
     * @param runner    the given Runner
     */
    private static void invokeAndWait( Runnable runner )
    {
        try
        {
            SwingUtilities.invokeAndWait( runner );
        }
        catch ( InterruptedException | InvocationTargetException exc )
        {
            exc.printStackTrace();
        }
    }
    
    /**
     * This is the encapsulated JPanel.
     * Its paint method invokes the paint method
     * in the encapsulated GUser.
     */
    private class IntPanel extends JPanel
    {
        private static final long serialVersionUID = 1L;

		@Override
        public void paint( Graphics graphics )
        {
            super.paint( graphics );
            Graphics2D  gtx = (Graphics2D)graphics.create();
            gUser.paint( gtx, this );
        }
    }
}
