package edu.uweo.javaintro.game_of_life;
import java.util.EventObject;

public class ControlEvent extends EventObject
{
    private final String    label;
    private final Controls  controls;
    
    public ControlEvent( Object src, String label, Controls controls )
    {
        super( src );
        this.controls = controls;
        this.label = label;
    }
    
    public Controls getControls()
    {
        return controls;
    }
    
    public String getLabel()
    {
        return label;
    }
}
