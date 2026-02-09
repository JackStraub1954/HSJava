package judah.com.mailco.sandbox;

import java.awt.Color;

public class CanineUser
{
    @SuppressWarnings("unused")
    public static void main(String[] args)
    {
        Canine1.Poodle  bestFriendA = new Canine1.Poodle();
        bestFriendA.setColor( Color.BLUE );
        
        Canine2.Poodle  bestFriendB = new Canine2.Poodle();
            // -- or --
        Canine2 bestFriendC = new Canine2.Poodle();
    }
}
