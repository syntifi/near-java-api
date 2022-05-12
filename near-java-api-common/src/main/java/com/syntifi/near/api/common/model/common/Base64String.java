package com.syntifi.near.api.common.model.common;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Base64;

/**
 * Base64String
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@NoArgsConstructor
public class Base64String {
    @JsonValue
    private String encodedString;

    public Base64String(String value) {
        setEncodedString(value);
    }

    public String getDecodedString() {
        return Arrays.toString(Base64.getDecoder().decode(this.encodedString));
    }

    public String getEncodedString() {
        return encodedString;
    }

    public void setEncodedString(String decodedString) {
        this.encodedString = Base64.getEncoder().encodeToString(decodedString.getBytes());
    }
}
