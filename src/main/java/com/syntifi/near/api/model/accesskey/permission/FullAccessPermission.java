package com.syntifi.near.api.model.accesskey.permission;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public enum FullAccessPermission implements Permission {
    @JsonProperty("FullAccess")
    @JsonUnwrapped
    FULL_ACCESS
}
