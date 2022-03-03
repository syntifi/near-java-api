package com.syntifi.near.api.model.key;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.syntifi.crypto.key.AbstractPublicKey;
import com.syntifi.crypto.key.Ed25519PublicKey;
import com.syntifi.near.api.exception.NoSuchTypeException;
import com.syntifi.near.borshj.Borsh;
import lombok.Builder;
import lombok.EqualsAndHashCode;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@EqualsAndHashCode(callSuper = true)
public class PublicKey extends KeySig implements Borsh {

    private static final int KEY_SIZE = 32;

    public PublicKey() {
        // This solves the case for borsh deserialization for keys of
        // type ED25591 because to read the 'fixed' byte array we must know
        // its size.
        // If any other key (and signature) is implemented, a different
        // approach is needed (like getters and setters annotation on borsh)
        this.data = new byte[KEY_SIZE];
    }

    @Builder
    public PublicKey(KeyType keyType, byte[] data) {
        super(keyType, data);
    }

    public AbstractPublicKey getPublicKey() {
        if (keyType == KeyType.ED25519) {
            return new Ed25519PublicKey(data);
        }
        throw new NoSuchTypeException(String.format("No implementation found for key type %s", keyType));
    }

    @JsonCreator
    public static PublicKey getPublicKeyFromJson(String base58String) {
        return PublicKey.fromEncodedBase58String(base58String, PublicKey.class);
    }

    @JsonValue
    public String getJsonPublicKey() {
        return this.toEncodedBase58String();
    }
}
