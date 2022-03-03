package com.syntifi.near.api.model.accesskey;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.model.key.PublicKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Key
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Key {
    @JsonProperty("account_id")
    private String accountId;


    @JsonProperty("public_key")
    private PublicKey publicKey;
}
