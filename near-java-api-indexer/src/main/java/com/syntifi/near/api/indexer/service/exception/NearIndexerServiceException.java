package com.syntifi.near.api.indexer.service.exception;


/**
 * Thrown on Near helper service error
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public class NearIndexerServiceException extends RuntimeException {
    public NearIndexerServiceException(String message) {
        super(message);
    }
}
