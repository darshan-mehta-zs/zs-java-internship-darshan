package com.zs.hobbytracker.models;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.Time;

import static org.junit.Assert.assertEquals;

/**
 * Test for chess model class
 */
public class ChessTest {

    /**
     * Chess Model object
     */
    Chess chess;

    /**
     * Chess Model Object
     */
    private Chess hobby1;

    /**
     * Chess Model Object
     */
    private Chess hobby2;

    /**
     * Setup
     */
    @Before
    public void setup() {
        chess = new Chess();
        hobby1 = new Chess(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-03"), "chess", 4, "win");
        hobby2 = new Chess(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-04"), "chess", 4, "win");
    }

    /**
     * Test method for getting number of players
     */
    @Test
    public void getNumberOfPlayers() {
        Chess chess = new Chess();
        chess.setNumberOfMoves(3);
        assertEquals(3, chess.getNumberOfMoves());
    }

    /**
     * Test method for getting result
     */
    @Test
    public void getResult() {
        Chess chess = new Chess();
        chess.setResult("win");
        assertEquals("win", chess.getResult());
    }

    /**
     * test for parameterised constructor and getting its field
     */
    @Test
    public void parameterisedConstructor() {
        hobby1 = new Chess(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-03"), "chess", 4, "win");
        assertEquals(1, hobby1.getUserId());
        assertEquals(1, hobby1.getHobbyId());
        assertEquals(true, hobby1.isTaskCompleted());
        assertEquals("10:10:10", hobby1.getStartTime().toString());
        assertEquals("11:11:11", hobby1.getEndTime().toString());
        assertEquals("2021-01-03", hobby1.getDateLastPlayed().toString());
        assertEquals("chess", hobby1.getHobbyName());
        assertEquals(4, hobby1.getNumberOfMoves());
        assertEquals("win", hobby1.getResult());

    }

    /**
     * Test for equals method which checks whether two objects are equal or not
     */
    @Test
    public void equalsTest() {
        hobby1 = new Chess(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-03"), "chess", 4, "win");
        hobby2 = new Chess(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-03"), "chess", 4, "win");
        assertEquals(true, hobby1.equals(hobby2));
    }

    /**
     * Test for toString method which just returns string representation of object
     */
    @Test
    public void toStringTest() {
        hobby1 = new Chess(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-03"), "chess", 4, "win");
        hobby2 = new Chess(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-03"), "chess", 4, "win");
        assertEquals(hobby1.toString(), hobby2.toString());
    }

    /**
     * Test for hashCode method which returns the hash code of the object
     */
    @Test
    public void hashCodeTest() {
        hobby1 = new Chess(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-03"), "chess", 4, "win");
        hobby2 = new Chess(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-03"), "chess", 4, "win");
        assertEquals(hobby1.hashCode(), hobby2.hashCode());
    }

    /**
     *
     */
    @Test
    public void settersOfParent() {

        hobby1.setUserId(1);
        hobby1.setHobbyId(1);
        hobby1.setHobbyName("chess");
        hobby1.setStartTime(Time.valueOf("10:10:10"));
        hobby1.setEndTime(Time.valueOf("11:11:11"));
        hobby1.setResult("win");
        hobby1.setNumberOfMoves(4);
        hobby1.setDateLastPlayed(Date.valueOf("2021-01-03"));
        hobby1.setTaskCompleted(true);

        assertEquals(1, hobby1.getUserId());
        assertEquals(1, hobby1.getHobbyId());
        assertEquals(true, hobby1.isTaskCompleted());
        assertEquals("10:10:10", hobby1.getStartTime().toString());
        assertEquals("11:11:11", hobby1.getEndTime().toString());
        assertEquals("2021-01-03", hobby1.getDateLastPlayed().toString());
        assertEquals("chess", hobby1.getHobbyName());
        assertEquals(4, hobby1.getNumberOfMoves());
        assertEquals("win", hobby1.getResult());

    }


}
