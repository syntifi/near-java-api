package com.syntifi.near.api.model.network;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.model.key.PublicKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Fisherman
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
public class Fisherman {
    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("public_key")
    private PublicKey publicKey;

    @JsonProperty("stake")
    private String stake;
}
