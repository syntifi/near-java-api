package com.syntifi.near.api.model.network;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
public class PrevEpochKickout {
    @Getter
    @Setter
    public class Reason {
        @Getter
        @Setter
        public class NotEnough {
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
