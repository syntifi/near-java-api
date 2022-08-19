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
@JsonTypeName("AccessKeyNotFound")
@JsonDeserialize //This is needed to override the Polymorphic deserializers
public class AccessKeyNotFound implements InvalidAccessKeyError {

    @JsonProperty("public_key")
    PublicKey publicKey;

    @JsonProperty("account_id")
    String accountId;
}
