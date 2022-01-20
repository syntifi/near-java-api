package com.syntifi.near.api.model.block;

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
public class BlockChange {
    @JsonProperty("type")
    private String type;

    @JsonProperty("account_id")
    private String accountId;
}