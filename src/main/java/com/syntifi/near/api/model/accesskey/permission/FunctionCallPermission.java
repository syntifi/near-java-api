package com.syntifi.near.api.model.accesskey.permission;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
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
public class FunctionCallPermission implements Permission {
    @JsonProperty("FunctionCall")
    private FunctionCallPermissionData permissionData;
}
