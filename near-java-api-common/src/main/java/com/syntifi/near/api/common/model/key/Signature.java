package com.syntifi.near.api.common.model.key;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.syntifi.near.borshj.Borsh;
import lombok.Builder;
import lombok.EqualsAndHashCode;

/**
 * Holds a Near Signature
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@EqualsAndHashCode(callSuper = true)
public class Signature extends KeySig implements Borsh {

    private static final int SIGNATURE_SIZE = 64;

    public Signature() {
        // This solves the case for borsh deserialization for signatures of
        // type ED25591 because to read the 'fixed' byte array we must know
        // its size.
        // If any other signature (and key) is implemented, a different
        // approach is needed (like getters and setters annotation on borsh)
        this.data = new byte[SIGNATURE_SIZE];
    }

    @Builder
    public Signature(KeyType keyType, byte[] data) {
        super(keyType, data);
    }

    @JsonCreator
    public static Signature getSignatureFromJson(String base58String) {
        return fromEncodedBase58String(base58String, Signature.class);
    }

    @JsonValue
    public String getJsonSignature() {
        return this.toEncodedBase58String();
    }
}
