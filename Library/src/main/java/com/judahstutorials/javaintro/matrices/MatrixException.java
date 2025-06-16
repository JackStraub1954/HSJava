package com.judahstutorials.javaintro.matrices;

/**
 * Unchecked exception to support the Matrix class.
 */
public class MatrixException extends RuntimeException
{
    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = -6849148106608252029L;

    /**
     * Default constructor.
     * See corresponding superclass constructor.
     */
    public MatrixException()
    {
        super();
    }

    /**
     * Message constructor.
     * See corresponding superclass constructor.
     * 
     * @param message   the message to incorporate in the exception
     */
    public MatrixException(String message)
    {
        super(message);
    }

    /**
     * Cause constructor.
     * See corresponding superclass constructor.
     * 
     * @param cause   the cause to incorporate in the exception
     */
    public MatrixException(Throwable cause)
    {
        super(cause);
    }

    /**
     * Message/cause constructor.
     * See corresponding superclass constructor.
     * 
     * @param message   the message to incorporate in the exception
     * @param cause     the cause to incorporate in the exception
     */
    public MatrixException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
