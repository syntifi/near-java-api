package com.syntifi.near.api.model.key;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.syntifi.crypto.key.encdec.Base58;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
public class Signature extends KeySig {
    public Signature(KeyType keyType, byte[] data) {
        super(keyType, data);
    }

    @JsonCreator
    public static Signature getPublicKeyFromJson(String base58String) {
        return Signature.fromEncodedBase58String(base58String, Signature.class);
    }

    @JsonValue
    public String getJsonPublicKey() {
        return this.toEncodedBase58String();
    }
}
