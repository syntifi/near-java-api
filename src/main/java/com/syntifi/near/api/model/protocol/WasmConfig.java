package com.syntifi.near.api.model.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WasmConfig {
    @JsonProperty("ext_costs")
    private ExtCosts extCosts;

    @JsonProperty("grow_mem_cost")
    private long growMemCost;

    @JsonProperty("regular_op_cost")
    private long regularOpCost;

    @JsonProperty("limit_config")
    private LimitConfig limitConfig;
}
