package com.judahstutorials.javaintro.hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * This class is responsible for allowing the operator
 * to select the word or phrase
 * that the player will try to guess.
 * First the operator is prompted
 * to select a category;
 * if the category is <em>custom</em>
 * the manager is then prompted
 * to enter the selected word or phrase,
 * otherwise a file containing the possible words/phrases
 * for the given category is opened,
 * and a word/phrase is selected at random.
 * When selecting the category
 * the operator is offered the option
 * to cancel the operation.
 * 
 * @see #select()
 */
public class WordSelector
{
    // The subfolder within the resources folder that holds the files
    // containing the words/phrases for all the categories.
    private static final String source  = "hangman/";
    
    // The names of the files that contain the words/phrases
    // for a particular category.
    private static final String easy    = "EasyWords.txt";
    private static final String hard    = "HardWords.txt";
    private static final String kids    = "KidsWords.txt";
    private static final String funny   = "FunnyWords.txt";
    private static final String phrases = "Phrases.txt";
    
    // Options to use when assembling the dialog asking the operator
    // to select a category.
    private static final String prompt  = "Select a Category";
    private static final String title   = "Word Selection";
    private static final int    msgType = JOptionPane.QUESTION_MESSAGE;
    private static final int    optType = 0;
    
    // Categories that the operator may choose from.
    private final Category          easyList;
    private final Category          hardList;
    private final Category          kidsList;
    private final Category          funnyList;
    private final Category          phrasesList;
    private final Category          customSentinel;
    private final Category          cancelSentinel;
    
    // List of all categories.
    private final List<Category>    categories          = new ArrayList<>();
    // Category selected by the operator.
    private Category                selectedCategory    = null;
    // Word/phrase selected from the selected category.
    private String                  selection           = null;
    
    /**
     * Constructor. 
     * Initializes this object.
     * 
     * @see #select()
     */
    public WordSelector()
    {
        easyList = new Category( "Easy Words", easy );
        hardList = new Category( "Hard Words", hard );
        kidsList = new Category( "Kids Words", kids );
        funnyList = new Category( "Funny Words", funny );
        phrasesList = new Category( "Phrases", phrases );
        customSentinel = new Category( "Custom", null );
        cancelSentinel = new Category( "Cancel", null );
        categories.add( easyList );
        categories.add( hardList );
        categories.add( kidsList );
        categories.add( funnyList );
        categories.add( phrasesList );
        categories.add( customSentinel );
        categories.add( cancelSentinel );
    }
    
    /**
     * Asks the player to select a category
     * from which to select a random word or phrase,
     * then randomly selects an item
     * from the selected list.
     * The selected item is returned,
     * trimmed and converted to upper case.
     * If the player selects the <em>custom</em> category,
     * the player is then prompted 
     * to enter their own word or phrase.
     * If the player selects the <em>cancel</em> category
     * null is returned.
     * 
     * @return  
     *      the selected word or phrase,
     *      or null if canceled
     */
    public String select()
    {
        int         numCats     = categories.size();
        Object[]    options     = new Object[numCats];
        int         inx         = 0;
        for ( Category cat : categories )
            options[inx++] = cat;
        
        int choice  = JOptionPane.showOptionDialog(
            null, 
            prompt, 
            title, 
            optType, 
            msgType, 
            null, 
            options, 
            options[0]
        );
        
        if ( choice < 0 )
            selection = null;
        else if ( options[choice] == cancelSentinel )
            selection = null;
        else if ( options[choice] == customSentinel )
            selection = selectCustom();
        else
        {
            Object  obj     = options[choice];
            if ( !(obj instanceof Category) )
                throw new Malfunction( "Malfunction in Selector" );
            selectedCategory = (Category)obj;
            selectedCategory.load();
            selection = selectedCategory.select();
        }

        return selection;
    }
    
    /**
     * Ask the user to enter 
     * the custom word or phrase to guess.
     * Continue to ask
     * until the operator makes a non-empty guess
     * or cancels the operation.
     * Returns the operator's input,
     * trimmed and converted to upper case,
     * or null if cancelled.
     * 
     * @return  the user's guess or null if cancelled
     */
    private String selectCustom()
    {
        String  prompt  = "Enter your selection";
        String  input   = null;
        while ( input != null && input.isEmpty() )
            input = JOptionPane.showInputDialog( null, prompt );

        if ( input != null )
            input = input.trim().toUpperCase();
        
        return input;
    }
    
    /**
     * An instance of this class
     * encapsulates the name of a category,
     * and the path within the resources folder
     * to the file that contains the words/phrases
     * associated with that category.
     * No I/O is attempted
     * until the {@linkplain #load() } method is invoked.
     * 
     * @see #load()
     */
    private static class Category
    {
        /**
         * The name of the category.
         */
        private final String        name;
        /**
         * The path to the file,
         * within the resources folder,
         * to the file that contains
         * the selections associated with the category.
         */
        private final String        path;
        /**
         * Selections from the file
         * associated with the category.
         */
        private final List<String>  selections;
        
        /**
         * Constructor.
         * Initializes the class's instance variable.
         * 
         * @param name  the name of the category
         * @param path  the path to the associated file
         */
        public Category( String name, String path )
        {
            this.name = name;
            this.path = path;
            selections = new ArrayList<>();
        }
        
        /**
         * Randomly selects a a word or phrase
         * from the encapsulated category.
         * The programmer must call the 
         * {@linkplain #load()} method
         * prior to calling this method.
         * The selection is returned,
         * trimmed and converted to upper case.
         * 
         * @return the selection
         * 
         * @throws Malfunction if the encapsulated category is empty
         */
        public String select() throws Malfunction
        {
            if ( selections.isEmpty() )
            {
                final String    msg = "Category contains no selections";
                throw new Malfunction( msg );
            }
            
            String  selection = null;
            int max     = selections.size();
            int choice  = (int)(Math.random() * (max - 1) );
            selection = selections.get( choice );
            
            return selection;
        }
        
        /**
         * Convenience method to add a given selection
         * to the list of selections.
         * The selection is trimmed and converted to upper case
         * before being added to the list.
         * Empty and duplicate selections are ignored.
         * 
         * @param selection the given selection
         */
        private void add( String selection )
        {
            String  upper   = selection.toUpperCase().trim();
            if ( !selections.contains( upper ) )
                selections.add( upper );
        }

        /**
         * Load the list of words and/or phrases
         * from the encapsulated resource file.
         * If the operation fails a status is returned
         * (no exception is thrown).
         * 
         * @return  true 
         *      if the operation completes successfully, false otherwise
         *      
         * @see #add(String)  
         */
        public boolean load()
        {
            boolean status  = false;
            ClassLoader classLoader = Category.class.getClassLoader();
            String      resPath     = source + path;
            try ( InputStream inStream    = 
                      classLoader.getResourceAsStream( resPath );
            )
            {
                if ( inStream == null )
                {
                    StringBuilder   bldr    = new StringBuilder();
                    bldr.append( getClass().getSimpleName() )
                        .append( ".load" )
                        .append( " failed to load " ).append( resPath );
                    throw new IOException( bldr.toString() );
                }
                InputStreamReader   inReader    = 
                    new InputStreamReader( inStream );
                BufferedReader      bufReader   = 
                    new BufferedReader( inReader );
                String  line    = null;
                while ((line = bufReader.readLine()) != null )
                {
                    add( line );
                }
                status = true;
            }
            catch ( IOException exc )
            {
                exc.printStackTrace();
            }
            return status;
        }
        
        /**
         * Returns the name of the encapsulated category.
         */
        @Override
        public String toString()
        {
            return name;
        }
    }
}
