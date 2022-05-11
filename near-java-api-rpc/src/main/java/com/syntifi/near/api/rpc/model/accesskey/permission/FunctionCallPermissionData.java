package com.syntifi.near.api.rpc.model.accesskey.permission;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

/**
 * FunctionCallPermissionData
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
public class FunctionCallPermissionData {
    @JsonProperty("allowance")
    private String allowance;

    @JsonProperty("receiver_id")
    private String receiverId;

    @JsonProperty("method_names")
    private Collection<String> methodNames;
}
