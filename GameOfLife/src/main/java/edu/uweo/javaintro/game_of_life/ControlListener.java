package edu.uweo.javaintro.game_of_life;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving control events.
 * The class that is interested in processing a control
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addControlListener</code> method. When
 * the control event occurs, that object's appropriate
 * method is invoked.
 *
 * @see ControlEvent
 */
public interface ControlListener 
{
    /**
     * Invoked when a control is activated on the Controls panel; 
     * that is, when a JButton or 
     * JToggleButton is clicked.
     * This method is <em>not</em> invoked when the slider is adjusted.
     *
     * @param evt the object that describes the event.
     * 
     * @see ControlEvent
     * @see #sliderAdjusted(ControlEvent)
     */
    public void controlActivated( ControlEvent evt );
    
    /**
     * Invoked when the slider on the Controls panel is adjusted.
     *
     * @param evt the object that describes the event.
     */
    public void sliderAdjusted( ControlEvent evt );
}
