package edu.uweo.javaintro.game_of_life;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.function.Predicate;

import javax.swing.AbstractButton;
import javax.swing.JFrame;

/**
 * An object of this class encapsulates a list of
 * java.awt.Components that match some criteria.
 * The criteria is determined via a Predicate&lt;Component&gt;
 * supplied by the user.
 */
class ComponentList extends ArrayList<Component>
{
    
    /** 
     * The predicate supplied by the user.
     * May be null.
     */
    private final Predicate<Component>  pred;
    
    /**
     * Instantiates a new component list.
     */
    public ComponentList()
    {
        pred = null;
    }
    
    /**
     * Instantiates a new component list
     * initialized with the given component.
     * The predicate for this list is explicitly 
     * set to null. If the given component is null,
     * it will not be added to the list.
     *
     * @param comp the given component
     */
    public ComponentList( Component comp )
    {
        pred = null;
        if ( comp != null )
            add( comp );
    }
    
    /**
     * Instantiates a new component list with the given predicate.
     * The given frame is used to initialize the list
     * by applying the predicate to all Components encapsulated
     * by the given frame. Neither the frame 
     * nor the predicate may not be null.
     *
     * @param frame the given frame
     * @param pred  the given predicate
     * 
     * @throws NullPointerException if either argument is null
     */
    public ComponentList( JFrame frame, Predicate<Component> pred )
        throws NullPointerException
    {
        super();
        this.pred = pred;
        findComponents( frame.getContentPane() );
    }
    
    /**
     * Adds to this list all descendant Components of the given container
     * that satisfy the criteria determined by this object's predicate.
     * Neither the predicate nor the given container may be null.
     *
     * @param container the container
     * 
     * @throws NullPointerException if either argument is null
     */
    private void findComponents( Container container )
        throws NullPointerException
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
