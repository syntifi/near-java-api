package com.syntifi.near.api.common.model.key;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * Simple structure class mainly to hold information read from json wallets.
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
public class Wallet {
    @JsonProperty("account_id")
    private String account;
    @JsonProperty("public_key")
    private PublicKey publicKey;
    @JsonProperty("private_key")
    private PrivateKey privateKey;
}
