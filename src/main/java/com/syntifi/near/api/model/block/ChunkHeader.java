package com.syntifi.near.api.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
public class ChunkHeader {
    @JsonProperty("chunk_hash")
    private String hash;

    @JsonProperty("prev_block_hash")
    private String previousBlockHash;

    @JsonProperty("outcome_root")
    private String outcomeRoot;

    @JsonProperty("prev_state_root")
    private String previousStateRoot;

    @JsonProperty("encoded_merkle_root")
    private String encodedMerkleRoot;

    @JsonProperty("encoded_length")
    private long encodedLength;

    @JsonProperty("height_created")
    private long heightCreated;

    @JsonProperty("height_included")
    private long heightIncluded;

    @JsonProperty("shard_id")
    private long shardId;

    @JsonProperty("gas_used")
    private long gasUsed;

    @JsonProperty("gas_limit")
    private long gasLimit;

    @JsonProperty("rent_paid")
    private String rentPaid;

    @JsonProperty("validator_reward")
    private String validatorReward;

    @JsonProperty("balance_burnt")
    private String balanceBurnt;

    @JsonProperty("outgoing_receipts_root")
    private String outgoingReceiptsRoot;

    @JsonProperty("tx_root")
    private String txRoot;

    @JsonProperty("validator_proposals")
    private ValidatorProposal[] validatorProposals;

    @JsonProperty("signature")
    private String signature;
}
