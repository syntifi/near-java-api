package com.syntifi.near.api.rpc.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Custom byte array serializer for handling serialization numerically
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public class ByteArraySerializer extends JsonSerializer<byte[]> {

    @Override
    public void serialize(byte[] bytes, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();

        for (byte b : bytes) {
            jsonGenerator.writeNumber(unsignedToBytes(b));
        }

        jsonGenerator.writeEndArray();
    }

    private static int unsignedToBytes(byte b) {
        return b & 0xFF;
    }

}
