package edu.uweo.javaintro.game_of_life;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.function.Predicate;

import javax.swing.AbstractButton;
import javax.swing.JFrame;

class ComponentList extends ArrayList<Component>
{
    private final Predicate<Component>  pred;
    
    public ComponentList()
    {
        pred = null;
    }
    
    public ComponentList( Component comp )
    {
        pred = null;
        add( comp );
    }
    
    public ComponentList( JFrame frame, Predicate<Component> pred )
    {
        super();
        this.pred = pred;
        findComponents( frame.getContentPane() );
    }
    
    private void findComponents( Container container )
    {
        Component[] components  = container.getComponents();
        
        for ( int inx = 0 ; inx < components.length ; ++inx )
        {
            Component   comp    = components[inx];
            if ( pred.test( comp ) )
                add( comp );
            else if ( comp instanceof Container )
                findComponents( (Container)comp );
            else
                ;
        }
    }
}
