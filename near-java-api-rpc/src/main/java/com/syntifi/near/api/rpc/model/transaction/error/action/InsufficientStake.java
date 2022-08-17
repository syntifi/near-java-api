package com.syntifi.near.api.rpc.model.transaction.error.action;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;


/**
 * The account doesn't have enough balance to increase the stake.
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.3.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("InsufficientStake")
public class InsufficientStake implements ActionErrorKind {
    @JsonProperty("account_id")
    String accountId;

    @JsonProperty("stake")
    BigInteger stake;

    @JsonProperty("minimum_stake")
    BigInteger minimumStake;
}
