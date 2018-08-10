package edu.uweo.javaintro.game_of_life;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JFrame;

class ButtonList extends ArrayList<AbstractButton>
{
    private final String    label;
    
    public ButtonList( JFrame frame, String label )
    {
        super();
        this.label = label;
        findButtons( frame.getContentPane() );
    }
    
    private void findButtons( Container container )
    {
        Component[] components  = container.getComponents();
        
        for ( int inx = 0 ; inx < components.length ; ++inx )
        {
            Component   comp    = components[inx];
            if ( comp instanceof AbstractButton )
            {
                AbstractButton  temp    = (AbstractButton)comp;
                if ( label.equals( temp.getText() ) )
                    add( temp );
                    
            }
            else if ( comp instanceof Container )
                findButtons( (Container)comp );
            else
                ;
        }
    }
}
