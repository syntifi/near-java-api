package com.syntifi.near.api.rpc.model.transaction.error;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT, property = "error")
@JsonSubTypes({@JsonSubTypes.Type(value = ActionError.class, name = "ActionError")})
public interface TxExecutionError {
}
