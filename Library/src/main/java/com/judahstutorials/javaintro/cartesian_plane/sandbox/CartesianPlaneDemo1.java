package com.judahstutorials.javaintro.cartesian_plane.sandbox;

import com.judahstutorials.javaintro.cartesian_plane.CPFrame;
import com.judahstutorials.javaintro.cartesian_plane.Plane;

/**
 * This is a simple application to demonstrate
 * how to instantiate and utilize the Plane class
 * of the Cartesian plane project.
 * It plots a straight line
 * using points with x-coordinates 
 * spaced at .5 units.
 * 
 * @author Jack Straub
 */
public class CartesianPlaneDemo1
{
    /**
     * Default constructor. Not used.
     */
    private CartesianPlaneDemo1()
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
        Plane   plane   = CPFrame.getPlane();
        for ( double coo = -2 ; coo <= 2 ; coo += .5 )
            plane.addPoint( coo, coo );
        plane.repaint();
    }

}
