package com.syntifi.near.api.model.accesskey;

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
public class AccessKeyChanges {
    @JsonProperty("block_hash")
    private String blockHash;

    @JsonProperty("changes")
    private Collection<AccessKeyChange> changes;
}
