package com.syntifi.near.api.rpc.model.network;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * PrevEpochKickout
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
public class PrevEpochKickout {
    @Getter
    @Setter
    public static class Reason {
        @Getter
        @Setter
        public static class NotEnough {
            private long produced;
            private long expected;
        }

        @JsonInclude(value = Include.NON_EMPTY)
        @JsonProperty("NotEnoughBlocks")
        private NotEnough notEnoughBlocks;

        @JsonInclude(value = Include.NON_EMPTY)
        @JsonProperty("NotEnoughChunks")
        private NotEnough notEnoughChunks;
    }

    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("reason")
    private Reason reason;
}
