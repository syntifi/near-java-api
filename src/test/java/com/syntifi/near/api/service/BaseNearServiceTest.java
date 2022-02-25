package com.syntifi.near.api.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Basic support functionality for test case classes
 * 
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public abstract class BaseNearServiceTest {
    protected static final ObjectMapper OBJECT_MAPPER = new NearObjectMapper();

    /**
     * Loads test json from resources
     * 
     * @param filename filename to load
     * @return file content
     * @throws IOException thrown if error reading file
     */
    protected String loadJsonFromFile(String filename) throws IOException {
        String fileJson;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(filename)) {
            // copy stream
            byte[] buffer = new byte[1024];
            int bytesRead;
            while (true) {
                assert is != null;
                if ((bytesRead = is.read(buffer)) == -1) break;
                baos.write(buffer, 0, bytesRead);
            }

            fileJson = baos.toString();
        }
        return fileJson;
    }

    /**
     * Prettifies json for assertion consistency
     * 
     * @param json json string to prettify
     * @return prettified json
     * @throws JsonMappingException thrown if error mapping json
     * @throws JsonProcessingException thrown if error processing json
     */
    protected String getPrettyJson(String json) throws JsonMappingException, JsonProcessingException {
        Object jsonObject = OBJECT_MAPPER.readValue(json, Object.class);
        return getPrettyJson(jsonObject);
    }

    /**
     * Prettifies json for assertion consistency
     * 
     * @param jsonObject object to serialize and prettify
     * @return prettified json
     * @throws JsonMappingException thrown if error mapping json
     * @throws JsonProcessingException thrown if error processing json
     */
    protected String getPrettyJson(Object jsonObject) throws JsonProcessingException {
        return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
    }
}
