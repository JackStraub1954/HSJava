package edu.uweo.javaintro.tictactoe;

/**
 * This class provides an example of how to launch the TicTacToe board.
 * Specifically:
 * <blockquote>
 * <code>
 * SampleLauncher  launcher    = new SampleLauncher();
 * TicTacToeBoard  board       = new TicTacToeBoard( launcher );
 * board.start();
 * </code>
 * </blockquote>
 * <p>
 * Note that the argument passed to the TicTacToeBoard constructor 
 * must be type TicTacToeUser.
 * </p>
 * 
 * @see TicTacToeUser
 * @see TicTacToeBoard
 * @see TicTacToeSquare
 * @see #clicked(TicTacToeSquare)
 * 
 * @author jstra
 *
 */
public class SampleLauncher implements TicTacToeUser
{
    private boolean exx = true;
    
    /**
     * Main program entry point.
     * 
     * @param args  Command line arguments; not used.
     */
    public static void main(String[] args)
    {
        SampleLauncher  launcher    = new SampleLauncher();
        TicTacToeBoard  board       = new TicTacToeBoard( launcher );
        board.start();
    }

    /**
     * This method is invoked each time a player clicks an empty square on 
     * the TicTacToeBoard. An <em>empty square</em> is a square that is not
     * currently owned by a player, typically <em>X</em> or <em>O</em>.
     * 
     * @param square    The square that was clicked.
     * 
     * @see TicTacToeSquare
     */
    @Override
    public void clicked( TicTacToeSquare square )
    {
        char    owner   = exx ? 'X' : 'O';
        square.setOwner( owner );
        exx = !exx;
        System.out.printf( "%d, %d: %c%n", square.getRow(), square.getColumn(), owner );
    }

}
