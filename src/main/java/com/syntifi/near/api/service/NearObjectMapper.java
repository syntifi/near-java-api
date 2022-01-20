package com.syntifi.near.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.syntifi.near.api.model.transaction.SuccessReceiptIdStatus;
import com.syntifi.near.api.model.transaction.SuccessValueStatus;

/**
 * Custom Jackson {@link ObjectMapper} for any customizations
 * 
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public class NearObjectMapper extends ObjectMapper {
    public NearObjectMapper() {
        super();

        coercionConfigFor(SuccessReceiptIdStatus.class)
                .setCoercion(CoercionInputShape.EmptyString, CoercionAction.AsEmpty);
        coercionConfigFor(SuccessValueStatus.class)
                .setCoercion(CoercionInputShape.EmptyString, CoercionAction.AsEmpty);
    }
}
