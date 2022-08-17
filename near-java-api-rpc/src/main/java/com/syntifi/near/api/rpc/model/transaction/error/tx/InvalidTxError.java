package com.syntifi.near.api.rpc.model.transaction.error.tx;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.syntifi.near.api.rpc.json.deserializer.TxErrorDeserializer;
import com.syntifi.near.api.rpc.model.transaction.error.TxExecutionError;

@JsonTypeName("InvalidTxError")
@JsonDeserialize(using = TxErrorDeserializer.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes({@JsonSubTypes.Type(value = InvalidSignerId.class, name = "InvalidSignerId"),
        @JsonSubTypes.Type(value = SignerDoesNotExist.class, name = "SignerDoesNotExist")})
public class InvalidTxError implements TxExecutionError {
}
