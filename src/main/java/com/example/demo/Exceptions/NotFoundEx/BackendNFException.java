package com.example.demo.Exceptions.NotFoundEx;

public class BackendNFException extends RuntimeException {
    public BackendNFException(String message) {
        super(message);
    }

    public BackendNFException(String message, Throwable cause) {
        super(message, cause);
    }
}
