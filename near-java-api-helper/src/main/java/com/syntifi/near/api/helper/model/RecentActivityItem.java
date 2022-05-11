package com.syntifi.near.api.helper.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.syntifi.near.api.helper.json.deserializer.RecentActivityItemDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * RecentActivityItem
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonDeserialize(using = RecentActivityItemDeserializer.class)
public class RecentActivityItem {
    public enum RecentActivityActionKind {
        CREATE_ACCOUNT,
        DEPLOY_CONTRACT,
        FUNCTION_CALL,
        TRANSFER,
        STAKE,
        ADD_KEY,
        DELETE_KEY,
        DELETE_ACCOUNT
    }

    @JsonProperty("block_hash")
    String blockHash;

    @JsonProperty("block_timestamp")
    String blockTimestamp;

    @JsonProperty("hash")
    String hash;

    @JsonProperty("action_index")
    Integer actionIndex;

    @JsonProperty("signer_id")
    String signerId;

    @JsonProperty("receiver_id")
    String receiverId;

    @JsonProperty("action_kind")
    RecentActivityActionKind actionKind;

    @JsonProperty("args")
    RecentActivityArg args;
}
