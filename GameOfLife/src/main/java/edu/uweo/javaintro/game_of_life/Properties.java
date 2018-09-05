package edu.uweo.javaintro.game_of_life;

import java.awt.Color;

// TODO: Auto-generated Javadoc
/**
 * Encapsulates the properties of a Game of Life board. Properties are
 * divided into several categories:
 * <blockquote>
 * <dl>
 * <dt>Category: Border</dt>
 * <dd>
 * 
 * <div style = "border-left: 5px solid black; border-top: 5px solid black;
 *      max-width: 5em; height: 5em;">
 * 
 * <pre>
 *    &#x2191;
 * &#x2190; border
 * </pre>
 * </div>
 * Properties of the edge drawn around the outside of the board.
 * <p>See:</p>
 * <ul>
 * <li>{@link #USE_BORDER}</li>
 * <li>{@link #BORDER_WIDTH}</li>
 * <li>{@link #BORDER_COLOR}</li>
 * </ul>
 * 
 * </dd>
 * <dt>Category: Grid</dt>
 * <dd>
 * <table style = "border-collapse: collapse">
 * <caption>&nbsp;</caption>
 * <tr>
 * <td style = 
 *     "border-right: 1px solid black;
 *      border-bottom: 1px solid black;
 *      width:  20px;
 *      height:  20px;"
 *  >
 *  &nbsp;
 *  </td>
 * <td style = 
 *     "border-left: 1px solid black
 *      border-right: 1px solid black;
 *      border-bottom: 1px solid black;
 *      width:  20px;
 *      height:  20px;"
 *  >
 *  &nbsp;
 *  </td>
 * <td style = 
 *     "border-left: 1px solid black;
 *      border-bottom: 1px solid black;
 *      width:  20px;
 *      height:  20px;"
 *  >
 *  &nbsp;
 *  </td>
 * </tr>
 * <tr>
 * <td style = 
 *     "border-top: 1px solid black;
 *      border-right: 1px solid black;
 *      border-bottom: 1px solid black;
 *      width:  20px;
 *      height:  20px;"
 *  >
 *  &nbsp;
 *  </td>
 * <td style = 
 *     "border: 1px solid black;
 *      width:  20px;
 *      height:  20px;"
 *  >
 *  &nbsp;
 *  </td>
 * <td style = 
 *     "border-top: 1px solid black;
 *      border-left: 1px solid black;
 *      border-bottom: 1px solid black;
 *      width:  20px;
 *      height:  20px;"
 *  >
 *  &nbsp;
 * </td>
 * </tr>
 * <tr>
 * <td style = 
 *     "border-right: 1px solid black;
 *      border-top: 1px solid black;
 *      width:  20px;
 *      height:  20px;"
 *  >
 *  &nbsp;
 *  </td>
 * <td style = 
 *     "border-left: 1px solid black
 *      border-right: 1px solid black;
 *      border-top: 1px solid black;
 *      width:  20px;
 *      height:  20px;"
 *  >
 *  &nbsp;
 *  </td>
 * <td style = 
 *     "border-left: 1px solid black;
 *      border-top: 1px solid black;
 *      width:  20px;
 *      height:  20px;"
 *  >
 *  &nbsp;
 *  </td>
 * </tr>
 * <tr>
 * </table>
 * 
 * Properties of the lines drawn between cells.
 * <p>See:</p>
 * <ul>
 * <li>{@link #USE_GRID}</li>
 * <li>{@link #GRID_LINE_WIDTH}</li>
 * <li>{@link #GRID_COLOR}</li>
 * <li>{@link #GRID_SIDE}</li>
 * <li>{@link #MIN_CELL_SIDE}</li>
 * </ul>
 * </dd>
 * 
 * <dt>Category: Colors</dt>
 * <dd>
 * The color of a cell that is alive/dead.
 * <p>See:</p>
 * <ul>
 * <li>{@link #BACKGROUND_COLOR}</li>
 * <li>{@link #CELL_COLOR}</li>
 * </ul>
 * </dd>
 * </dl>
 * </blockquote>
 */
public enum Properties
{
    
    /** 
     * Controls whether or not a border is drawn around the 
     * game board.
     * The default is true.
     * 
     *   @see #BORDER_COLOR
     *   @see #BORDER_WIDTH
     */
    USE_BORDER( true ),
    
    /** 
     * The width in pixels of the border drawn around the game board.
     * Only applicable if USE_BORDER is true. The border will
     * not be drawn if BORDER_WIDTH is 0.
     *  The default is 10.
     */
    BORDER_WIDTH( 10 ),
    
    /** 
     * The color of the border drawn around the game board.
     * Only applicable if USE_BORDER is true. The border will
     * not be drawn if BORDER_COLOR is null.
     * The default is <em>Color( 0f, .28f, .73f ).</em>
     */
    BORDER_COLOR( new Color( 0f, .28f, .73f ) ),
    
    /**
     *  Indicates whether or not the grid is to be drawn over
     *  the game board.
     *  The default is true.
     *  
     *  @see #GRID_LINE_WIDTH
     *  @see #GRID_COLOR
     */
    USE_GRID( true ),
    
    /** 
     * The width of a line on the grid. If this value is 0
     * the grid will not be drawn.
     * The default is 1. 
     */
    GRID_LINE_WIDTH( 1 ),
    
    /** 
     * The color of the grid drawn over the game board
     * If this value is null the grid will not be drawn.
     * The default is gray80 (<em>Color(.8f,.8f,.8f)).</em>
     * 
     */
    GRID_COLOR( new Color( .8f, .8f, .8f ) ),
    
    /** 
     * The number of cells on the side of the grid.
     * Note that the width and height of the grid
     * are always equal.
     * The default is 500.
     */
    GRID_SIDE( 500 ),
    
    /** 
     * The minimum length of the side of a cell.
     * Note that the width and height of a cell are
     * always equal.
     * The default value is 10.
     */
    MIN_CELL_SIDE( 10 ),
    
    /** 
     * The background color of the game board.
     * This will be the color of a cell when it is not selected.
     * The default is white.
     * @see #CELL_COLOR
     */
    BACKGROUND_COLOR( Color.WHITE ),
    
    /** 
     * The color of a cell when it is selected.
     * 
     * @see #BACKGROUND_COLOR
     */
    CELL_COLOR( Color.BLACK );
    
    /** The default value of this property. */
    private final Object    defValue;
    
    /** The current value of this property. */
    private Object          currValue;
    
    /**
     * Instantiates a new the Properties object
     * (an enum constant).
     *
     * @param obj the obj
     */
    private Properties( Object obj )
    {
        defValue = currValue = obj;
    }
    
    /**
     * Gets the current value of this property.
     *
     * @return the current value of this property
     */
    public Object getProperty()
    {
        return currValue;
    }
    
    /**
     * Sets the current value of this property.
     *
     * @param obj the new property
     */
    public void setProperty( Object obj )
    {
        currValue = obj;
    }
    
    /**
     * Resets all properties to their default values.
     */
    public static void reset()
    {
        Properties[]    props   = Properties.values();
        for ( Properties prop : props )
            prop.setProperty( prop.getDefaultValue() );
    }
    
    /**
     * Gets the default value of this property.
     *
     * @return the default value of this property
     */
    public Object getDefaultValue()
    {
        return defValue;
    }
}
