package com.syntifi.near.api.model.key;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PublicKeyTest extends AbstractKeyTest {
    @Test
    void loadWalletFromString_and_loadAPublicKey() {
        assertEquals(KeyType.ED25519, aliceNearPublicKey.getKeyType());

        assertDoesNotThrow(aliceNearPublicKey::getPublicKey);
    }
}
