package cartesian_plane;

public class CartesianPlaneDemo
{

    /**
     * Default constructor. Not used.
     */
    private CartesianPlaneDemo()
    {
        // Not used
    }

    public static void main(String[] args)
    {
        Plane   plane   = CPFrame.getPlane();
        for ( double coo = -2 ; coo <= 2 ; coo += .5 )
            plane.addPoint( coo, coo );
        plane.repaint();
    }

}
