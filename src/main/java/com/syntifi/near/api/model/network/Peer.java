package com.syntifi.near.api.model.network;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class Peer {
    @JsonProperty("id")
    private String id;

    @JsonProperty("addr")
    private String addr;

    @JsonProperty("account_id")
    private String accountId;
}
