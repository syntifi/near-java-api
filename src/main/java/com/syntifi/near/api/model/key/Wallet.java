package com.syntifi.near.api.model.key;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Wallet {
    @JsonProperty("account_id")
    private String account;
    @JsonProperty("public_key")
    private String publicKey;
    @JsonProperty("private_key")
    private String privateKey;
}
