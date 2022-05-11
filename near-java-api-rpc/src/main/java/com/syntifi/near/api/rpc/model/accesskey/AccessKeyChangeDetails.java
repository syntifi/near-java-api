package com.syntifi.near.api.rpc.model.accesskey;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.common.model.key.PublicKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * AccessKeyChangeDetails
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
public class AccessKeyChangeDetails {
    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("public_key")
    private PublicKey publicKey;

    @JsonProperty("access_key")
    private AccessKey accessKey;
}
