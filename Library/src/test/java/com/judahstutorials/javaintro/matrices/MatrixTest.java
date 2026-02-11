package com.judahstutorials.javaintro.matrices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class MatrixTest
{
    private static final double[][] detTestA        =
    {
        {   55,   55,   55,   55,   55,   55 },
        {   55,   56,   55,   55,   55,   55 },
        {   55,   55,   57,   55,   55,   55 },
        {   55,   55,   55,   58,   55,   55 },
        {   55,   55,   55,   55,   59,   55 },
        {   55,   55,   55,   55,   55,   60 },
    };
    private static final double     detTestASol     = 6600;
    
    private static final double[][] detTestB        =
    {
        { 20, 21 },
        { 30, 31 }
    };
    private static final double     detTestBSol     = -10;
    
    private static final double[][] detTestC        =
    {
        { 10, 11, 12 },
        { 20, 21, 22 },
        { 31, 32, 35 }
    };
    private static final double     detTestCSol     = -20;
        
    private static final double[][] detTestD        =
    {
        { -3.14 },
    };
    private static final double     detTestDSol     = -3.14;
    
    private static final double[][] testArrSquare   =
    { 
        { 10, 11, 12 },
        { 20, 21, 22 },
        { 30, 31, 32 },
    };
    private static final double[][] testArr3By4     =
    { 
        { 10, 11, 12, 13 },
        { 20, 21, 22, 23 },
        { 30, 31, 32, 33 },
    };
    private static final double[][] testArr4By3     =
    { 
        { 10, 11, 12 },
        { 20, 21, 22 },
        { 30, 31, 32 },
        { 40, 41, 42 },
    };

    @Test
    public void testMatrixDouble()
    {
        Matrix      matrix  = new Matrix( testArrSquare );
        validateCopy( testArrSquare, matrix );
        
        matrix  = new Matrix( testArr3By4 );
        validateCopy( testArr3By4, matrix );
        
        matrix  = new Matrix( testArr4By3 );
        validateCopy( testArr4By3, matrix );
    }
    
    @Test
    public void testProduct()
    {
        double[][]  leftData    =
        {
            {  0,  1,  2 },
            { 10, 11, 12 },
            { 20, 21, 22 },
            { 30, 31, 32 },
            { 40, 41, 42 }
        };
        double[][]  rightData   =
        {
            { 100, 101, 102, 103 },
            { 200, 201, 202, 203 },
            { 300, 301, 302, 303 },
        };
        double[][] expectedData =
        {
            { 800.0,  803.0,    806.0,   809.0 },   
            {6800.0,  6833.0,  6866.0,   6899.0},
            {12800.0, 12863.0, 12926.0, 12989.0},
            {18800.0, 18893.0, 18986.0, 19079.0},
            {24800.0, 24923.0, 25046.0, 25169.0}
        };
        Matrix  leftMatrix  = new Matrix( leftData );
        Matrix  rightMatrix = new Matrix( rightData );
        Matrix  product     = leftMatrix.multiply( rightMatrix );
        validateData( expectedData, product );
    }
    
    @Test
    public void testNegate()
    {
        double[][]  data        = 
        {
            { 100, 101, 102, 103 },
            { 200, 201, 202, 203 },
            { 300, 301, 302, 303 },
        };
        double[][]  expected    = 
        {
            { -100, -101, -102, -103 },
            { -200, -201, -202, -203 },
            { -300, -301, -302, -303 },
        };
        Matrix  matrix      = new Matrix( data );
        Matrix  negation    = matrix.negate();
        validateData( expected, negation );
    }
    
    @Test
    public void testDeterminant()
    {
        testDeterminant( detTestA, detTestASol );
        testDeterminant( detTestB, detTestBSol );
        testDeterminant( detTestC, detTestCSol );
        testDeterminant( detTestD, detTestDSol );
    }
    
    @Test
    public void testGetDataColumn()
    {
        double[][]  data    =
        {
            { 0, 0, 1, 0 },
            { 0, 0, 2, 0 },
            { 0, 0, 3, 0 },
        };
        Matrix      matrix  = new Matrix( data );
        double[]    col     = matrix.getDataColumn( 2 );
        assertEquals( 3, col.length );
        assertEquals( 1, col[0] );
        assertEquals( 2, col[1] );
        assertEquals( 3, col[2] );
    }
    
    private static void 
    testDeterminant( double[][] data, double expectedSolution )
    {
        Matrix  matrix          = new Matrix( data );
        double  actualSolution  = matrix.determinant();
        assertEquals( expectedSolution, actualSolution );
    }
    
    /**
     * Validate the content and uniqueness of a matrix.
     * 
     * @param expected  the expected content
     * @param actual    the actual content
     */
    private static void validateCopy( double[][] expected, Matrix actual )
    {
        validateData( expected, actual );
        validateUniqueness( expected, actual );
    }
    
    /**
     * Validate that a copy of the data from a matrix
     * is not a reference to the original data.
     * 
     * @param expected  the original data
     * @param actual    the matrix to validate
     */
    private static void validateUniqueness( double[][] expected, Matrix actual )
    {
        validateUniqueness( expected, actual.getData() );
    }
    
    /**
     * Validate that a copy of a double[][] array
     * is not a reference to the original data.
     * 
     * @param arrA  the original data
     * @param arrB    the matrix to validate
     */
    private static void validateUniqueness( double[][] arrA, double[][] arrB )
    {
        assertNotEquals( arrA, arrB );
        for ( int inx = 0 ; inx < arrA.length ; ++inx )
            assertNotEquals( arrA[inx], arrB[inx] );
    }
    
    /**
     * Validate that the data from a given matrix
     * equals the expected data.
     * 
     * @param expected  the expected data
     * @param actual    the given matrix
     */
    private static void validateData( double[][] expected, Matrix actual )
    {
        validateData( expected, actual.getData() );
    }

    /**
     * Validate that a given array has the expected content.
     * 
     * @param expected  the expected content
     * @param actual    the given array
     */
    private static void validateData( double[][] expected, double[][] actual )
    {
        assertEquals( expected.length, actual.length );
        int     numRows     = expected.length;
        for ( int inx = 0 ; inx < numRows ; ++inx )
        {
            double[]    rowExp  = expected[inx];
            double[]    rowAct  = actual[inx];
            assertEquals( rowExp.length, rowAct.length );
            assertTrue( Arrays.equals( rowExp, rowAct ) );
        }
    }
}
