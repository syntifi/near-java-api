package com.syntifi.near.api.rpc.model.transaction.error.tx;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("FunctionCallZeroAttachedGas")
@JsonDeserialize //This is needed to override the Polymorphic deserializers
public class FunctionCallZeroAttachedGas implements ActionsValidation {
}
