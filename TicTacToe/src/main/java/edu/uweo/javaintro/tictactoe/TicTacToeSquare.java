package edu.uweo.javaintro.tictactoe;

/**
 * Encapsulates a square on a tic-tac-toe board, including row,
 * column and owner.
 * 
 * @author jstra
 */
public interface TicTacToeSquare
{
	/**
	 * Gets the column this square belongs to.
	 * @return The column this square belongs to.
	 */
	public int 	getColumn();
	
	/**
	 * Gets the row this square belongs to.
	 * @return The row this square belongs to.
	 */
	public int 	getRow();
	
	/**
	 * Establishes the owner of this square, typically 'X' or 'O'.
	 * 
	 * @param owner    The owner of this square.
	 */
	public void	setOwner( char owner );
}
