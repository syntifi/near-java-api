package com.syntifi.near.api.helper.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.helper.model.NearValue;

import java.io.IOException;

/**
 * Custom serializer for near fiat value
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public class NearValueDeserializer extends JsonDeserializer<NearValue> {
    public NearValue deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        node = node.get("near");

        return NearValue.builder()
                .usDollars(node.get("usd").floatValue())
                .euros(node.get("eur").floatValue())
                .chineseYuan(node.get("cny").floatValue())
                .lastUpdatedAt(node.get("last_updated_at").asLong())
                .build();
    }
}