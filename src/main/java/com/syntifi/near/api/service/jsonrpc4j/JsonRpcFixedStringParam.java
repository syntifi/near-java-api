package com.syntifi.near.api.service.jsonrpc4j;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation for fixed RPC param for modified jsonrpc4j
 * 
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonRpcFixedStringParam {

	/**
	 * @return the parameter's name.
	 */
	String[] name();

	/**
	 * @return the parameter's value.
	 */
	String[] value();
}
