package com.syntifi.near.api.indexer.exception;


import com.syntifi.near.api.common.exception.NearException;

/**
 * Thrown on Near Indexer error
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public class NearIndexerException extends NearException {
    public NearIndexerException(String message) {
        super(message);
    }
}
