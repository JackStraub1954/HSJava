package edu.uweo.javaintro.tictactoe;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * Encapsulation of a 3x3 tic-tac-toe board. The board consists of three
 * rows and three columns of squares. The squares are identified by their 
 * row/column indexes. Indexes begin at 0, to the top-left square is (0,0)
 * while the bottom right square is (2,2).
 *
 * @author jstra
 */
public class TicTacToeBoard
{
    private static final int    SQUARE_SIDE = 100;
    private static final float  FONT_SIZE   = SQUARE_SIDE * .6f;
    private static final String EMPTY       = " ";
    
    private final TicTacToeUser     user;
    private final TicTacButton[][]  board;
    private final JFrame            frame;
    private final ClickListener     listener;
    private final ComponentListener compListener;
    private boolean                 gameOver;
    
    /**
     * Constructor. Creates and populates the board. Establishes the callback for
     * button click events. The board is not displayed;
     * to display the board, call the <em>start method</em>. 
     * The user's <em>clicked</em> method is invoked each time a player
     * clicks on an empty square while the game is in progress.
     * 
     * @param user  This board's controller.
     */
    public TicTacToeBoard( TicTacToeUser user )
    {
        this.user = user;
        gameOver = false;
        board = new TicTacButton[3][3];
        for ( int inx = 0 ; inx < 3 ; ++inx )
            for ( int jnx = 0 ; jnx < 3 ; ++jnx )
                board[inx][jnx] = new TicTacButton( inx, jnx );
        
        frame = new JFrame( "Tic-Tac-Toe" );
        listener = new ClickListener();
        compListener = new ResizeListener();
    }
    
    /**
     * Indicates that the game is over. Once the game is over, <em>clicked</em> 
     * events will no longer be passed to the user (the game's controller).
     */
    public void gameOver()
    {
        gameOver = true;
    }
    
    /**
     * Creates and displays this TicTacToeBoard.
     */
    public void start()
    {
        if ( !frame.isVisible() )
        {
            Thread  thread  = new Thread( new Runner(), "Runner" );
            thread.start();
        }
    }
    
    private class Runner implements Runnable
    {
        /* (non-Javadoc)
         * @see java.lang.Runnable#run()
         */
        public void run()
        {
            Border  inn     = BorderFactory.createEmptyBorder( 5, 5, 5, 5 );
            Border  out     = BorderFactory.createEtchedBorder();
            Border  border  = BorderFactory.createCompoundBorder( out, inn );
            
            frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
            JPanel      pane    = new JPanel( new GridLayout( 3, 3 ) );
            frame.setContentPane( pane );
            pane.setLayout( new GridLayout( 3, 3 ) );
            pane.setBorder( border );
            for ( int inx = 0 ; inx < 3 ; ++inx )
                for ( int jnx = 0 ; jnx < 3 ; ++jnx )
                {
                    board[inx][jnx].addActionListener( listener );
                    board[inx][jnx].addComponentListener( compListener );
                    pane.add( board[inx][jnx] );
                }
            frame.pack();
            frame.setVisible( true );
        }
    }
    
    @SuppressWarnings("serial")
    private class TicTacButton 
        extends JButton 
        implements TicTacToeSquare
    {
        private final int   col;
        private final int   row;
        
        public TicTacButton( int row, int col )
        {
            super( EMPTY );
            
            this.row = row;
            this.col = col;
            
            Dimension   dim     = new Dimension( SQUARE_SIDE, SQUARE_SIDE );
            setPreferredSize( dim );
            
            Font        font    = getFont().deriveFont( FONT_SIZE );
            setFont( font );
        }

        @Override
        public int getColumn()
        {
            return col;
        }

        @Override
        public int getRow()
        {
            return row;
        }

        @Override
        public void setOwner( char owner )
        {
            if ( getText().equals( EMPTY ) )
                setText( "" + owner );
        }
    }
    
    private class ClickListener implements ActionListener
    {
        public void actionPerformed( ActionEvent evt )
        {
            Object  obj = evt.getSource();
            if ( !gameOver && obj instanceof TicTacButton )
            {
                TicTacButton    button  = (TicTacButton)obj;
                String          text    = button.getText();
                if ( text.equals( EMPTY ) )
                    user.clicked( button );
            }
        }
    }
    
    private class ResizeListener extends ComponentAdapter
    {
        public void componentResized( ComponentEvent evt )
        {
            Object  obj = evt.getSource();
            if ( obj instanceof TicTacButton )
            {
                TicTacButton    button  = (TicTacButton)obj;
                Font            font    = button.getFont();
                int             width   = button.getWidth();
                int             height  = button.getHeight();
                float           side    = width < height ? width : height;
                float           ratio   = side / SQUARE_SIDE;
                float           size    = FONT_SIZE * ratio;
                font = font.deriveFont( size );
                button.setFont( font );
            }
        }
    }
}
