package com.syntifi.near.api.common.exception;

import lombok.Getter;

/**
 * Thrown on Near RPC service error
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public class NearException extends RuntimeException {
    @Getter
    private final NearErrorData nearErrorData;

    public NearException(NearErrorData error) {
        super(String.format("%s (Code: %d): %s", error.getMessage(), error.getCode(), error.getData().toString()));
        nearErrorData = error;
    }

    public NearException(String message) {
        this(message, null);
    }

    public NearException(String message, Throwable cause) {
        super(message, cause);
        nearErrorData = null;
    }
}
