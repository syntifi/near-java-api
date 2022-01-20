package com.syntifi.near.api.model.accesskey.permission;

import java.util.Collection;

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
public class FunctionCallPermissionData {
    @JsonProperty("allowance")
    private String allowance;

    @JsonProperty("receiver_id")
    private String receiverId;

    @JsonProperty("method_names")
    private Collection<String> methodNames;
}
