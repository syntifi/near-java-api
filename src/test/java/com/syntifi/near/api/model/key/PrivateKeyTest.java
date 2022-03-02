package com.syntifi.near.api.model.key;

import com.syntifi.near.api.exception.NoSuchTypeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrivateKeyTest extends AbstractKeyTest {

    @Test
    void getPrivateKey_with_invalidKeyType_should_throw_NoSuchTypeException() {
        PrivateKey key = new PrivateKey();
        assertThrows(NoSuchTypeException.class, () -> key.getPrivateKey());
    }

    @Test
    void loadWalletFromString_and_loadAPrivateKey() {
        assertEquals(KeyType.ED25519, aliceNearPrivateKey.getKeyType());

        assertDoesNotThrow(aliceNearPrivateKey::getPrivateKey);
    }
}
