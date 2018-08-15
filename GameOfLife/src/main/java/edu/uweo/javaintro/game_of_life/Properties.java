package edu.uweo.javaintro.game_of_life;

import java.awt.Color;

public enum Properties
{
    BORDER_COLOR( new Color( 0f, .28f, .73f ) ),
    USE_GRID( true ),
    USE_BORDER( true ),
    GRID_COLOR( new Color( .8f, .8f, .8f ) ),
    BORDER_WIDTH( 10 ),
    GRID_LINE_WIDTH( 1 ),
    GRID_SIDE( 500 ),
    MIN_CELL_SIDE( 10 ),
    BACKGROUND_COLOR( Color.WHITE ),
    CELL_COLOR( Color.BLACK );
    
    private final Object    defValue;
    private Object          currValue;
    
    private Properties( Object obj )
    {
        defValue = currValue = obj;
    }
    
    public Object getProperty()
    {
        return currValue;
    }
    
    public void setProperty( Object obj )
    {
        currValue = obj;
    }
    
    public Object getDefaultValue()
    {
        return defValue;
    }
}
