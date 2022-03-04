package com.syntifi.near.api.model.network;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SyncInfo
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
public class SyncInfo {
    @JsonProperty("latest_block_hash")
    private String latestBlockHash;

    @JsonProperty("latest_block_height")
    private long latestBlockHeight;

    @JsonProperty("latest_state_root")
    private String latestStateRoot;

    @JsonProperty("latest_block_time")
    private String latestBlockTime;

    @JsonProperty("syncing")
    private boolean syncing;
    @JsonInclude(value = Include.NON_EMPTY)

    @JsonProperty("earliest_block_hash")
    private String earliestBlockHash;
    @JsonInclude(value = Include.NON_EMPTY)

    @JsonProperty("earliest_block_height")
    private Long earliestBlockHeight;
    @JsonInclude(value = Include.NON_EMPTY)

    @JsonProperty("earliest_block_time")
    private String earliestBlockTime;
}
