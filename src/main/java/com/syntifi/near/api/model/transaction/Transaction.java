package com.syntifi.near.api.model.transaction;

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
public class Transaction {
    @JsonProperty("signer_id")
    private String signerId;

    @JsonProperty("public_key")
    private String publicKey;

    @JsonProperty("nonce")
    private long nonce;

    @JsonProperty("receiver_id")
    private String receiverId;

    @JsonProperty("actions")
    private Collection<Action> actions;

    @JsonProperty("signature")
    private String signature;

    @JsonProperty("hash")
    private String hash;
}
