package org.vstu.compprehension.Exceptions.NotFoundEx;

public class LawFormulationNFException extends RuntimeException {
    public LawFormulationNFException(String message) {
        super(message);
    }

    public LawFormulationNFException(String message, Throwable cause) {
        super(message, cause);
    }
}
