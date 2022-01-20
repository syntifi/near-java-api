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
public class Peer {
    @JsonProperty("id")
    private String id;

    @JsonProperty("addr")
    private String addr;

    @JsonProperty("account_id")
    private String accountId;
}
