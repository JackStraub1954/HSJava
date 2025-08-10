package com.judahstutorials.javaintro.towerofhanoi.utils;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import com.judahstutorials.javaintro.towerofhanoi.Disk;
import com.judahstutorials.javaintro.towerofhanoi.Pitch;
import com.judahstutorials.javaintro.towerofhanoi.Tower;

public class Animator
{
    /**
     * The state in which the animation is not in progress.
     */
    private static final int    unengaged       = 0;
    /**
     * The state in which the disk being animated
     * is rising from its position on the source rod.
     */
    private static final int    rising          = 1;
    /**
     * The state in which the disk being animated
     * is falling to its position on the source rod.
     */
    private static final int    falling         = 2;
    /**
     * The state in which the disk being animated
     * laterally traversing from the source rod
     * to the destination rod.
     */
    private static final int    traversing      = 3;
    
    /**
     * The Pitch on which the animation is occurring.
     */
    private final Pitch         pitch;
    /**
     * The disk being animated.
     */
    private final Disk          disk;
    /**
     * The bounding rectangle of the disk registered as
     * "auxiliary" with the Pitch.
     * @see Pitch#addAuxDisk(Disk).
     */
    private final Rectangle2D   auxRect     = new Rectangle2D.Double();
    /**
     * Temporary rectangle to be used in formulating the clip region
     * when repainting the Pitch in order to relocate the disk being
     * animated. Declared as an instance variable so that it does
     * not need to be instantiated with every animation frame.
     */
    private final Rectangle2D   rect        = new Rectangle2D.Double();
    /**
     * The x-coordinate of the disk at the start of the animation.
     */
    private final double        beginXco;
    /**
     * The y-coordinate of the disk at the start of the animation.
     */
    private final double        beginYco;
    /**
     * The horizontal midpoint between the source and destination rods.
     */
    private final double        midXco;
    /**
     * The x-coordinate of the disk at the end of the animation.
     */
    private final double        endXco;
    /**
     * The y-coordinate of the disk at the end of the animation.
     */
    private final double        endYco;
    /**
     * The y-coordinate at which horizontal traversal begins
     * and ends. This will be approximately the y-coordinate of the
     * top of the rods minus the height of the disk.
     */
    private final double        traverseYco;
    /**
     * x0 in the formula to calculate the parametric traversal
     * of a Bezier curve. This is the same value as beginXco, 
     * but encapsulating the value in a distinct variable aids
     * readability.
     */
    private final double        arcXco0;
    /**
     * y0 in the formula to calculate the parametric traversal
     * of a Bezier curve. This is the same value as traverseYco, 
     * but encapsulating the value in a distinct variable aids
     * readability.
     */
    private final double        arcYco0;
    /**
     * x1 in the formula to calculate the parametric traversal
     * of a Bezier curve. This is the same value as midXco, 
     * but encapsulating the value in a distinct variable aids
     * readability.
     */
    private final double        arcXco1;
    /**
     * y1 in the formula to calculate the parametric traversal
     * of a Bezier curve. This value controls the height of the
     * curve, but how is not straightforward. I experimented with
     * different values till I got a curve that scraped the top
     * of the application window.
     */
    private final double        arcYco1;
    /**
     * x2 in the formula to calculate the parametric traversal
     * of a Bezier curve. This is the same value as endXco, 
     * but encapsulating the value in a distinct variable aids
     * readability.
     */
    private final double        arcXco2;
    /**
     * y2 in the formula to calculate the parametric traversal
     * of a Bezier curve. This is the same value as traversalYco, 
     * but encapsulating the value in a distinct variable aids
     * readability.
     */
    private final double        arcYco2;
    private final int           toRod;
    
    private int                 state       = unengaged;
    private double              yIncr       = 1;
    private double              nextXco;
    private double              nextYco;
    private double              nextTee     = 0;
    private double              teeIncr     = .005;
    
    public Animator( Pitch pitch, int fromRod, int toRod )
    {
        this.pitch = pitch;
        this.toRod = toRod;
        disk = pitch.pop( fromRod );
        int     fromTop     = pitch.getDiskCount( fromRod );
        int     toTop       = pitch.getDiskCount( toRod );
        beginXco = Tower.getDiskXco( disk, fromRod );
        beginYco = Tower.getDiskYco( fromTop );
        endXco = Tower.getDiskXco( disk, toRod );
        endYco = Tower.getDiskYco( toTop );
        midXco = beginXco + (endXco - beginXco) / 2;
        nextXco = beginXco;
        nextYco = beginYco;
        nextTee = 0;
        traverseYco = 
            Tower.getRodYco() - 1.5 * Tower.getComponentHeight();
        arcXco0 = beginXco;
        arcYco0 = traverseYco;
        arcXco1 = midXco;
        arcYco1 = -traverseYco;
        arcXco2 = endXco;
        arcYco2 = arcYco0;
        disk.setLocation( nextXco, nextYco );
        state = rising;
    }
    
    public void animate()
    {
        pitch.addAuxDisk( disk );
        while ( state != unengaged )
        {
            next();
        }
        pitch.removeAuxDisk( disk );
        pitch.push( disk, toRod );
        state = unengaged;
    }
    
    private void computeNextCoordinates()
    {
        switch( state )
        {
        case rising:
            if ( (nextYco -= yIncr) < traverseYco )
            {
                nextYco = traverseYco;
//                state = direction > 0 ? traversingRight : traversingLeft;
                state = traversing;
            }
            break;
        case falling:
            if ( (nextYco += yIncr) >= endYco )
            {
                nextYco = endYco;
                state = unengaged;
            }
            break;
        case traversing:
            if ( (nextTee += teeIncr) > 1 )
            {
                nextTee = 1;
                state = falling;
            }
            Point2D point   = getPointOnCurve();
            nextXco = point.getX();
            nextYco = point.getY();
            break;
        }
    }
    
    private void next()
    {
        computeNextCoordinates();
        auxRect.setRect( rect );
        rect.setRect( disk.getBounds() );
        disk.setLocation( nextXco, nextYco );
        Rectangle2D union   = rect.createUnion( auxRect );
        pitch.repaint(
            (int)union.getX() - 2,
            (int)union.getY() - 2,
            (int)union.getWidth() + 4,
            (int)union.getHeight() + 4
        );
        Pitch.pause( 3 );
    }

    
    private Point2D getPointOnCurve()
    {
        double xco = 
            (1 - nextTee) * (1 - nextTee) * arcXco0
            + 2 * (1 - nextTee) * nextTee * arcXco1 
            + nextTee * nextTee * arcXco2;
        double yco = 
            (1 - nextTee) * (1 - nextTee) * arcYco0 
            + 2 * (1 - nextTee) * nextTee * arcYco1 
            + nextTee * nextTee * arcYco2;
        return new Point2D.Double(xco, yco);
    }
}
