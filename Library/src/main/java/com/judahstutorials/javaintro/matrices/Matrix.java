package com.judahstutorials.javaintro.matrices;

import java.util.Arrays;

/**
 * An object of this class encapsulates a matrix 
 * and operations on the matrix.
 * The matrix is stored as a 2-D array of doubles
 * which is initialized from an argument
 * passed to the constructor;
 * a copy is made of the constructor parameter,
 * so changes made to the original array
 * will not affect the state of the Matrix object.
 */
public class Matrix
{
    /**
     * A copy of the original input data.
     * Will never be modified.
     */
    private final double[][]    matrix;
    
    /**
     * Constructor.
     * Makes a copy of the input array.
     * 
     * @param matrixIn
     */
    public Matrix( double[][] matrixIn )
    {
       matrix = getCopy( matrixIn );
    }
    
    /**
     * Return a new matrix consisting of the sum
     * of the encapsulated matrix and a given matrix.
     * 
     * @param toAdd the given matrix
     * 
     * @return
     *      a new matrix consisting of the sum
     *      of the encapsulated matrix and a given matrix
     *      
     * @throws MatrixException if the two matrices are not the same shape.
     */
    public Matrix add( Matrix toAdd )
    {
        int rowLen  = matrix.length;
        int colLen  = matrix[0].length;
        if ( rowLen != toAdd.matrix.length )
            throw new MatrixException( "Incompatible matrices" );
        if ( colLen != toAdd.matrix[0].length )
            throw new MatrixException( "Incompatible matrices" );
        
        double[][]  input   = toAdd.matrix;
        double[][]  copy    = getData();
        for( int rowInx = 0 ; rowInx < rowLen ; ++rowInx )
            for ( int colInx = 0 ; colInx < colLen ; ++colInx )
                copy[rowInx][colInx] += input[rowInx][colInx];
        Matrix      sum     = new Matrix( copy );
        return sum;
    }
    
    /**
     * Return a new matrix consisting of the a given matrix
     * subtracted from the encapsulated matrix.
     * 
     * @param toSubtract the given matrix
     * 
     * @return
     *      a new matrix consisting of the sum
     *      of the encapsulated matrix and a given matrix
     *      
     * @throws MatrixException if the two matrices are not the same shape.
     */
    public Matrix subtract( Matrix toSubtract )
    {
        int rowLen  = matrix.length;
        int colLen  = matrix[0].length;
        if ( rowLen != toSubtract.matrix.length )
            throw new MatrixException( "Incompatible matrices" );
        if ( colLen != toSubtract.matrix[0].length )
            throw new MatrixException( "Incompatible matrices" );
        
        double[][]  input   = toSubtract.matrix;
        double[][]  copy    = getData();
        for( int rowInx = 0 ; rowInx < rowLen ; ++rowInx )
            for ( int colInx = 0 ; colInx < colLen ; ++colInx )
                copy[rowInx][colInx] -= input[rowInx][colInx];
        Matrix      diff    = new Matrix( copy );
        return diff;
    }
    
    /**
     * Creates a new Matrix consisting of the product
     * of the encapsulated matrix and a scalar value.
     * 
     * @param scalar    the scalar value
     * 
     * @return
     *      Matrix consisting of the product
     *      of the encapsulated matrix and a scalar value
     */
    public Matrix multiply( double scalar )
    {
        double[][]  copy    = getData();
        int         numRows = matrix.length;
        int         numCols = matrix[0].length;
        for ( int row = 0 ; row < numRows ; ++row )
            for ( int col = 0 ; col < numCols ; ++col )
                copy[row][col] *= scalar;
        Matrix      matrix  = new Matrix( copy );
        return matrix;
    }
    
    /**
     * Returns the dot product of this matrix (the left matrix)
     * with a given matrix (the right matrix)
     * 
     * @param rightMatrix   the given matrix
     * 
     * @return  the dot product of this matrix with the given matrix
     */
    public Matrix multiply( Matrix rightMatrix )
    {
        double[][]  leftData        = matrix;
        int         numLeftRows     = leftData.length;
        int         numLeftCols     = leftData[0].length;

        double[][]  rightData       = rightMatrix.matrix;
        int         numRightRows    = rightData.length;
        int         numRightCols    = rightData[0].length;

        if ( numLeftCols != numRightRows )
            throw new MatrixException( "Incompatible matrices" );
        
        // The docs say that all elements of a new numeric array
        // will be initialized to 0.
        double[][]  productData     = new double[numLeftRows][numRightCols];
        for ( int inx = 0 ; inx < numLeftRows ; ++inx )
        {
            for ( int jnx = 0 ; jnx < numRightCols ; ++jnx )
            {
                for ( int knx = 0 ; knx < numRightRows ; ++knx )
                    productData[inx][jnx] += 
                        leftData[inx][knx] * rightData[knx][jnx];
            }
        }
        
        Matrix  product = new Matrix( productData );
        return product;
    }
    
    /**
     * Returns a new matrix which is a negation
     * of the encapsulated matrix.
     * 
     * @return  a new matrix which is a negation of the encapsulated matrix
     */
    public Matrix negate()
    {
        double[][]  copy    = getData();
        int         numRows = copy.length;
        int         numCols = copy[0].length;
        for ( int row = 0 ; row < numRows ; ++row )
            for ( int col = 0 ; col < numCols ; ++col )
                copy[row][col] *= -1;
        Matrix  result  = new Matrix( copy );
        return result;
    }
    
    public Matrix determinant()
    {
        int     numRows     = matrix.length;
        int     numCols     = matrix[0].length;
        if ( numRows != numCols )
        {
            String  msg = 
                "Can only calculate the determinant "
                + "of matrix its not square";
            throw new MatrixException( msg );
            
        }
        
        for ( int inx = 0 ; inx < numCols ; ++inx )
        {
            printStuff( getSubMatrix( inx, matrix ) );
            System.out.println( "*************" );
        }
        
        return null;
    }
    
    /**
     * Returns a copy of the encapsulated data.
     * 
     * @return a copy of the encapsulated data
     */
    public double[][] getData()
    {
        double[][]  copy    = getCopy( matrix );
        return copy;
    }
    
    /**
     * Returns a copy of the given row in the encapsulated data.

     * @param rowNum    the given row
     * 
     * @return a copy of the given row in the encapsulated data
     */
    public double[] getData( int rowNum )
    {
        double[]    row     = matrix[rowNum];
        int         rowLen  = row.length;
        double[]    copy    = new double[row.length];
        System.arraycopy(row, 0, copy, 0, rowLen );
        return copy;
    }
    
    /**
     * Returns the value from the given row and column
     * of the encapsulated data.
     * 
     * @param row   the given row
     * @param col   the given column
     * 
     * @return  the value from the given row and column 
     *          of the encapsulated data
     */
    public double getData( int row, int col )
    {
        double  datum   = matrix[row][col];
        return datum;
    }
    
    @Override
    public String toString()
    {
        StringBuilder   bldr    = new StringBuilder();
        bldr.append( '[' );
        for ( double[] row : matrix )
            bldr.append( Arrays.toString( row ) ).append( ',' );
        
        // replace the last ',' with ']'
        int length  = bldr.length();
        if ( length > 0 )
            bldr.replace( length - 1, length, "]" );
        return bldr.toString();
    }
    
    private void printStuff( double[][] matrixIn )
    {
        int numRows = matrixIn.length;
        for ( int inx = 0 ; inx < numRows ; ++inx )
        {
            System.out.println( Arrays.toString( matrixIn[inx] ) );
        }
    }
    
    /**
     * Makes a copy of the given array.
     * 
     * @param input the given array
     * 
     * @return a copy of the given array
     */
    private double[][] getCopy( double[][] input )
    {
        int         numRows = input.length;
        double[][]  copy    = new double[numRows][];
        for ( int inx = 0 ; inx < numRows ; ++inx )
        {
            double[]    colsIn      = input[inx];
            int         numCols     = colsIn.length;
            double[]    cols        = new double[numCols];
            System.arraycopy( colsIn, 0, cols, 0, numCols );
            copy[inx] = cols;
        }
        return copy;
    }
    
    private static double[][] getSubMatrix( int baseCol, double[][] data )
    {
        double[][]  subData = data;
        if ( data.length > 2 )
        {
            int     subLength   = data.length - 1;
            subData = new double[subLength][subLength];
            for ( int row = 0 ; row < subLength ; ++row )
            {
                for ( int inx = 0, col = 0 ; col < subLength ; ++inx, ++col )
                {
                    if ( inx == baseCol )
                        --col;
                    else
                        subData[row][col] = data[row+1][inx];
                }
            }
        }
        return subData;
    }
}
