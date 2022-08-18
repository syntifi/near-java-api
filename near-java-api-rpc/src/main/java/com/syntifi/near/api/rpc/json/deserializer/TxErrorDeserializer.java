package com.syntifi.near.api.rpc.json.deserializer;

import com.syntifi.near.api.rpc.model.transaction.error.TxExecutionError;

/**
 * Serializer to handle the polymorphic terms of type TxExecutionError
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.3.0
 */
public class TxErrorDeserializer extends SubTypesDeserializer<TxExecutionError> {
}

