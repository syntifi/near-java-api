package com.syntifi.near.api.rpc.model.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DataReceiptCreationConfig
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
public class DataReceiptCreationConfig {
    @JsonProperty("base_cost")
    private Cost baseCost;

    @JsonProperty("cost_per_byte")
    private Cost costPerByte;
}
