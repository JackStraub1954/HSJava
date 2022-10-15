package lectures.graphics_02.timer;

/**
 * This is the class that kicks off our graphics project.
 * 
 * @author Jack Straub
 *
 */
public class Main
{
	/** The object we'll be drawing to. */
    private static Canvas    canvas;
    /** The frame that encloses the project's GUI. */
    private static Root      root;
    
    /**
     * Main method to start the application.
     * 
     * @param args	Command line arguments; not used
     */
    public static void main(String[] args)
    {
    	/** Instantiate the drawing area. */
        canvas = new Canvas();
        /** Instantiate the application frame. */
        root = new Root( canvas );
        /** Display the application frame. */
        root.start();
    }

}
