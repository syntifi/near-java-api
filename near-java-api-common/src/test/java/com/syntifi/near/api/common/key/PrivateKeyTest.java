package com.syntifi.near.api.common.key;

import com.syntifi.near.api.common.exception.NoSuchTypeException;
import com.syntifi.near.api.common.model.key.KeyType;
import com.syntifi.near.api.common.model.key.PrivateKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrivateKeyTest extends AbstractKeyTest {

    @Test
    void getPrivateKey_with_invalidKeyType_should_throw_NoSuchTypeException() {
        PrivateKey key = new PrivateKey();
        assertThrows(NoSuchTypeException.class, key::getPrivateKey);
    }

    @Test
    void loadWalletFromString_and_loadAPrivateKey() {
        assertEquals(KeyType.ED25519, aliceNearPrivateKey.getType());

        assertDoesNotThrow(aliceNearPrivateKey::getPrivateKey);
    }
}
