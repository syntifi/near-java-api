package com.syntifi.near.api.model.block;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.syntifi.near.api.model.common.EncodedHash;
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
public class BlockChanges {
    @JsonProperty("block_hash")
    private EncodedHash hash;

    @JsonProperty("changes")
    private Collection<BlockChange> changes;
}
