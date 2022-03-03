package com.syntifi.near.api.model.contract;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.model.common.EncodedHash;
import com.syntifi.near.api.model.transaction.Proof;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractState {
    @JsonProperty("values")
    private Collection<ContractStateDetails> values;

    @JsonProperty("proof")
    private Collection<Proof> proof;

    @JsonProperty("block_height")
    private long blockHeight;

    @JsonProperty("block_hash")
    private EncodedHash blockHash;
}
