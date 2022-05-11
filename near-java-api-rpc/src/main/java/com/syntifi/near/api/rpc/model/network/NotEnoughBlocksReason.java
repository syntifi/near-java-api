package com.syntifi.near.api.rpc.model.network;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * NotEnoughBlocksReason
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
@JsonTypeName("NotEnoughBlocks")
public class NotEnoughBlocksReason implements Reason {
    @JsonProperty("produced")
    private long produced;

    @JsonProperty("expected")
    private long expected;

}
