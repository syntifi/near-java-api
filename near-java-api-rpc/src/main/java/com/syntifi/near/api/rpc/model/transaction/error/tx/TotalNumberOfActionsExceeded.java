package com.syntifi.near.api.rpc.model.transaction.error.tx;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("TotalNumberOfActionsExceeded")
@JsonDeserialize //This is needed to override the Polymorphic deserializers
public class TotalNumberOfActionsExceeded implements ActionsValidation {
    @JsonProperty("total_number_of_actions")
    BigInteger totalNumberOfActions;

    BigInteger limit;
}
