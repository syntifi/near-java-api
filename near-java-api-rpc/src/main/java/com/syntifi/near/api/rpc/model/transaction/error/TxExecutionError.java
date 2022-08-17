package com.syntifi.near.api.rpc.model.transaction.error;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.syntifi.near.api.rpc.model.transaction.error.action.ActionError;
import com.syntifi.near.api.rpc.model.transaction.error.tx.InvalidTxError;

/**
 * Transaction Execution error interface
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.3.0
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes({@JsonSubTypes.Type(value = ActionError.class, name = "ActionError"),
        @JsonSubTypes.Type(value = InvalidTxError.class, name = "InvalidTxError")})
public interface TxExecutionError {
}
