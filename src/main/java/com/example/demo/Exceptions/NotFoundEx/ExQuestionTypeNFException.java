package com.example.demo.Exceptions.NotFoundEx;

public class ExQuestionTypeNFException extends RuntimeException{
    public ExQuestionTypeNFException(String message) {
        super(message);
    }

    public ExQuestionTypeNFException(String message, Throwable cause) {
        super(message, cause);
    }
}
