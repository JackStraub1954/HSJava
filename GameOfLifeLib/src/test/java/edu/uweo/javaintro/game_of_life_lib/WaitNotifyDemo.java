package edu.uweo.javaintro.game_of_life_lib;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class WaitNotifyDemo
    implements Runnable, ActionListener
{
    private JButton button      = new JButton( "Push Me" );
    private Object  threader    = new Object();
    private JFrame  frame       = new JFrame();
    
    public static void main(String[] args)
    {
        new WaitNotifyDemo().execute();
    }
    
    private void execute()
    {
        SwingUtilities.invokeLater( this );
        try
        {
            Thread.sleep( 2000 ); // wait for JFrame to settle down
            Robot   robot   = new Robot();
            Point   loc     = button.getLocationOnScreen();
            loc.x += button.getWidth() / 2;
            loc.y += button.getHeight() / 2;
            
            synchronized( threader )
            {
                robot.mouseMove( loc.x, loc.y );
                robot.mousePress( InputEvent.BUTTON1_DOWN_MASK );
                Thread.sleep( 1000 );
                robot.mouseRelease( InputEvent.BUTTON1_DOWN_MASK );
                threader.wait();
            }
        }
        catch ( InterruptedException exc )
        {
            System.out.println( "interrupted" );
        }
        catch ( AWTException exc )
        {
            exc.printStackTrace();
        }
        System.out.println( "awake" );
    }

    @Override
    public void run()
    {
        JPanel  panel   = new JPanel();
        
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        button.addActionListener( this );
        panel.add( button );
        frame.setContentPane( panel );
        frame.pack();
        frame.setVisible( true );
    }

    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        synchronized ( threader )
        {
            System.out.println( "pushed" );
            threader.notify();
        }
    }
}
