package judah.com.mailco.plane;

import judah.com.mailco.graph_utils.Root;

public class Main
{

    public static void main(String[] args)
    {
        CartesianPlane  plane   = new CartesianPlane();
        Root            frame   = new Root( plane );
        frame.start();
    }

}
