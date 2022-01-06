package com.syntifi.near.api.service.exception;

import lombok.Getter;

/**
 * Thrown on Near RPC service error
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public class NearServiceException extends RuntimeException {
    @Getter
    private final NearServiceErrorData nearServiceErrorData;

    public NearServiceException(NearServiceErrorData error) {
        super(String.format("%s (code: %d)", error.getMessage(), error.getCode()));
        nearServiceErrorData = error;
    }

    public NearServiceException(String message, Throwable cause) {
        super(message, cause);
        nearServiceErrorData = null;
    }
}
