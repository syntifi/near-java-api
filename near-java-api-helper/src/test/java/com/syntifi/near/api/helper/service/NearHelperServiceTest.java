package com.syntifi.near.api.helper.service;

import com.syntifi.near.api.helper.model.NearValue;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static com.syntifi.near.api.helper.service.NearHelperServiceHelper.nearHelperService;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

        assertNotNull(nearValue);

        LOGGER.debug("Response {}", nearValue.getUsDollars());
        LOGGER.debug("Response {}", nearValue.getEuros());
        LOGGER.debug("Response {}", nearValue.getChineseYuan());
        LOGGER.debug("Response {}", nearValue.getLastUpdatedAt());

        assertNotNull(nearValue);
    }

    @Test
    void getNearAccounts_valid() throws IOException {
        Response<List<String>> value = nearHelperService.getNearAccounts("ed25519:F8jARHGZdHqnwrxrnv1pFVzzirXZR2vJzeYbvwQbxZyP").execute();

        assertTrue(value.isSuccessful());

        List<String> nearAccounts = value.body();

        assertNotNull(nearAccounts);

        assertEquals(1, nearAccounts.size());

        assertEquals("syntifi-alice.testnet", nearAccounts.get(0));
    }
}