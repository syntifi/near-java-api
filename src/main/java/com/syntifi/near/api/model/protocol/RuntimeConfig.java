package com.syntifi.near.api.model.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * RuntimeConfig
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
public class RuntimeConfig {
    @JsonProperty("storage_amount_per_byte")
    private String storageAmountPerByte;

    @JsonProperty("transaction_costs")
    private TransactionCosts transactionCosts;

    @JsonProperty("wasm_config")
    private WasmConfig wasmConfig;

    @JsonProperty("account_creation_config")
    private AccountCreationConfig accountCreationConfig;
}
