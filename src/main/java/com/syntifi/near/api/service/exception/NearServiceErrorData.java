package com.syntifi.near.api.service.exception;

import lombok.Data;

import java.io.Serializable;

/**
 * Json RPC service error data
 * 
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Data
public class NearServiceErrorData implements Serializable {
    private String name;
    private Serializable cause;
    private int code;
    private String message;
    private Serializable data;
}
