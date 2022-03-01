package com.syntifi.near.api.model.block;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.model.key.Signature;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
public class BlockHeader {
    @JsonProperty("height")
    private long height;

    @JsonProperty("prev_height")
    @JsonInclude(value = Include.NON_NULL)
    private Long previousHeight;

    @JsonProperty("epoch_id")
    private String epochId;

    @JsonProperty("next_epoch_id")
    private String nextEpochId;

    @JsonProperty("hash")
    private String hash;

    @JsonProperty("prev_hash")
    private String previousHash;

    @JsonProperty("prev_state_root")
    private String previousStateRoot;

    @JsonProperty("chunk_receipts_root")
    private String chunkReceiptsRoot;

    @JsonProperty("chunk_headers_root")
    private String chunkHeadersRoot;

    @JsonProperty("chunk_tx_root")
    private String chunkTxRoot;

    @JsonProperty("outcome_root")
    private String outcomeRoot;

    @JsonProperty("chunks_included")
    private long chunksIncluded;

    @JsonProperty("challenges_root")
    private String chalengesRoot;

    @JsonProperty("timestamp")
    private long timeStamp;

    @JsonProperty("timestamp_nanosec")
    private String timeStampNanoSeconds;

    @JsonProperty("random_value")
    private String randomValue;

    @JsonProperty("validator_proposals")
    private ValidatorProposal[] validatorProposals;

    @JsonProperty("chunk_mask")
    private Collection<Boolean> chunkMask;

    @JsonProperty("block_ordinal")
    @JsonInclude(value = Include.NON_NULL)
    private Long blockOrdinal;

    @JsonProperty("gas_price")
    private String gasPrice;

    @JsonProperty("rent_paid")
    private String rentPaid;

    @JsonProperty("validator_reward")
    private String validatorReward;

    @JsonProperty("total_supply")
    private String totalSupply;

    // TODO: find which object represents this
    @JsonProperty("challenges_result")
    private Object[] challengesResult;

    @JsonProperty("last_final_block")
    private String lastFinalBlock;

    @JsonProperty("last_ds_final_block")
    private String lastDsFinalBlock;

    @JsonProperty("next_bp_hash")
    private String nextBpHash;

    @JsonProperty("block_merkle_root")
    private String blockMerkleRoot;

    @JsonProperty("epoch_sync_data_hash")
    @JsonInclude(value = Include.NON_EMPTY)
    private String epochSyncDataHash;

    @JsonProperty("approvals")
    private Collection<String> approvals;

    @JsonProperty("signature")
    private Signature signature;

    @JsonProperty("latest_protocol_version")
    private long latestProtocolVersion;
}
