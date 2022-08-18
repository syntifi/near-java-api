package com.syntifi.near.api.rpc.model.transaction.error.tx;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

/**
 * Signer account doesn't have enough balance after transaction.
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.3.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("LackBalanceForState")
@JsonDeserialize //This is needed to override the Polymorphic deserializers
public class LackBalanceForStateTxError implements InvalidTxError {
    @JsonProperty("signer_id")
    String signerId;

    BigInteger amount;
}
