package com.syntifi.near.api.model.key;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
public class Signature {
    private KeyType keyType;
    private byte[] data; // 64 bytes

    public Signature() {
    }
}
