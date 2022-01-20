package com.syntifi.near.api.model.block;

import java.util.Collection;

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
public class BlockChanges {
    @JsonProperty("block_hash")
    private String hash;

    @JsonProperty("changes")
    private Collection<BlockChange> changes;
}
