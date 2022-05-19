package com.syntifi.near.api.helper.service;

import com.syntifi.near.api.helper.model.RecentActivity;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;

import java.io.IOException;

import static com.syntifi.near.api.helper.service.NearKitWalletServiceHelper.nearKitWalletService;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Basic Service call testing
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public class NearKitWalletServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NearKitWalletServiceTest.class);

    @Test
    void getNearRecentActivity_valid() throws IOException {
        Response<RecentActivity> value = nearKitWalletService.getNearRecentActivity("wallet-test.testnet").execute();

        assertTrue(value.isSuccessful());

        RecentActivity nearValue = value.body();

        assertNotNull(nearValue);

        LOGGER.debug("Response {}", nearValue.size());

        assertNotNull(nearValue);
    }
}