package com.syntifi.near.api.model.key;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.syntifi.crypto.key.AbstractPublicKey;
import com.syntifi.crypto.key.Ed25519PublicKey;
import com.syntifi.near.api.exception.NoSuchTypeException;
import lombok.EqualsAndHashCode;
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
public class PublicKey extends KeySig {
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
