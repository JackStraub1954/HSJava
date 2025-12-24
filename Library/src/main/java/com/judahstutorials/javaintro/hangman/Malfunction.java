package com.judahstutorials.javaintro.hangman;

/**
 * This is an all-purpose exception to raise
 * when something unexpected goes wrong.
 * For example, 
 * if JOptionPane.showOptionDialog 
 * returns an out-of-bounds index.
 */
public class Malfunction extends Error
{

    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * See the documentation for 
     * java.lang.Error().
     */
    public Malfunction()
    {
    }

    /**
     * See the documentation for 
     * java.lang.Error( String ).
     * 
     * @param message see java.lang.Error
     */
    public Malfunction(String message)
    {
        super(message);
    }

    /**
     * See the documentation for 
     * java.lang.Error( Throwable ).
     * @param cause see java.lang.Error
     */
    public Malfunction(Throwable cause)
    {
        super(cause);
    }

    /**
     * See the documentation for 
     * java.lang.Error( String, Throwable ).
     * @param message   see java.lang.Error
     * @param cause     see java.lang.Error
     */
    public Malfunction(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * See the documentation for 
     * java.lang.Error( String, Throwable, boolean, boolean ).
     * @param message                       see java.lang.Error
     * @param cause                         see java.lang.Error
     * @param enableSuppression   message   see java.lang.Error
     * @param writableStackTrace  message   see java.lang.Error
     */
    public Malfunction(
        String message, 
        Throwable cause, 
        boolean enableSuppression, 
        boolean writableStackTrace
    )
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
