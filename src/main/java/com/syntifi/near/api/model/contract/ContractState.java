package com.syntifi.near.api.model.contract;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.model.transaction.Proof;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
public class ContractState {
    @JsonProperty("values")
    private Collection<ContractStateDetails> values;

    @JsonProperty("proof")
    private Collection<Proof> proof;

    @JsonProperty("block_height")
    private long blockHeight;

    @JsonProperty("block_hash")
    private String blockHash;
}
