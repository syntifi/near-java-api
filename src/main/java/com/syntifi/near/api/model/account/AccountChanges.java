package com.syntifi.near.api.model.account;

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
public class AccountChanges {
    @JsonProperty("block_hash")
    private String blockHash;

    @JsonProperty("changes")
    private Collection<AccountChange> changes;
}
