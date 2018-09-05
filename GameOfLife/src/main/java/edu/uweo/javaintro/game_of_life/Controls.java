package edu.uweo.javaintro.game_of_life;

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

// TODO: Auto-generated Javadoc
/**
 * The Class Controls.
 */
public class Controls
    implements Runnable
{
    /** The Constant RUN_LABEL. */
    public static final String RUN_LABEL           = "Run";
    
    /** The Constant INTERACTIVE_LABEL. */
    public static final String INTERACTIVE_LABEL   = "Interactive";
    
    /** The Constant STOP_LABEL. */
    public static final String STOP_LABEL          = "Stop";
    
    /** The Constant EXIT_LABEL. */
    public static final String EXIT_LABEL          = "Exit";
    
    /** The Constant STEP_LABEL. */
    public static final String STEP_LABEL          = "Step";
    
    /** The Constant CLEAR_LABEL. */
    public static final String CLEAR_LABEL         = "Clear";
    
    /** The Constant OPEN_LABEL. */
    public static final String OPEN_LABEL          = "Open";
    
    /** The Constant SAVE_LABEL. */
    public static final String SAVE_LABEL          = "Save";
    
    /** The Constant APPLY_LABEL. */
    public static final String APPLY_LABEL         = "Apply";        
    
    /** The Constant GPS_LABEL. */
    public static final String GPS_LABEL           = 
        "<html><body>Generations<br>/Second</body></html>";
    
    /** The Constant MAX_GPS_LABEL. */
    public static final String MAX_GPS_LABEL       = 
        "<html><body>Max Generations<br>/Second</body></html>";
    
    /** The Constant GPS_TEXT_NAME. */
    public static final String  GPS_TEXT_NAME       = "GPSTextBox";
    
    /** The Constant MAX_GPS_TEXT_NAME. */
    public static final String  MAX_GPS_TEXT_NAME   = "MaxGPSTextBox";
    
    /** The user buttons. */
    private String[]   userButtons     = { "", "", "" };
    
    /** The frame. */
    private final JFrame       frame       = 
        new JFrame( "Game of Life Controller" );
    
    /** The gps text. */
    private final JTextField   gpsText     = new JTextField( "10", 3 );
    
    /** The interactive. */
    private final JCheckBox    interactive = new JCheckBox( INTERACTIVE_LABEL );
    
    /** The slider. */
    private final JSlider      slider      = new JSlider();
    
    /** The max GPS text. */
    private final JTextField   maxGPSText  = new JTextField( "5", 3 );
    
    // The main panel can't be created during construction, so
    /** The main panel. */
    // it can't be final. The main panel is created in the run method.
    private MainPanel    mainPanel;
    
    /** The control listeners. */
    private final List<ControlListener>    controlListeners    = new ArrayList<>();
    
    /**
     * Instantiates a new controls.
     */
    public Controls()
    {
        gpsText.setName( GPS_TEXT_NAME );
        maxGPSText.setName( MAX_GPS_TEXT_NAME );
    }
    
    /**
     * Sets the user buttons.
     *
     * @param buttons the new user buttons
     */
    public void setUserButtons( String[] buttons )
    {
        userButtons = buttons;
    }
    
    /**
     * Start.
     */
    public void start()
    {
        SwingUtilities.invokeLater( this );
    }
    
    /**
     * Show.
     *
     * @param state the state
     */
    public void show( boolean state )
    {
        frame.setVisible( state );
    }
    
    /**
     * Checks if is showing.
     *
     * @return true, if is showing
     */
    public boolean isShowing()
    {
        return frame.isShowing();
    }
    
    /**
     * Adds the control listener.
     *
     * @param listener the listener
     */
    public void addControlListener( ControlListener listener )
    {
        controlListeners.add( listener );
    }
    
    /**
     * Removes the control listener.
     *
     * @param listener the listener
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
     * Sets the max generations per second.
     *
     * @param max the new max generations per second
     */
    public void setMaxGenerationsPerSecond( double max )
    {
        String  dStr    = "" + max;
        setMaxGenerationsPerSecond( dStr );
    }
    
    /**
     * Sets the max generations per second.
     *
     * @param max the new max generations per second
     */
    public void setMaxGenerationsPerSecond( String max )
    {
        maxGPSText.setText( max );
    }
    
    /**
     * Gets the max generations per second.
     *
     * @return the max generations per second
     * @throws NumberFormatException the number format exception
     */
    public double getMaxGenerationsPerSecond()
        throws NumberFormatException
    {
        String  gpsStr  = maxGPSText.getText();
        double  gps     = Double.parseDouble( gpsStr );
        return gps;
    }
    
    /**
     * Sets the generations per second.
     *
     * @param max the new generations per second
     */
    public void setGenerationsPerSecond( double max )
    {
        String  dStr    = "" + max;
        setGenerationsPerSecond( dStr );
    }
    
    /**
     * Sets the generations per second.
     *
     * @param max the new generations per second
     */
    public void setGenerationsPerSecond( String max )
    {
        gpsText.setText( max );
    }
    
    /**
     * Gets the generations per second.
     *
     * @return the generations per second
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
     * Checks if is interactive.
     *
     * @return true, if is interactive
     */
    public boolean isInteractive()
    {
        return interactive.isSelected();
    }
    
    /**
     * Sets the interactive.
     *
     * @param val the new interactive
     */
    public void setInteractive( boolean val )
    {
        interactive.setSelected( val );
    }
    
    /**
     * Sets the enabled.
     *
     * @param activate the activate
     * @param text the text
     */
    public void setEnabled( boolean activate, String text )
    {
        ComponentList   list    = getComponentList( text );
        for ( Component comp : list )
            comp.setEnabled( activate );
    }
    
    /**
     * Toggle enabled.
     *
     * @param text the text
     */
    public void toggleEnabled( String text )
    {
        ComponentList   list    = getComponentList( text );
        for ( Component comp : list )
        {
            boolean activate    = !comp.isEnabled();
            comp.setEnabled( activate );
        }
    }
    
    /**
     * Checks if is enabled.
     *
     * @param text the text
     * @return true, if is enabled
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
     * Sets the slider value.
     *
     * @param dVal the new slider value
     */
    public void setSliderValue( double dVal )
    {
        int iVal    = (int)( dVal * 100 + .5 );
        slider.setValue( iVal );
    }
    
    /**
     * Gets the slider value.
     *
     * @return the slider value
     */
    public double getSliderValue()
    {
        double  rval    = slider.getValue() / 100.0;
        return rval;
    }
    
    /**
     * Dispose.
     */
    public void dispose()
    {
        frame.setContentPane( new JPanel() );
        frame.dispose();
    }
    
    /**
     * Gets the component list.
     *
     * @param text the text
     * @return the component list
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
     * The Class MainPanel.
     */
    @SuppressWarnings("serial")
    private class MainPanel extends JPanel
        implements ActionListener, ChangeListener
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
        
        /* (non-Javadoc)
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
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

        /* (non-Javadoc)
         * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
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
    
    /**
     * The Class WestPanel.
     */
    @SuppressWarnings("serial")
    private class WestPanel extends JPanel
    {
        
        /**
         * Instantiates a new west panel.
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
                button.addActionListener( mainPanel );
                add( button );
            }
            
            for ( String str : userButtons )
            {
                JButton button  = new JButton( str );
                button.addActionListener( mainPanel );
                add( button );
            }
            
            int     rows        = this.getComponentCount();
            setLayout( new GridLayout( rows, 1) );
        }
    }
    
    /**
     * The Class CenterPanel.
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
            applyButton.addActionListener( mainPanel );
            maxGPSPanel.add( applyButton );
            JComponent[]   components  =
            {
                gpsPanel,
                slider,
                maxGPSPanel,
            };
            
            slider.addChangeListener( mainPanel );
            for ( JComponent component : components )
                add( component );
        }
    }
    
    /**
     * The Class SouthPanel.
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
                button.addActionListener( mainPanel);
            }
        }
    }
}
