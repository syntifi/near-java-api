package com.syntifi.near.api.model.network;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * Reason Interface
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@JsonTypeInfo(use = Id.NAME, include = As.WRAPPER_OBJECT)
@JsonSubTypes({@JsonSubTypes.Type(value = NotEnoughBlocksReason.class, name = "NotEnoughBlocks"),
        @JsonSubTypes.Type(value = NotEnoughChunksReason.class, name = "NotEnoughChunks")})
public interface Reason {
}
