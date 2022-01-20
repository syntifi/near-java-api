package com.syntifi.near.api.model.accesskey;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data structure for access key service calls
 * 
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Key {
    @JsonProperty("account_id")
    private String accountId;


    @JsonProperty("public_key")
    private String publicKey;
}
