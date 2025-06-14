package com.judahstutorials.javaintro.towerofhanoi;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * Encapsulates a rod that can hold a stack of disks
 * in the Tower of Hanoi project.
 */
public class Rod implements Iterable<Disk>
{
    /**
     * The stack of disks.
     */
    private final Deque<Disk>   stack   = new ArrayDeque<>();
    
    /**
     * Default constructor.
     * Present here so that it can be documented,
     * because "Javadoc expects a comment for every constructor, 
     * including the default one."
     */
    public Rod()
    {
    }
    
    /**
     * Pushes a disk onto the top of the rod.
     * 
     * @param disk  the disk to push
     */
    public void push( Disk disk )
    {
        stack.push( disk );
    }
    
    /**
     * Removes a disk from the top of the rod.
     * Returns null if the rod is empty.
     * 
     * @return  
     *      the disk removed from the top of the rod,
     *      or null if the rod is empty
     */
    public Disk pop()
    {
        Disk    disk    = null;
        if ( !stack.isEmpty() )
            disk = stack.pop();
        return disk;
    }
    
    /**
     * Gets the disk at the top of the rod
     * without returning it.
     * Returns null if the rod is empty.
     * 
     * @return  
     *      the disk at the top of the rod,
     *      or null if the rod is empty
     */
    public Disk peek()
    {
        Disk    disk    = null;
        if ( !stack.isEmpty() )
            disk = stack.peek();
        return disk;
    }
    
    /**
     * Returns the number of disks on this rod.
     * @return the number of disks on this rod
     */
    public int getDiskCount()
    {
        int count   = stack.size();
        return count;
    }
    
    /**
     * Returns true if this rod is empty.
     * @return true if this rod is empty
     */
    public boolean isEmpty()
    {
        boolean isEmpty = stack.isEmpty();
        return isEmpty;
    }

    /**
     * Returns an iterator that traverses this rod's stack
     * in reverse order.
     * The first disk returned will be the one
     * from the bottom of the stack,
     * and the last will be
     * the disk from the top of the stack.
     */
    @Override
    public Iterator<Disk> iterator()
    {
        Iterator<Disk>  iterator    = stack.descendingIterator();
        return iterator;
    }
}
