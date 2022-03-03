package com.syntifi.near.api.model.key;

import com.syntifi.near.api.exception.NoSuchTypeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PublicKeyTest extends AbstractKeyTest {

    @Test
    void getPublicKey_with_invalidKeyType_should_throw_NoSuchTypeException() {
        PublicKey key = new PublicKey();
        assertThrows(NoSuchTypeException.class, key::getPublicKey);
    }

    @Test
    void loadWalletFromString_and_loadAPublicKey() {
        assertEquals(KeyType.ED25519, aliceNearPublicKey.getType());

        assertDoesNotThrow(aliceNearPublicKey::getPublicKey);
    }
}
