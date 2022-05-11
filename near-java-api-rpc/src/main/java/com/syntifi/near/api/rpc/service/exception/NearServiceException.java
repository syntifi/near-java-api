package com.syntifi.near.api.rpc.service.exception;

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
        super(String.format("%s (Code: %d): %s", error.getMessage(), error.getCode(), error.getData().toString()));
        nearServiceErrorData = error;
    }

    public NearServiceException(String message, Throwable cause) {
        super(message, cause);
        nearServiceErrorData = null;
    }
}
