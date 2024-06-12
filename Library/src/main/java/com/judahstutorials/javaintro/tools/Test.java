package com.judahstutorials.javaintro.tools;
import com.judahstutorials.javaintro.tools.Turtle;

import java.awt.Color;

public class Test
{

    public static void main(String[] args)
    {
        Turtle  tutt    = new Turtle();
        tutt.switchTo( Color.RED );
        tutt.fillCircle( 128 );
    }

}
