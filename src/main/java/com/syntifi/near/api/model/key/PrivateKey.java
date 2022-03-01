package com.syntifi.near.api.model.key;

import com.syntifi.crypto.key.AbstractPrivateKey;
import com.syntifi.crypto.key.Ed25519PrivateKey;
import com.syntifi.near.api.exception.NoSuchTypeException;
import lombok.NoArgsConstructor;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@NoArgsConstructor
public class PrivateKey extends KeySig {
    public PrivateKey(KeyType keyType, byte[] data) {
        super(keyType, data);
    }

    public AbstractPrivateKey getPrivateKey() {
        if (keyType == KeyType.ED25519) {
            return new Ed25519PrivateKey(data);
        }
        throw new NoSuchTypeException(String.format("No implementation found for key type %s", keyType));
    }
}
