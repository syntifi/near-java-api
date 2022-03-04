package com.syntifi.near.api.model.key;

import com.syntifi.near.api.exception.NoSuchTypeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class KeySigTest {
    @Test
    void fromEncodedBase58String_with_invalid_keyType_should_throw_NoSuchTypeException() {
        assertThrows(NoSuchTypeException.class, () -> KeySig.fromEncodedBase58String("ASDF", KeySig.class));
    }
    @Test
    void fromEncodedBase58String_with_invalid_class_should_throw_NoSuchTypeException() {
        class KeySigNoConstructor extends KeySig {}
        assertThrows(NoSuchTypeException.class, () -> KeySig.fromEncodedBase58String("ed25519:F8jARHGZdHqnwrxrnv1pFVzzirXZR2vJzeYbvwQbxZyP", KeySigNoConstructor.class));
    }
}
