package com.syntifi.near.api.rpc.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.common.model.common.EncodedHash;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

/**
 * ReceiptOutcome
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
public class ReceiptOutcome {
    @JsonProperty("proof")
    private Collection<Proof> proof;

    @JsonProperty("block_hash")
    private EncodedHash blockHash;

    @JsonProperty("id")
    private String id;

    @JsonProperty("outcome")
    private Outcome outcome;
}
