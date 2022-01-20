package com.syntifi.near.api.model.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
public class Cost {
    @JsonProperty("send_sir")
    private long sendSir;

    @JsonProperty("send_not_sir")
    private long sendNotSir;

    @JsonProperty("execution")
    private long execution;
}
