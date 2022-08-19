package com.syntifi.near.api.rpc.model.transaction.error.tx;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("InvalidAccountId")
@JsonDeserialize //This is needed to override the Polymorphic deserializers
public class InvalidAccountId implements ActionsValidation {
    @JsonProperty("account_id")
    String accountId;
}
