package com.syntifi.near.api.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.syntifi.near.api.model.common.EncodedHash;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Proof
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
public class Proof {
    public enum Direction {
        RIGHT("Right"),
        LEFT("Left");

        @Getter
        @JsonValue
        private final String name;

        Direction(String name) {
            this.name = name;
        }
    }

    @JsonProperty("hash")
    private EncodedHash hash;

    @JsonProperty("direction")
    private Direction direction;
}
