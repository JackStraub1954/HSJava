/**
 * This package encapsulates a GUI
 * to draw a representation of the Cartesian plane,
 * including points plotted by the client.
 * The intent is to provide students
 * with a utility that allows them to plot equations
 * without worrying about the complexity
 * of a user interface.
 * The following discussion outlines some of the key features
 * and properties of the implementation.
 * <h2>The Plane Class</h2>
 * <div style="float:right; width:35%;">
 *      <figure>
     *      <img 
     *          src="doc-files/GeneralPicture.png" 
     *          alt="General Picture" 
     *          style="width:75%;"
     *      >
     *      <figcaption style="font: italic 75% sans-serif; text-align: center;">
     *          The Plane Class
     *      </figcaption>
 *      </figure>
 *  </div>
 * <p>
 * The <em>Plane class</em>
 * is the only class of interest to the client.
 * To create a <em>Plane</em> object,
 * invoke <em>CPFrame.getPlane()</em>,
 * which will instantiate and display 
 * the GUI encapsulating the Cartesian plane grid 
 * and return to you an object 
 * that you can use to manipulate the GUI.
 * </p>
 * <h2>The Unit Length</h2>
 * <p>
 * The <em>unit length</em> determines how many pixels
 * of a screen are spanned by one unit on the Cartesian plane grid.
 * The figures below depict a grid with a large unit length
 * and one with a small unit length.
 * </p>
 * <table>
 *      <caption style="caption-side:bottom; font-size:75%;">
 *          Unit Length Values
 *      </caption>
 *      <tr>
 *          <td style="text-align:center">
 *              <figure>
 *                  <img 
 *                      src="doc-files/SmallUnitLengthValue.png" 
 *                      alt="Short Unit Length" 
 *                      style="width:60%;"
 *                  >
 *                  <figcaption style="font: italic 75% sans-serif;">
 *                      <em>Plane with Small Unit Length</em>
 *                  </figcaption>
 *              </figure>
 *          </td>
 *           <td style="text-align:center">
 *              <figure>
 *                  <img 
 *                      src="doc-files/LargeUnitLengthValue.png" 
 *                      alt="Short Unit Length" 
 *                      style="width:50%;"
 *                  >
 *                  <figcaption style="font: italic 75% sans-serif;">
 *                      Plane with Large Unit Length
 *                  </figcaption>
 *              </figure>
 *          </td>
 *      </tr>
 * </table>
 * <h2>Line Classes and Line Properties</h2>
 * <p>
 *      Lines on the grid come in four classes 
 *      (see {@link com.judahstutorials.javaintro.cartesian_plane.LineClass}):
 * </p>
 * <ul>
 *      <li>
 *          LineClass.AXIS:
 *          The x- and y-axes.
 *      </li>
 *      <li>
 *          LineClass.GRIDLINE:
 *          The gridlines that typically span the length and width of the grid.
 *          By default, they are thin lines that occur at every unit mark.
 *      </li>
 *      <li>
 *          LineClass.MAJOR_TICK:
 *          The longer tick marks on the x- and y-axes.
 *      </li>
 *      <li>
 *          LineClass.MINOR_TICK:
 *          The shorter tick marks on the x- and y-axes.
 *      </li>
 * </ul>
 * <p>
 *  Every class of line has the following properties:
 * </p>
 * <ul>
 *      <li>
 *          boolean draw:
 *           If this property is true, 
 *           the Cartesian plane facility will display 
 *           the lines in the given class. 
 *           To suppress the display of a class of lines, 
 *           change this property to false.
 *      </li>
 *      <li>
 *          Color color:
 *          The color of every line in the class.
 *      </li>
 *      <li>
 *          double length:
 *          The length of every line in the class.
 *          If this property has a value of -1,
 *          the lines will span the width and height
 *          of the grid.
 *          Gridlines typically have a length of -1;
 *          as a rule, axes should have a length of -1.
 *      </li>
 *      <li>
 *          double width:
 *          The width of every line in the class.
 *      </li>
 *      <li>
 *          int perUnit:
 *          The number of lines that the Cartesian plane facility 
 *          will draw per unit of the grid.
 *          In the figure on the left below,
 *          gridlines have a per-unit value of 1,
 *          major tick marks have a per-unit value of 1,
 *          and minor tick marks have a per-unit value of 2.
 *          In the figure on the right,
 *          gridlines have a per-unit value of 1,
 *          major tick marks have a per-unit value of 2,
 *          and minor tick marks have a per-unit value of 4.
 *      </li>
 * </ul>
 * <table>
 *      <caption style="caption-side:bottom; font-size:75%;">
 *          Per-unit Values
 *      </caption>
 *      <tr>
 *          <td style="text-align:center">
 *              <figure>
 *                  <img 
 *                      src="doc-files/SmallerPerUnitExample.png" 
 *                      alt="Smaller per unit example" 
 *                      style="width:60%;"
 *                  >
 *                  <figcaption style="font: italic 75% sans-serif;">
 *                      <em>Grid with Smaller Per-unit Values</em>
 *                  </figcaption>
 *              </figure>
 *          </td>
 *           <td style="text-align:center">
 *              <figure>
 *                  <img 
 *                      src="doc-files/LargerPerUnitExample.png" 
 *                      alt="Larger per unit example" 
 *                      style="width:50%;"
 *                  >
 *                  <figcaption style="font: italic 75% sans-serif;">
 *                      <em>Grid with Larger Per-unit Values</em>
 *                  </figcaption>
 *              </figure>
 *          </td>
 *      </tr>
 * </table>
 * <p>
 *      The client can interrogate and change line properties 
 *      using methods in the <em>Plane</em> class.
 *      Invoke the getter or setter for the property,
 *      specifying the target line class
 *      (e.g., LineClass.MAJOR_TICK).
 *      The <em>Plane</em> methods are:
 * </p>
 * <dl>
 *      <dt>
 *          <strong>
 *              public Color getColor( int lineClass )<br>
 *              public void setColor( int lineClass, Color color )
 *          </strong>
 *      </dt>
 *      <dd>
 *          Get or set the color of a line class.<br>&nbsp;
 *      </dd>
 *      <dt>
 *          <strong>
 *              public double getLength( int lineClass )<br>
 *              public void setLength( int lineClass, double length )
 *          </strong>
 *      </dt>
 *      <dd>
 *          Get or set the length of the lines in a line class.<br>&nbsp;
 *      </dd>
 *      <dt>
 *          <strong>
 *              public double getWidth( int lineClass )<br>
 *              public void setWidth( int lineClass, double width )
 *          </strong>
 *      </dt>
 *      <dd>
 *          Get or set the width of the lines in a line class.<br>&nbsp;
 *      </dd>
 *      <dt>
 *          <strong>
 *              public int getPerUnit( int lineClass )<br>
 *              public void setPerUnit( int lineClass, int perUnit )
 *          </strong>
 *      </dt>
 *      <dd>
 *          Get or set the lines-per-unit value of a line class.<br>&nbsp;
 *      </dd>
 * </dl>
 * <h2>Other Plane Class Methods and Properties</h2>
 * <p>
 *      The client can utilize other methods in the <em>Plane</em> class
 *      to plot points and control the properties 
 *      of the encapsulating grid.
 *      To plot points, use the following methods:
 * </p>
 * <dl>
 *      <dt>
 *          <strong>
 *              public void addPoint( double xco, double yco )
 *          </strong>
 *      </dt>
 *      <dd>
 *          Adds the point with the given x- and y-coordinates
 *          to the list of points to draw on the Cartesian plane.
 *      </dd>
 *      <dt>
 *          <strong>
 *              public void clear()
 *          </strong>
 *      </dt>
 *      <dd>
 *          Empties the list of points to draw on the Cartesian plane.
 *      </dd>
 *      <dt>
 *          <strong>
 *              public void repaint()
 *          </strong>
 *      </dt>
 *      <dd>
 *          Whenever you modify the grid, 
 *          whether to change the list of points 
 *          or the appearance of the grid, 
 *          you must call this method 
 *          before the changes take effect in the GUI.
 *      </dd>
 * </dl>
 * <p>
 * The plane class has the following additional properties:
 * </p>
 * <ul>
 *      <li>
 *          <strong>String fontFamily<br>
 *          int fontSize<br>   
 *          private int fontStyle</strong><br>
 *          These properties control the appearance of text on the grid.
 *          To interrogate or modify one of these properties,
 *          use one of the <em>Plane</em> class methods,
 *          such as <em>getFontX</em> or <em>setFontX,</em>
 *          where X is the name of the property,
 *          for example, <em>getFontSize</em> and <em>setFontSize.</em>
 *      </li>
 *      <li>
 *          <strong>Color textColor</strong><br>
 *          This property determines the color of text
 *          displayed on the grid.
 *          The client can interrogate or change this value
 *          using the <em>getTextColor</em> and <em>setTextColor</em> methods.
 *      </li>
 *      <li>
 *          <strong>boolean drawText</strong><br>
 *          Set this property to false 
 *          to suppress the display of labels on the grid.
 *          Use the methods <em>getTextDraw</em> and <em>setTextDraw</em>
 *          to get or set the value of this property.    
 *      </li>
 *      <li>
 *          <strong>double pointDiam<br>
 *          Color pointColor</strong><br>
 *          The facility represents points 
 *          on the Cartesian plane as circles. 
 *          These properties control the diameter
 *          and color of the circles.
 *          Interrogate and modify these properties
 *          using <em>getPointColor, setPointColor,
 *          getPointDiam,</em> and <em>setPointDiam.</em>
 *      </li>
 *      <li>
 *          <strong>Color backgroundColor</strong><br>
 *          This property controls the background color
 *          of the window that contains the grid.
 *          Use the methods <em>getBackgroundColor</em> 
 *          and <em>setBackgroundColor</em>
 *          to get or set the value of this property.    
 *      </li>
 *      <li>
 *          <strong>Color pitchColor</strong><br>
 *          This property controls the color of the 
 *          Cartesian plane grid.
 *          You can get it and set it using
 *          the methods <em>getPitchColor</em> 
 *          and <em>setPitchColor</em>.    
 *      </li>
 *      <li>
 *          <strong>double unitLength</strong><br>
 *          As discussed earlier,
 *          this property controls the number of pixels 
 *          occupied by a unit on the grid.
 *          The client can get it and set it using
 *          the methods <em>getUnitLength</em> 
 *          and <em>setUnitLength</em>.    
 *      </li>
 * </ul>
 * <h2>Sample Code</h2>
 * <h3>CartesianPlaneDemo1</h3>
 * <p>
 * This program demonstrates how to instantiate 
 * and utilize <em>Plane</em> objects.
 * It plots a straight line
 * using points with x-coordinates 
 * spaced at .5 units.
 * All defaults for the GUI appearance are accepted.
 * </p>
<pre>public static void main(String[] args)
{
    Plane   plane   = CPFrame.getPlane();
    for ( double coo = -2 ; coo &lt;= 2 ; coo += .5 )
        plane.addPoint( coo, coo );
    plane.repaint();
}</pre>
 * <h3>CartesianPlaneDemo2</h3>
 * <p>
 * This application modifies specific properties 
 * that control the GUI's appearance, 
 * then plots a parabola between -1.5 and +1.5 
 * in increments of 0.01.
 * </p>
<pre>public static void main(String[] args)
{
    Color   darkGreen   = new Color( 0x006400 );
    Plane   plane       = CPFrame.getPlane();
    plane.setMargin( 35 );
    plane.setBackground( Color.ORANGE );
    plane.setPitchColor( Color.CYAN );
    plane.setPointColor( Color.BLUE );
    plane.setColor( LineClass.AXIS, darkGreen );
    plane.setColor( LineClass.MAJOR_TICK, Color.RED );
    plane.setUnitLength( 100 );
    plane.setPointDiam( 3 );
    plane.setPerUnit( LineClass.MAJOR_TICK, 4 );
    plane.setPerUnit( LineClass.MINOR_TICK, 8 );
    plane.setFontSize( 8 );
    for ( double coo = -1.5 ; coo &lt;= 1.5 ; coo += .01 )
        plane.addPoint( coo, Math.pow( coo,  2 ) );
    plane.repaint();
}</pre>
 * <h2>Further Suggestions</h2>
 * <p>
 * As part of a project,
 * the student can use this facility to:
 * </p>
 * <ul>
 *      <li>Plot polynomials, trigonometric, and other simple equations</li>
 *      <li>Plot polar equations</li>
 *      <li>Plot parametric equations</li>
 *      <li>Draw geometric figures</li>
 *      <li>Implement rotation, translation, and scaling features</li>
 * </ul>
 */
package com.judahstutorials.javaintro.cartesian_plane;
