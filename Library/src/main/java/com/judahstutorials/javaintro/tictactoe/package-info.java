/**
 * 
 * <img 
 *      src="doc-files/TicTacToeBoard.png"
 *      alt="Tic-tac-toe Board"
 *      style="padding-left: .5em; padding-bottom: .5em; max-width:8em; float:right;"
 * >
 * This package encapsulates a facility to display
 * a tic-tac-toe board
 * and respond to mouse clicks.
 * The intention is to allow a student
 * to write a tic-tac-toe application
 * without requiring detailed knowledge
 * about building graphical user interfaces (GUIs).
 * To utilize the facility,
 * you need a class that implements <em>TicTacToeUser</em>:<br>
 * <code>
 *     &nbsp;&nbsp;&nbsp;&nbsp;
 *     public class TicTacToeDemo implements TicTacToeUser<br>
 * </code>
 * <em>TicTacToeUser</em> requires you to add the <em>clicked</em> method 
 * to the implementing class:<br>
 * <code>
 *     &nbsp;&nbsp;&nbsp;&nbsp;
 *     public void clicked( int row, int col, char owner )<br>
 * </code>
 * The <em>clicked</em> method is called every time the operator
 * clicks the mouse on a tic-tac-toe board.
 * The parameters indicate the row (0-2), column (0-2),
 * and the owner ('X,' 'O,' or blank for none) of the square
 * that the operator clicked.
 * <p>
 * To create and display the tic-tac-toe board,
 * obtain a <em>TicTacToeBoard</em> object
 * by calling the class's <em>getTicTacToeBoard</em> class method,
 * passing an object of your <em>TicTacToeUser</em> class:<br>
 * <code>
 *     &nbsp;&nbsp;&nbsp;&nbsp;TicTacToeDemo&nbsp; demo &nbsp;= new TicTacToeDemo();<br>
 *     &nbsp;&nbsp;&nbsp;&nbsp;TicTacToeBoard board = TicTacToeBoard.getTicTacToeBoard( demo );
 * </code><br>
 * <p>
 * The <em>TicTacToeBoard</em> class has the
 * <em>setOwner(int row, int col, char owner)</em>
 * and <em>getOwner(int row, int col)</em> methods, 
 * which allow you to set and retrieve the owner
 * of a square on the board,
 * and a <em>repaint</em> method
 * which must be called every time
 * you make a change to the GUI.
 * It also has some utility methods
 * that enable you to configure the appearance
 * of the board and individual squares;
 * see {@link TicTacToeBoard}.
 * <em>TicTacToeDemo</em>, below,
 * is a sample application that creates a <em>TicTacToeBoard</em>
 * and reports clicks on tic-tac-toe squares.
 * Substitute your logic
 * to create a working tic-tac-toe game.
 * 
 * @see <a href="doc-files/TicTacToeDemo.htm">TicTacToeDemo</a>
 * 
 * @since 1.0
 * @author Jack Straub
 * @version 1.0
 */
package com.judahstutorials.javaintro.tictactoe;
