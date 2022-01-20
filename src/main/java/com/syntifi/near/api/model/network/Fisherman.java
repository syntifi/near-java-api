package com.syntifi.near.api.model.network;

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
public class Fisherman {
    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("public_key")
    private String publicKey;

    @JsonProperty("stake")
    private String stake;
}
