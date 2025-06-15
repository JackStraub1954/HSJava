package com.judahstutorials.javaintro.matrices;

/**
 * The class encapsulates the data
 * describing an exception
 * encountered in a matrix operation.
 */
public class MatrixException extends RuntimeException
{
    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor;
     * see corresponding constructor in the superclass.
     */
    public MatrixException()
    {
    }

    /**
     * Message constructor;
     * see corresponding constructor in the superclass.
     * 
     * @param message   message to encapsulate in the exception
     */
    public MatrixException(String message)
    {
        super(message);
    }

    /**
     * Cause constructor;
     * see corresponding constructor in the superclass.
     * 
     * @param cause  cause to encapsulate in the exception
     */
    public MatrixException(Throwable cause)
    {
        super(cause);
    }

    /**
     * Message/cause constructor;
     * see corresponding constructor in the superclass.
     * 
     * @param message   message to include in the exception
     * @param cause     cause to include in the exception
     */
    public MatrixException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Comprehensive constructor;
     * see corresponding constructor in the superclass.
     * 
     * @param message               message to include in the exception
     * @param cause                 cause to include in the exception
     * @param enableSuppression     enable-suppression flag
     * @param writableStackTrace    writable-stack-trace flag
     */
    public MatrixException(
        String message, 
        Throwable cause, 
        boolean enableSuppression, 
        boolean writableStackTrace
    )
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
