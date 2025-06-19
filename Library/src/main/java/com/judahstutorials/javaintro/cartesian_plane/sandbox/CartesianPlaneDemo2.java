package com.judahstutorials.javaintro.cartesian_plane.sandbox;

import java.awt.Color;

import com.judahstutorials.javaintro.cartesian_plane.CPFrame;
import com.judahstutorials.javaintro.cartesian_plane.LineClass;
import com.judahstutorials.javaintro.cartesian_plane.Plane;

/**
 * Simple application to demonstrate
 * how to instantiate and manipulate
 * the Plane class from the Cartesian plane project.
 * It changes some drawing parameters,
 * then plots a parabola between -1.5 and +1.5
 * with x-coordinates spaced .01 units apart
 * 
 */
public class CartesianPlaneDemo2
{
    /**
     * Default constructor. Not used.
     */
    private CartesianPlaneDemo2()
    {
        // Not used
    }

    /**
     * Application entry point.
     * 
     * @param args  Command-line arguments; not used.
     */
    public static void main(String[] args)
    {
        Color   darkGreen   = new Color( 0x006400 );
        Plane   plane       = CPFrame.getPlane();
        plane.setMargin( 35 );
        plane.setBackground( Color.ORANGE );
        plane.setPitchColor( Color.CYAN );
        plane.setPointColor( Color.BLUE );
        plane.setColor( LineClass.AXIS, darkGreen );
        plane.setColor( LineClass.MAJOR_TICK, Color.RED );
        plane.setUnitLength( 100 );
        plane.setPointDiam( 3 );
        plane.setPerUnit( LineClass.MAJOR_TICK, 4 );
        plane.setPerUnit( LineClass.MINOR_TICK, 8 );
        plane.setFontSize( 8 );
        for ( double coo = -1.5 ; coo <= 1.5 ; coo += .01 )
            plane.addPoint( coo, Math.pow( coo,  2 ) );
        plane.repaint();
    }

}
