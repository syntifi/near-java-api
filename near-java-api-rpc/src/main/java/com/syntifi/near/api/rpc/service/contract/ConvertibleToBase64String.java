package com.syntifi.near.api.rpc.service.contract;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.syntifi.near.api.common.model.common.Base64String;
import com.syntifi.near.api.common.service.NearObjectMapper;

/**
 * Adds support to convert the object to a Json as Base64String object
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public interface ConvertibleToBase64String {
    /**
     * Creates a Base64String with the Json string of the object
     *
     * @return the Base64String
     * @throws JsonProcessingException thrown if it can't parse the object to Json
     */
    default Base64String toJsonBase64String() throws JsonProcessingException {
        return Base64String.fromDecodedString(NearObjectMapper.INSTANCE.writeValueAsString(this));
    }
}
