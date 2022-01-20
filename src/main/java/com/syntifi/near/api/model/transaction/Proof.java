package com.syntifi.near.api.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
public class Proof {
    public enum Direction {
        RIGHT("Right"),
        LEFT("Left");

        @JsonValue
        private String name;

        private Direction(String name) {
            this.name = name;
        }
    }

    @JsonProperty("hash")
    private String hash;

    @JsonProperty("direction")
    private Direction direction;
}
