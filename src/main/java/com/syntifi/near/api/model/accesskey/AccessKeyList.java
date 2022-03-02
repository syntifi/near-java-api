package com.syntifi.near.api.model.accesskey;

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
public class AccessKeyList {
    @JsonProperty("keys")
    private Collection<AccessKeyListItem> items;

    @JsonProperty("block_height")
    private long blockHeight;

    @JsonProperty("block_hash")
    private String blockHash;
}
