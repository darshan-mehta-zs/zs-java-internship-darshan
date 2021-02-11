package com.zs.hobbytracker.restcontroller;

import com.zs.hobbytracker.exception.ApplicationRuntimeException;
import com.zs.hobbytracker.models.User;
import com.zs.hobbytracker.service.UserService;
import com.zs.hobbytracker.utils.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller for user
 */
@RequestMapping("/user")
@RestController
public class UserController {

    /**
     * User Service class
     */
    @Autowired
    UserService userService;

    /**
     * Adds User data to database
     *
     * @param user accepts user
     * @return Response Entity containing added if user added or error code and error message in case internal server error
     */
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody User user) {
        try {
            userService.addUser(DatabaseConnection.getConnection(), user);
            return new ResponseEntity("Added", HttpStatus.OK);
        } catch (ApplicationRuntimeException e) {
            return new ResponseEntity(e.getErrorCode() + " " + e.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
