package com.syntifi.near.api.common.exception;

/**
 * Thrown in case of a type which does not exist being requested
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public class NoSuchTypeException extends RuntimeException {
    public NoSuchTypeException(String message) {
        super(message);
    }
    public NoSuchTypeException(String message, Throwable t) {
        super(message, t);
    }
}
