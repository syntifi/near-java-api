package com.syntifi.near.api.model.network;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.model.key.PublicKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

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
public class Validator {
    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("public_key")
    @JsonInclude(value = Include.NON_EMPTY)
    private PublicKey publicKey;

    @JsonProperty("is_slashed")
    @JsonInclude(value = Include.NON_EMPTY)
    private Boolean isSlashed;

    @JsonProperty("stake")
    @JsonInclude(value = Include.NON_EMPTY)
    private String stake;

    @JsonProperty("amount")
    @JsonInclude(value = Include.NON_EMPTY)
    private String amount;

    @JsonProperty("shards")
    @JsonInclude(value = Include.NON_EMPTY)
    private Collection<Long> shards;

    @JsonProperty("num_produced_blocks")
    @JsonInclude(value = Include.NON_EMPTY)
    private Long numProducedBlocks;

    @JsonProperty("num_expected_blocks")
    @JsonInclude(value = Include.NON_EMPTY)
    private Long numExpectedBlocks;

    @JsonProperty("num_produced_chunks")
    @JsonInclude(value = Include.NON_EMPTY)
    private Long numProducedChunks;

    @JsonProperty("num_expected_chunks")
    @JsonInclude(value = Include.NON_EMPTY)
    private Long numExpectedChunks;
}
