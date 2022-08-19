package com.syntifi.near.api.rpc.json.deserializer;

import com.syntifi.near.api.rpc.model.transaction.error.tx.InvalidAccessKeyError;


/**
 * Serializer to handle the polymorphic terms of type InvalidAccessKeyError
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.3.0
 */
public class InvalidAccessKeyErrorDeserializer extends SubTypesDeserializer<InvalidAccessKeyError> {
    public InvalidAccessKeyErrorDeserializer() {
        super(InvalidAccessKeyError.class);
    }

}

