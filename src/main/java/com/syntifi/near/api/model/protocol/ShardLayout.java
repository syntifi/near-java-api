package com.syntifi.near.api.model.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ShardLayout
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
public class ShardLayout {
    @Getter
    @Setter
    public static class V0 {
        @JsonProperty("num_shards")
        private long numShards;

        @JsonProperty("version")
        private long version;
    }

    @JsonProperty("V0")
    private V0 v0;
}
