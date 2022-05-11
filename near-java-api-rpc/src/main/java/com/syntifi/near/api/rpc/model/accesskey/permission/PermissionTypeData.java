package com.syntifi.near.api.rpc.model.accesskey.permission;

import com.syntifi.near.api.common.exception.NoSuchTypeException;
import lombok.Getter;

/**
 * PermissionTypeData
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
public enum PermissionTypeData {
    FULL_ACCESS("FullAccess", FullAccessPermission.class),
    FUNCTION_CALL("FunctionCall", FunctionCallPermission.class);

    private final String name;
    private final Class<?> clazz;

    PermissionTypeData(String name, Class<?> clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    /**
     * Retrieve Transform implementation class from Transform name
     *
     * @param name the name of the permission type
     * @return the class of given permission type
     * @throws NoSuchTypeException no such type found
     */
    public static Class<?> getClassByName(String name) throws NoSuchTypeException {
        for (PermissionTypeData t : values()) {
            if (t.name.equals(name)) {
                return t.getClazz();
            }
        }
        throw new NoSuchTypeException(String.format("Permission Type %s invalid/not found.", name));
    }
}