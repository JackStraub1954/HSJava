package com.judahstutorials.javaintro.cartesian_plane.sandbox;

import javax.swing.JOptionPane;

import com.judahstutorials.javaintro.cartesian_plane.CPFrame;
import com.judahstutorials.javaintro.cartesian_plane.LineClass;
import com.judahstutorials.javaintro.cartesian_plane.Plane;

/**
 * This is application is used to generate miscellaneous figures
 * for inclusion in the documentation.
 * Its content changes frequently.
 * It is of little interest to the student.
 */
public class MiscDemo
{
    /**
     * Default constructor; not used.
     */
    public MiscDemo()
    {
        // not used
    }

    public static void main(String[] args)
    {
        Plane       plane           = CPFrame.getPlane();
        plane.setDrawText( false );
        plane.setUnitLength( 50 );
        plane.setMargin( 5 );
        plane.setPerUnit( LineClass.MAJOR_TICK, 1 );
        plane.setPerUnit( LineClass.MINOR_TICK, 2 );
        plane.repaint();
        
        JOptionPane.showMessageDialog( null, "Dismiss whenb ready" );
        plane.setPerUnit( LineClass.MAJOR_TICK, 2 );
        plane.setPerUnit( LineClass.MINOR_TICK, 4 );
        plane.repaint();
    }

}
