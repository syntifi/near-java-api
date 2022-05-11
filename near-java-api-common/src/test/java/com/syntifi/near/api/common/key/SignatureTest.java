package com.syntifi.near.api.common.key;

import org.junit.jupiter.api.Test;

import java.security.GeneralSecurityException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignatureTest extends AbstractKeyTest {
    @Test
    void loadPrivateAndPublicKey_matchesDerivedPublicKey() {
        assertArrayEquals(alicePublicKey.getKey(), alicePrivateKey.derivePublicKey().getKey());
        assertArrayEquals(bobPublicKey.getKey(), bobPrivateKey.derivePublicKey().getKey());
    }

    @Test
    void loadPrivateAndPublicKey_Sign_and_Verify_should_be_valid() throws GeneralSecurityException {
        byte[] aliceMessage = "Hello, Bob!".getBytes();
        byte[] aliceSignature = alicePrivateKey.sign(aliceMessage);

        assertTrue(alicePublicKey.verify(aliceMessage, aliceSignature));

        byte[] bobMessage = "Hello, Alice!".getBytes();
        byte[] bobSignature = bobPrivateKey.sign(bobMessage);

        assertTrue(bobPublicKey.verify(bobMessage, bobSignature));
    }
}
