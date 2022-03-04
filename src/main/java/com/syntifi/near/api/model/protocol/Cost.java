package com.syntifi.near.api.model.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Cost
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
public class Cost {
    @JsonProperty("send_sir")
    private long sendSir;

    @JsonProperty("send_not_sir")
    private long sendNotSir;

    @JsonProperty("execution")
    private long execution;
}
