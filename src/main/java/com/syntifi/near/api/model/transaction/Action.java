package com.syntifi.near.api.model.transaction;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@JsonTypeInfo(use = Id.NAME, include = As.WRAPPER_OBJECT)
@JsonSubTypes({ @JsonSubTypes.Type(value = TransferAction.class, name = "Transfer"),
                @JsonSubTypes.Type(value = FunctionCallAction.class, name = "FunctionCall") })
public interface Action {
}
