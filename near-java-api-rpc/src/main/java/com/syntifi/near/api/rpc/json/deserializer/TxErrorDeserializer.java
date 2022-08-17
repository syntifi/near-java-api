package com.syntifi.near.api.rpc.json.deserializer;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.syntifi.near.api.rpc.model.transaction.error.TxExecutionError;
import com.syntifi.near.api.rpc.model.transaction.error.tx.InvalidTxError;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

public class TxErrorDeserializer extends JsonDeserializer<TxExecutionError> {
    private final Map<String, Class<?>> propertyNameToType;

    public TxErrorDeserializer() {
        this.propertyNameToType = Arrays.stream(InvalidTxError.class.getAnnotation(JsonSubTypes.class).value())
                .collect(Collectors.toMap(JsonSubTypes.Type::name, JsonSubTypes.Type::value,
                        (a, b) -> a, HashMap::new));
    }

    @Override
    public TxExecutionError deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectMapper objectMapper = (ObjectMapper) jsonParser.getCodec();
        ObjectNode object = objectMapper.readTree(jsonParser);
        for (Iterator<String> it = object.fieldNames(); it.hasNext(); ) {
            String field = it.next();
            if (propertyNameToType.containsKey(field)) {
                return (TxExecutionError) objectMapper.treeToValue(object, propertyNameToType.get(field));
            }
        }
        throw new IllegalArgumentException("could not infer to which class to deserialize " + object);
    }

}

