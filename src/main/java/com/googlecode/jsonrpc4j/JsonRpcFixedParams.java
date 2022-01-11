package com.googlecode.jsonrpc4j;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Temporary workaround
 * Pull request https://github.com/briandilley/jsonrpc4j/pull/282
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonRpcFixedParams {

    /**
     * @return a fixed parameter's collection.
     */
    JsonRpcFixedParam[] fixedParams();
}
