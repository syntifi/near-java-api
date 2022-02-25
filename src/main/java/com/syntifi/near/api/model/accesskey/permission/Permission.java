package com.syntifi.near.api.model.accesskey.permission;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;
import com.syntifi.near.api.jackson.resolver.PermissionResolver;
import com.syntifi.near.borshj.annotation.BorshSubTypes;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
@JsonTypeResolver(PermissionResolver.class)
@BorshSubTypes({
        @BorshSubTypes.BorshSubType(when = Permission.FUNCTION_CALL_PERMISSION, use = FunctionCallPermission.class),
        @BorshSubTypes.BorshSubType(when = Permission.FULL_ACCESS_PERMISSION, use = FullAccessPermission.class)})
public interface Permission {
    int FUNCTION_CALL_PERMISSION = 0;
    int FULL_ACCESS_PERMISSION = 1;
}
