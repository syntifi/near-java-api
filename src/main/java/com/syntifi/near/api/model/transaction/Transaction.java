package com.syntifi.near.api.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.model.key.PublicKey;
import com.syntifi.near.api.model.key.Signature;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

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
    private PublicKey publicKey;

    @JsonProperty("nonce")
    private long nonce;

    @JsonProperty("receiver_id")
    private String receiverId;

    @JsonProperty("actions")
    private Collection<Action> actions;

    @JsonProperty("signature")
    private Signature signature;

    @JsonProperty("hash")
    private String hash;
}
