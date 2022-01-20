package com.syntifi.near.api.model.accesskey;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.model.accesskey.permission.Permission;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
public class AccessKey {
    @JsonProperty("nonce")
    private long nonce;

    @JsonProperty("permission")
    private Permission permission;

    @JsonProperty("block_height")
    @JsonInclude(value = Include.NON_EMPTY)
    private Long blockHeight;

    @JsonProperty("block_hash")
    @JsonInclude(value = Include.NON_EMPTY)
    private String blockHash;
}
