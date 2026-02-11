package com.judahstutorials.javaintro.tools;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *  This class encapsulates a JComboBox&lt;Color>. It uses
 *  a custom ComboBoxModel to manage the contents of the
 *  JComboBox, displaying each item in the color it
 *  represents.
 */
@SuppressWarnings("serial")
public class ColorComboBox
    extends JComboBox<Color>
{
    /**
     *  Constructs an empty ColorComboBox.
     */
    public ColorComboBox()
    {
        initialize();
    }

    /**
     *  Constructs a ColorComboBox with contents initialized
     *  from an array.
     *
     *  @param  array   The array with which to initialize
     *                  the contents of the ColorComboBox.
     */
    public ColorComboBox( Color[] array )
    {
        super( array );
        initialize();
    }

    /**
     *  Constructs a ColorComboBox with contents initialized
     *  from an ArrayList.
     *
     *  @param  list    The list with which to initialize
     *                  the contents of the ColorComboBox.
     */
    public ColorComboBox( ArrayList<Color> list )
    {
        initialize();
		update( list );
    }

    /**
     *  Constructs a ColorComboBox with contents initialized
     *  from a ComboBoxModel.
     *
     *  @param  model   The ComboBoxModel with which to initialize
     *                  the contents of the ColorComboBox.
     */
    public ColorComboBox( ComboBoxModel<Color> model )
    {
        super( model );
        initialize();
    }

    /**
     *  Updates a ColorComboBox with contents of an ArrayList.
     *
     *  @param  list    The list from which to update
     *                  the contents of the ColorComboBox.
     */
	public void update( ArrayList<Color> list )
	{
		Color[]						array	= list.toArray( new Color[0] );
		DefaultComboBoxModel<Color>	model	=
			new DefaultComboBoxModel<Color>( array );
		setModel( model );
	}

    /**
     * Initializes an object of this class.
     */
    private void initialize()
    {
        setRenderer( new ColorCellRenderer() );
        setEditor( new CBoxEditor() );
        setEditable( true );
    }

    /**
     * Class to use as a cell renderer in a ColorComboBox.
     */
    private class ColorCellRenderer
        implements ListCellRenderer<Color>
    {
        /**
         * Default constructor; not used.
         */
        private ColorCellRenderer()
        {
            // not used
        }
        
        /**
         * Default size of the renderer.
         */
        private Dimension preferredSize_ = new Dimension( 200, 20 );
        /**
         * Base of the renderer.
         */
        private DefaultListCellRenderer defaultRenderer_ =
            new DefaultListCellRenderer();

        @Override
        public Component
        getListCellRendererComponent( JList<? extends Color>  list,
                                      Color  value,
                                      int    index,
                                      boolean isSelected,
                                      boolean cellHasFocus
                                    )
        {
            JLabel  renderer    =
                (JLabel)defaultRenderer_.
                getListCellRendererComponent( list,
                                              value,
                                              index,
                                              isSelected,
                                              cellHasFocus
                                            );
            renderer.setBackground( value );
			renderer.setText( "" );
            renderer.setPreferredSize( preferredSize_ );

            return renderer;
        }
    }

    /**
     * The editor used by the ColorComboBox.
     */
    private class CBoxEditor
        implements ComboBoxEditor
    {
        /**
         * Default constructor; not used.
         */
        private CBoxEditor()
        {
            // not used
        }
        
        /**
         * The edit button.
         */
        private JButton editor_ = new JButton();
        /**
         * The selected item.
         */
        private Object  item_   = null;

        @Override
        public void addActionListener( ActionListener listener )
        {
        }

        @Override
        public Component getEditorComponent()
        {
            return editor_;
        }

        @Override
        public Object getItem()
        {
            return editor_.getBackground();
        }

        @Override
        public void removeActionListener( ActionListener listener )
        {
        }

        @Override
        public void selectAll()
        {
        }

        @Override
        public void setItem( Object item )
        {
            item_ = item;
            if ( item_ instanceof Color )
                editor_.setBackground( (Color)item );
        }
    }
}
