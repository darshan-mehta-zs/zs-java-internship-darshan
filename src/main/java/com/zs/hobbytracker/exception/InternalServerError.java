package com.zs.hobbytracker.exception;

/**
 * Internal Server Error which contains the error code and its message
 */
public class InternalServerError extends Exception {

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
    public InternalServerError(String message) {
        super(message);
    }

    /**
     * @param errorCode    accepts code for error
     * @param errorMessage accepts error message
     */
    public InternalServerError(int errorCode, String errorMessage) {
        this(errorCode + " " + errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
