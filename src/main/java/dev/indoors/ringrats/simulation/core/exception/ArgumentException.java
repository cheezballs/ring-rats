package dev.indoors.ringrats.simulation.core.exception;

import java.io.Serial;

public class ArgumentException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public ArgumentException() {
        super();
    }

    public ArgumentException(String message) {
        super(message);
    }

    public ArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArgumentException(Throwable cause) {
        super(cause);
    }

}