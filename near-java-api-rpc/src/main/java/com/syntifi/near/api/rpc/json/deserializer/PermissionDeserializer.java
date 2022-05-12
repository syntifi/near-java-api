package com.syntifi.near.api.rpc.json.deserializer;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.AsPropertyTypeDeserializer;
import com.syntifi.near.api.common.exception.NoSuchTypeException;
import com.syntifi.near.api.rpc.model.accesskey.permission.PermissionTypeData;

/**
 * Specific implementation of AbstractAnyOfDeserializer for Permission type objects
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public class PermissionDeserializer extends AbstractAnyOfDeserializer {

    public PermissionDeserializer(final JavaType bt, final TypeIdResolver idRes, final String typePropertyName,
                                  final boolean typeIdVisible, JavaType defaultImpl) {
        super(bt, idRes, typePropertyName, typeIdVisible, defaultImpl);
    }

    public PermissionDeserializer(final AsPropertyTypeDeserializer src, final BeanProperty property) {
        super(src, property);
    }

    @Override
    public TypeDeserializer forProperty(final BeanProperty prop) {
        return new PermissionDeserializer(this, prop);
    }

    @Override
    protected Class<?> getClassByName(String anyOfType) throws NoSuchTypeException {
        return PermissionTypeData.getClassByName(anyOfType);
    }
}
