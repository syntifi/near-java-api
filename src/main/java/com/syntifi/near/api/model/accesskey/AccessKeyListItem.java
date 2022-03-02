package com.syntifi.near.api.model.accesskey;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.model.key.PublicKey;
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
public class AccessKeyListItem {
    @JsonProperty("public_key")
    private PublicKey publicKey;

    @JsonProperty("access_key")
    private AccessKey accessKey;
}
