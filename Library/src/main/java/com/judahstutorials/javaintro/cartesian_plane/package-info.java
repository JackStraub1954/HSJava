/**
 * This package encapsulates a GUI
 * to draw a representation of the Cartesian plane,
 * including points plotted by the client.
 * The intent is to provide students
 * with a utility that allows then to plot equations
 * without worrying about the complexity
 * of a user interface.
 * Following is a discussion of some of the 
 * features and properties of the implementation.
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
 * To create a <em>Plane</em> object
 * invoke <em>CPFrame.getPlane()</em>.
 * This will instantiate and display the GUI
 * encapsulating the Cartesian plane grid
 * and return to you and object
 * that can be used to manipulate the GUI.
 * </p>
 * <h2>The Unit Length</h2>
 * <p>
 * The <em>unit length</em> determines how many pixel
 * of your screen are spanned by one unit on the Cartesian plane grid.
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
 *          the x- and y-axes.
 *      </li>
 *      <li>
 *          LineClass.GRIDLINE:
 *          the gridlines that typically span the length and width of the grid.
 *          By default, they are thin lines that occur at every unit mark.
 *      </li>
 *      <li>
 *          LineClass.MAJOR_TICK:
 *          the longer tick marks on the x- and y-axes.
 *      </li>
 *      <li>
 *          LineClass.MINOR_TICK:
 *          the shorter tick marks on the x- and y-axes.
 *      </li>
 * </ul>
 * <p>
 *  Every class of line has the following properties:
 * </p>
 * <ul>
 *      <li>
 *          boolean draw:
 *          if this property is true the lines in the given class
 *          will be displayed.
 *          To suppress display of a class of line
 *          change this property to false.
 *      </li>
 *      <li>
 *          Color color:
 *          the color of every line in the class.
 *      </li>
 *      <li>
 *          double length:
 *          the length of every line in the class.
 *          If this property has a value of -1
 *          the lines will span the width and height
 *          of the grid.
 *          Gridlines typically have a length of -1;
 *          as a rule, axes should have a length of -1.
 *      </li>
 *      <li>
 *          double width:
 *          the width of every line in the class.
 *      </li>
 *      <li>
 *          int perUnit:
 *          the number of lines that are drawn per unit of the grid.
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
 *      Line properties can be interrogated and changed
 *      using methods in the <em>Plane</em>.
 *      Invoke the getter or setter for the property,
 *      specifying the target line class
 *      (e.g. LineClass.MAJOR_TICK).
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
 *          Get or set the lines-per-unit value a line class.<br>&nbsp;
 *      </dd>
 * </dl>
 * <h2>Other Plane Class Methods and Properties</h2>
 * <p>
 *      Other methods in the <em>Plane</em> class
 *      are used to plot points,
 *      and to control the properties of the encapsulating grid.
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
 *          Any time you make a modification to the grid,
 *          whether you are changing the list of points
 *          or the appearance of the grid,
 *          you must call this method before you can see your changes
 *          in the GUI.
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
 *          use one the <em>Plane</em> class methods,
 *          <em>getFontX</em> or <em>getFontX</em>
 *          where X is the name of the property,
 *          for example, <em>getFontSize</em> and <em>setFontSize.</em>
 *      </li>
 *      <li>
 *          <strong>Color textColor</strong><br>
 *          This property determines the color of text
 *          displayed on the grid.
 *          You can interrogate or change this value
 *          using <em>getTextColor</em> and <em>setTextColor.</em>
 *      </li>
 *      <li>
 *          <strong>boolean drawText</strong><br>
 *          Set this property to false 
 *          to suppress displaying labels on the grid.
 *          Use the methods <em>getTextDraw</em> and <em>setTextDraw</em>
 *          to get or set the value of this property.    
 *      </li>
 *      <li>
 *          <strong>double pointDiam<br>
 *          Color pointColor</strong><br>
 *          Points on the Cartesian plane
 *          are represented as circles.
 *          These properties control the diameter
 *          and color of the circles.
 *          Interrogate and modify these properties
 *          using <em>getPointColor, setPointColor,
 *          getPointDiam</em> and <em>setPointDiam.</em>
 *      </li>
 *      <li>
 *          <strong>Color backgroundColor</strong><br>
 *          This property controls the background color
 *          of the window that contains the grid;
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
 *          You can get it and set it using
 *          the methods <em>getUnitLength</em> 
 *          and <em>setUnitLength</em>.    
 *      </li>
 * </ul>
 * <h2>Sample Code</h2>
 * <h3>CartesianPlaneDemo1</h3>
 * <p>
 * This program shows you how to instantiate 
 * and utilize <em>Plane</em> object.
 * It plots a straight line
 * using points with x-coordinates 
 * spaced at .5 units.
 * All defaults for the appearance of the GUI are accepted.
 * </p>
<pre>public static void main(String[] args)
{
    Plane   plane   = CPFrame.getPlane();
    for ( double coo = -2 ; coo <= 2 ; coo += .5 )
        plane.addPoint( coo, coo );
    plane.repaint();
}</pre>
 */
package com.judahstutorials.javaintro.cartesian_plane;

import java.awt.Color;
import java.awt.Font;
