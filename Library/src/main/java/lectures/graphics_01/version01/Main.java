package lectures.graphics_01.version01;

public class Main
{
    private static Canvas    canvas;
    private static Root      root;
    
    public static void main(String[] args)
    {
        canvas = new Canvas();
        root = new Root( canvas );
        root.start();
    }

}
