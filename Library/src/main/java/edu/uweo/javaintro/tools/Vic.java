package edu.uweo.javaintro.tools;

// <pre>
// Copy this file in its entirety to a file named Vic.java
// Compile it before trying to compile any program that uses it
// This implementation uses ArrayLists for the sequences of CDs
// It also uses an initializer method to create the tableau before any Vics


import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Vic
{
	private static Stack<String> theStack =
        new Stack<>();  // where spare CDs are kept
	private SlotsList itsSequence;  // its slots
	private int itsPos;             // 0,1,2,...; 0 is at front
	private int itsID;              // 1 for first Vic, 2 for...
	
	// for initializing the stack with up to eight CDs.
	static private String[]	initCDs	=
		new String[]{ "LyleL", "GarthB", "Calexico", "MethenyP",
		              "FloydP", "CoreaC", "DiMeolaA", "ClarkeS"
					};
	// Number of cds to initialize the stack; -1 means
	// initialize stack randomly.
	static private int	numInitCDs	= -1;


/* QUERY METHODS */

	/** Return the current position as a String value.  */

	public String getPosition()
	{	return itsID + ", " + itsPos;
	}	//======================


	/** Tell whether there is a slot at its position.  */

	public boolean seesSlot()
	{	return itsPos < itsSequence.size();
	}	//======================


	/** Tell whether there is a CD in its current slot.  */

	public boolean seesCD()
	{	if (! seesSlot())
			fail ("Can't see a CD where there is no slot!");
		return itsSequence.get (itsPos) != null;
	}	//======================


	/** Return the CD that is in its current slot.  */

	public String getCD()
	{	if (! seesSlot())
			fail ("There is no slot to get a CD from!");
		return itsSequence.get (itsPos);
	}	//======================


	/** Tell whether the stack has any CDs available. */

	public static boolean stackHasCD()
	{	return ! theStack.isEmpty();
	}	//======================


/* ACTION METHODS */

	/** Move forward to the next slot in the sequence. */

	public void moveOn()
	{	if (! seesSlot())
			fail ("Already at the end of the sequence!");
		itsPos++;
		trace ("moveOn to slot " + (itsPos + 1));
	}	//======================


	/** Back up to the previous slot in the sequence. */

	public void backUp()
	{	if (itsPos == 0)
			fail ("Already at the front of the sequence!");
		itsPos--;
		trace ("backUp to slot " + (itsPos + 1));
	}	//======================


	/** Move a CD from the stack to the current slot.  */

	public void putCD()
	{	if (! seesCD() && stackHasCD())
			itsSequence.set (itsPos, theStack.pop());
		trace ("putCD at slot " + (itsPos + 1));
	}	//======================


	/** Move a CD from the current slot to the stack.  */

	public void takeCD()
	{	if (seesCD())
		{	theStack.push (itsSequence.get (itsPos));
			itsSequence.set (itsPos, null);
		}
		trace ("takeCD at slot " + (itsPos + 1));
	}	//======================


	/** Terminate the program with an appropriate message.  */

	private void fail (String cause)
	{	JOptionPane.showMessageDialog (null, "STOPPING: " + cause
			      + "  (Vic #)" + itsID + ", position =" + itsPos);
		System.exit (0);
	}	//======================


	/** Two convenience methods */

	public void shiftFromSlotToStack()
	{	takeCD();
	}	//======================

	public void shiftFromStackToSlot()
	{	putCD();
	}	//======================


/* METHODS THAT USE THE FRAME */

	private static String vicSay = "Programmable CD Organizer "
	                             + "        mfd by Jones & Co.";
	private static final VicFrame theFrame = new VicFrame();
	//////////////////////////////////


	public static void say (String message)
	{	vicSay = message;
		theFrame.repaint();
	}	//======================


	/** Print a trace of the Vic's action.  */

	private void trace (String message)
	{	System.out.println (message + " for Vic #" + itsID);
		theFrame.repaint();
		pause (500);  // half-a-second between actions
	}	//======================


	/** Pause for the specified number of milliseconds.  */

	private static void pause (int milliseconds)
	{	try
		{	Thread.sleep (milliseconds);
		}
		catch (InterruptedException e)
		{	// never happens
		}
	}	//======================


/* THE INITIALIZER AND CONSTRUCTOR METHODS */

	private static final int MAXSLOTS = 8;
	private static final int MINSLOTS = 3;
	private static final int MAXVICS  = 4;
	private static final Random random = new Random();
	private static int theMaxVics = random.nextInt (MAXVICS) + 1;
	private static SlotsList[] theSeq = new SlotsList[theMaxVics];
	private static int theNumVics = 0;
	private static final Vic[] theVics = {null, null, null, null};
	//////////////////////////////////


	/** Initialize individual sequences and stacks.  An initializer method
	    is used because these have to exist before any Vics are created. */

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


	/** Replace random initialization of rows and stack with user-specified arrangement.
		<p>
		Parameter <tt>args</tt> is an array of up to five strings:
			
		A string beginning with a pound sign (#) must be followed by
		a valid integer between 0 and 8, inclusive, and indicates 
		the number of CDs initially placed on the stack. A number
		greater than 8 will be forced to 8; a number less than 0, 
		or an invalid integer will be ignored.
		<p>
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

		@author Jack Straub
		
		@param args An array of strings that specify the
		       configuration of rows and stack.
  
	
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
					// it used to.
					vec.add( args[inx] );
				}
			}
		}
		
		String[]	newArgs = new String[vec.size()];
		vec.toArray( newArgs );
		reset1( newArgs );
	}

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


// THE NESTED FRAME CLASS

	static class VicFrame extends JFrame
	{
        private static final long serialVersionUID = 0x10L;

        private final int SLOT = 75;           // between CD slots
		private final int EDGE = 10;           // leave free at left side
		private final int WIDTH = (MAXSLOTS + 2) * SLOT + 2 * EDGE;
		private final int DIST = 60;           // between CD sequences
		private final int SAY = 45;            // depth of say's output
		private final int TOPSEQ = SAY + DIST; // depth of first seq

		public VicFrame()
		{	addWindowListener (new Closer());
			setSize (WIDTH, TOPSEQ + MAXVICS * DIST + 2 * EDGE);
			setBackground (new Color (255, 252, 224)); // a nice cream
	 		setVisible (true);    // make it visible to user
		}	//======================


		/** Same as for an applet; called by repaint. */

		public void paint (Graphics page)  
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
     

		/** Called by VicFrame's paint method. */

		private void drawSequence (Graphics page, int index, int y)
		{	page.setColor (Color.red);
			if (theVics[index] != null)
				drawMacMan (page, theVics[index].itsPos, y - 15);
			page.setColor (Color.blue);
			drawAllCDs (page, y, theSeq[index]); 
		}	//======================


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

    private static class SlotsList
    {
        ArrayList<String>   slots   = new ArrayList<String>();

        public void add( String str )
        {
            slots.add( str );
        }

        public String get( int inx )
        {
            return slots.get( inx );
        }

        public int size()
        {
            return slots.size();
        }

        public void set( int inx, String str )
        {
            slots.set( inx, str );
        }
    }

	private static class Closer extends java.awt.event.WindowAdapter 
	{	
		public void windowClosing (java.awt.event.WindowEvent e) 
		{	System.exit (0);
		}	//======================
	}     
}     

// </pre>
