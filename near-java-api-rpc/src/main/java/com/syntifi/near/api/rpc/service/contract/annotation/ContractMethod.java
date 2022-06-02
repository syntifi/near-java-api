package com.syntifi.near.api.rpc.service.contract.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Contract function metadata containing function type and name
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface ContractMethod {
    MethodType type();
    String name();
}
