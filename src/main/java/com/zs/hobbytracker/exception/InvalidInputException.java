package com.zs.hobbytracker.exception;

/**
 * InvalidInputException will be raised if the user input is invalid
 */
public class InvalidInputException extends Exception {

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

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
