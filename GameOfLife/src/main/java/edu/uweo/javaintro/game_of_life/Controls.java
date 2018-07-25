package edu.uweo.javaintro.game_of_life;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Controls
	implements Runnable
{
	private final JFrame	frame	= new JFrame( "Game of Life Controller" );
	private final List<ControlListener>	controlListeners	= new ArrayList<>();
	
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
					{ "Run", "", "" },
					{ "", "", "" },
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
}
