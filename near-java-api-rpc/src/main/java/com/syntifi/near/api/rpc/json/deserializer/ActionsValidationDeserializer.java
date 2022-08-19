package com.syntifi.near.api.rpc.json.deserializer;

import com.syntifi.near.api.rpc.model.transaction.error.tx.ActionsValidation;

public class ActionsValidationDeserializer extends SubTypesDeserializer<ActionsValidation> {
    public ActionsValidationDeserializer() {
        super(ActionsValidation.class);
    }
}

