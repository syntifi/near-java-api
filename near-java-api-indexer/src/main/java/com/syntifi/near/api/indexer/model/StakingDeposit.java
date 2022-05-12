package com.syntifi.near.api.indexer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * Holds data of a staking deposit
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@Getter
@Setter
public class StakingDeposit {
    @JsonProperty("deposit")
    private BigInteger deposit;
    @JsonProperty("validator_id")
    private String validator;
}
