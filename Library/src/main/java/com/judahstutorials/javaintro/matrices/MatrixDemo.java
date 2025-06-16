package com.judahstutorials.javaintro.matrices;

/**
 * Simple program to show how to use the Matrix class.
 */
public class MatrixDemo
{
    /**
     * Application entry point.
     * 
     * @param args  command-line arguments; not used.
     */
    public static void main( String[] args )
    {
        double[][]  data1   =
        {
            {  0,  1,  2, },
            { 10, 11, 12, },
            { 20, 21, 22, },
            { 30, 31, 32, },
            { 40, 41, 42, },
        };

        double[][]  data2   =
        {
            {  -2,   3 ,  -5 },
            {   7,  -11,  13 },
            { -17,   19, -23, },
            {  27,  -29,  31, },
            { -33,   37, -41, },
        };

        Matrix  matrix1 = new Matrix( data1 );
        Matrix  matrix2 = new Matrix( data2 );
        Matrix  matrix3 = matrix1.add( matrix2 );
        System.out.println( matrix3 );
    }
    
    /**
     * Default constructor; not used.
     */
    private MatrixDemo()
    {
        // not used
    }
}
