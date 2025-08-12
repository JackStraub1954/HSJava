package com.judahstutorials.javaintro.tictactoe;

import java.awt.Color;

/**
 * This class encapsulated the propertied of a tic-tac-toe square.
 * Properties include the background color of the square,
 * the foreground color of the square,
 * and the owner of the square.
 */
public class TicTacToeSquare
{
    /**
     * The background color of the square.
     */
    private Color   bgColor     = Color.LIGHT_GRAY;
    /**
     * The foreground color of the square.
     */
    private Color   fgColor     = Color.BLACK;
    /**
     * The owner of the square, 'X' or 'O'. 
     * If the square is not owned, it will have a value of ' '.
     */
    private char    owner       = ' ';
    
    /**
     * Default constructor.
     * Present for documentation purposes only.
     */
    public TicTacToeSquare()
    {
        // not used
    }
    
    /**
     * Gets the background color of this square.
     * @return the background color of this square
     */
    public Color getBGColor()
    {
        return bgColor;
    }
    
    /**
     * Sets the background color of this square.
     * 
     * @param bgColor   the background color to set
     */
    public void setBGColor(Color bgColor)
    {
        this.bgColor = bgColor;
    }
    
    /**
     * Gets the foreground color of this square.
     * 
     * @return the foreground color of this square
     */
    public Color getFGColor()
    {
        return fgColor;
    }
    
    /**
     * Sets the foreground color of the square.
     * @param fgColor the foreground color of the square
     */
    public void setFGColor(Color fgColor)
    {
        this.fgColor = fgColor;
    }
    
    /**
     * Gets the owner of this square.
     * @return the owner of this square
     */
    public char getOwner()
    {
        return owner;
    }
    
    /**
     * Sets the owner of this square.
     * @param owner the owner of this square
     */
    public void setOwner(char owner)
    {
        this.owner = Character.toUpperCase( owner );
    }   
}
