package com.syntifi.near.api.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

/**
 * Block
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
public class Block {
    @JsonProperty("author")
    private String author;

    @JsonProperty("header")
    private BlockHeader header;

    @JsonProperty("chunks")
    private Collection<ChunkHeader> chunks;
}
