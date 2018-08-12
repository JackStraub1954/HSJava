package edu.uweo.javaintro.game_of_life;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Component;
import java.util.function.Predicate;

import javax.swing.AbstractButton;
import javax.swing.JSlider;

import org.junit.Before;
import org.junit.Test;

public class ControlsTest
{
    private static final String     TEST_LABEL1     = "Test Label 1";
    private static final String     TEST_LABEL2     = "Test Label 2";
    private static final String     TEST_LABEL3     = "Test Label 3";
    
    private static final String[]   allLabels       =
    { 
        Controls.RUN_LABEL,
        // Can't get JSlider using its label
        //Controls.INTERACTIVE_LABEL,
        Controls.STOP_LABEL,
        Controls.EXIT_LABEL,
        Controls.STEP_LABEL,
        Controls.CLEAR_LABEL,
        Controls.OPEN_LABEL,
        Controls.SAVE_LABEL,
        Controls.APPLY_LABEL,
        Controls.GPS_LABEL,
        Controls.MAX_GPS_LABEL,
    };
    
    private Controls    controls;
    private Object      threader    = new Object();
    
    @Before
    public void setUp()
    {
        controls = new Controls();
    }

    @Test
    public void testSetUserButtons()
    {
        String[]        labels      = { TEST_LABEL1, TEST_LABEL2, TEST_LABEL3 };
        
        controls.setUserButtons( labels );
        startControls();
        for ( String label : labels )
        {
            Predicate<Component>    pred    = new ButtonFinder( label );
            Component               comp    = Utils.findComponent( pred );
            assertNotNull( comp );
            assertTrue( comp instanceof AbstractButton );
            
            EventListener   listener    = new EventListener( label );
            controls.addControlListener( listener );
            AbstractButton  button      = (AbstractButton)comp;
            button.addActionListener( e -> System.out.println( "clicked" ) );
            // FIXME    wait/notify logic isn't working
            synchronized ( threader )
            {
                button.doClick();
                try
                {
                    threader.wait( 5 );
                }
                catch ( InterruptedException exc )
                {
                }
            }
            
            System.out.println( listener.isClicked() );
            System.out.println( listener.test() );
            
            assertTrue( listener.isClicked() );
            assertTrue( listener.test() );
        }
    }

    @Test
    public void testStart()
    {
        controls.start();
        Utils.pause( 500 );
        assertTrue( controls.isShowing() );        
    }

    @Test
    public void testShow()
    {
        startControls();
        assertTrue( controls.isShowing() );
        controls.show( false );
        assertFalse( controls.isShowing() );
        controls.show( true );
        assertTrue( controls.isShowing() );
    }

    @Test
    public void testAddRemoveControlListener()
    {
        startControls();
        
        String                  label   = Controls.EXIT_LABEL;
        Predicate<Component>    pred    = new ButtonFinder( label );
        Component               comp    = Utils.findComponent( pred );
        assertNotNull( comp );
        assertTrue( comp instanceof AbstractButton );
        
        EventListener   listener    = new EventListener( label );
        AbstractButton  button      = (AbstractButton)comp;

        controls.addControlListener( listener );
        synchronized ( threader )
        {
            button.doClick();
            try
            {
                threader.wait( 15 );
            }
            catch ( InterruptedException exc )
            {
            }
        }
        assertTrue( listener.clicked );

        listener.reset();
        controls.removeControlListener( listener );
        synchronized ( threader )
        {
            button.doClick();
            try
            {
                threader.wait( 15 );
            }
            catch ( InterruptedException exc )
            {
            }
        }
        assertFalse( listener.clicked );
    }

    @Test
    public void testSetGetMaxGenerationsPerSecondString()
    {
        double  expMax     = 5.1;
        controls.setMaxGenerationsPerSecond( expMax );
        double  actMax      = controls.getMaxGenerationsPerSecond();
        assertEquals( expMax, actMax, .001 );
    }

    @Test
    public void testSetGetGenerationsPerSecondDouble()
    {
        double  expGPS     = 5.1;
        controls.setGenerationsPerSecond( expGPS );
        double  actGPS      = controls.getGenerationsPerSecond();
        assertEquals( expGPS, actGPS, .001 );
    }

    @Test
    public void testSetGenerationsPerSecondString()
    {
        double  expGPS     = 5.1;
        controls.setGenerationsPerSecond( "" + expGPS );
        double  actGPS      = controls.getGenerationsPerSecond();
        assertEquals( expGPS, actGPS, .001 );
    }

    @Test
    public void testSetIsInteractive()
    {
        controls.setInteractive( false );
        assertFalse( controls.isInteractive() );
        
        controls.setInteractive( true );
        assertTrue( controls.isInteractive() );
    }

    @Test
    public void testSetIsEnabled()
    {
        startControls();
        for ( String label : allLabels )
        {
            Predicate<Component>    pred    = new ButtonFinder( label );
            testSetIsEnabled( pred, label );
        }
        
        String                  label   = Controls.INTERACTIVE_LABEL;
        Predicate<Component>    pred    = new ButtonFinder( label );
        testSetIsEnabled( pred, label );
    }
    
    private void testSetIsEnabled( Predicate<Component> pred, String label )
    {
        Component               comp    = Utils.findComponent( pred );
        assertNotNull( comp );
        controls.setEnabled( true, label );
        assertTrue( comp.isEnabled() );
        controls.setEnabled( false, label );
        assertFalse( comp.isEnabled() );
        controls.setEnabled( true, label );
        assertTrue( comp.isEnabled() );
    }

    @Test
    public void testToggleEnabled()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testGetSliderValue()
    {
        fail("Not yet implemented");
    }
    
    private void startControls()
    {
        controls.start();
        while ( !controls.isShowing() )
            Utils.pause( 2 );
    }
    
    private class EventListener implements ControlListener
    {
        private String  expectedLabel   = null;
        private boolean clicked         = false;
        private boolean result          = false;
        
        public EventListener( String label )
        {
            expectedLabel = label;
        }
        
        public void reset()
        {
            clicked = false;
            result = false;
        }
        
        public boolean isClicked()
        {
            return clicked;
        }
        
        public boolean test()
        {
            return result;
        }
        
        @Override
        public void controlActivated( ControlEvent evt )
        {
            synchronized ( threader )
            {
                String  actualLabel = evt.getLabel();
                clicked = true;
                result = expectedLabel.equals( actualLabel );
                threader.notify();
                System.out.println( "*** done: " + actualLabel );
            }
        }

        @Override
        public void sliderAdjusted(ControlEvent evt)
        {
            // TODO Auto-generated method stub
            
        }
        
    }

    private class ButtonFinder implements Predicate<Component>
    {
        private String text = "";
        
        public ButtonFinder()
        {
        }
        
        public ButtonFinder( String text )
        {
            this.text = text;
        }
        
        public void setText( String text )
        {
            this.text = text;
        }
        
        public String getText()
        {
            return text;
        }
        
        public boolean test( Component comp )
        {
            boolean result  = false;
            if ( comp instanceof AbstractButton )
            {
                AbstractButton  button  = (AbstractButton)comp;
                String          label   = button.getText();
                result = text.equals( label );
            }
            
            return result;
        }
    }
}
