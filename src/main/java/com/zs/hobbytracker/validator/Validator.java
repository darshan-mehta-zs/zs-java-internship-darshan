package main.java.com.zs.hobbytracker.validator;

import main.java.com.zs.hobbytracker.exception.InvalidInputException;
import main.java.com.zs.hobbytracker.models.Badminton;
import main.java.com.zs.hobbytracker.models.Chess;
import main.java.com.zs.hobbytracker.models.HobbyAttributes;

import java.sql.Date;

public class Validator {

    public static void validate(HobbyAttributes hobby) throws InvalidInputException {
        if (hobby.getStartTime().compareTo(hobby.getEndTime()) > 0) {
            throw new InvalidInputException(400, "Start time cannot be greater than end time");
        } else if (hobby.getDateLastPlayed().after(new Date(System.currentTimeMillis()))) {
            throw new InvalidInputException(400, "Date cannot be greater than today's date");
        }
        if (hobby instanceof Badminton) {
            if (((Badminton) hobby).getNumberOfPlayers() > 4) {
                throw new InvalidInputException(400, "Badminton cannot have be played by more than 4 players");
            } else if (!(((Badminton) hobby).getResult().equals("win") || ((Badminton) hobby).getResult().equals("lost") || ((Badminton) hobby).getResult().equals("draw"))) {
                throw new InvalidInputException(400, "Result can be one of win, lost or draw ");
            }
        } else if (hobby instanceof Chess) {
            if (!(((Chess) hobby).getResult().equals("win") || ((Chess) hobby).getResult().equals("lost") || ((Chess) hobby).getResult().equals("draw"))) {
                throw new InvalidInputException(400, "Result can be one of win, lost or draw ");
            } else if (hobby instanceof Chess && ((Chess) hobby).getNumberOfMoves() > 100) {
                throw new InvalidInputException(400, "Number of moves cannot be greater than 100");
            }
        }

    }
}
