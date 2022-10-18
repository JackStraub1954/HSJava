package edu.uweo.javaintro.game_of_life_lib;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.uweo.javaintro.game_of_life_lib.ControlEvent;
import edu.uweo.javaintro.game_of_life_lib.Controls;

public class ControlEventTest
{

    @Test
    public void test()
    {
        String          expSource   = "source";
        String          expLabel    = "label";
        Controls        expControls = new Controls();
        ControlEvent    event       = new ControlEvent( expSource, expLabel, expControls );
        
        assertEquals( expSource, event.getSource() );
        assertEquals( expLabel, event.getLabel() );
        assertEquals( expControls, event.getControls() );
    }

}
