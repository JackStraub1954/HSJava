package edu.uweo.javaintro.game_of_life_lib;

import java.util.EventObject;

/**
 * Encapsulates those events which are propagated by the
 * Controls user interface. Each event is associated with
 * the activation of an AWT/Swing component of the user
 * interface. A specific event can be identified by examining 
 * the label associated with a component.
 * 
 * @see Controls
 */
@SuppressWarnings("serial")
public class ControlEvent extends EventObject
{
    
    /** 
     * The label that identifies the user interface component
     * that has been activated.
     */
    private final String    label;
    
    /** The Controls object that contains the activated component. */
    private final Controls  controls;
    
    /**
     * Instantiates a new ControlEvent.
     *
     * @param src       the source of the event
     * @param label     the label associated with the source
     * @param controls  the Controls object that contains the source
     */
    public ControlEvent( Object src, String label, Controls controls )
    {
        super( src );
        this.controls = controls;
        this.label = label;
    }
    
    /**
     * Gets the Controls object associated with this event.
     *
     * @return the controls
     */
    public Controls getControls()
    {
        return controls;
    }
    
    /**
     * Gets the label associated with the component associated
     * with this event..
     *
     * @return the label
     */
    public String getLabel()
    {
        return label;
    }
}
