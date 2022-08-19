package com.syntifi.near.api.rpc.model.transaction.error.tx;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.syntifi.near.api.common.model.key.PublicKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("NotEnoughAllowance")
@JsonDeserialize //This is needed to override the Polymorphic deserializers
public class NotEnoughAllowance implements InvalidAccessKeyError {

    @JsonProperty("account_id")
    String accountId;

    @JsonProperty("public_key")
    PublicKey publicKey;

    BigInteger balance;

    BigInteger cost;
}
