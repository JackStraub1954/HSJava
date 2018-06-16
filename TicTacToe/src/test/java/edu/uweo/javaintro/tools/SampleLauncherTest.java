package edu.uweo.javaintro.tools;

import org.junit.Test;

import edu.uweo.javaintro.tictactoe.SampleLauncher;

public class SampleLauncherTest
{
    @Test
    public void test()
    {
        SampleLauncher.main( null );
        try
        {
            Thread.sleep( 2000 );
        }
        catch ( InterruptedException exc )
        {
        }
    }
}
