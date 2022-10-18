package edu.uweo.javaintro.game_of_life_lib;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.awt.Button;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.function.Predicate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.uweo.javaintro.game_of_life_lib.ComponentList;

public class ComponentListTest
{

    @Test
    public void testComponentList()
    {
        // Get test coverage on the default constructor
        ComponentList   list    = new ComponentList();
        assertEquals( 0, list.size() );
    }

    @Test
    public void testComponentListComponent()
    {
        Component       comp    = new Button( "button" );
        ComponentList   list    = new ComponentList( comp );
        assertEquals( 1, list.size() );
        assertEquals( comp, list.get( 0 ) );
    }

    @Test
    public void testComponentListJFramePredicateOfComponent()
    {
        JFrame  frame       = new JFrame();
        JPanel  pane        = new JPanel( new GridLayout( 2, 1 ) );
        JPanel  north       = new JPanel();
        JPanel  south       = new JPanel();
        String  text1       = "Button 1";
        String  text2       = "Button 2";
        String  text3       = "Button 3";
        String  text4       = "Label 1";
        String  text5       = "Label 2";
        String  text6       = "Label 3";
        String  notFound    = "Component not found";
        JButton button1     = new JButton( text1 );
        JButton button2     = new JButton( text2 );
        JButton button3     = new JButton( text3 );
        JLabel  label1      = new JLabel( text4 );
        JLabel  label2      = new JLabel( text5 );
        JLabel  label3      = new JLabel( text6 );
        
        north.add( button1 );
        north.add( button2 );
        north.add( button3 );
        south.add( label1 );
        south.add( label2 );
        south.add( label3 );
        
        // There's a test in the production code for "instanceof Container".
        // Since every JComponent is a container, the negative branch is
        // never selected. Add a non-JComponent component so the the negative
        // branch is exercised.
        south.add( new Button( "OddBall" ) );
        
        pane.add( north );
        pane.add( south );
        
        frame.setContentPane( pane );
        frame.pack();
//        frame.setVisible( true );
//        Utils.pause();
        
        JPanel[]    panels  = { north, south };
        JButton[]   buttons = { button1, button2, button3 };
        JLabel[]    labels  = { label1, label2, label3 };
        
        Predicate<Component>    pred    = c -> c instanceof JPanel;
        ComponentList           list    = new ComponentList( frame, pred );
        assertEquals( panels.length, list.size() );
        for ( JPanel panel : panels )
            assertTrue( list.contains( panel ) );
        
        pred = c -> c instanceof JLabel;
        list = new ComponentList( frame, pred );
        assertEquals( labels.length, list.size() );
        for ( JLabel label : labels )
        {
            assertEquals( labels.length, list.size() );
            assertTrue( list.contains( label ) );
            
            pred = c -> c instanceof JLabel &&
                   ((JLabel)c).getText().equals( label.getText() );
            ComponentList   innerList   = new ComponentList( frame, pred );
            assertEquals( 1, innerList.size() );
            assertTrue( innerList.contains( label ) );
        }
        
        pred = c -> c instanceof JButton;
        list = new ComponentList( frame, pred );
        assertEquals( buttons.length, list.size() );
        for ( JButton button : buttons )
        {
            assertEquals( buttons.length, list.size() );
            assertTrue( list.contains( button ) );
            
            pred = c -> c instanceof JButton &&
                   ((JButton)c).getText().equals( button.getText() );
            ComponentList   innerList   = new ComponentList( frame, pred );
            assertEquals( 1, innerList.size() );
            assertTrue( innerList.contains( button ) );
        }
    }
}
