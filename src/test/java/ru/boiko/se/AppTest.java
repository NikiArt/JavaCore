package ru.boiko.se;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void newArr()
    {
        char[][] arr = new char[5][3];
        System.out.println(arr.length + " " + arr[0].length);
    }
}
