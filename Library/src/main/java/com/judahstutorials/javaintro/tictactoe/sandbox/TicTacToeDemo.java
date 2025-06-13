package com.judahstutorials.javaintro.tictactoe.sandbox;

import java.awt.Color;

import com.judahstutorials.javaintro.tictactoe.TicTacToeBoard;
import com.judahstutorials.javaintro.tictactoe.TicTacToeUser;

/**
 * This application is an example of how
 * to encapsulate the user interface of a tic-tac-toe board.
 * It sets some properties of the board,
 * and prints a message every time a square of the board is clicked.
 * It does not implement the logic behind
 * a game of tic-tac-toe;
 * that is the responsibility of the student.
 * <p>
 * Note that the application must implement TicTacToeUser;
 * this means that your application must include 
 * a "clicked(int row, int col, char owner)" method.
 * This method is called every time a square
 * of the tic-tac-toe board is clicked.
 */
public class TicTacToeDemo implements TicTacToeUser
{
    private static TicTacToeBoard  board;
    
    /**
     * Application entry point.
     * 
     * @param args  not used
     */
    public static void main( String[] args )
    {
        TicTacToeDemo   demo    = new TicTacToeDemo();
        board = TicTacToeBoard.getTicTacToeBoard( demo );
        board.setBGColor( 1, 1, Color.YELLOW );
        board.setFGColor( 1, 1, Color.BLUE );
        board.setOwner( 1, 1, 'X' );
        board.setOwner( 0, 2, 'O' );
        board.setOwner( 0, 0, 'X' );
        board.setOwner( 2, 2, 'O' );
        board.repaint();
    }
    
    /**
     * This method is required by "implements TicTacToeUser"
     * in the class declaration.
     */
    @Override
    public void clicked( int row, int col, char owner )
    {
        String  feedback    = 
            "Clicked: row " + row 
            + ", col " + col
            + ", owner: " + owner;
        System.out.println( feedback );
        // This is where you update the state of your GUI,
        // depending on the nature of the click.
        // Don't forget to call repaint
        board.repaint();
    }
}
