package com.syntifi.near.api.model.key;

import lombok.Getter;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public enum KeyType {
    ED25519((byte) 0);

    @Getter
    private final byte keyTypeValue;

    KeyType(byte keyTypeValue) {
        this.keyTypeValue = keyTypeValue;
    }
}
