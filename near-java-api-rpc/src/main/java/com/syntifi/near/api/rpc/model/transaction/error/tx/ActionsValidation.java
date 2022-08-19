package com.syntifi.near.api.rpc.model.transaction.error.tx;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.syntifi.near.api.rpc.json.deserializer.ActionsValidationDeserializer;

/**
 * An error occurred while validating actions of a Transaction.
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.3.0
 */
@JsonTypeName("ActionsValidation")
@JsonDeserialize(using = ActionsValidationDeserializer.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DeleteActionMustBeFinal.class, name = "DeleteActionMustBeFinal"),
        @JsonSubTypes.Type(value = TotalPrepaidGasExceeded.class, name = "TotalPrepaidGasExceeded"),
        @JsonSubTypes.Type(value = TotalNumberOfActionsExceeded.class, name = "TotalNumberOfActionsExceeded"),
        @JsonSubTypes.Type(value = AddKeyMethodNamesNumberOfBytesExceeded.class, name = "AddKeyMethodNamesNumberOfBytesExceeded"),
        @JsonSubTypes.Type(value = AddKeyMethodNameLengthExceeded.class, name = "AddKeyMethodNameLengthExceeded"),
        @JsonSubTypes.Type(value = IntegerOverflow.class, name = "IntegerOverflow"),
        @JsonSubTypes.Type(value = InvalidAccountId.class, name = "InvalidAccountId"),
        @JsonSubTypes.Type(value = ContractSizeExceeded.class, name = "ContractSizeExceeded"),
        @JsonSubTypes.Type(value = FunctionCallMethodNameLengthExceeded.class, name = "FunctionCallMethodNameLengthExceeded"),
        @JsonSubTypes.Type(value = FunctionCallArgumentsLengthExceeded.class, name = "FunctionCallArgumentsLengthExceeded"),
        @JsonSubTypes.Type(value = UnsuitableStakingKey.class, name = "UnsuitableStakingKey"),
        @JsonSubTypes.Type(value = FunctionCallZeroAttachedGas.class, name = "FunctionCallZeroAttachedGas")
})
public interface ActionsValidation extends InvalidTxError {
}
