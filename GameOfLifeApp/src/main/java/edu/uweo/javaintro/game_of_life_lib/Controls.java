package edu.uweo.javaintro.game_of_life_lib;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Encapsulation of the Game of Life control panel. The principal
 * components of the control panel are identified by unique labels.
 * They are:
 * <ol>
 * <li>
 * A set of predefined pushbuttons.
 * When a pushbutton is activated a <em>ControlEvent</em> is dispatched
 * to all registered <em>Control Listeners.</em>
 * </li>
 * <li>
 * A slider. A <em>ControlEvent</em> is dispatched
 * to all registered <em>ControlListeners</em> 
 * each time the slider is set to a new value.
 * </li>
 * <li>
 * A check box. Clicking on the check box does not generate an event,
 * but the value of the check box (true or false) can be obtained by calling
 * {@link #isInteractive() isInteractive()}.
 * </li>
 * <li>
 * An uneditable text box. The content of the text box can be set
 * by calling
 * {@link #setGenerationsPerSecond(double)} or
 * {@link #setGenerationsPerSecond(String)}. 
 * </li>
 * <li>
 * An editable text box. Typing in the text box does not generate an event,
 * but the value of the text box can be obtained by calling
 * {@link #getMaxGenerationsPerSecond()}.
 * </li>
 * <li>
 * A set of buttons with user-defined labels.
 * When activated, a <em>ControlEvent</em> is dispatched
 * to all registered <em>Control Listeners.</em>
 * </li>
 * </ol>
 * 
 * <p>
 * Note that, other than dispatching an event, the controls by themselves
 * <em>do nothing.</em> If you want an action to take place
 * as a result of a control being activated you will have to add
 * a ControlsListener and perform the action yourself.
 * 
 * @see ControlListener
 * @see ControlEvent
 */
public class Controls
    implements Runnable
{
    /** The label of the <em>Run</em> button. */
    public static final String RUN_LABEL           = "Run";
    
    /** The label of the checkbox. */
    public static final String INTERACTIVE_LABEL   = "Interactive";
    
    /** The label of the <em>Stop</em> button. */
    public static final String STOP_LABEL          = "Stop";
    
    /** The label of the <em>Exit</em> button. */
    public static final String EXIT_LABEL          = "Exit";
    
    /** The label of the <em>Step</em> button. */
    public static final String STEP_LABEL          = "Step";
    
    /** The label of the <em>Clear</em> button. */
    public static final String CLEAR_LABEL         = "Clear";
    
    /** The label of the <em>Open</em> button. */
    public static final String OPEN_LABEL          = "Open";
    
    /** The label of the <em>Save</em> button. */
    public static final String SAVE_LABEL          = "Save";
    
    /** The label of the <em>Apply</em> button. */
    public static final String APPLY_LABEL         = "Apply";        
    
    /** 
     * The label that identifies the 
     * <em>Generations/Second</em> text box. 
     */
    public static final String GPS_LABEL           = 
        "<html><body>Generations<br>/Second</body></html>";
    
    /** 
     * The label that identifies the 
     * <em>Max Generations/Second</em> text box. 
     */
    public static final String MAX_GPS_LABEL       = 
        "<html><body>Max Generations<br>/Second</body></html>";
    
    /** 
     * The name of the <em>Generations/Second</em> text box.
     * Mostly useful for testing. 
     */
    public static final String  GPS_TEXT_NAME       = "GPSTextBox";
    
    /** 
     * The name of the <em>Max Generations/Second</em> text box.
     * Mostly useful for testing. 
     */
    public static final String  MAX_GPS_TEXT_NAME   = "MaxGPSTextBox";
    
    /** Array of user-defined buttons. */
    private String[]   userButtons     = { "", "", "" };
    
    /** The frame that contains the GUI. */
    private final JFrame       frame       = 
        new JFrame( "Game of Life Controller" );
    
    /** The Generations/Second text box. */
    private final JTextField   gpsText     = new JTextField( "10", 3 );
    
    /** The Interactive checkbox. */
    private final JCheckBox    interactive = new JCheckBox( INTERACTIVE_LABEL );
    
    /** The slider. */
    private final JSlider      slider      = new JSlider();
    
    /** The Max Generations/Second text box. */
    private final JTextField   maxGPSText  = new JTextField( "5", 3 );
    
    /** Monitors action and change events */
    private final ControlMonitor    monitor = new ControlMonitor();
    
    /** 
     * The frame's content pane. Note that the main panel can't 
     * be created during construction, so it can't be final. 
     * The main panel is created in the run method.
     */
    private MainPanel    mainPanel;
    
    /** The ControlListeners. */
    private final List<ControlListener>    controlListeners    = new ArrayList<>();
    
    /**
     * Instantiates a new control panel.
     */
    public Controls()
    {
        gpsText.setName( GPS_TEXT_NAME );
        maxGPSText.setName( MAX_GPS_TEXT_NAME );
    }
    
    /**
     * Sets the array of user buttons.
     * Calling this method after <em>start()</em> is invoked has no effect.
     *
     * @param buttons the new user buttons
     */
    public void setUserButtons( String[] buttons )
    {
        userButtons = buttons;
    }
    
    /**
     * Creates and launches the control panel.
     */
    public void start()
    {
        SwingUtilities.invokeLater( this );
    }
    
    /**
     * Sets the visibility of the control panel.
     * Use <em>true</em> for visible, <em>false</em> for not visible.
     *
     * @param state the new state of visibility
     */
    public void show( boolean state )
    {
        frame.setVisible( state );
    }
    
    /**
     * Checks if the control panel is showing.
     *
     * @return true, if the control panel is showing
     */
    public boolean isShowing()
    {
        return frame.isShowing();
    }
    
    /**
     * Adds the given ControlListener to the list of
     * control listeners. All ControlListeners are notified
     * when a pushbutton is clicked
     * or the slider changes value.
     *
     * @param listener the given ControListener
     */
    public void addControlListener( ControlListener listener )
    {
        controlListeners.add( listener );
    }
    
    /**
     * Removes the given ControlListener from the list
     * of ControlListeners.
     *
     * @param listener the given ControlListener
     */
    public void removeControlListener( ControlListener listener )
    {
        controlListeners.remove( listener );
    }
            
    /** 
     * Thread run method. Constructs and displays the Controls UI.
     */
    public void run()
    {
        mainPanel = new MainPanel();
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setContentPane( mainPanel );
        frame.pack();
        
        frame.setVisible( true );
    }
    
    /**
     * Sets the MaxGenerationsPerSecond text box to the given value.
     *
     * @param max the given value
     */
    public void setMaxGenerationsPerSecond( double max )
    {
        String  dStr    = "" + max;
        setMaxGenerationsPerSecond( dStr );
    }
    
    /**
     * Sets the MaxGenerationsPerSecond text box to the given value.
     *
     * @param max the given value
     */
    public void setMaxGenerationsPerSecond( String max )
    {
        maxGPSText.setText( max );
    }
    
    /**
     * Gets the value of the MaxGenerationsPerSecond text box.
     *
     * @return the value of the MaxGenerationsPerSecond text box
     * 
     * @throws NumberFormatException if the value in the text box
     *         cannot be converted to double
     */
    public double getMaxGenerationsPerSecond()
        throws NumberFormatException
    {
        String  gpsStr  = maxGPSText.getText();
        double  gps     = Double.parseDouble( gpsStr );
        return gps;
    }
    
    /**
     * Sets the GenerationsPerSecond text box to the given value.
     *
     * @param max the given value
     */
    public void setGenerationsPerSecond( double max )
    {
        String  dStr    = "" + max;
        setGenerationsPerSecond( dStr );
    }
    
    /**
     * Sets the GenerationsPerSecond text box to the given value.
     *
     * @param max the given value
     */
    public void setGenerationsPerSecond( String max )
    {
        gpsText.setText( max );
    }
    
    /**
     * Gets the value of the GenerationsPerSecond text box.
     *
     * @return the value of the GenerationsPerSecond text box
     * 
     * @throws NumberFormatException the number format exception
     */
    public double getGenerationsPerSecond()
        throws NumberFormatException
    {
        String  gpsStr  = gpsText.getText();
        double  gps     = Double.parseDouble( gpsStr );
        return gps;
    }
    
    /**
     * Checks the state of the Interactive check box.
     *
     * @return true, if is interactive
     */
    public boolean isInteractive()
    {
        return interactive.isSelected();
    }
    
    /**
     * Sets the state of the Interactive check box.
     *
     * @param val the state of the Interactive check box.
     */
    public void setInteractive( boolean val )
    {
        interactive.setSelected( val );
    }
    
    /**
     * Sets the enabled state of a component in the control panel
     * associated with the given label.
     *
     * @param activate  the new enabled state.
     * @param label      the given label
     */
    public void setEnabled( boolean activate, String label )
    {
        ComponentList   list    = getComponentList( label );
        for ( Component comp : list )
            comp.setEnabled( activate );
    }
    
    /**
     * Toggles the enabled state of a component in the control panel
     * associated with the given label.
     *
     * @param label the given label
     */
    public void toggleEnabled( String label )
    {
        ComponentList   list    = getComponentList( label );
        for ( Component comp : list )
        {
            boolean activate    = !comp.isEnabled();
            comp.setEnabled( activate );
        }
    }
    
    /**
     * Checks the enabled state of the component in the control panel
     * with the given label.
     *
     * @param text the given label
     * 
     * @return true, if the component exists and is enabled
     */
    public boolean isEnabled( String text )
    {
        boolean         rval    = false;
        ComponentList   comps   = getComponentList( text );
        if ( comps.size() > 0 )
            rval = comps.get( 0 ).isEnabled();
        return rval;
    }
    
    /**
     * Sets the slider to the given value.
     * The new value should be a decimal number between 0 and 1.
     * If the value is outside of this range
     * the results will be unpredictable. 
     *
     * @param dVal the new slider value
     */
    public void setSliderValue( double dVal )
    {
        int iVal    = (int)( dVal * 100 + .5 );
        slider.setValue( iVal );
    }
    
    /**
     * Gets the slider value. The value will be in the range
     * 0 to 1.
     *
     * @return the slider value
     */
    public double getSliderValue()
    {
        double  rval    = slider.getValue() / 100.0;
        return rval;
    }
    
    /**
     * Disposes the control panel.
     * This is mainly useful for testing.
     */
    public void dispose()
    {
        frame.setContentPane( new JPanel() );
        frame.dispose();
    }
    
    /**
     * Gets a list of components associated with the given text.
     * The result will typically be a list of size 1.
     *
     * @param text the text
     * 
     * @return the list of components associated with the given text
     */
    private ComponentList getComponentList( String text )
    {
        ComponentList   list    = null;
        if ( text.equals( INTERACTIVE_LABEL ) )
            list = new ComponentList( slider );
        else if ( text.equals( GPS_LABEL))
            list = new ComponentList( gpsText ); 
        else if ( text.equals( MAX_GPS_LABEL))
            list = new ComponentList( maxGPSText );
        else
        {
            Predicate<Component>    pred    = 
                c -> c instanceof AbstractButton 
                     && ((AbstractButton)c).getText().equals( text );
            list = new ComponentList( frame, pred );
        }
        
        return list;
    }
    
    /**
     * Encapsulates the main panel (the content pane) of the frame associated
     * with this control panel. The main panel has a BorderLayout and three
     * JPanel children in the center, west and south positions.
     */
    @SuppressWarnings("serial")
    private class MainPanel extends JPanel
    {
        /**
         * Instantiates a new main panel.
         */
        public MainPanel()
        {
            super( new BorderLayout() );
            add( new WestPanel(), BorderLayout.WEST );
            add( new CenterPanel(), BorderLayout.CENTER );
            add( new SouthPanel(), BorderLayout.SOUTH );
        }
    }
    
    /**
     * The west child of the main panel. The panel will contain
     * the Interactive check box, several pre-defined pushbuttons and 
     * user-defined pushbuttons
     * arranged in a single, vertical column.
     */
    @SuppressWarnings("serial")
    private class WestPanel extends JPanel
    {
        
        /**
         * Instantiates a new WestPanel.
         */
        public WestPanel()
        {
            AbstractButton[]   controls    =
            {
                interactive,
                new JButton( EXIT_LABEL ),
                new JButton( OPEN_LABEL ),
                new JButton( SAVE_LABEL ),
                new JButton( CLEAR_LABEL ),
            };
            
            for ( AbstractButton button : controls )
            {
                button.addActionListener( monitor );
                add( button );
            }
            
            for ( String str : userButtons )
            {
                JButton button  = new JButton( str );
                button.addActionListener( monitor );
                add( button );
            }
            
            int     rows        = this.getComponentCount();
            setLayout( new GridLayout( rows, 1) );
        }
    }
    
    /**
     * The center child of the main panel. The panel will contain
     * the text boxes, slider and Apply button.
     */
    @SuppressWarnings("serial")
    private class CenterPanel extends JPanel
    {
        
        /**
         * Instantiates a new center panel.
         */
        public CenterPanel()
        {
            super( new GridLayout( 3, 1 ) );
            
            gpsText.setEditable( false );
            gpsText.setHorizontalAlignment(SwingConstants.RIGHT);
            maxGPSText.setHorizontalAlignment(SwingConstants.RIGHT);
            
            JPanel gpsPanel    = new JPanel();
            gpsPanel.add( new JLabel( GPS_LABEL ) );
            gpsPanel.add( gpsText );
            
            JPanel maxGPSPanel = new JPanel();
            maxGPSPanel.add( new JLabel( MAX_GPS_LABEL ) );
            maxGPSPanel.add( maxGPSText );
            JButton    applyButton = new JButton( APPLY_LABEL );
            applyButton.addActionListener( monitor );
            maxGPSPanel.add( applyButton );
            JComponent[]   components  =
            {
                gpsPanel,
                slider,
                maxGPSPanel,
            };
            
            slider.addChangeListener( monitor );
            for ( JComponent component : components )
                add( component );
        }
    }
    
    /**
     * The south child of the main panel. The panel will contain
     * the Run and Step buttons.
     */
    @SuppressWarnings("serial")
    private class SouthPanel extends JPanel
    {
        /**
         * Instantiates a new south panel.
         */
        public SouthPanel()
        {
            AbstractButton[]   components  = 
            {
                new JButton( RUN_LABEL ),
                new JButton( STEP_LABEL ),
            };
            
            for ( AbstractButton button : components )
            {
                add( button );
                button.addActionListener( monitor);
            }
        }
    }
    
    /**
     * Processes button clicks and slider changes
     */
    private class ControlMonitor
        implements ActionListener, ChangeListener
    {
        /**
         * Processes action events when a button
         * on the control panel is clicked.
         * All control listeners are notified of a ControlEvent.
         * The event object will contain the label on the button.
         * 
         * @param evt the event object that identifies this event
         */
        public void actionPerformed( ActionEvent evt )
        {
            Object        src     = evt.getSource();
            if ( !(src instanceof AbstractButton) )
                throw new RuntimeException( "Invalid source: " + src );
            AbstractButton button = (AbstractButton)src;
            String        label   = button.getText();
            ControlEvent  event   = 
                new ControlEvent( evt.getSource(), label, Controls.this );
            for ( ControlListener listener : controlListeners )
                listener.controlActivated( event );
        }

        /**
         * Processes change events when the slider
         * on the control panel is changed.
         * All control listeners are notified of a ControlEvent.
         * 
         * @param evt the event object that identifies this event
         */
        @Override
        public void stateChanged( ChangeEvent evt )
        {
            ControlEvent  event   = 
                new ControlEvent( evt.getSource(), GPS_LABEL, Controls.this );
            if ( !slider.getValueIsAdjusting() )
                for ( ControlListener listener : controlListeners )
                    listener.sliderAdjusted( event );
        }
    }
}
    
