package edu.uweo.javaintro.tictactoe;

/**
 * Encapsulates the event listener interface for a TicTacToeSquare.
 * Any time an empty square is clicked the 
 * <i>clicked( TicTacToeSquare )</i> method is invoked.
 * 
 * @author jstra
 */
public interface TicTacToeUser
{
    /**
     * This method is called whenever the user clicks on 
     * an empty square on a tic-tac-toe board.
     * 
     * @param square    The square that was clicked.
     */
    public void clicked( TicTacToeSquare square );
}
