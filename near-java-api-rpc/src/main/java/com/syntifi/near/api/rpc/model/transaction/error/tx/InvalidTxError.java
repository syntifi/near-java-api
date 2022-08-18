package com.syntifi.near.api.rpc.model.transaction.error.tx;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.syntifi.near.api.rpc.json.deserializer.TxErrorDeserializer;
import com.syntifi.near.api.rpc.model.transaction.error.TxExecutionError;

/**
 * Returned when an error occurred during TX execution
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.3.0
 */
@JsonTypeName("InvalidTxError")
@JsonDeserialize(using = TxErrorDeserializer.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ActionsValidation.class, name = "ActionsValidation"),
        @JsonSubTypes.Type(value = CostOverflow.class, name = "CostOverflow"),
        @JsonSubTypes.Type(value = Expired.class, name = "Expired"),
        @JsonSubTypes.Type(value = InvalidAccessKeyError.class, name = "InvalidAccessKeyError"),
        @JsonSubTypes.Type(value = InvalidChain.class, name = "InvalidChain"),
        @JsonSubTypes.Type(value = InvalidNonce.class, name = "InvalidNonce"),
        @JsonSubTypes.Type(value = InvalidReceiverId.class, name = "InvalidReceiverId"),
        @JsonSubTypes.Type(value = InvalidSignature.class, name = "InvalidSignature"),
        @JsonSubTypes.Type(value = InvalidSignerId.class, name = "InvalidSignerId"),
        @JsonSubTypes.Type(value = LackBalanceForStateTxError.class, name = "LackBalanceForState"),
        @JsonSubTypes.Type(value = NonceTooLarge.class, name = "NonceTooLarge"),
        @JsonSubTypes.Type(value = NotEnoughBalance.class, name = "NotEnoughBalance"),
        @JsonSubTypes.Type(value = SignerDoesNotExist.class, name = "SignerDoesNotExist"),
        @JsonSubTypes.Type(value = TransactionSizeExceeded.class, name = "TransactionSizeExceeded")
})
public interface InvalidTxError extends TxExecutionError {
}
