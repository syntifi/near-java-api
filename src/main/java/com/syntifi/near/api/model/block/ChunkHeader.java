package com.syntifi.near.api.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.model.common.EncodedHash;
import com.syntifi.near.api.model.key.Signature;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ChunkHeader
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
public class ChunkHeader {
    @JsonProperty("chunk_hash")
    private EncodedHash hash;

    @JsonProperty("prev_block_hash")
    private EncodedHash previousBlockHash;

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
    private Signature signature;
}
