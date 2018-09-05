package edu.uweo.javaintro.game_of_life;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;

/**
 * Encapsulates the game board on which the Game of Life plays out.
 * The board is divided into square cells, which can be marked as
 * alive or dead. The appearance of the board is controlled by the
 * Properties singleton,
 * and is fixed during construction;
 * changing a property after instantiating the board
 * will not change the appearance
 * of the board.
 * <p>
 * An ActionEvent is dispatched each time the
 * user clicks on the board.
 * 
 * @see Properties
 * @see <a href="https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life">
 *          Conway's Game of Life.</a>
 */
public class Board implements Runnable
{
    
    /**
     * The name of the frame that contains the game board.
     *  Mainly useful for testing.
     */
    public static final String CANVAS_NAME = "GameOfLifeCanvas";
    
    /** The frame that contains the game board. */
    private JFrame frame = new JFrame("Game of Life");

    /** The use grid. */
    private boolean useGrid;
    
    /** 
     * If true, a border will be drawn
     * around the outside of the game board. 
     */
    private boolean useBorder;
    
    /** The color of the grid drawn on the inside of the game board. */
    private Color   gridColor;
    
    /** The color of the border drawn around the outside of the game board. */
    private Color   borderColor;
    
    /** The length of the side of a cell, in pixels. */
    private int     cellSide;
    
    /** The frame's content pane, on which is drawn the game board. */
    private Canvas  canvas;

    /**
     * The width of the border 
     * drawn around the outside of the board, in pixels.
     */
    private int         borderWidth;
    
    /** The width of a grid line drawn inside the game board, in pixels. */
    private int         gridLineWidth;
    
    /** The number of cells in the side of a grid. */
    private int         gridSide;
    
    /**
     *  The minimum length/height of a cell.
     */
    private int         minCellSide;
    
    /** The background color. */
    private Color       backgroundColor;
    
    /** The color of a live cell. */
    private Color       cellColor;
    
    /** 
     * Reflects the state (alive/dead) of every cell on the
     * game board.
     */
    private boolean[][] allCells;

    /** Event listeners. */
    private List<ActionListener> listeners = new ArrayList<>();

    /**
     * Instantiates a new game board with the default width.
     */
    public Board()
    {
        initState();
    }

    /**
     * Instantiates a new board of the given width/height, in cells.
     *
     * @param gridSide the given width/height
     */
    public Board( int gridSide )
    {
        Properties.GRID_SIDE.setProperty(gridSide);
        initState();
    }

    /**
     * Configures the game board. The game board consists of
     * a drawing surface contained in a JScrollPane.
     */
    public void run()
    {
        canvas = new Canvas();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane pane = new JScrollPane( canvas );
        frame.setContentPane(pane);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Adds the given ActionListener.
     *
     * @param listener the given ActionListener
     */
    public void addActionListener(ActionListener listener)
    {
        listeners.add(listener);
    }

    /**
     * Removes the given ActionListener.
     *
     * @param listener the given ActionListener
     */
    public void removeActionListener(ActionListener listener)
    {
        listeners.remove(listener);
    }

    /**
     * Returns a copy of the state of all cells on the game board.
     * Note that, since a <em>copy</em> of the state is returned,
     * changing the returned array will <em>not</em> affect
     * the state of the board.
     *
     * @return the cells
     * 
     * @see #setCell(Cell)
     * @see #setCells(Cell[])
     * @see #setCells(boolean[][])
     */
    public boolean[][] getCells()
    {
        boolean[][] arr = new boolean[allCells.length][];
        for (int inx = 0; inx < arr.length; ++inx)
            arr[inx] = Arrays.copyOf(allCells[inx], allCells[inx].length);
        return arr;
    }

    /**
     * Gets the length of a side of the game board.
     * Note that the game board is always square.
     *
     * @return the side
     */
    public int getSide()
    {
        return gridSide;
    }

    /**
     * Sets the state of a given cell.
     * Note that the board will not visually reflect the given state
     * until the user performs a refresh operation.
     *
     * @param cell the given cell
     * 
     * @throws IndexOutOfBoundsException if the row or column
     *         of the given cell is outside the bounds of the board.
     *         
     * @see #refresh()
     */
    public void setCell(Cell cell)
        throws IndexOutOfBoundsException
    {
        int row = cell.getRow();
        int col = cell.getCol();
        if (row >= gridSide || row < 0 || col >= gridSide || col < 0)
            throw new IndexOutOfBoundsException(cell.toString());
        allCells[row][col] = cell.isAlive();
    }

    /**
     * Sets the state of the given cells.
     * Note that the board will not visually reflect the given state
     * until the user performs a refresh operation.
     *
     * @param cells the new cells
     * 
     * @throws IndexOutOfBoundsException if any of the given cells
     *         are outside the bounds of the board.
     *         
     * @see #refresh()
     */
    public void setCells(Cell[] cells)
        throws IndexOutOfBoundsException
    {
        for (Cell cell : cells)
            setCell(cell);
    }

    /**
     * Sets the state of all the cells on the board.
     * Note that the board will not visually reflect the given state
     * until the user performs a refresh operation.
     *
     * @param state array that determines the state of each cell
     *              on the board.
     * 
     * @throws IllegalArgumentException the illegal argument exception
     *         if the dimensions of the given array do not exactly match
     *         the dimensions of the board.
     *         
     * @see #refresh()
     */
    public void setCells( boolean[][] state ) throws IllegalArgumentException
    {
        validateState( state );

        int rowLimit = allCells.length;
        for (int row = 0; row < rowLimit; ++row)
        {
            int colLimit = allCells[row].length;
            for (int col = 0; col < colLimit; ++col)
                allCells[row][col] = state[row][col];
        }
    }
    
    /**
     * Gets the length of the side of the board, in cells.
     *
     * @return the length of the side of the board, in cells
     */
    public int getCellSide()
    {
        return cellSide;
    }

    /**
     * Redraws the board.
     */
    public void refresh()
    {
        frame.getContentPane().repaint();
    }

    /**
     * Closes and disposes the frame that contains the game board.
     */
    public void close()
    {
        frame.setContentPane( new JPanel() );
        frame.setVisible(false);
        frame.dispose();
    }

    /**
     * Clears the game board; every cell will be marked dead.
     */
    public void clear()
    {
        for (int inx = 0; inx < allCells.length; ++inx)
            for (int jnx = 0; jnx < allCells[inx].length; ++jnx)
                allCells[inx][jnx] = false;
    }
    
    /**
     * Launches the game board.
     */
    public void start()
    {
        try
        {
            SwingUtilities.invokeAndWait( this );
        }
        catch ( InvocationTargetException exc )
        {
            exc.printStackTrace();
            throw new RuntimeException( "Board.start failure", exc );
        }
        catch ( InterruptedException exc )
        {
            exc.printStackTrace();
            throw new RuntimeException( "Board.start failure", exc );
        }
    }

    /**
     * Initializes the state of the game board.
     */
    private void initState()
    {
        useGrid         = (boolean) Properties.USE_GRID.getProperty();
        useBorder       = (boolean) Properties.USE_BORDER.getProperty();
        gridColor       = (Color) Properties.GRID_COLOR.getProperty();
        borderColor     = (Color) Properties.BORDER_COLOR.getProperty();
        cellColor       = (Color)Properties.CELL_COLOR.getProperty();
        borderWidth     = (int) Properties.BORDER_WIDTH.getProperty();
        gridLineWidth   = (int) Properties.GRID_LINE_WIDTH.getProperty();
        gridSide        = (int) Properties.GRID_SIDE.getProperty();
        minCellSide     = (int) Properties.MIN_CELL_SIDE.getProperty();
        backgroundColor = (Color)Properties.BACKGROUND_COLOR.getProperty();
        allCells        = new boolean[gridSide][gridSide];
        
        if ( gridColor == null || gridLineWidth <= 0 )
            useGrid = false;
        
        if ( borderColor == null || borderWidth <= 0 )
            useBorder = false;
    }

    /**
     * Verifies that the shape of a 2-D boolean array is suitable for initializing
     * the 2-D boolean array of cells (<em>allCells</em>). Most likely, 
     * the input array was passed by a user for the purpose of setting the board
     * to a predetermined state.
     *
     * @param state     The state to validate.
     *  
     * @throws IllegalArgumentException the illegal argument exception
     * @see #setCells( boolean[][] )
     */
    private void validateState( boolean[][] state ) throws IllegalArgumentException
    {
        final String errFmt = "Invalid array dimensions: [%d][%d]; expected: [%d][%d]";

        boolean err = false;
        int expRows = allCells.length;
        int expCols = allCells[0].length;
        int actRows = state.length;
        int actCols = state[0].length;

        if (expRows != actRows)
            err = true;
        else
        {
            for ( int row = 0 ; row < expRows && !err ; ++row )
            {
                expCols = allCells[row].length;
                actCols = state[row].length;
                if (expCols != actCols)
                    err = true;
            }
        }

        if (err)
        {
            String msg = String.format(errFmt, actRows, actCols, expRows, expCols);
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * The Swing component responsible for drawing the game board.
     * The geometry of a cell drawn on the board is based on the
     * minimum of the board's width and height, in pixels. When
     * the board is resized, the geometry of a cell will be adjusted.
     * The side of a cell will never be smaller than the 
     * MIN_CELL_SIDE property.
     */
    @SuppressWarnings("serial")
    private class Canvas extends JPanel
    {
        
        /** The grid line stroke. */
        private final Stroke    gridLineStroke = 
            new BasicStroke(gridLineWidth);

        /**
         * Instantiates a new canvas.
         */
        public Canvas()
        {
            setName( CANVAS_NAME );
            
            int size = (gridSide + 1) * minCellSide;
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = screenSize.width;
            int height = screenSize.height;
            int base = width < height ? width : height;
            base = (int) (.75 * base);
            size = size < base ? size : base;
            setPreferredSize(new Dimension(size, size));
            addMouseListener(new MouseProcessor());

            if (backgroundColor != null)
                setBackground(backgroundColor);

            if (useBorder)
            {
                MatteBorder border = 
                    new MatteBorder( 
                        borderWidth, 
                        borderWidth, 
                        borderWidth, 
                        borderWidth, 
                        borderColor
                    );
                setBorder(border);
            }
        }

        /* (non-Javadoc)
         * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
         */
        @Override
        public void paintComponent(Graphics graphics)
        {
            super.paintComponent(graphics);

            Graphics2D gtx = (Graphics2D) graphics.create();

            // The board must be square, so set the working dimensions 
            // to minimum(width, height).
            int width = getWidth();
            int height = getHeight();
            int base = width < height ? width : height;
            
            // Calculate the size of a cell; do not allow it to be
            // smaller than MIN_CELL_SIDE.
            cellSide = base / gridSide;
            if (cellSide < minCellSide)
            {
                cellSide = minCellSide;
            }

            // Draw the board.
            paintGrid(gtx);
            
            // Recalculate the preferred size of the Canvas.
            // This is necessary to make the scroll bars work properly.
            int size = gridSide * cellSide;
            if (useBorder)
                size += 2 * borderWidth;
            setPreferredSize(new Dimension(size, size));
            revalidate();
        }

        /**
         * Paints the grid.
         *
         * @param gtx the gtx
         */
        private void paintGrid(Graphics2D gtx)
        {
            int xco = 0;
            int yco = 0;
            gtx.setStroke(gridLineStroke);

            if (useBorder)
            {
                xco += borderWidth;
                yco += borderWidth;
            }

            Rectangle2D rect = new Rectangle2D.Double();
            int saveYco = yco;
            for (int inx = 0; inx < gridSide; ++inx)
            {
                yco = saveYco;
                for (int jnx = 0; jnx < gridSide; ++jnx)
                {
                    rect.setRect(xco, yco, cellSide, cellSide);
                    if (useGrid)
                    {
                        gtx.setColor(gridColor);
                        gtx.draw(rect);
                    }
                    if (allCells[inx][jnx])
                    {
                        gtx.setColor(cellColor);
                        gtx.fill(rect);
                    }
                    yco += cellSide;
                }
                xco += cellSide;
            }
        }
    }

    /**
     * Listens for mouse events, and dispatches events to the 
     * board's Actionlisteners.
     */
    private class MouseProcessor extends MouseAdapter
    {
        
        /* (non-Javadoc)
         * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseClicked(MouseEvent evt)
        {
            int xco = evt.getX();
            int yco = evt.getY();

            if (useBorder)
            {
                xco -= borderWidth;
                yco -= borderWidth;
            }

            int row = xco / cellSide;
            int col = yco / cellSide;
            if (row < allCells.length && col < allCells[row].length)
            {
                boolean alive = allCells[row][col];
                Cell cell = new Cell(row, col, alive);
                int ident = evt.getID();
                int modifiers = evt.getModifiers();
                ActionEvent event = new ActionEvent(cell, ident, "", modifiers);
                for (ActionListener listener : listeners)
                    listener.actionPerformed(event);
            }
        }
    }
}
