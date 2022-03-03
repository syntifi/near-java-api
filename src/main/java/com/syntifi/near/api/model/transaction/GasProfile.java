package com.syntifi.near.api.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * GasProfile
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
public class GasProfile {
    @JsonProperty("cost_category")
    private String costCategory;

    @JsonProperty("cost")
    private CostType cost;

    @JsonProperty("gas_used")
    private String gasUsed;
}