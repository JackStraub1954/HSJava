package com.judahstutorials.javaintro.tools;

import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * Abstract class which can be subclassed
 * for use with a GFrame.
 * When the user creates a GFrame
 * an object of this type must be specified.
 * Any time repainting is required 
 * by the JFrame (or one of its constituent controls)
 * the paint method of the GUser object
 * is invoked.
 * 
 * @see GFrame
 * @see <a href="https://judahstutorials.com/Classes/JavaClass/mini-lessons/index.html">
 * GFrame Mini-lessons</a>
 */
public abstract class GUser
{
    /**
     * This method is invoked every time 
     * the GFrame encapsulating this object
     * needs to be repainted.
     * The given panel is the JPanel in which
     * the drawing takes place.
     * The given graphics context is linked to the JPanel.
     * The graphics context is a copy of the original,
     * and as such can be modified
     * at the user's discretion.
     * 
     * @param graphics  the given graphics context
     * @param panel     the given panel
     *
     * @see <a href="https://judahstutorials.com/Classes/JavaClass/mini-lessons/index.html">
     * GFrame Mini-lessons</a>
     */
    public abstract void paint( Graphics2D graphics, JPanel panel );
    
    /**
     *  Default constructor.
     *  Present here for documentation purposes.
     *  Performs no actions.
     */
    public GUser()
    {
    }
}