package edu.uweo.javaintro.game_of_life;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Component;
import java.util.function.Predicate;

import javax.swing.AbstractButton;
import javax.swing.JSlider;

import org.junit.After;
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
        //     Controls.INTERACTIVE_LABEL,
        // Not implemented
        //     Controls.STOP_LABEL,
        Controls.EXIT_LABEL,
        Controls.STEP_LABEL,
        Controls.CLEAR_LABEL,
        Controls.OPEN_LABEL,
        Controls.SAVE_LABEL,
        Controls.APPLY_LABEL,
        // Can't get JTextField using its label
        //     Controls.GPS_LABEL,
        //     Controls.MAX_GPS_LABEL,
    };
    
    private Controls    controls;
    private Object      threader    = new Object();
    
    @Before
    public void setUp()
    {
        controls = new Controls();
    }
    
    @After
    public void tearDown()
    {
        controls.dispose();
        controls = null;
//        Utils.pause( 1000 );
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
        Predicate<Component>    pred    = c -> c instanceof JSlider;
        testSetIsEnabled( pred, label );
        
        label = Controls.GPS_LABEL;
        pred = c -> Controls.GPS_TEXT_NAME.equals( c.getName() );
        testSetIsEnabled( pred, label );
        
        label = Controls.MAX_GPS_LABEL;
        pred = c -> Controls.MAX_GPS_TEXT_NAME.equals( c.getName() );
        testSetIsEnabled( pred, label );
    }
    
    private void testSetIsEnabled( Predicate<Component> pred, String label )
    {
        Component   comp    = Utils.findComponent( pred );
        assertNotNull( comp );
        controls.setEnabled( true, label );
        assertTrue( controls.isEnabled( label ) );
        assertTrue( comp.isEnabled() );
        controls.setEnabled( false, label );
        assertFalse( controls.isEnabled( label ) );
        assertFalse( comp.isEnabled() );
        controls.setEnabled( true, label );
        assertTrue( controls.isEnabled( label ) );
        assertTrue( comp.isEnabled() );
    }

    @Test
    public void testToggleEnabled()
    {
        startControls();
        for ( String label : allLabels )
        {
            Predicate<Component>    pred    = new ButtonFinder( label );
            testToggleEnabled( pred, label );
        }
        
        String                  label   = Controls.INTERACTIVE_LABEL;
        Predicate<Component>    pred    = c -> c instanceof JSlider;
        testToggleEnabled( pred, label );
        
        label = Controls.GPS_LABEL;
        pred = c -> Controls.GPS_TEXT_NAME.equals( c.getName() );
        testToggleEnabled( pred, label );
        
        pred = c ->
            { String name = c.getName(); 
              return name != null && name.equals( Controls.MAX_GPS_TEXT_NAME ); 
            };
        label = Controls.MAX_GPS_LABEL;
        testToggleEnabled( pred, label );
    }
    
    @Test
    public void testSliderAdjusted()
    {
        startControls();
        Component   comp    = Utils.findComponent( c -> c instanceof JSlider );
        assertNotNull( comp );
        assertTrue( comp instanceof JSlider );
        JSlider slider  = (JSlider)comp;
        
        double          expDValue   = .75;
        int             expIValue   = (int)(expDValue * 100);
        EventListener   listener    = new EventListener( "" );
        controls.addControlListener( listener );
        controls.setSliderValue( expDValue );
        assertTrue( listener.isAdjusted() );
        assertEquals( expDValue, controls.getSliderValue(), .001 );
        assertEquals( expIValue, slider.getValue() );
    }
    
    private void testToggleEnabled( Predicate<Component> pred, String label )
    {
        Component   comp    = Utils.findComponent( pred );
        assertNotNull( comp );
        controls.setEnabled( true, label );
        assertTrue( comp.isEnabled() );
        controls.toggleEnabled( label );
        assertFalse( comp.isEnabled() );
        controls.toggleEnabled( label );
        assertTrue( comp.isEnabled() );
    }

    @Test
    public void testSetGetSliderValue()
    {
        double  val = .25;
        
        startControls();
        controls.setSliderValue( val );
        assertEquals( val, controls.getSliderValue(), .001 );
        
        val = .75;
        controls.setSliderValue( val );
        assertEquals( val, controls.getSliderValue(), .001 );
    }
    
    /**
     * Get test coverage for "component not found" in isEnabled()
     */
    @Test
    public void testIsEnabledFailure()
    {
        startControls();
        assertFalse( controls.isEnabled( "dummy" ) );
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
        private boolean adjusted        = false;
        private boolean result          = false;
        
        public EventListener( String label )
        {
            expectedLabel = label;
        }
        
        public void reset()
        {
            adjusted = false;
            clicked = false;
            result = false;
        }
        
        public boolean isAdjusted()
        {
            return adjusted;
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
            }
        }

        @Override
        public void sliderAdjusted( ControlEvent evt )
        {
            adjusted = true;
        }
    }

    private class ButtonFinder implements Predicate<Component>
    {
        private String text = "";
        
        public ButtonFinder( String text )
        {
            this.text = text;
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
