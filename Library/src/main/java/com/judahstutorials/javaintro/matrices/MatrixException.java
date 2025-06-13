package com.judahstutorials.javaintro.matrices;

public class MatrixException extends RuntimeException
{
    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = -6849148106608252029L;

    public MatrixException()
    {
    }

    public MatrixException(String message)
    {
        super(message);
    }

    public MatrixException(Throwable cause)
    {
        super(cause);
    }

    public MatrixException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
