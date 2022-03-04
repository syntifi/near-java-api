package com.syntifi.near.api.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.model.common.EncodedHash;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

/**
 * TransactionOutcome
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionOutcome {
    @JsonProperty("proof")
    private Collection<Proof> proof;

    @JsonProperty("block_hash")
    private EncodedHash blockHash;

    @JsonProperty("id")
    private String id;

    @JsonProperty("outcome")
    private Outcome outcome;
}
