package org.vstu.compprehension.Exceptions.NotFoundEx;

public class ExConceptNFException extends RuntimeException {
    public ExConceptNFException(String message) {
        super(message);
    }

    public ExConceptNFException(String message, Throwable cause) {
        super(message, cause);
    }
}
