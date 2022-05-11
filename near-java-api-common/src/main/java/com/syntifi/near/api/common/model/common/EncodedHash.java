package com.syntifi.near.api.common.model.common;

import com.fasterxml.jackson.annotation.JsonValue;
import com.syntifi.crypto.key.encdec.Base58;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * EncodedHash
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EncodedHash {
    @JsonValue
    private String encodedHash;

    public byte[] getDecodedHash() {
        return Base58.decode(this.encodedHash);
    }

    public void setEncodedHash(byte[] decodedHash) {
        this.encodedHash = Base58.encode(decodedHash);
    }
}
