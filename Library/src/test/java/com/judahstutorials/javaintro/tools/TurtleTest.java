package com.judahstutorials.javaintro.tools;

import org.junit.jupiter.api.Test;

import com.judahstutorials.javaintro.tools.Turtle;

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
