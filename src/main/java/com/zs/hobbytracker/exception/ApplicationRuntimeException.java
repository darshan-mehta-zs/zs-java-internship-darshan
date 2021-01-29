package com.zs.hobbytracker.exception;

/**
 * Internal Server Error which contains the error code and its message
 */
public class ApplicationRuntimeException extends Exception {

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
    public ApplicationRuntimeException(String message) {
        super(message);
    }

    /**
     * @param errorCode    accepts code for error
     * @param errorMessage accepts error message
     */
    public ApplicationRuntimeException(int errorCode, String errorMessage) {
        this(errorCode + " " + errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
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
}
