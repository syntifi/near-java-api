package com.syntifi.near.api.rpc.model.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ExtCosts
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
public class ExtCosts {
    @JsonProperty("base")
    private long base;

    @JsonProperty("contract_loading_base")
    private long contractLoadingBase;

    @JsonProperty("contract_loading_bytes")
    private long contractLoadingBytes;

    @JsonProperty("contract_compile_base")
    private long contractCompileBase;

    @JsonProperty("contract_compile_bytes")
    private long contractCompileBytes;

    @JsonProperty("read_memory_base")
    private long readMemoryBase;

    @JsonProperty("read_memory_byte")
    private long readMemoryByte;

    @JsonProperty("write_memory_base")
    private long writeMemoryBase;

    @JsonProperty("write_memory_byte")
    private long writeMemoryByte;

    @JsonProperty("read_register_base")
    private long readRegisterBase;

    @JsonProperty("read_register_byte")
    private long readRegisterByte;

    @JsonProperty("write_register_base")
    private long writeRegisterBase;

    @JsonProperty("write_register_byte")
    private long writeRegisterByte;

    @JsonProperty("utf8_decoding_base")
    private long utf8DecodingBase;

    @JsonProperty("utf8_decoding_byte")
    private long utf8DecodingByte;

    @JsonProperty("utf16_decoding_base")
    private long utf16DecodingBase;

    @JsonProperty("utf16_decoding_byte")
    private long utf16DecodingByte;

    @JsonProperty("sha256_base")
    private long sha256Base;

    @JsonProperty("sha256_byte")
    private long sha256Byte;

    @JsonProperty("keccak256_base")
    private long keccak256Base;

    @JsonProperty("keccak256_byte")
    private long keccak256Byte;

    @JsonProperty("keccak512_base")
    private long keccak512Base;

    @JsonProperty("keccak512_byte")
    private long keccak512Byte;

    @JsonProperty("ripemd160_base")
    private long ripemd160Base;

    @JsonProperty("ripemd160_block")
    private long ripemd160Block;

    @JsonProperty("ecrecover_base")
    private long ecrecoverBase;

    @JsonProperty("log_base")
    private long logBase;

    @JsonProperty("log_byte")
    private long logByte;

    @JsonProperty("storage_write_base")
    private long storageWriteBase;

    @JsonProperty("storage_write_key_byte")
    private long storageWriteKeyByte;

    @JsonProperty("storage_write_value_byte")
    private long storageWriteValueByte;

    @JsonProperty("storage_write_evicted_byte")
    private long storageWriteEvictedByte;

    @JsonProperty("storage_read_base")
    private long storageReadBase;

    @JsonProperty("storage_read_key_byte")
    private long storageReadKeyByte;

    @JsonProperty("storage_read_value_byte")
    private long storageReadValueByte;

    @JsonProperty("storage_remove_base")
    private long storageRemoveBase;

    @JsonProperty("storage_remove_key_byte")
    private long storageRemoveKeyByte;

    @JsonProperty("storage_remove_ret_value_byte")
    private long storageRemoveRetValueByte;

    @JsonProperty("storage_has_key_base")
    private long storageHasKeyBase;

    @JsonProperty("storage_has_key_byte")
    private long storageHasKeyByte;

    @JsonProperty("storage_iter_create_prefix_base")
    private long storageIterCreatePrefixBase;

    @JsonProperty("storage_iter_create_prefix_byte")
    private long storageIterCreatePrefixByte;

    @JsonProperty("storage_iter_create_range_base")
    private long storageIterCreateRangeBase;

    @JsonProperty("storage_iter_create_from_byte")
    private long storageIterCreateFromByte;

    @JsonProperty("storage_iter_create_to_byte")
    private long storageIterCreateToByte;

    @JsonProperty("storage_iter_next_base")
    private long storageIterNextBase;

    @JsonProperty("storage_iter_next_key_byte")
    private long storageIterNextKeyByte;

    @JsonProperty("storage_iter_next_value_byte")
    private long storageIterNextValueByte;

    @JsonProperty("touching_trie_node")
    private long touchingTrieNode;

    @JsonProperty("read_cached_trie_node")
    private long readCachedTrieNode;

    @JsonProperty("promise_and_base")
    private long promiseAndBase;

    @JsonProperty("promise_and_per_promise")
    private long promiseAndPerPromise;

    @JsonProperty("promise_return")
    private long promiseReturn;

    @JsonProperty("validator_stake_base")
    private long validatorStakeBase;

    @JsonProperty("validator_total_stake_base")
    private long validatorTotalStakeBase;

    @JsonProperty("alt_bn128_g1_multiexp_base")
    private long altBn128G1MutiexpBase;

    @JsonProperty("alt_bn128_g1_multiexp_element")
    private long altBn128G1MutiexpElement;

    @JsonProperty("alt_bn128_g1_sum_base")
    private long altBn128G1SumBase;

    @JsonProperty("alt_bn128_g1_sum_element")
    private long altBn128G1SumElement;

    @JsonProperty("alt_bn128_pairing_check_base")
    private long altBn128PairingCheckBase;

    @JsonProperty("alt_bn128_pairing_check_element")
    private long altBn128PairingCheckElement;

}
