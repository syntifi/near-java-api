package com.syntifi.near.api.model.protocol;

import java.util.Collection;

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
public class TransactionCosts {

    @JsonProperty("action_receipt_creation_config")
    private Cost actionReceiptCreationConfig;

    @JsonProperty("data_receipt_creation_config")
    private DataReceiptCreationConfig dataReceiptCreationConfig;

    @JsonProperty("action_creation_config")
    private ActionCreationConfig actionCreationConfig;

    @JsonProperty("storage_usage_config")
    private StorageUsageConfig storageUsageConfig;

    @JsonProperty("burnt_gas_reward")
    private Collection<Long> burntGasReward;

    @JsonProperty("pessimistic_gas_price_inflation_ratio")
    private Collection<Long> pessimisticGasPriceInflationRatio;
}
