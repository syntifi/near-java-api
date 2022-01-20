package com.syntifi.near.api.model.contract;

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
public class ContractFunctionCallResult {
    @JsonProperty("result")
    private int[] result;

    @JsonProperty("logs")
    private Collection<String> logs;

    @JsonProperty("block_height")
    private long blockHeight;

    @JsonProperty("block_hash")
    private String blockHash;
}
