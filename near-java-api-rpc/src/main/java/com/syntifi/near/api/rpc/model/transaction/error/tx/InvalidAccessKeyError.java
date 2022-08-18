package com.syntifi.near.api.rpc.model.transaction.error.tx;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.syntifi.near.api.rpc.json.deserializer.TxErrorDeserializer;

/**
 * Happens if a wrong AccessKey used or AccessKey has not enough permissions
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.3.0
 */
@JsonTypeName("InvalidAccessKeyError")
@JsonDeserialize(using = TxErrorDeserializer.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
/*@JsonSubTypes({
        @JsonSubTypes.Type(value = AccessKeyNotFound.class, name = "AccessKeyNotFound"),
        @JsonSubTypes.Type(value = ReceiverMismatch.class, name = "ReceiverMismatch"),
        @JsonSubTypes.Type(value = MethodNameMismatch.class, name = "MethodNameMismatch"),
        @JsonSubTypes.Type(value = RequiresFullAccess.class, name = "RequiresFullAccess"),
        @JsonSubTypes.Type(value = NotEnoughAllowance.class, name = "NotEnoughAllowance"),
        @JsonSubTypes.Type(value = DepositWithFunctionCall.class, name = "DepositWithFunctionCall")
})*/
public interface InvalidAccessKeyError extends InvalidTxError {
}
