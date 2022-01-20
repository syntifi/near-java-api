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
public class DataReceiptCreationConfig {
    @JsonProperty("base_cost")
    private Cost baseCost;

    @JsonProperty("cost_per_byte")
    private Cost costPerByte;
}
