package edu.uweo.javaintro.game_of_life_lib;

import static edu.uweo.javaintro.game_of_life_lib.Properties.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.awt.Color;


import edu.uweo.javaintro.game_of_life_lib.Properties;

public class PropertiesTest
{

    private Tester[]    allProperties   = 
    {
        new Tester( BORDER_COLOR, new Color( 0f, .28f, .73f ), Color.BLACK ),
        new Tester( USE_GRID, true, false ),
        new Tester( USE_BORDER, true, false ),
        new Tester( GRID_COLOR, new Color( .8f, .8f, .8f ), Color.BLACK ),
        new Tester( BORDER_WIDTH, 10, 20 ),
        new Tester( GRID_LINE_WIDTH, 1, 2 ),
        new Tester( GRID_SIDE, 500, 600 ),
        new Tester( MIN_CELL_SIDE, 10, 20 ),
        new Tester( CELL_COLOR, Color.BLACK, Color.YELLOW ),
    };
    
    @Test
    public void testGetSetProperty()
    {
        for ( Tester tester : allProperties )
            tester.test();
    }
    
    // Get coverage on generated method
    @Test
    public void testValueOf()
    {
        String      name    = "BORDER_COLOR";
        Properties  actProp = Properties.valueOf( name );
        Properties  expProp = BORDER_COLOR;
        assertEquals( expProp, actProp );
    }
    
    // Get coverage on generated method
    @Test
    public void testValues()
    {
        Properties[]    values  = Properties.values();
        boolean         found   = false;
        for ( int inx = 0 ; inx < values.length && !found ; ++inx )
            found = values[inx].equals( BORDER_COLOR );
        assertTrue( found );
    }

    private class Tester
    {
        private final Properties property;
        Object defValue;
        Object newValue;

        public Tester( Properties property, Object defValue, Object newValue )
        {
            assertNotEquals( defValue, newValue );
            
            this.property = property;
            this.defValue = defValue;
            this.newValue = newValue;
        }
        
        public void test()
        {
            assertEquals( defValue, property.getDefaultValue() );
            assertEquals( defValue, property.getProperty() );
            
            property.setProperty( newValue );
            assertEquals( defValue, property.getDefaultValue() );
            assertEquals( newValue, property.getProperty() );
        }
    }
}
