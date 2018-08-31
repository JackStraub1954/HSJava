package edu.uweo.javaintro.game_of_life;

import java.awt.Color;

/**
 * Encapsulates the properties of a Game of Life board. Properties are
 * divided into several categories:
 * <blockquote>
 * <dl>
 * <dt>Category: Border</dt>
 * <dd>
 * 
 * <div style = "border-left: 5px solid black; border-top: 5px solid black; max-width: 5em; height: 5em;">
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
 * </ul>
 * 
 * </dd>
 * </dl>
 * </blockquote>
 */
public enum Properties
{
    
    /** The use border. */
    USE_BORDER( true ),
    
    /** The border width. */
    BORDER_WIDTH( 10 ),
    
    /** The border color. */
    BORDER_COLOR( new Color( 0f, .28f, .73f ) ),
    
    /** The use grid. */
    USE_GRID( true ),
    
    /** The grid line width. */
    GRID_LINE_WIDTH( 1 ),
    
    /** The grid color. */
    GRID_COLOR( new Color( .8f, .8f, .8f ) ),
    
    /** The grid side. */
    GRID_SIDE( 500 ),
    
    /** The min cell side. */
    MIN_CELL_SIDE( 10 ),
    
    /** The background color. */
    BACKGROUND_COLOR( Color.WHITE ),
    
    /** The cell color. */
    CELL_COLOR( Color.BLACK );
    
    /** The def value. */
    private final Object    defValue;
    
    /** The curr value. */
    private Object          currValue;
    
    /**
     * Instantiates a new properties.
     *
     * @param obj the obj
     */
    private Properties( Object obj )
    {
        defValue = currValue = obj;
    }
    
    /**
     * Gets the property.
     *
     * @return the property
     */
    public Object getProperty()
    {
        return currValue;
    }
    
    /**
     * Sets the property.
     *
     * @param obj the new property
     */
    public void setProperty( Object obj )
    {
        currValue = obj;
    }
    
    /**
     * Reset.
     */
    public static void reset()
    {
        Properties[]    props   = Properties.values();
        for ( Properties prop : props )
            prop.setProperty( prop.getDefaultValue() );
    }
    
    /**
     * Gets the default value.
     *
     * @return the default value
     */
    public Object getDefaultValue()
    {
        return defValue;
    }
}
