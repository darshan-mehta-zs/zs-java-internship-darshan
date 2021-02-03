package com.zs.hobbytracker.restcontroller;

import com.zs.hobbytracker.exception.ApplicationRuntimeException;
import com.zs.hobbytracker.exception.InvalidInputException;
import com.zs.hobbytracker.models.Chess;
import com.zs.hobbytracker.service.ChessService;
import com.zs.hobbytracker.utils.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

/**
 * Rest Controller for Chess hobby in which user can tick the hobby, get the last tick, get the longest streak and the latest streak of user
 */
@RequestMapping("/chess")
@RestController
public class ChessController {

    /**
     * Badminton Service class
     */
    @Autowired
    ChessService chessService;

    /**
     * Stores Chess tick data of user to database
     *
     * @param chess accepts chess as request body and from request body created object of Chess
     * @return Response Entity Object which responds Added if data was successfully inserted returns error code and error message in case of invalid input or internal server error
     */
    @PostMapping("/tick")
    public ResponseEntity<String> tick(@RequestBody Chess chess) {

        try {
            chessService.tick(DatabaseConnection.getConnection(), chess);
            return new ResponseEntity<>("Added", HttpStatus.OK);
        } catch (InvalidInputException e) {
            return new ResponseEntity<>(e.getErrorCode() + " " + e.getErrorMessage(), HttpStatus.BAD_REQUEST);
        } catch (ApplicationRuntimeException e) {
            return new ResponseEntity<>(e.getErrorCode() + " " + e.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * For fetching longest streak of chess hobby
     *
     * @param userId accepts id of user
     * @return Response Entity Object which responds with Integer value or  error code and error message in case internal server error
     */
    @GetMapping("/longestStreak/{userId}")
    public ResponseEntity getLongestStreak(@PathVariable int userId) {
        Integer longestStreak = null;
        try {
            longestStreak = chessService.getLongestStreak(DatabaseConnection.getConnection(), userId);
            return new ResponseEntity<>(longestStreak, HttpStatus.OK);
        } catch (ApplicationRuntimeException e) {
            return new ResponseEntity(e.getErrorCode() + " " + e.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * For fetching latest streak of chess hobby
     *
     * @param userId accepts id of user
     * @return Response Entity Object which responds with Integer value or error code and error message in case internal server error
     */
    @GetMapping("/latestStreak/{userId}")
    public ResponseEntity<Integer> getLatestStreak(@PathVariable int userId) {
        Integer latestStreak = null;
        try {
            latestStreak = chessService.getLatestStreak(DatabaseConnection.getConnection(), userId);
            return new ResponseEntity<>(latestStreak, HttpStatus.OK);
        } catch (ApplicationRuntimeException e) {
            return new ResponseEntity(e.getErrorCode() + " " + e.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * For Fetching the last tick of chess hobby
     *
     * @param userId accepts id of user
     * @return Response Entity containing Chess data or error code and error message in case internal server error
     */
    @GetMapping("/lastTick/{userId}")
    public ResponseEntity<Chess> lastTick(@PathVariable int userId) {
        try {
            return new ResponseEntity<Chess>(chessService.lastTick(DatabaseConnection.getConnection(), userId), HttpStatus.OK);
        } catch (ApplicationRuntimeException e) {
            return new ResponseEntity(e.getErrorCode() + " " + e.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * For fetching the data of user given the date
     *
     * @param userId accepts id of user
     * @param date   accepts date for searching purpose
     * @return Response Entity containing Chess data or error code and error message in case internal server error
     */
    @GetMapping("/detailsForDate/{userId}/{date}")
    public ResponseEntity<Chess> detailsForDate(@PathVariable int userId, @PathVariable Date date) {
        try {
            return new ResponseEntity<Chess>(chessService.detailsForDate(DatabaseConnection.getConnection(), userId, date), HttpStatus.OK);
        } catch (ApplicationRuntimeException e) {
            return new ResponseEntity(e.getErrorCode() + " " + e.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
