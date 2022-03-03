package com.syntifi.near.api.model.contract;

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
public class ContractCode {
    @JsonProperty("code_base64")
    private String codeBase64;

    @JsonProperty("hash")
    private EncodedHash hash;

    @JsonProperty("block_height")
    private long blockHeight;

    @JsonProperty("block_hash")
    private EncodedHash blockHash;
}
