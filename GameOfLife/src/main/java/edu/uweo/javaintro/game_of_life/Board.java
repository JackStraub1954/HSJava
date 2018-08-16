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

public class Board implements Runnable
{
    public static final String CANVAS_NAME = "GameOfLifeCanvas";
    
    private JFrame frame = new JFrame("Game of Life");

    private boolean useGrid;
    private boolean useBorder;
    private Color   gridColor;
    private Color   borderColor;
    private int     cellSide;
    private Canvas  canvas;

    /**
     * Determines the "width" of the border drawn around the outside of the board.
     * 
     * <div style = "border-left: 5px solid black; border-top: 5px solid black;
     * max-width: 5em; height: 5em;" >
     * 
     * <pre>
     *    &#x2191;
     * &#x2190; border
     * </pre>
     * 
     * </div>
     */
    private int         borderWidth;
    private int         gridLineWidth;
    private int         gridSide;
    private int         minCellSide;
    private Color       backgroundColor;
    private Color       cellColor;
    private boolean[][] allCells;

    private List<ActionListener> listeners = new ArrayList<>();

    public Board()
    {
        initState();
    }

    public Board( int gridSide )
    {
        Properties.GRID_SIDE.setProperty(gridSide);
        initState();
    }

    public void run()
    {
        canvas = new Canvas();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane pane = new JScrollPane( canvas );
        frame.setContentPane(pane);
        frame.pack();
        frame.setVisible(true);
    }

    public void addActionListener(ActionListener listener)
    {
        listeners.add(listener);
    }

    public void removeActionListener(ActionListener listener)
    {
        listeners.remove(listener);
    }

    public boolean[][] getCells()
    {
        boolean[][] arr = new boolean[allCells.length][];
        for (int inx = 0; inx < arr.length; ++inx)
            arr[inx] = Arrays.copyOf(allCells[inx], allCells[inx].length);
        return arr;
    }

    public int getSide()
    {
        return gridSide;
    }

    public void setCell(Cell cell)
    {
        int row = cell.getRow();
        int col = cell.getCol();
        if (row >= gridSide || row < 0 || col >= gridSide || col < 0)
            throw new IndexOutOfBoundsException(cell.toString());
        allCells[row][col] = cell.isAlive();
    }

    public void setCells(Cell[] cells)
    {
        for (Cell cell : cells)
            setCell(cell);
    }

    public void setCells(boolean[][] state) throws IllegalArgumentException
    {
        validateState(state);

        int rowLimit = allCells.length;
        for (int row = 0; row < rowLimit; ++row)
        {
            int colLimit = allCells[row].length;
            for (int col = 0; col < colLimit; ++col)
                allCells[row][col] = state[row][col];
        }
    }
    
    public int getCellSide()
    {
        return cellSide;
    }

    public void refresh()
    {
        frame.getContentPane().repaint();
    }

    public void close()
    {
        frame.setVisible(false);
        frame.dispose();
    }

    public void clear()
    {
        for (int inx = 0; inx < allCells.length; ++inx)
            for (int jnx = 0; jnx < allCells[inx].length; ++jnx)
                allCells[inx][jnx] = false;
    }
    
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
        }
    }

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
    }

    private void validateState(boolean[][] state) throws IllegalArgumentException
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
            for (int row = 0; row < expRows && !err; ++row)
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

    @SuppressWarnings("serial")
    private class Canvas extends JPanel
    {
        private Stroke gridLineStroke = new BasicStroke(gridLineWidth);
        private int width;
        private int height;

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

            if (useBorder && borderColor != null)
            {
                MatteBorder border = new MatteBorder(borderWidth, borderWidth, borderWidth, borderWidth, borderColor);
                setBorder(border);
            }
        }

        @Override
        public void paintComponent(Graphics graphics)
        {
            super.paintComponent(graphics);

            Graphics2D gtx = (Graphics2D) graphics.create();

            width = getWidth();
            height = getHeight();
            int base = width < height ? width : height;
            cellSide = base / gridSide;
            if (cellSide < minCellSide)
            {
                cellSide = minCellSide;
            }

            paintGrid(gtx);
            int size = gridSide * cellSide;
            if (useBorder)
                size += 2 * borderWidth;
            setPreferredSize(new Dimension(size, size));
            revalidate();
        }

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

    private class MouseProcessor extends MouseAdapter
    {
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
