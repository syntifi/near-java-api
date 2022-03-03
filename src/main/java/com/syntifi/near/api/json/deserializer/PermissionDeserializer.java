package com.syntifi.near.api.json.deserializer;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.AsPropertyTypeDeserializer;
import com.syntifi.near.api.exception.NoSuchTypeException;
import com.syntifi.near.api.model.accesskey.permission.PermissionTypeData;

/**
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
