package com.syntifi.near.api.helper.service.exception;


/**
 * Thrown on Near helper service error
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public class NearHelperServiceException extends RuntimeException{
    public NearHelperServiceException(String message) {
        super(message);
    }
}
