package com.judahstutorials.javaintro.hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

public class WordSelector
{
    private static final String source  = "hangman/";
    private static final String easy    = "EasyWords.txt";
    private static final String hard    = "HardWords.txt";
    private static final String kids    = "KidsWords.txt";
    private static final String funny   = "FunnyWords.txt";
    private static final String phrases = "Phrases.txt";
    
    private static final String prompt  = "Select a Category";
    private static final String title   = "Word Selection";
    private static final int    msgType = JOptionPane.QUESTION_MESSAGE;
    private static final int    optType = 0;
    private static final String custom  = "Custom";
    private static final String cancel  = "Cancel";
    
    private final Category          easyList;
    private final Category          hardList;
    private final Category          kidsList;
    private final Category          funnyList;
    private final Category          phrasesList;
    
    private final List<Category>    categories          = new ArrayList<>();
    private Category                selectedCategory    = null;
    private String                  selection           = null;
    private char[]                  selectedWord        = null;
    private char[]                  assembledChars      = null;
    private String                  lastGuess           = null;
    
    public WordSelector()
    {
        easyList = new Category( "Easy Words", easy );
        hardList = new Category( "Hard Words", hard );
        kidsList = new Category( "Kids Words", kids );
        funnyList = new Category( "Funny Words", funny );
        phrasesList = new Category( "Phrases", phrases );
        categories.add( easyList );
        categories.add( hardList );
        categories.add( kidsList );
        categories.add( funnyList );
        categories.add( phrasesList );
        for ( Category cat : categories )
            cat.load();
    }
    
    public String select()
    {
        int         numCats     = categories.size();
        Object[]    options     = new Object[ numCats + 2];
        int         inx         = 0;
        for ( Category cat : categories )
            options[inx++] = cat;
        options[inx++] = custom;
        options[inx] = cancel;
        
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
        else if ( options[choice] == cancel )
            selection = null;
        else if ( options[choice] == custom )
            selection = selectCustom();
        else
        {
            Object  obj     = options[choice];
            if ( !(obj instanceof Category) )
                throw new Error( "Malfunction in Selector" );
            selectedCategory = (Category)obj;
            selection = selectedCategory.select();
        }
        
        if ( selection != null )
        {
            selectedWord = selection.toCharArray();
            assembledChars = new char[selectedWord.length];
            Arrays.fill( assembledChars, ' ' );
        }
        return selection;
    }
    
    public boolean processGuess()
    {
        String  guess   = getGuess();
        boolean rcode   = false;
        if ( guess != null )
            rcode = processGuess( guess );
        return rcode;
    }
    
    public boolean processGuess( String guess )
    {
        boolean rcode   = false;
        String  test    = guess.trim().toUpperCase();
        if ( guess.length() == 1 )
            rcode = processGuess( test.charAt( 0 ) );
        else
            rcode = selectedWord.equals( test );
        return rcode;
    }
    
    public boolean processGuess( char guess )
    {
        boolean rcode   = false;
        for ( int inx = 0 ; inx < selectedWord.length ; ++inx )
        {
            if ( guess == selectedWord[inx] )
            {
                rcode = true;
                assembledChars[inx] = guess;
            }
        }
        return rcode;
    }
    
    public boolean isWin()
    {
        boolean rcode   = Arrays.equals( selectedWord, assembledChars );
        return rcode;
    }
    
    public String getGuess()
    {
        String  prompt  =
            "Guess the hidden word, or enter a single character";
        String  input   = 
            JOptionPane.showInputDialog( null, prompt );
        if ( input.isEmpty() )
            input = null;
        if ( input != null )
            input = input.trim().toUpperCase();
        lastGuess = input;
        return input;
    }
    
    private String selectCustom()
    {
        String  selection   =
            JOptionPane.showInputDialog( null, "Enter your selection" );
        return selection;
    }
    
    private static class Category
    {
        private final String        name;
        private final String        path;
        private final List<String>  selections;
        
        public Category( String name, String path )
        {
            this.name = name;
            this.path = path;
            selections = new ArrayList<>();
        }
        
        public String select()
        {
            String  selection = null;
            if ( !selections.isEmpty() )
            {
                int max     = selections.size();
                int choice  = (int)(Math.random() * (max - 1) );
                selection = selections.get( choice );
            }
            
            return selection;
        }
        
        private void add( String selection )
        {
            String  upper   = selection.toUpperCase().trim();
            if ( !selections.contains( upper ) )
                selections.add( upper );
        }
        
        /**
         * @return the name
         */
        public String getName()
        {
            return name;
        }

        /**
         * @return the path
         */
        public String getPath()
        {
            return path;
        }

        /**
         * @return the selections
         */
        public List<String> getSelections()
        {
            return selections;
        }

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
                    bldr.append( getClass().getSimpleName() ).append( ".load" )
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
                    line = line.trim();
                    if ( !line.isEmpty() )
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
        
        @Override
        public String toString()
        {
            return name;
        }
    }
}
