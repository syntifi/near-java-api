package com.syntifi.near.api.rpc.model.accesskey.permission;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * FunctionCallPermission
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
public class FunctionCallPermission implements Permission {
    @JsonProperty("FunctionCall")
    private FunctionCallPermissionData permissionData;
}
