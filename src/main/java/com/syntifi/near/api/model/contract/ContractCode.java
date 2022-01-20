package com.syntifi.near.api.model.contract;

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
public class ContractCode {
    @JsonProperty("code_base64")
    private String codeBase64;

    @JsonProperty("hash")
    private String hash;

    @JsonProperty("block_height")
    private long blockHeight;

    @JsonProperty("block_hash")
    private String blockHash;
}
