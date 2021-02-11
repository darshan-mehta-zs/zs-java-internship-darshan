package com.zs.hobbytracker.models;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.Time;

import static org.junit.Assert.assertEquals;

/**
 * Test for Badminton Model
 */
public class BadmintonTest {


    /**
     * Model class object for Badminton hobby
     */
    Badminton badminton;

    /**
     * Model class object for Badminton hobby
     */
    private Badminton hobby1;

    /**
     * Model class object for Badminton hobby
     */
    private Badminton hobby2;

    /**
     * Initialising the model
     */
    @Before
    public void setup() {
        badminton = new Badminton();
        hobby1 = new Badminton(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-03"), "badminton", 4, "win");
        hobby2 = new Badminton(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-04"), "badminton", 4, "win");
    }

    /**
     * Test method for getting number of players
     */
    @Test
    public void getNumberOfPlayers() {
        Badminton badminton = new Badminton();
        badminton.setNumberOfPlayers(3);
        assertEquals(3, badminton.getNumberOfPlayers());
    }

    /**
     * Test method for getting result
     */
    @Test
    public void getResult() {
        Badminton badminton = new Badminton();
        badminton.setResult("win");
        assertEquals("win", badminton.getResult());
    }

    /**
     * Test for parameterisedConstructor
     */
    @Test
    public void parameterisedConstructor() {
        hobby1 = new Badminton(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-03"), "badminton", 4, "win");
    }

    /**
     * Test for equals method
     */
    @Test
    public void equalsTest() {
        hobby1 = new Badminton(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-03"), "badminton", 4, "win");
        hobby2 = new Badminton(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-03"), "badminton", 4, "win");
        assertEquals(true, hobby1.equals(hobby2));
    }

    /**
     * Test for toString() method
     */
    @Test
    public void toStringTest() {
        hobby1 = new Badminton(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-03"), "badminton", 4, "win");
        hobby2 = new Badminton(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-03"), "badminton", 4, "win");
        assertEquals(hobby1.toString(), hobby2.toString());
    }

    /**
     * Test for hash code method
     */
    @Test
    public void hashCodeTest() {
        hobby1 = new Badminton(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-03"), "badminton", 4, "win");
        hobby2 = new Badminton(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-03"), "badminton", 4, "win");
        assertEquals(hobby1.hashCode(), hobby2.hashCode());
    }

}
