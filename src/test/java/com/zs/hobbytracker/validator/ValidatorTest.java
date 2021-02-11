package com.zs.hobbytracker.validator;

import com.zs.hobbytracker.exception.InvalidInputException;
import com.zs.hobbytracker.models.Badminton;
import com.zs.hobbytracker.models.Chess;
import com.zs.hobbytracker.models.HobbyAttributes;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Date;
import java.sql.Time;

import static org.junit.Assert.*;

/**
 * Tests the validator for seeing is input is valid
 */
@RunWith(MockitoJUnitRunner.class)
public class ValidatorTest {

    /**
     * Model class object of Badminton
     */
    private HobbyAttributes hobby1;

    /**
     * Model class object of Badminton
     */
    private HobbyAttributes hobby2;

    /**
     * Model class object of Badminton
     */
    private HobbyAttributes hobby3;

    /**
     * Model class object of Badminton
     */
    private HobbyAttributes hobby4;

    /**
     * Model class object of Badminton
     */
    private HobbyAttributes hobby5;

    /**
     * Model class object of Chess
     */
    private HobbyAttributes hobby6;

    /**
     * Model class object of Chess
     */
    private HobbyAttributes hobby7;


    Validator validator;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        validator = new Validator();
        hobby1 = new Badminton(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("10:09:11"), Date.valueOf("2021-01-03"), "badminton", 4, "win");
        hobby2 = new Badminton(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-02-09"), "badminton", 4, "loss");
        hobby3 = new Badminton(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-05"), "badminton", 5, "win");
        hobby4 = new Badminton(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-07"), "badminton", 4, "loss");
        hobby5 = new Badminton(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-08"), "badminton", 4, "win");

        hobby6 = new Chess(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-07"), "chess", 150, "win");
        hobby7 = new Chess(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-08"), "chess", 4, "loss");

    }

    @Test
    public void isResultValidTest() {
        assertTrue(validator.isResultValid(((Badminton) hobby1).getResult()));
    }

    @Test
    public void resultNotValidTest() {
        assertFalse(validator.isResultValid(((Badminton) hobby2).getResult()));
    }

    @Test(expected = InvalidInputException.class)
    public void validateStartTimeEndTime() throws InvalidInputException {
        Validator.validate(hobby1);
    }

    @Test(expected = InvalidInputException.class)
    public void validateDateLastPlayed() throws InvalidInputException {
        Validator.validate(hobby2);
    }

    @Test(expected = InvalidInputException.class)
    public void validateNumberOfPlayers() throws InvalidInputException {
        Validator.validate(hobby3);
    }

    @Test(expected = InvalidInputException.class)
    public void validateResultBadminton() throws InvalidInputException {
        Validator.validate(hobby4);
    }

    @Test(expected = InvalidInputException.class)
    public void validateNumberOfMoves() throws InvalidInputException {
        Validator.validate(hobby6);
    }

    @Test(expected = InvalidInputException.class)
    public void validateResultChess() throws InvalidInputException {
        Validator.validate(hobby7);
    }


}
