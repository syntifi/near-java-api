package com.syntifi.near.api.model.protocol;

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
public class SimpleNightShadeShardLayout {
    @Getter
    @Setter
    public static class V1 {
        @JsonProperty("fixed_shards")
        private Collection<Long> fixedShards;

        @JsonProperty("boundary_accounts")
        private Collection<String> boundaryAccounts;

        @JsonProperty("shards_split_map")
        private Collection<Collection<Long>> shardsSplitMap;

        @JsonProperty("to_parent_shard_map")
        private Collection<Long> toParentShardMap;

        @JsonProperty("version")
        private long version;
    }

    @JsonProperty("V1")
    private V1 v1;
}
