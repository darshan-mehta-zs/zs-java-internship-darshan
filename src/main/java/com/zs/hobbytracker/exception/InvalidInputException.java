package main.java.com.zs.hobbytracker.exception;

public class InvalidInputException extends Exception {

    int errorCode;
    String errorMessage;

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(int errorCode, String errorMessage) {
        this(errorCode + " " + errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
