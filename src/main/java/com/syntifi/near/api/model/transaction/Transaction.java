package com.syntifi.near.api.model.transaction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.model.common.EncodedHash;
import com.syntifi.near.api.model.key.PublicKey;
import com.syntifi.near.api.model.key.Signature;
import com.syntifi.near.borshj.Borsh;
import com.syntifi.near.borshj.annotation.BorshField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

/**
 * Transaction
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Transaction implements Borsh {
    private final static int BLOCK_HASH_LENGTH = 32;

    @BorshField(order = 1)
    @JsonProperty("signer_id")
    private String signerId;

    @BorshField(order = 2)
    @JsonProperty("public_key")
    private PublicKey publicKey;

    @BorshField(order = 3)
    @JsonProperty("nonce")
    private Long nonce;

    @BorshField(order = 4)
    @JsonProperty("receiver_id")
    private String receiverId;

    @BorshField(order = 6)
    @JsonProperty("actions")
    private Collection<Action> actions;

    @JsonProperty("signature")
    private Signature signature;

    @BorshField(order = 5)
    @JsonIgnore
    private byte[] blockHash = new byte[BLOCK_HASH_LENGTH];

    @JsonProperty("hash")
    private EncodedHash hash;
}
