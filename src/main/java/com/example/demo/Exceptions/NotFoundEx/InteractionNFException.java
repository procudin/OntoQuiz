package com.example.demo.Exceptions.NotFoundEx;

public class InteractionNFException extends RuntimeException{
    public InteractionNFException(String message) {
        super(message);
    }

    public InteractionNFException(String message, Throwable cause) {
        super(message, cause);
    }
}
