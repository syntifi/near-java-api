package com.syntifi.near.api.rpc.model.network;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Reason Interface
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes({@JsonSubTypes.Type(value = NotEnoughBlocksReason.class, name = "NotEnoughBlocks"),
        @JsonSubTypes.Type(value = NotEnoughChunksReason.class, name = "NotEnoughChunks")})
public interface Reason {
}
