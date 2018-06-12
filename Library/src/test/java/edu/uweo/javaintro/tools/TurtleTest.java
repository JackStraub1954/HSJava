package edu.uweo.javaintro.tools;

import static org.junit.Assert.*;

import org.junit.Test;

import util.Util;

public class TurtleTest
{

	@Test
	public void test()
	{
		Turtle toots = new Turtle();
		toots.switchTo( Turtle.RED );
		toots.fillCircle( 64 );
		Util.pause( 2000 );
	}

}
