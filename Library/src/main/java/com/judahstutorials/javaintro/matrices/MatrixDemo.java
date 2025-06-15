package com.judahstutorials.javaintro.matrices;

public class MatrixDemo
{
    public static void main( String[] args )
    {
        double[][]  data    =
        {
            {  0,  1,  2,  3,  4 },
            { 10, 11, 12, 13, 14 },
            { 20, 21, 22, 23, 24 },
            { 30, 31, 32, 33, 34 },
            { 40, 41, 42, 43, 44 }
        };
        Matrix  matrix  = new Matrix( data );
        matrix.determinant();
    }
}
