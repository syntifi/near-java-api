package com.syntifi.near.api.model.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * AddKeyCost
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
public class AddKeyCost {
    @JsonProperty("full_access_cost")
    private Cost fullAccessCost;

    @JsonProperty("function_call_cost")
    private Cost functionCallCost;

    @JsonProperty("function_call_cost_per_byte")
    private Cost functionCallCostPerByte;
}
