package com.judahstutorials.javaintro.tictactoe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.judahstutorials.javaintro.tictactoe.sandbox.TicTacToeDemo;

/**
 * This class encapsulates a tic-tac-toe board.
 * Upon instantiation, it displays a board containing nine squares.
 * The class is instantiated with a {@link TicTacToeUser} object,
 * which is notified every time a square is clicked.
 * The user can change a property of the the board
 * from the {@link TicTacToeUser#clicked(int, int, char)} method
 * of the TicTicToeUser interface.
 * Every time a property is changed 
 * the repaint() method of this class must be invoked
 * before the change can be observed.
 * 
 * @see TicTacToeUser
 * @see TicTacToeUser#clicked(int, int, char)
 * @see TicTacToeDemo
 */
public class TicTacToeBoard extends JPanel
{
    /**
     * Generated Serial Version ID
     */
    private static final long serialVersionUID = -1111829360678105787L;
    
    /**
     * The default side of a square on the board.
     */
    private static final int        squareSideDefault   = 100;
    /**
     * The default side of of the board that contains the squares.
     */
    private static final int        boardSideDefault    = squareSideDefault * 3;
    /**
     * The default width of a line drawn on the board.
     */
    private static final float      strokeDefault       = 2;
    /**
     * The default margin between the side of a square
     * and the display of the owner of the square (if any).
     */
    private static final float      marginDefault       = 3;
    /**
     * The instance of this board. It is created by the 
     * {@linkplain #getTicTacToeBoard(TicTacToeUser)} method.
     * Only one board may be instantiated by an application;
     * multiple attempts to instantiate the board will be ignored.
     */
    private static TicTacToeBoard   board;
    /**
     * The user that will be notified 
     * every time a square on the board is clicked.
     */
    private final  TicTacToeUser    user;
    
    /**
     * The nine squares of the board.
     */
    private final TicTacToeSquare[][]   squares             = 
    {
        { new TicTacToeSquare(), new TicTacToeSquare(), new TicTacToeSquare() },
        { new TicTacToeSquare(), new TicTacToeSquare(), new TicTacToeSquare() },
        { new TicTacToeSquare(), new TicTacToeSquare(), new TicTacToeSquare() },
    };
    /**
     * The color of the grid lines used to draw the board.
     */
    private Color           gridColor   = Color.BLACK;
    
    /**
     * Temporary variable to hold the current side of a square
     * drawn on the board.
     * Recalculated every time paintComponent is called.
     * Only valid when paintComponent is executing.
     */
    private transient double    squareSide;
    /**
     * Temporary variable to hold the current side of the board.
     * Recalculated every time paintComponent is called.
     * Only valid when paintComponent is executing.
     */
    private transient double    workingSide;
    /**
     * Temporary variable to hold the current margin
     * between the side of a square,
     * and the display of the board's owner.
     * Recalculated every time paintComponent is called.
     * Only valid when paintComponent is executing.
     */
    private transient double    margin;
    
    /**
     * Constructs the user interface of the TicTacToeBoard.
     * Assures that all AWT operations are performed
     * on the AWT event dispatch thread (EDT).
     * 
     * @param user  the user to be notified every time the board is clicked
     * 
     * @return  the TicTacToeBoard
     */
    public static TicTacToeBoard getTicTacToeBoard( TicTacToeUser user )
    {
        Runnable    work    = () -> build( user );
        if ( SwingUtilities.isEventDispatchThread() )
            work.run();
        else
        {
            try
            {
                SwingUtilities.invokeAndWait( work );
            }
            catch ( InterruptedException | InvocationTargetException exc )
            {
                exc.printStackTrace();
                System.exit( 1 );
            }
        }
        return board;
    }
    
    /**
     * Constructor.
     * Sets the initial size of the board
     * and registers the user of the board.
     * 
     * @param user  the user to be notified whenever the board is clicked
     */
    private TicTacToeBoard( TicTacToeUser user )
    {
        this.user = user;
        Dimension   preferredSize   = 
            new Dimension( boardSideDefault, boardSideDefault );
        setPreferredSize( preferredSize );
        addMouseListener( new Mouser() );
    }
    
    /**
     * Creates the board and the frame that contains it,
     * which is made visible.
     * Must only be invoked on the EDT.
     * May only be called once;
     * attempts to invoke this method multiple times
     * will be ignored.
     * 
     * @param user  the user to be notified whenever the board is clicked
     */
    private static void build( TicTacToeUser user )
    {
        if ( board == null )
        {
            board = new TicTacToeBoard( user );
            JFrame  frame       = new JFrame( "Tic Tac Toe" );
            frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
            frame.setContentPane( board );
            frame.pack();
            frame.setVisible( true );
        }
    }
    
    /**
     * Sets the color of the grid lines to the given value.
     *  
     * @param color the given value
     */
    public void setGridColor( Color color )
    {
        gridColor = color;
    }
    
    /**
     * Returns the color of the board's grid lines.
     * @return the color of the board's grid lines
     */
    public Color getGridColor()
    {
        return gridColor;
    }
    
    /**
     * Sets the background color of a square
     * to the given value.
     * 
     * @param row       row of the target square (0 - 2)
     * @param col       column of the target square (0 - 2)
     * @param color     the given value.
     */
    public void setBGColor( int row, int col, Color color )
    {
        squares[row][col].setBGColor( color );
    }
    
    /**
     * Gets the background color of a square.
     * 
     * @param row   the row of the target square
     * @param col   the column of the target square
     * 
     * @return  the background color of the square at the given row and column
     */
    public Color getBGColor( int row, int col )
    {
        Color   color   = squares[row][col].getBGColor();
        return color;
    }
    
    /**
     * Sets the foreground color of a square
     * to the given value.
     * 
     * @param row       row of the target square (0 - 2)
     * @param col       column of the target square (0 - 2)
     * @param color     the given value
     */
    public void setFGColor( int row, int col, Color color )
    {
        squares[row][col].setFGColor( color );
    }
    
    /**
     * Gets the foreground color of a square.
     * 
     * @param row   the row of the target square
     * @param col   the column of the target square
     * 
     * @return  the foreground color of the square at the given row and column
     */
    public Color getFGColor( int row, int col )
    {
        Color   color   = squares[row][col].getFGColor();
        return color;
    }
    
    /**
     * Gets the owner of a square.
     * Will return 'X', 'O', or,
     * if there is no owner, ' '.
     * 
     * @param row   the row of the target square
     * @param col   the column of the target square
     * 
     * @return  the owner of the square at the given row and column
     */
    public char getOwner( int row, int col )
    {
        char    owner   = squares[row][col].getOwner();
        return owner;
    }
    
    /**
     * Sets the owner of a square
     * to the given value.
     * 
     * @param row       row of the target square (0 - 2)
     * @param col       column of the target square (0 - 2)
     * @param owner     the given value.
     */
    public void setOwner( int row, int col, char owner)
    {
        squares[row][col].setOwner( owner );
    }
    
    /**
     * Displays this board in its current state.
     */
    @Override
    protected void paintComponent( Graphics graphics )
    {
        Graphics2D  gtx             = (Graphics2D)graphics.create();
        
        // If necessary, make the board square by setting its
        // dimensions equal to the smaller of the current width and height.
        // Given the established dimensions, calculate the length of the
        // side of a an edge of the board.
        int         width           = getWidth();
        int         height          = getHeight();
        workingSide = width < height ? width : height;
        int         side             =(int)(workingSide + .5);
        Dimension   preferredSize   = new Dimension( side, side );
        setPreferredSize( preferredSize );
        
        // Adjust the properties of the board (stroke, margin, square side)
        // According to the ration of the default board size to the current
        // board size.
        double      sidePercent     = workingSide / squareSideDefault;
        double      strokeWidth     = strokeDefault * sidePercent;
        if ( strokeWidth < 1 )
            strokeWidth = 1;
        Stroke      stroke          = new BasicStroke( (float)strokeWidth );
        squareSide = workingSide / 3.;
        margin = marginDefault * sidePercent;
        double  offset  = strokeWidth;
        
        // Draw all the squares.
        gtx.setStroke( stroke );
        Rectangle2D rect            = 
            new Rectangle2D.Double( offset, offset, workingSide, workingSide );
        for ( int row = 0 ; row < 3 ; ++row )
        {
            double  rectYco = row * squareSide;
            for ( int col = 0 ; col < 3 ; ++col )
            {
                TicTacToeSquare square  = squares[row][col];
                double          rectXco = col * squareSide;
                rect.setRect( rectXco, rectYco, squareSide, squareSide );
                gtx.setColor( square.getBGColor() );
                gtx.fill( rect );
                gtx.setColor( square.getFGColor() );
                Shape           shape   = 
                    getShape( row, col, square.getOwner() );
                if ( shape != null )
                    gtx.draw( shape );
            }
        }
        
        // Draw the border and grid lines of the board.
        gtx.setColor( gridColor );
        Rectangle2D border  = 
            new Rectangle2D.Double( 0, 0, workingSide, workingSide );
        Line2D      line    = new Line2D.Double();
        gtx.draw( border );
        line.setLine( squareSide, 0, squareSide, workingSide );
        gtx.draw( line );
        line.setLine( 2 * squareSide, 0, 2 * squareSide, workingSide );
        gtx.draw( line );
        line.setLine( 0, squareSide, workingSide, squareSide );
        gtx.draw( line );
        line.setLine( 0, 2 * squareSide, workingSide, 2 * squareSide );
        gtx.draw( line );
        
        gtx.dispose();
    }
    
    /**
     * Based on the properties of a square (row, column, owner)
     * generate a shape that encapsulate the owner's representation
     * ('X' or 'O) at the correct coordinates.
     * Returns null of the square has no owner.
     * 
     * @param row       the row of the square
     * @param col       the column of the square
     * @param owner     the owner of the square
     * 
     * @return  a shape to draw in the square, 
     *          or null if the square has no owner
     */
    private Shape getShape( int row, int col, char owner )
    {
        Shape   shape   = null;
        double  startX  = col * squareSide + margin;
        double  startY  = row * squareSide + margin;
        if ( owner == 'X' )
        {
            double  endX    = startX + squareSide - 2 * margin;
            double  endY    = startY + squareSide - 2 * margin;
            Path2D  path    = new Path2D.Double();
            path.moveTo( startX, startY );
            path.lineTo( endX, endY );
            path.moveTo( endX, startY );
            path.lineTo( startX, endY);
            shape = path;
        }
        else if ( owner == 'O' )
        {
            double      len     = squareSide - 2 * margin;
            shape = new Ellipse2D.Double( startX, startY, len, len );
        }
        else
            ;
        return shape;
    }
    
    /**
     * An instance of this class has a method, mouseClicked,
     * which is invoked every time the tic-tac-toe board is clicked.
     * In turn, this method invokes the clicked method
     * in the user's application.
     */
    private class Mouser extends MouseAdapter
    {
        /**
         * Default constructor.
         */
        private Mouser()
        {
            // not used
        }
        
        @Override
        public void mouseClicked( MouseEvent evt )
        {
            int     panelSide   = board.getWidth() / 3;
            int     col         = evt.getX() / panelSide;
            int     row         = evt.getY() / panelSide;
            char    owner       = squares[row][col].getOwner();
            user.clicked( row, col, owner );
        }
    }
}
