package com.syntifi.near.api.model.account;

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
public class Account {
    @JsonProperty("amount")
    private String amount;

    @JsonProperty("locked")
    private String locked;

    @JsonProperty("code_hash")
    private String codeHash;

    @JsonProperty("storage_usage")
    private long storageUsage;

    @JsonProperty("storage_paid_at")
    private long storagePaidAt;

    @JsonProperty("block_height")
    private long blockHeight;

    @JsonProperty("block_hash")
    private String blockHash;
}
