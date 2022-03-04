package com.syntifi.near.api.model.key;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;

import static com.syntifi.near.api.json.JsonHelper.OBJECT_MAPPER;
import static com.syntifi.near.api.json.JsonHelper.getPrettyJson;
import static com.syntifi.near.api.json.JsonHelper.loadJsonFromResourceFile;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class WalletTest {
    @Test
    void loadWallet_should_load_ok() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile("testnet-wallets/alice.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, Wallet.class));

        Wallet wallet = OBJECT_MAPPER.readValue(inputJson, Wallet.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(wallet), false);
    }
}
