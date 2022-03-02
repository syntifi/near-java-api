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
public class ActionCreationConfig {
    @JsonProperty("create_account_cost")
    private Cost createAccountCost;

    @JsonProperty("deploy_contract_cost")
    private Cost deployContractCost;

    @JsonProperty("deploy_contract_cost_per_byte")
    private Cost deployContractCostPerByte;

    @JsonProperty("function_call_cost")
    private Cost functionCallCost;

    @JsonProperty("function_call_cost_per_byte")
    private Cost functionCallCostPerByte;

    @JsonProperty("transfer_cost")
    private Cost transferCost;

    @JsonProperty("stake_cost")
    private Cost stakeCost;

    @JsonProperty("add_key_cost")
    private AddKeyCost addKeyCost;

    @JsonProperty("delete_key_cost")
    private Cost deleteKeyCost;

    @JsonProperty("delete_account_cost")
    private Cost deleteAccountCost;
}
