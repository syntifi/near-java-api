package com.syntifi.near.api.common.model.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Base64;

/**
 * Base64String
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@Getter
@Builder(access = AccessLevel.PRIVATE)
@ToString
public class Base64String {
    @JsonValue
    private String encodedString;

    @JsonIgnore
    private String decodedString;

    public static Base64String fromDecodedString(String value) {
        return Base64String.builder()
                .decodedString(value)
                .encodedString(Base64.getEncoder().encodeToString(value.getBytes())).build();
    }

    public static Base64String fromEncodedString(String value) {
        return Base64String.builder()
                .decodedString(Arrays.toString(Base64.getDecoder().decode(value)))
                .encodedString(value).build();
    }
}
