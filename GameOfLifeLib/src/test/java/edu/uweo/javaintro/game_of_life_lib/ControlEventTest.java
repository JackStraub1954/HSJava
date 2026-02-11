package edu.uweo.javaintro.game_of_life_lib;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
