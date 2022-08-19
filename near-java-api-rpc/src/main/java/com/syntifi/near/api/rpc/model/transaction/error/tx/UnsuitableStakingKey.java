package com.syntifi.near.api.rpc.model.transaction.error.tx;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.syntifi.near.api.common.model.key.PublicKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("UnsuitableStakingKey")
@JsonDeserialize //This is needed to override the Polymorphic deserializers
public class UnsuitableStakingKey implements ActionsValidation {
    @JsonProperty("public_key")
    PublicKey publicKey;
}
