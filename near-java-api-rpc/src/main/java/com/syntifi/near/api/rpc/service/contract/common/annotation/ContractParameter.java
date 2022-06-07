package com.syntifi.near.api.rpc.service.contract.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Contract function metadata containing argument name
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ContractParameter {
    String value() default "";

    ContractParameterType[] type() default {ContractParameterType.ARGUMENT};
}
