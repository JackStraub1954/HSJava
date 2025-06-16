package com.judahstutorials.javaintro.matrices;

import java.util.Arrays;

/**
 * An object of this class encapsulates a matrix, 
 * and operations on the matrix.
 * The matrix is stored as a 2-D array of doubles
 * which is initialized from an argument
 * passed to the constructor;
 * a copy is made of the input,
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
     * @param matrixIn  the data to encapsulate
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
     * Return a new matrix consisting of a given matrix
     * subtracted from the encapsulated matrix.
     * 
     * @param toSubtract the given matrix
     * 
     * @return
     *      a new matrix consisting of the given matrix
     *      subtracted from the encapsulated matrix
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
     * with a given matrix (the right matrix).
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
    
    /**
     * Calculates the determinant of the encapsulated matrix.
     * 
     * @throws MatrixException if the encapsulated matrix is not square
     * 
     * @return  the determinant of the encapsulated matrix
     */
    public double determinant()
    {
        if ( matrix.length != matrix[0].length )
        {
            String  msg = "Can't calculate determinant on non-square matrix";
            throw new MatrixException( msg );
        }
        double  det     = determinant( 1, matrix );
        return det;
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
    
    /**
     * Gets a copy of the data from the given column
     * of the encapsulated matrix.
     * 
     * @param column    the given column
     * 
     * @return  a copy of the data from the given column
     *          of the encapsulated matrix
     */
    public double[] getDataColumn( int column )
    {
        int         numRows = matrix.length;
        double[]    data    = new double[numRows];
        for ( int inx = 0 ; inx < numRows ; ++inx )
        {
            data[inx] = matrix[inx][column];
        }
        return data;
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
    
    /**
     * Generates a submatrix for use in calculating determinants.
     * If the input is an NxN array,
     * the output will be an (N-1)x(N-1) array.
     * Missing from the output will be the first row,
     * and column <em>B</em> of the input array,
     * where <em>B</em> is determined by the <em>baseCol</em> parameter.
     * 
     * @param baseCol   the index of the column to be omitted from the result
     * @param data      the input from which to generate the submatrix
     * @return  the generated submatrix
     */
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
    
    /**
     * Calculates the product of a scalar
     * and the determinant of a given matrix.
     * To calculate the determinant of the encapsulated data
     * pass a scalar value of 1.
     * 
     * @param scalar    the scalar value
     * @param data      the data from which to derive a determinant
     * 
     * @return  the product of a scalar and the determinant of the given matrix
     */
    private static double determinant( double scalar, double[][] data )
    {
        int     length  = data.length;
        double  result  = 0;
            if ( length == 1 )
                result = data[0][0];
            else if ( data.length == 2 )
                result = scalar * determinant2X2( data );
            else
            {
                int xier    = 1;
                for ( int inx = 0 ; inx < length ; ++inx, xier *= -1 )
                {
                    double      element = xier * data[0][inx];
                    double[][]  subData = getSubMatrix( inx, data );
                    result += determinant( element, subData );
                }
                result *= scalar;
            }
        return result;
    }
    
    /**
     * Calculates the determinant of a 2X2 matrix.
     * It is a utility method that removes some of the complexity
     * of the more general determinant(int, data[][]) method.
     * 
     * @param data  the data comprising the 2X2 matrix
     * 
     * @return  the determinant of the given 2X2 matrix
     * 
     * @throws MatrixException  if the input data is not a 2X2 array
     */
    private static double determinant2X2( double[][] data )
    {
        if ( data.length != 2 )
            throw new MatrixException( "Invalid data array" );
        double  result = data[0][0] * data[1][1] - data[0][1] * data[1][0];
        return result;
    }
}
