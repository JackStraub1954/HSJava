package edu.uweo.javaintro.game_of_life_lib;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.uweo.javaintro.game_of_life_lib.Cell;

public class CellTest
{
    @Test
    public void testHashCode()
    {
        int     row1    = 10;
        int     col1    = 20;
        boolean alive1  = true;
        Cell    cell1   = new Cell( row1, col1, alive1 );
        int     hash1   = cell1.hashCode();
        
        int     row2    = row1;
        int     col2    = col1;
        boolean alive2  = alive1;
        Cell    cell2   = new Cell( row2, col2, alive2 );
        int     hash2   = cell2.hashCode();
        
        assertEquals( hash1, hash2 );
    }

    @Test
    public void testCellIntInt()
    {
        int     row     = 15;
        int     col     = 20;
        boolean alive   = false;
        Cell    cell    = new Cell( row, col );
        assertEquals( row, cell.getRow() );
        assertEquals( col, cell.getCol() );
        assertEquals( alive, cell.isAlive() );
    }

    @Test
    public void testCellIntIntBoolean()
    {
        int     row     = 15;
        int     col     = 20;
        boolean alive   = false;

        Cell    cell    = new Cell( row, col, alive );
        assertEquals( row, cell.getRow() );
        assertEquals( col, cell.getCol() );
        assertEquals( alive, cell.isAlive() );

        alive = true;
        cell = new Cell( row, col, alive );
        assertEquals( row, cell.getRow() );
        assertEquals( col, cell.getCol() );
        assertEquals( alive, cell.isAlive() );
    }

    @Test
    public void testCellCell()
    {
        int     row       = 10;
        int     col       = 20;
        boolean alive     = true;
        
        Cell    cellOrig    = new Cell( row, col, alive );
        Cell    cellCopy    = new Cell( cellOrig );
        assertEquals( row, cellCopy.getRow() );
        assertEquals( col, cellCopy.getCol() );
        assertEquals( alive, cellCopy.isAlive() );
    }

    @Test
    public void testGetSetRow()
    {
        int     row     = 10;
        int     updRow  = 15;
        int     col     = 20;
        Cell    cell    = new Cell( row, col );
        
        assertEquals( row, cell.getRow() );
        cell.setRow( updRow );
        assertEquals( updRow, cell.getRow() );
    }

    @Test
    public void testSetGetCol()
    {
        int     row     = 10;
        int     col     = 20;
        int     updCol  = 25;
        Cell    cell    = new Cell( row, col );
        
        assertEquals( col, cell.getCol() );
        cell.setCol( updCol );
        assertEquals( updCol, cell.getCol() );
    }

    @Test
    public void testSetIsAlive()
    {
        int     row     = 10;
        int     col     = 20;
        boolean alive   = true;
        Cell    cell    = new Cell( row, col, alive );
        
        assertEquals( alive, cell.isAlive() );
        alive = !alive;
        cell.setAlive( alive );
        assertEquals( alive, cell.isAlive() );
    }

    @Test
    public void testToggleAlive()
    {
        int     row     = 10;
        int     col     = 20;
        boolean alive   = true;
        Cell    cell    = new Cell( row, col, alive );
        
        assertEquals( alive, cell.isAlive() );
        cell.toggleAlive();
        assertNotEquals( alive, cell.isAlive() );
        cell.toggleAlive();
        assertEquals( alive, cell.isAlive() );
    }

    @Test
    public void testEqualsObject()
    {
        int     row1    = 10;
        int     col1    = 20;
        boolean alive1  = true;
        Cell    cell1   = new Cell( row1, col1, alive1 );
        
        int     row2    = row1;
        int     col2    = col1;
        boolean alive2  = alive1;
        Cell    cell2   = new Cell( row2, col2, alive2 );
        
        // boilerplate
        assertEquals( cell1, cell1 );
        assertNotEquals( cell1, null );
        assertNotEquals( cell1, new Object() );
        assertEquals( cell1, cell2 );
        assertEquals( cell2, cell1 );
        
        // row
        cell2.setRow( row2 + 1 );
        assertNotEquals( cell1, cell2 );
        assertNotEquals( cell2, cell1 );
        cell2.setRow( row2 );
        assertEquals( cell1, cell2 );
        
        // col
        cell2.setCol( col2 + 1 );
        assertNotEquals( cell1, cell2 );
        assertNotEquals( cell2, cell1 );
        cell2.setCol( col2 );
        assertEquals( cell1, cell2 );
        
        // alive
        cell2.toggleAlive();
        assertNotEquals( cell1, cell2 );
        assertNotEquals( cell2, cell1 );
        cell2.toggleAlive();
        assertEquals( cell1, cell2 );
    }

    @Test
    public void testToString()
    {
        int     row         = 10;
        int     col         = 20;
        boolean alive       = true;
        Cell    cell        = new Cell( row, col, alive );

        String  rowPart     = "row=" + row;
        String  colPart     = "col=" + col;
        String  alivePart   = "alive=" + alive;
        String  testStr     = cell.toString();
        
        assertTrue( testStr.contains( rowPart ) );
        assertTrue( testStr.contains( colPart ) );
        assertTrue( testStr.contains( alivePart ) );
    }

}
