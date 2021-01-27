package com.zs.hobbytracker.exception;

public class InvalidInputException extends Exception {

    /**
     * Error code for exception
     */
    int errorCode;

    /**
     * message for the error
     */
    String errorMessage;

    /**
     * @param message accepts message for error
     */
    public InvalidInputException(String message) {
        super(message);
    }

    /**
     * @param errorCode    accepts code for error
     * @param errorMessage accepts error message
     */
    public InvalidInputException(int errorCode, String errorMessage) {
        this(errorCode + " " + errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
