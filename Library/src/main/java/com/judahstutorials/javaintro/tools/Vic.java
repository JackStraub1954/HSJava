package com.judahstutorials.javaintro.tools;

import java.awt.Color;
import java.awt.Graphics;

// <pre>
// Copy this file in its entirety to a file named Vic.java
// Compile it before trying to compile any program that uses it
// This implementation uses ArrayLists for the sequences of CDs
// It also uses an initializer method to create the tableau before any Vics
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This is a "CD changer simulator."
 * It's purpose is to introduce students
 * to boolean expressions and conditionals in Java.
 * An instance of this class
 * encapsulates a row of slots
 * which may or may not be occupied by a CD.
 * In addition,
 * a row has an "end position"
 * which may not contain a CD.
 * At any time,
 * an instance has a "current position,"
 * which may be anywhere on the encapsulated row,
 * including the end position.
 * The class includes a stack,
 * shared by all instances,
 * where CDs are stored
 * when not allocated a slot.
 */
public class Vic
{
	/**
	 * Collection of CDs that are not allocated slots.
	 * @see #initCDs
	 */
	private static Stack<String> theStack  = new Stack<>();
	/**
	 * Sequence of slots assigned to an instance.
	 */
	private SlotsList itsSequence;  // its slots
	/**
	 * The instance's current position.
	 */
	private int itsPos;
	/**
	 * The ID  of this instance, assigned sequentially beginning at one.
	 */
	private int itsID;

	/**
	 * Source of CDs for initial population of stack;
	 * a possibly empty subset of these CDs
	 * is assigned to the stack when the first Vic is created.
	 * #see {@link #theStack}
	 */
	static private String[]	initCDs	=
		new String[]{ "LyleL", "GarthB", "Calexico", "MethenyP",
		              "FloydP", "CoreaC", "DiMeolaA", "ClarkeS"
					};
	/**
	 * Number of cds to initialize the stack; -1 means
	 * initialize stack randomly.
	 */
	static private int	numInitCDs	= -1;

	/** 
	 * Return the current position as a String value.
	 * The string consists of two comma-separated integers
	 * where the first integer is the ID of the Vic instance
	 * (beginning at 1)
	 * and the second integer is the sequence number
	 * of its current position
	 * (beginning at 0).
	 * 
	 * @return the current position of this Vic
	 */
	public String getPosition()
	{	return itsID + ", " + itsPos;
	}	//======================


	/** 
	 * Returns true if there is a slot
	 * at this Vic's current position.
	 * 
	 * @return true there is a slot at this Vic's current position
	 */

	public boolean seesSlot()
	{	return itsPos < itsSequence.size();
	}	//======================


	/** 
	 * Tell whether there is a CD in this Vic's current slot. 
	 * If there is no slot at the current position
	 * the program is abruptly terminated.
	 * 
	 * @return true if there is a CD in the current slot
	 */
	public boolean seesCD()
	{	if (! seesSlot())
			fail ("Can't see a CD where there is no slot!");
		return itsSequence.get (itsPos) != null;
	}	//======================


	/**
	 * Return the name of the CD
	 * that is in this Vic's current slot,
	 * or null if the slot is empty.
     * If there is no slot at the current position
     * the program is abruptly terminated.
     * 
     * @return the name of the CD in the current slot, or null if none
	 */
	public String getCD()
	{	if (! seesSlot())
			fail ("There is no slot to get a CD from!");
		return itsSequence.get (itsPos);
	}	//======================


	/**
	 * Return true if the stack is non-empty.
	 * 
	 * @return  true if the stack is non-empty
	 */
	public static boolean stackHasCD()
	{	return ! theStack.isEmpty();
	}	//======================

	/** 
	 * Move forward to the next position in the sequence.
	 * If there is no next position
	 * the program is abruptly terminated.
	 */
	public void moveOn()
	{	if (! seesSlot())
			fail ("Already at the end of the sequence!");
		itsPos++;
		trace ("moveOn to slot " + (itsPos + 1));
	}	//======================

    /** 
     * Move backward to the previous position in the sequence.
     * If there is no previous position
     * the program is abruptly terminated.
     */
	public void backUp()
	{	if (itsPos == 0)
			fail ("Already at the front of the sequence!");
		itsPos--;
		trace ("backUp to slot " + (itsPos + 1));
	}	//======================

	/**
	 * Move a CD from the stack to the current slot.
	 * If the stack is empty,
	 * or the slot is already occupied,
	 * no action is taken.
     * If there is no slot at the current position
     * the program is abruptly terminated.
     */
	public void putCD()
	{	if (! seesCD() && stackHasCD())
			itsSequence.set (itsPos, theStack.pop());
		trace ("putCD at slot " + (itsPos + 1));
	}	//======================



    /**
     * Move a CD from the current slot to the stack.
     * If the slot is not occupied,
     * no action is taken.
     * If there is no slot at the current position
     * the program is abruptly terminated.
     */
	public void takeCD()
	{	if (seesCD())
		{	theStack.push (itsSequence.get (itsPos));
			itsSequence.set (itsPos, null);
		}
		trace ("takeCD at slot " + (itsPos + 1));
	}	//======================


	/** 
	 * Terminate the program with the given message.
	 * @param  cause  the given message
	 */
	private void fail (String cause)
	{	JOptionPane.showMessageDialog (null, "STOPPING: " + cause
			      + "  (Vic #)" + itsID + ", position =" + itsPos);
		System.exit (0);
	}	//======================

	/**
	 * Initial string to display in message area.
	 */
	private static String vicSay = "Programmable CD Organizer "
	                             + "        mfd by Jones & Co.";
	/**
	 * The frame that contains the main application window.
	 */
	private static final VicFrame theFrame = new VicFrame();
	//////////////////////////////////

	/**
	 * Display a message in the application's message area.
	 * 
	 * @param message  the message to display
	 */
	public static void say (String message)
	{	vicSay = message;
		theFrame.repaint();
	}	//======================


	/**
	 * Print a trace of the Vic's action.
	 * @param message  message to accompany the trace
	 */
	private void trace (String message)
	{	System.out.println (message + " for Vic #" + itsID);
		theFrame.repaint();
		pause (500);  // half-a-second between actions
	}	//======================


	/**
	 * Pause for the given number of milliseconds.
	 * 
	 * @param  milliseconds    the given number of milliseconds
	 */

	private static void pause (int milliseconds)
	{	try
		{	Thread.sleep (milliseconds);
		}
		catch (InterruptedException e)
		{	// never happens
		}
	}	//======================

	/**
	 * Maximum number of slots that can be assigned to an instance.
	 */
	private static final int MAXSLOTS = 8;
    /**
     * Minimum number of slots that can be assigned to an instance.
     */
	private static final int MINSLOTS = 3;
	/**
	 * Maximum number of Vics that can be instantiated.
	 */
	private static final int MAXVICS  = 4;
	/**
	 * Random number generator for populating the application window
	 * when no configuration is specified.
	 * @see #reset(String[])
	 */
	private static final Random random = new Random();
	/**
	 * The number of Vics that may be instantiated
	 * for this application configuration.
	 */
	private static int theMaxVics = random.nextInt (MAXVICS) + 1;
	/**
	 * The physical rows of slots assigned to this application.
	 */
	private static SlotsList[] theSeq = new SlotsList[theMaxVics];
	/**
	 * The number of Vics currently instantiated.
	 */
	private static int theNumVics = 0;
	/**
	 * Collection of Vics assigned to this application.
	 */
	private static final Vic[] theVics = {null, null, null, null};
	//////////////////////////////////

	static
	{	for (int k = 0;  k < theMaxVics;  k++)
		{	theSeq[k] = new SlotsList();
			int numSlots = random.nextInt (MAXSLOTS - MINSLOTS + 1)
					                   + MINSLOTS;
			for (int i = 0;  i < numSlots;  i++)
			{	String it = random.nextInt (2) == 0  ?  null 
				            : "" + (char) (i + 'a') + (k + 1);
				theSeq[k].add (it);
			}
		}

		// start with up to 2 CDs on the stack
		if (random.nextInt (3) > 0)  // 2 times out of 3
		{  theStack.push ("GarthB");
			if (random.nextInt (2) == 0) // 1 time out of 3
				theStack.push ("LyleL");
		}
	}	//======================


	/** Construct a new Vic. */
	public Vic()
	{	super();
		itsSequence =  theNumVics < theMaxVics
				?   theSeq[theNumVics]   :  new SlotsList();
		itsPos = 0;
		itsID = theNumVics + 1;
		theVics[theNumVics] = this;
		theNumVics++;
		trace ("construction");
	}	//======================


	/** 
	 * Replace random initialization of rows and stack
	 *  with user-specified arrangement.
		<p>
		Parameter <em>args</em> is an array of up to five strings:
        <ul>
        <li>
		A string beginning with a pound sign (#) must be followed by
		a valid integer between 0 and 8, inclusive, and indicates 
		the number of CDs initially placed on the stack. A number
		greater than 8 will be forced to 8; a number less than 0, 
		or an invalid integer will be ignored.
		<li>
		There may be up to four additional strings consisting of
		0 to 8 ones and zeros. The number of strings indicates the
		number of rows to be created; if there are more than four
		strings the extraneous strings will be ignored. Within each
		of the strings there may be 0 to eight ones and zeros. The 
		number of characters indicates the number of slots to create.
		A zero indicates that the slot will be empty; a one indicates
		that the slot will be occupied. If a string consists of more
		than 8 characters, the extraneous characters will be ignored;
		a character other than zero or one will be treated as a one.
		</ul>
		<p>
		Examples:
		<blockquote>
			Create one row of four empty slots:
			<pre>    "0000"</pre>
			Create one row of four empty slots,
			and one row of seven slots, alternating between
			filled and empty:
			<pre>    "0000" "0101010"</pre>
			Create two rows of three filled slots each, and
			a stack with an initial size of four:
			<pre>    "111" "111" "#4"</pre>
		</blockquote>
		
		@param args 
		    array of strings that specify the
		    configuration of rows and stack
		       
		@see  #reset1(String[])
	*/
	public static void reset( String[] args )
	{
		Vector<String>	vec	= new Vector<String>();
		for ( int inx = 0 ; inx < args.length ; ++inx )
		{
			if ( args[inx].length() < 2 || args[inx].charAt( 0 ) != '#' )
				vec.add( args[inx] );
			else
			{
				try
				{
					int	num	= Integer.parseInt( args[inx].substring( 1 ) );
					numInitCDs = num;
				}
				catch ( NumberFormatException exc )
				{
					// if number after # is invalid, just toss the
					// string into the vector and let life go on as
					// usual.
					vec.add( args[inx] );
				}
			}
		}
		
		String[]	newArgs = new String[vec.size()];
		vec.toArray( newArgs );
		reset1( newArgs );
	}

	/**
	 * Complete the initialization of the application.
	 * 
	 * @param args
     *      array of strings that specify the
     *      configuration of rows
	 */
	private static void reset1 (String[] args)
	{	if (args.length > 0 && theNumVics == 0)
		{	theMaxVics = Math.min (args.length, MAXVICS);
			theSeq = new SlotsList[theMaxVics];
			for (int k = 0; k < theMaxVics;  k++)
			{	theSeq[k] = new SlotsList();
				int longest = Math.min (args[k].length(), MAXSLOTS);
				for (int i = 0;  i < longest;  i++)
				{	String it = args[k].charAt (i) == '0' ? null 
					          : "" + (char)(i + 'a') + (k + 1);
					theSeq[k].add (it);
				}
			}
		}
		
		theStack = new Stack<String>();
		int	num	= numInitCDs > -1 ? numInitCDs: random.nextInt( 3 );
		if ( num > initCDs.length )
			num = initCDs.length;
		for ( int inx = 0 ; inx < num ; ++inx )
			theStack.push( initCDs[inx] );

	}	//======================

	/**
	 * JFrame comprising the main application window.
	 */
	static class VicFrame extends JFrame
	{
        /**
         * Serial version UID.
         */
        private static final long serialVersionUID = 0x10L;

        /**
         * Pixels between slots.
         */
        private final int SLOT = 75;
		/**
		 * Margin.
		 */
		private final int EDGE = 10;           // leave free at left side
		/**
		 * Window width.
		 */
		private final int WIDTH = (MAXSLOTS + 2) * SLOT + 2 * EDGE;
		/**
		 * Distance between rows.
		 */
		private final int DIST = 60;
		/**
		 * Y-coordinate of message area.
		 */
		private final int SAY = 45;
		/**
		 * Y-coordinated of first row of slots.
		 */
		private final int TOPSEQ = SAY + DIST; // depth of first seq

		/**
		 * Constructor.
		 * Initializes te main application window
		 * and makes it visible.
		 */
		public VicFrame()
		{
		    setDefaultCloseOperation( EXIT_ON_CLOSE );
			setSize (WIDTH, TOPSEQ + MAXVICS * DIST + 2 * EDGE);
			setBackground (new Color (255, 252, 224)); // a nice cream
	 		setVisible (true);    // make it visible to user
		}	//======================

		@Override
		public void paint( Graphics page )  
		{	// PRINT THE vicSay MESSAGE AT THE TOP
			page.setColor (getBackground());
			page.fillRect (EDGE, EDGE, WIDTH - 2 * EDGE, 
						       TOPSEQ + MAXVICS * DIST);
			page.setColor (Color.white);
			page.fillRect (20, SAY - 20, WIDTH - 40, 20);
			page.setColor (new Color (0, 96, 0));  // a light green
			page.drawString (vicSay, 25, SAY - 5); // message

			// DRAW UP TO FOUR Vic SEQUENCES AND THE STACK
			for (int k = 0;  k < theMaxVics;  k++)
				drawSequence (page, k, TOPSEQ + k * DIST);
			page.setColor (Color.red);
			int y = TOPSEQ + MAXVICS * DIST;
			page.drawString ("stack", EDGE, y);
			page.fillRect (EDGE, y - 25, 40, 5);  // dividing line
			for (int k = 0;  k < theStack.size();  k++)
				page.drawString (theStack.get (k),
					            EDGE, y - 30 - k * 20);
		}	//======================
     
		/**
		 * Draw a row.
		 * 
		 * @param page    graphics context
		 * @param index   index to controlling Vic
		 * @param y       y-coordinate of the row
		 */
		private void drawSequence (Graphics page, int index, int y)
		{	page.setColor (Color.red);
			if (theVics[index] != null)
				drawMacMan (page, theVics[index].itsPos, y - 15);
			page.setColor (Color.blue);
			drawAllCDs (page, y, theSeq[index]); 
		}	//======================


		/**
		 * Draw the CD configuration for a row of slots.
		 * 
		 * @param page    graphics context
		 * @param y       y-coordinate of row
		 * @param slots   list of slots in this row
		 */
		private void drawAllCDs (Graphics page, int y,
	      	                   SlotsList slots)
		{	int atEnd = slots.size();
			for (int n = 0;  n < atEnd;  n++)
			{	String it = slots.get (n);
				page.drawString (it == null ? "---" : it, 
						           (n + 1) * SLOT + EDGE, y);
			}
			page.drawString ("END", (atEnd + 1) * SLOT + EDGE, y);
		}	//======================


		/**
		 * Draw the stick figure at the current position.
		 * 
		 * @param page    the graphics context
		 * @param pos     the x-coordinate of the lower-left corner
		 *                of the stick figure.
		 * @param y       the y-coordinate of the lower-left corner
         *                of the stick figure.
		 */
		private void drawMacMan (Graphics page, int pos, int y)
		{	// <x, y> is the lower-left corner of the stick figure
			int x = pos * SLOT + EDGE + 78;
			page.setColor (Color.black);
			page.drawLine (x,     y,      x + 6,  y - 6);  // leg
			page.drawLine (x + 6, y - 6,  x + 12, y);      // leg
			page.drawLine (x + 6, y - 6,  x + 6,  y - 18); // body
			page.drawLine (x,     y - 14, x + 12, y - 14); // arms
			page.drawOval (x + 1, y - 28, 10,     10);     // head
			page.drawLine (x + 4, y - 25, x + 5,  y - 25); // eye
			page.drawLine (x + 7, y - 25, x + 8,  y - 25); // eye
			page.drawLine (x + 3, y - 22, x + 9,  y - 22); // mouth
		}	//======================
	} // end of VicFrame class

    /**
     * Encapsulation of the list of slots assigned to a row.
     */
    private static class SlotsList
    {
        /**
         * The list of slots assigned to a row.
         */
        ArrayList<String>   slots   = new ArrayList<String>();

        /**
         * Default constructor; not used.
         */
        public SlotsList()
        {
            // not used
        }
        
        /**
         * Add a slot to this list.
         * 
         * @param str   the ID of the slot
         */
        public void add( String str )
        {
            slots.add( str );
        }

        /**
         * Get the ID of the slot at the given index.
         * 
         * @param inx   the given index
         * @return  the ID of the slot at the given index
         */
        public String get( int inx )
        {
            return slots.get( inx );
        }

        /**
         * Get the size of this list.
         * 
         * @return  the size of this list
         */
        public int size()
        {
            return slots.size();
        }

        /**
         * Set the ID of the slot at the given index.
         * 
         * @param inx   the given index
         * @param str   the ID of the slot
         */
        public void set( int inx, String str )
        {
            slots.set( inx, str );
        }
    }
}     

// </pre>
