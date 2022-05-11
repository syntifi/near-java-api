package com.syntifi.near.api.rpc.model.protocol;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * LimitConfig
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
public class LimitConfig {
    @JsonProperty("max_gas_burnt")
    private long maxGasBurnt;

    @JsonProperty("max_gas_burnt_view")
    @JsonInclude(value = Include.NON_EMPTY)
    private Long maxGasBurntView;

    @JsonProperty("max_stack_height")
    private long maxStackHeight;

    @JsonProperty("stack_limiter_version")
    private long stackLimiterVersion;

    @JsonProperty("initial_memory_pages")
    private long initialMemoryPages;

    @JsonProperty("max_memory_pages")
    private long maxMemoryPages;

    @JsonProperty("registers_memory_limit")
    private long registersMemoryLimit;

    @JsonProperty("max_register_size")
    private long maxRegisterSize;

    @JsonProperty("max_number_registers")
    private long maxNumberRegisters;

    @JsonProperty("max_number_logs")
    private long maxNumberLogs;

    @JsonProperty("max_total_log_length")
    private long maxTotalLogLength;

    @JsonProperty("max_total_prepaid_gas")
    private long maxTotalPrepaidGas;

    @JsonProperty("max_actions_per_receipt")
    private long maxActionsPerReceipt;

    @JsonProperty("max_number_bytes_method_names")
    private long maxNumberBytesMethodNames;

    @JsonProperty("max_length_method_name")
    private long maxLengthMethodName;

    @JsonProperty("max_arguments_length")
    private long maxArgumentsLength;

    @JsonProperty("max_length_returned_data")
    private long maxLengthReturnedData;

    @JsonProperty("max_contract_size")
    private long maxContractSize;

    @JsonProperty("max_transaction_size")
    private long maxTransactionSize;

    @JsonProperty("max_length_storage_key")
    private long maxLengthStorageKey;

    @JsonProperty("max_length_storage_value")
    private long maxLengthStorageValue;

    @JsonProperty("max_promises_per_function_call_action")
    private long maxPromisesPerFunctionCallAction;

    @JsonProperty("max_number_input_data_dependencies")
    private long maxNumberInputDataDependencies;

    @JsonProperty("max_functions_number_per_contract")
    private long maxFunctionsNumberPerContract;

    @JsonProperty("wasmer2_stack_limit")
    private long wasmer2StackLimit;
}
