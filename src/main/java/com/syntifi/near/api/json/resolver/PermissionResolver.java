package com.syntifi.near.api.json.resolver;

import java.util.Collection;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.syntifi.near.api.json.deserializer.PermissionDeserializer;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public class PermissionResolver extends StdTypeResolverBuilder {
    @Override
    public TypeDeserializer buildTypeDeserializer(final DeserializationConfig config, final JavaType baseType,
            final Collection<NamedType> subtypes) {
        return new PermissionDeserializer(baseType, null, _typeProperty, _typeIdVisible, baseType);
    }
}