package com.techelevator.tenmo.exceptionHandler;

public class TransferServiceException extends RuntimeException {

    public TransferServiceException(String message) {
        super(message);
    }

    public TransferServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
