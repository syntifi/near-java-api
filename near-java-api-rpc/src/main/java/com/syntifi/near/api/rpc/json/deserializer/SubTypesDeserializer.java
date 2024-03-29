package com.syntifi.near.api.rpc.json.deserializer;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.syntifi.near.api.rpc.model.transaction.error.tx.InvalidTxError;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Serializer to handle the polymorphic terms with subtype annotations
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.3.0
 */
public class SubTypesDeserializer<T> extends JsonDeserializer<T> {
    private final Map<String, Class<?>> propertyNameToType;

    public SubTypesDeserializer(Class<T> typeParameterClass)  {
        this.propertyNameToType = Arrays.stream(typeParameterClass.getAnnotation(JsonSubTypes.class).value())
                .collect(Collectors.toMap(JsonSubTypes.Type::name, JsonSubTypes.Type::value,
                        (a, b) -> a, HashMap::new));
    }

    @Override
    @SuppressWarnings("unchecked")
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectMapper objectMapper = (ObjectMapper) jsonParser.getCodec();
        ObjectNode object = objectMapper.readTree(jsonParser);
        for (Iterator<String> it = object.fieldNames(); it.hasNext(); ) {
            String field = it.next();
            if (propertyNameToType.containsKey(field)) {
                return (T) objectMapper.treeToValue(object, propertyNameToType.get(field));
            }
        }
        throw new IllegalArgumentException("could not infer to which class to deserialize " + object);
    }

}

