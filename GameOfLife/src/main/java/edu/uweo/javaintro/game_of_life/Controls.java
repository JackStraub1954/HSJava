package edu.uweo.javaintro.game_of_life;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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

public class Controls
	implements Runnable
{
    public static final String NEW_LINE            = System.lineSeparator();
	public static final String RUN_LABEL           = "Run";
	public static final String INTERACTIVE_LABEL   = "Interactive";
	public static final String STOP_LABEL          = "Stop";
	public static final String EXIT_LABEL          = "Exit";
	public static final String STEP_LABEL          = "Step";
	public static final String CLEAR_LABEL         = "Clear";
	public static final String OPEN_LABEL          = "Open";
	public static final String SAVE_LABEL          = "Save";
	public static final String APPLY_LABEL         = "Apply";        
    public static final String GPS_LABEL           = 
        "<html><body>Generations<br>/Second</body></html>";
    public static final String MAX_GPS_LABEL       = 
        "<html><body>Max Generations<br>/Second</body></html>";
	
	private String[]   userButtons     = { "", "", "" };
	
	private final JFrame       frame       = new JFrame( "Game of Life Controller" );
	private final JTextField   gpsText     = new JTextField( "10", 3 );
    private final JCheckBox    interactive = new JCheckBox( INTERACTIVE_LABEL );
	private final JSlider      slider      = new JSlider();
	private final JTextField   maxGPSText  = new JTextField( "5", 3 );
    private final MainPanel    mainPanel   = new MainPanel();
	
	private final List<ControlListener>	controlListeners	= new ArrayList<>();
	
	public void setUserButtons( String[] buttons )
	{
	    userButtons = buttons;
	}
	
	public void start()
	{
		SwingUtilities.invokeLater( this );
	}
	
	public void show( boolean state )
	{
		frame.setVisible( state );
	}
	
	public boolean isShowing()
	{
	    return frame.isShowing();
	}
	
	public void addControlListener( ControlListener listener )
	{
		controlListeners.add( listener );
	}
	
	public void removeControlListener( ControlListener listener )
	{
		controlListeners.remove( listener );
	}
			
	public void run()
	{
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setContentPane( new MainPanel() );
		frame.pack();
		frame.setVisible( true );
	}
    
    public void setMaxGenerationsPerSecond( double max )
    {
        String  dStr    = "" + max;
        setMaxGenerationsPerSecond( dStr );
    }
    
    public void setMaxGenerationsPerSecond( String max )
    {
        maxGPSText.setText( max );
    }
    
    public double getMaxGenerationsPerSecond()
        throws NumberFormatException
    {
        String  gpsStr  = maxGPSText.getText();
        double  gps     = Double.parseDouble( gpsStr );
        return gps;
    }
    
    public void setGenerationsPerSecond( double max )
    {
        String  dStr    = "" + max;
        setGenerationsPerSecond( dStr );
    }
    
    public void setGenerationsPerSecond( String max )
    {
        gpsText.setText( max );
    }
    
    public double getGenerationsPerSecond()
        throws NumberFormatException
    {
        String  gpsStr  = gpsText.getText();
        double  gps     = Double.parseDouble( gpsStr );
        return gps;
    }
    
    public boolean isInteractive()
    {
        return interactive.isSelected();
    }
    
    public void setInteractive( boolean val )
    {
        interactive.setSelected( val );
    }
    
    public void setEnabled( boolean activate, String text )
    {
        ButtonList  buttons = new ButtonList( frame, text );
        for ( AbstractButton button : buttons )
            button.setEnabled( activate );
    }
    
    public void toggleEnabled( String text )
    {
        ButtonList  buttons = new ButtonList( frame, text );
        for ( AbstractButton button : buttons )
        {
            boolean activate    = !button.isEnabled();
            button.setEnabled( !activate );
        }
    }
    
    public boolean isEnabled( String text )
    {
        boolean     rval    = false;
        ButtonList  buttons = new ButtonList( frame, text );
        if ( buttons.size() == 0 )
            rval = buttons.get( 0 ).isEnabled();
        return rval;
    }
    
    public double getSliderValue()
    {
        double  rval    = slider.getValue() / 100.0;
        return rval;
    }
	
	private class MainPanel extends JPanel
		implements ActionListener, ChangeListener
	{
		public MainPanel()
		{
			super( new BorderLayout() );
			add( new WestPanel(), BorderLayout.WEST );
			add( new CenterPanel(), BorderLayout.CENTER );
			add( new SouthPanel(), BorderLayout.SOUTH );
		}
		
		public void actionPerformed( ActionEvent evt )
		{
		    Object        src     = evt.getSource();
		    if ( !(src instanceof AbstractButton ) )
		        throw new RuntimeException( "Invalid source: " + src );
		    AbstractButton button = (AbstractButton)src;
		    String        label   = button.getText();
		    ControlEvent  event   = new ControlEvent( evt.getSource(), label, Controls.this );
			for ( ControlListener listener : controlListeners )
				listener.controlActivated( event );
		}

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
	
	private class WestPanel extends JPanel
	{
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
	
	private class CenterPanel extends JPanel
	{
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
	
	private class SouthPanel extends JPanel
	{
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
