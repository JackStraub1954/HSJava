package edu.uweo.javaintro.tools;

import org.junit.jupiter.api.Test;

public class TurtleTest {

	@Test
	public void test()
	{
		Turtle    tutt    = new Turtle();
		tutt.fillCircle( 64 );
		try
		{
		    Thread.sleep( 2000 );
		}
		catch ( InterruptedException exc )
		{
		}
	}

}
