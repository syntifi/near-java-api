package com.syntifi.near.api.rpc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.syntifi.near.api.common.service.NearObjectMapper;
import com.syntifi.near.api.rpc.model.transaction.SuccessReceiptIdStatus;
import com.syntifi.near.api.rpc.model.transaction.SuccessValueStatus;

/**
 * Custom Jackson {@link ObjectMapper} for any customizations
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public class NearRpcObjectMapper extends NearObjectMapper {
    public static final ObjectMapper INSTANCE = new NearObjectMapper();

    private NearRpcObjectMapper() {
        super();

        coercionConfigFor(SuccessReceiptIdStatus.class)
                .setCoercion(CoercionInputShape.EmptyString, CoercionAction.AsEmpty);
        coercionConfigFor(SuccessValueStatus.class)
                .setCoercion(CoercionInputShape.EmptyString, CoercionAction.AsEmpty);
    }
}
