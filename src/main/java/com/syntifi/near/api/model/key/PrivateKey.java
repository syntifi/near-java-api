package com.syntifi.near.api.model.key;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.syntifi.crypto.key.AbstractPrivateKey;
import com.syntifi.crypto.key.Ed25519PrivateKey;
import com.syntifi.near.api.exception.NoSuchTypeException;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public class PrivateKey extends KeySig {

    private static final int KEY_SIZE = 32;

    public PrivateKey() {
        // This solves the case for borsh deserialization for keys of
        // type ED25591 because to read the 'fixed' byte array we must know
        // its size.
        // If any other key (and signature) is implemented, a different
        // approach is needed (like getters and setters annotation on borsh)
        this.data = new byte[KEY_SIZE];
    }

    @Builder
    public PrivateKey(KeyType keyType, byte[] data) {
        super(keyType, data);
    }

    public AbstractPrivateKey getPrivateKey() {
        if (keyType == KeyType.ED25519) {
            return new Ed25519PrivateKey(data);
        }
        throw new NoSuchTypeException(String.format("No implementation found for key type %s", keyType));
    }

    @JsonCreator
    public static PrivateKey getPublicKeyFromJson(String base58String) {
        return PrivateKey.fromEncodedBase58String(base58String, PrivateKey.class);
    }

    @JsonValue
    public String getJsonPublicKey() {
        return this.toEncodedBase58String();
    }
}
