package com.syntifi.near.api.rpc.model.accesskey;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.common.model.common.EncodedHash;
import com.syntifi.near.api.rpc.model.accesskey.permission.Permission;
import com.syntifi.near.borshj.Borsh;
import com.syntifi.near.borshj.annotation.BorshField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * AccessKey
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessKey implements Borsh {
    @BorshField(order = 1)
    @JsonProperty("nonce")
    private long nonce;

    @BorshField(order = 2)
    @JsonProperty("permission")
    private Permission permission;

    @JsonProperty("block_height")
    @JsonInclude(value = Include.NON_EMPTY)
    private Long blockHeight;

    @JsonProperty("block_hash")
    @JsonInclude(value = Include.NON_EMPTY)
    private EncodedHash blockHash;
}
