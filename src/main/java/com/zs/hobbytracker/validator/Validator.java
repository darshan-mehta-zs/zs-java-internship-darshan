package com.zs.hobbytracker.validator;

import com.zs.hobbytracker.exception.InvalidInputException;
import com.zs.hobbytracker.models.Badminton;
import com.zs.hobbytracker.models.Chess;
import com.zs.hobbytracker.models.HobbyAttributes;

import java.sql.Date;

/**
 * Validator class for validating input provided by user
 */
public class Validator {

    /**
     * enum for result as it can be one of win, lost or draw
     */
    public enum result {WIN, LOST, DRAW}

    /**
     * Validates the user input
     *
     * @param hobby accepts hobby
     * @throws InvalidInputException if user input does not match certain conditions
     */
    public static void validate(HobbyAttributes hobby) throws InvalidInputException {
        if (hobby.getStartTime().compareTo(hobby.getEndTime()) > 0) {
            throw new InvalidInputException(400, "Start time cannot be greater than end time");
        } else if (hobby.getDateLastPlayed().after(new Date(System.currentTimeMillis()))) {
            throw new InvalidInputException(400, "Date cannot be greater than today's date");
        }
        if (hobby instanceof Badminton) {
            if (((Badminton) hobby).getNumberOfPlayers() > 4) {
                throw new InvalidInputException(400, "Badminton cannot have be played by more than 4 players");
            } else if (!isResultValid(((Badminton) hobby).getResult())) {
                throw new InvalidInputException(400, "Result can be one of win, lost or draw");
            }
        } else if (hobby instanceof Chess) {
            if (hobby instanceof Chess && ((Chess) hobby).getNumberOfMoves() > 100) {
                throw new InvalidInputException(400, "Number of moves cannot be greater than 100");
            } else if (!isResultValid(((Chess) hobby).getResult())) {
                throw new InvalidInputException(400, "Result can be one of win, lost or draw");
            }
        }

    }

    /**
     * @param actualResult accepts actualResult stored in hobby object passed for validating input
     * @return if the result is valid
     */
    public static boolean isResultValid(String actualResult) {
        for (result r : result.values()) {
            if (actualResult.equalsIgnoreCase(r.toString())) {
                return true;
            }
        }
        return false;
    }

}
