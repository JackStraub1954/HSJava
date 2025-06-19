package com.judahstutorials.javaintro.cartesian_plane;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlaneTest
{
    private static final String     testString      = "Test String";
    private static final double     testFloat       = 2.5;
    private static final int        testInt         = 256;
    private static final Color      testColor       = new Color( 0x112233 );
    private static final int        testLineClassA  = LineClass.AXIS;
    private static final int        testLineClassB  = LineClass.GRIDLINE;
    
    private Plane   testPlane;
    
    @BeforeEach
    public void beforeEach()
    {
        testPlane = CPFrame.getPlane();
    }
    
    @Test
    public void testPlane()
    {
        Plane   plane   = CPFrame.getPlane();
        assertNotNull( plane );
    }

    @Test
    public void testAddPoint()
    {
        Point2D pointA  = new Point2D.Double( 1, 2 );
        Point2D pointB  = new Point2D.Double( 10, 20 );
        Point2D pointC  = new Point2D.Double( 100, 200 );
        Stream.of( pointA, pointB, pointC ).forEach( p ->
            testPlane.addPoint( p.getX(), p.getY()) );
        
        List<Point2D>   allPoints   = testPlane.getPoints();
        assertEquals( 3, allPoints.size() );
        assertEquals( pointA, allPoints.get( 0 ) );
        assertEquals( pointB, allPoints.get( 1 ) );
        assertEquals( pointC, allPoints.get( 2 ) );
    }

    @Test
    public void testClear()
    {
        List<Point2D>   list    = testPlane.getPoints();
        assertTrue( list.isEmpty() );
        testPlane.addPoint( 1, 2 );
        list = testPlane.getPoints();
        assertFalse( list.isEmpty() );
        testPlane.clear();
        list = testPlane.getPoints();
        assertTrue( list.isEmpty() );
    }

    @Test
    public void testDrawText()
    {
        testPlane.setDrawText( false );
        assertFalse( testPlane.getDrawText() );
        testPlane.setDrawText( true );
        assertTrue( testPlane.getDrawText() );
    }

    @Test
    public void testFontFamily()
    {
        testPlane.setFontFamily( testString );
        assertEquals( testString, testPlane.getFontFamily() );
    }

    @Test
    public void testFontSize()
    {
        testPlane.setFontSize( testInt );
        assertEquals( testInt, testPlane.getFontSize() );
    }

    @Test
    public void testFontStyle()
    {
        testPlane.setFontStyle( testInt );
        assertEquals( testInt, testPlane.getFontStyle() );
    }

    @Test
    public void testTextColor()
    {
        testPlane.setTextColor( testColor );
        assertEquals( testColor, testPlane.getTextColor() );
    }

    @Test
    public void testPointDiam()
    {
        testPlane.setPointDiam( testFloat );
        assertEquals( testFloat, testPlane.getPointDiam() );
    }

    @Test
    public void testGetPointColor()
    {
        testPlane.setPointColor( testColor );
        assertEquals( testColor, testPlane.getPointColor() );
    }

    @Test
    public void testBgColor()
    {
        testPlane.setBackground( testColor );
        assertEquals( testColor, testPlane.getBackground() );
    }

    @Test
    public void testPitchColor()
    {
        testPlane.setPitchColor( testColor );
        assertEquals( testColor, testPlane.getPitchColor() );
    }

    @Test
    public void testMargin()
    {
        testPlane.setMargin( testInt );
        assertEquals( testInt, testPlane.getMargin() );
    }

    @Test
    public void testUnitLength()
    {
        testPlane.setUnitLength( testFloat );
        assertEquals( testFloat, testPlane.getUnitLength() );
    }

    @Test
    public void testLineColor()
    {
        assertNotEquals( testColor, testPlane.getColor( testLineClassA ) );
        testPlane.setColor( testLineClassA, testColor );
        assertEquals( testColor, testPlane.getColor( testLineClassA ) );

        assertNotEquals( testColor, testPlane.getColor( testLineClassB ) );
        testPlane.setColor( testLineClassB, testColor );
        assertEquals( testColor, testPlane.getColor( testLineClassB ) );
    }

    @Test
    public void testLineLength()
    {
        assertNotEquals( testFloat, testPlane.getLength( testLineClassA ) );
        testPlane.setLength( testLineClassA, testFloat );
        assertEquals( testFloat, testPlane.getLength( testLineClassA ) );

        assertNotEquals( testFloat, testPlane.getLength( testLineClassB ) );
        testPlane.setLength( testLineClassB, testFloat );
        assertEquals( testFloat, testPlane.getLength( testLineClassB ) );
    }

    @Test
    public void testLineWidth()
    {
        assertNotEquals( testFloat, testPlane.getWidth( testLineClassA ) );
        testPlane.setWidth( testLineClassA, testFloat );
        assertEquals( testFloat, testPlane.getWidth( testLineClassA ) );

        assertNotEquals( testFloat, testPlane.getWidth( testLineClassB ) );
        testPlane.setWidth( testLineClassB, testFloat );
        assertEquals( testFloat, testPlane.getWidth( testLineClassB ) );
    }

    @Test
    public void testLinePerUnit()
    {
        assertNotEquals( testInt, testPlane.getPerUnit( testLineClassA ) );
        testPlane.setPerUnit( testLineClassA, testInt );
        assertEquals( testInt, testPlane.getPerUnit( testLineClassA ) );

        assertNotEquals( testInt, testPlane.getPerUnit( testLineClassB ) );
        testPlane.setPerUnit( testLineClassB, testInt );
        assertEquals( testInt, testPlane.getPerUnit( testLineClassB ) );
    }
}
