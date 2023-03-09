package com.techelevator.tenmo.exceptionHandler;

public class TransferNotFoundException extends TransferServiceException {
    public TransferNotFoundException(String message) {
        super(message);
    }

    public TransferNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}


