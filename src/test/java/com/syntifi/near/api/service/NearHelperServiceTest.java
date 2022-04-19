package com.syntifi.near.api.service;

import com.syntifi.near.api.model.helper.NearValue;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;

import java.io.IOException;

import static com.syntifi.near.api.service.NearHelperServiceHelper.nearHelperService;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Basic Service call testing
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public class NearHelperServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NearHelperServiceTest.class);

    @Test
    void getNearValue_valid() throws IOException {
        Response<NearValue> value = nearHelperService.getNearValue().execute();

        assertTrue(value.isSuccessful());

        NearValue nearValue = value.body();

        LOGGER.debug("Response {}", nearValue.getUsDollars());
        LOGGER.debug("Response {}", nearValue.getEuros());
        LOGGER.debug("Response {}", nearValue.getChineseYuan());
        LOGGER.debug("Response {}", nearValue.getLastUpdatedAt());

        assertNotNull(nearValue);
    }
}