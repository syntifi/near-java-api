package com.syntifi.near.api.rpc.jsonrpc4j.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.googlecode.jsonrpc4j.ExceptionResolver;
import com.syntifi.near.api.common.exception.NearErrorData;
import com.syntifi.near.api.common.exception.NearException;
import com.syntifi.near.api.common.service.NearObjectMapper;

/**
 * Custom exception handler for jsonrpc4j client
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public class NearExceptionResolver implements ExceptionResolver {
    private static final ObjectMapper objectMapper = new NearObjectMapper();

    @Override
    public Throwable resolveException(ObjectNode response) {
        try {
            JsonNode errorNode = response.get("error");
            NearErrorData error = objectMapper.treeToValue(errorNode, NearErrorData.class);
            return new NearException(error);
        } catch (JsonProcessingException | IllegalArgumentException e) {
            return new NearException(String.format("Could not extract error, response was: %s", response), e);
        }
    }
}
