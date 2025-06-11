package com.judahstutorials.javaintro.tictactoe;

/**
 * A class of this type is used to detect a click on a TicTacToeBoard.
 * Every time a click occurs the user is notified via the clicked method.
 */
@FunctionalInterface
public interface TicTacToeUser
{
    /**
     * This method is called every time a mouse click
     * occurs in a TicTacToeBoard.
     * 
     * @param row       the row in which the click occurred (0 - 2)
     * @param col       the column in which the click occurred (0 - 2)
     * @param owner     the owner of the clicked square ('X', 'O', or ' ')
     */
    void clicked( int row, int col, char owner );
}
