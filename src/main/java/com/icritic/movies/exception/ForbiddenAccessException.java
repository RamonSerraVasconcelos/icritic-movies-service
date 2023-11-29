package com.icritic.movies.exception;

public class ForbiddenAccessException extends RuntimeException {
    public ForbiddenAccessException() {
        super("Insufficient access rights");
    }

    public ForbiddenAccessException(String message) {
        super(message);
    }
}
