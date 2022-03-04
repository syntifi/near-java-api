package com.syntifi.near.api.service.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.googlecode.jsonrpc4j.ExceptionResolver;
import com.syntifi.near.api.service.NearObjectMapper;

/**
 * Custom exception handler for jsonrpc4j client
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public class NearServiceExceptionResolver implements ExceptionResolver {
    private static final ObjectMapper objectMapper = new NearObjectMapper();

    @Override
    public Throwable resolveException(ObjectNode response) {
        try {
            JsonNode errorNode = response.get("error");
            NearServiceErrorData error = objectMapper.treeToValue(errorNode, NearServiceErrorData.class);
            return new NearServiceException(error);
        } catch (JsonProcessingException | IllegalArgumentException e) {
            return new NearServiceException(String.format("Could not extract error, response was: %s", response), e);
        }
    }
}
