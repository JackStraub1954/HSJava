package edu.uweo.javaintro.game_of_life;

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
import javax.swing.SwingUtilities;

public class Controls
	implements Runnable
{
	public static final String RUN_LABEL           = "Run";
	public static final String INTERACTIVE_LABEL   = "Interactive";
	public static final String STOP_LABEL          = "Stop";
	public static final String EXIT_LABEL          = "Exit";
	public static final String STEP_LABEL          = "Step";
	public static final String CLEAR_LABEL         = "Clear";
	public static final String OPEN_LABEL          = "Open";
	public static final String SAVE_LABEL          = "Save";
    public static final String GPS_LABEL           = "Generations per Second";
    public static final String MAX_GPS_LABEL       = "Max Generations per Second";
	
	private String[]   userButtons     = { "", "", "" };
	
	private final JFrame       frame       = new JFrame( "Game of Life Controller" );
	private final MainPanel    mainPanel   = new MainPanel();
	private final JSlider      slider      = new JSlider();
	private final JTextField   maxGPSText  = new JTextField();
	private final JLabel       maxGPLabel  = new JLabel( MAX_GPS_LABEL );
	
	private final List<ControlListener>	controlListeners	= new ArrayList<>();
	
	public static void main( String[] args )
	{
		new Controls().start();
	}
	
	public void start()
	{
		SwingUtilities.invokeLater( this );
	}
	
	public void show( boolean state )
	{
		frame.setVisible( state );
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
		frame.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
		frame.setContentPane( new MainPanel() );
		frame.pack();
		frame.setVisible( true );
	}
	
	private class MainPanel extends JPanel
		implements ActionListener
	{
		public MainPanel()
		{
			super( new GridLayout( 3, 3 ) );
			String[][]	labels	=
			{
				{ "Run", "Step", "Clear" },
				{ "Save", "Open", "" },
				{ "", "", "" },
			};
			
			for ( String[] outer : labels )
				for ( String str : outer )
				{
					JButton	button	= new JButton( str );
					button.addActionListener( this );
					add( button );
				}
		}
		
		public void actionPerformed( ActionEvent evt )
		{
			for ( ControlListener listener : controlListeners )
				listener.controlActivated( evt );
		}
	}
	
	private class WestPanel extends JPanel
	{
	    public WestPanel()
	    {
    	    AbstractButton[]   controls    =
            {
                new JCheckBox( INTERACTIVE_LABEL ),
                new JButton( EXIT_LABEL ),
                new JButton( OPEN_LABEL ),
                new JButton( SAVE_LABEL ),
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
	        JPanel maxGPSpanel = new JPanel();
	        maxGPSpanel.add( new JLabel( MAX_GPS_LABEL ) );
	        JComponent[]   components  =
	            {
                    new JLabel( GPS_LABEL ),
                    slider,
	            };
	    }
	}
}
