package com.syntifi.near.api.model.accesskey;

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
public class AccessKeyListItem {
    @JsonProperty("public_key")
    private String publicKey;

    @JsonProperty("access_key")
    private AccessKey accessKey;
}
