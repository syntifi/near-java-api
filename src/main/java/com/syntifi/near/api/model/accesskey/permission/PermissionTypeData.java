package com.syntifi.near.api.model.accesskey.permission;

import com.syntifi.near.api.exception.NoSuchTypeException;

import lombok.Getter;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
public enum PermissionTypeData {
    FULL_ACCESS("FullAccess", NoDataPermission.class),
    FUNCTION_CALL("FunctionCall", FunctionCallPermission.class);

    private final String name;
    private final Class<?> clazz;

    private PermissionTypeData(String name, Class<?> clazz) {
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
        throw new NoSuchTypeException();
    }

    /**
     * Retrieve Transform implementation class from Transform name
     * 
     * @param clazz the type class
     * @return the type name of given type class
     * @throws NoSuchTypeException no such type found
     */
    public static String getNameByClass(Class<?> clazz) throws NoSuchTypeException {
        for (PermissionTypeData t : values()) {
            if (t.clazz.equals(clazz)) {
                return t.getName();
            }
        }
        throw new NoSuchTypeException();
    }
}