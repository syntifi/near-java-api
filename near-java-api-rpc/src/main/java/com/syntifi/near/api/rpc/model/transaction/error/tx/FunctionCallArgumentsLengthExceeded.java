package com.syntifi.near.api.rpc.model.transaction.error.tx;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("FunctionCallArgumentsLengthExceeded")
@JsonDeserialize //This is needed to override the Polymorphic deserializers
public class FunctionCallArgumentsLengthExceeded implements ActionsValidation {
    BigInteger length;

    BigInteger limit;
}
