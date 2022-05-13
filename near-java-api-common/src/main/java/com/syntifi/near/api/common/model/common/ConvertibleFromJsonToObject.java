package com.syntifi.near.api.common.model.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.syntifi.near.api.common.service.NearObjectMapper;

import java.io.IOException;

public interface ConvertibleFromJsonToObject {
    @JsonIgnore
    byte[] getJson();

    default <T> T toResultObject(Class<T> clazz) throws IOException {
        return NearObjectMapper.INSTANCE.readValue(getJson(), clazz);
    }
}
