package com.judahstutorials.javaintro.towerofhanoi.utils;

import java.awt.geom.Rectangle2D;

import com.judahstutorials.javaintro.towerofhanoi.Disk;
import com.judahstutorials.javaintro.towerofhanoi.Pitch;
import com.judahstutorials.javaintro.towerofhanoi.Tower;

public class Animator
{
    private static final int    unengaged       = 0;
    private static final int    rising          = 1;
    private static final int    falling         = 2;
    private static final int    traversingRight = 3;
    private static final int    traversingLeft  = 4;
    
    private final Pitch         pitch;
    private final Disk          disk;
    private final Rectangle2D   rect        = new Rectangle2D.Double();
    private final Rectangle2D   auxRect     = new Rectangle2D.Double();
    private final int           direction;
    private final double        height;
    private final double        beginXco;
    private final double        beginYco;
    private final double        endXco;
    private final double        endYco;
    private final double        traverseYco;
    private final int           toRod;
    
    private int                 state       = unengaged;
    private double              xIncr       = 1;
    private double              yIncr       = 1;
    private double              nextXco;
    private double              nextYco;
    
    public Animator( Pitch pitch, int fromRod, int toRod )
    {
        this.pitch = pitch;
        this.toRod = toRod;
        disk = pitch.pop( fromRod );
        int     fromTop     = pitch.getDiskCount( fromRod );
        int     toTop       = pitch.getDiskCount( toRod );
        direction = toRod - fromRod;
        beginXco = Tower.getDiskXco( disk, fromRod );
        beginYco = Tower.getDiskYco( fromTop );
        endXco = Tower.getDiskXco( disk, toRod );
        endYco = Tower.getDiskYco( toTop );
        height = Tower.getComponentHeight();
        nextXco = beginXco;
        nextYco = beginYco;
        traverseYco = Tower.getRodYco() - 1.5 * height;
//        disk.setXco( nextXco );
//        disk.setYco( nextYco );
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
                state = direction > 0 ? traversingRight : traversingLeft;
            }
            break;
        case falling:
            if ( (nextYco += yIncr) >= endYco )
            {
                nextYco = endYco;
                state = unengaged;
            }
            break;
        case traversingRight:
            if ( (nextXco += xIncr) >= endXco )
            {
                nextXco = endXco;
                state = falling;
            }
            break;
        case traversingLeft:
            if ( (nextXco -= xIncr) <= endXco )
            {
                nextXco = endXco;
                state = falling;
            }
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
            (int)union.getX() - 1,
            (int)union.getY() - 1,
            (int)union.getWidth() + 2,
            (int)union.getHeight() + 2
        );
        Pitch.pause( 3 );
    }

}
