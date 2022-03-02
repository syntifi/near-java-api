package com.syntifi.near.api.model.transaction;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class ReceiptOutcome {
    @JsonProperty("proof")
    private Collection<Proof> proof;

    @JsonProperty("block_hash")
    private String blockHash;

    @JsonProperty("id")
    private String id;

    @JsonProperty("outcome")
    private Outcome outcome;
}
