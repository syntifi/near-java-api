package com.syntifi.near.api.model.protocol;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.model.network.Validator;

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
public class GenesisConfig {
    @JsonProperty("protocol_version")
    private long protocolVersion;

    @JsonProperty("genesis_time")
    private String genesisTime;

    @JsonProperty("chain_id")
    private String chainId;

    @JsonProperty("genesis_height")
    private long genesisHeight;

    @JsonProperty("num_block_producer_seats")
    private long numBlockProducerSeats;

    @JsonProperty("num_block_producer_seats_per_shard")
    private Collection<Long> numBlockProducerSeatsPerShard;

    @JsonProperty("avg_hidden_validator_seats_per_shard")
    private Collection<Long> avgHiddenValidatorSeatsPerShard;

    @JsonProperty("dynamic_resharding")
    private boolean dynamicResharding;

    @JsonProperty("protocol_upgrade_stake_threshold")
    private Collection<Long> protocolUpgradeStakeThreshold;

    @JsonProperty("protocol_upgrade_num_epochs")
    private long protocolUpgradeNumEpochs;

    @JsonProperty("epoch_length")
    private long epochLength;

    @JsonProperty("gas_limit")
    private long gasLimit;

    @JsonProperty("min_gas_price")
    private String minGasPrice;

    @JsonProperty("max_gas_price")
    private String maxGasPrice;

    @JsonProperty("block_producer_kickout_threshold")
    private long blockProducerKickoutThreshold;

    @JsonProperty("chunk_producer_kickout_threshold")
    private long chunkProducerKickoutThreshold;

    @JsonProperty("online_min_threshold")
    private Collection<Long> onlineMinThreshold;

    @JsonProperty("online_max_threshold")
    private Collection<Long> onlineMaxThreshold;

    @JsonProperty("gas_price_adjustment_rate")
    private Collection<Long> gasPriceAdjustmentRate;

    @JsonProperty("runtime_config")
    @JsonInclude(value = Include.NON_NULL)
    private RuntimeConfig runtimeConfig;

    @JsonProperty("validators")
    private Collection<Validator> validators;

    @JsonProperty("transaction_validity_period")
    private long transactionValidityPeriod;

    @JsonProperty("protocol_reward_rate")
    private Collection<Long> protocolRewardRate;

    @JsonProperty("max_inflation_rate")
    private Collection<Long> maxInflationRate;

    @JsonProperty("total_supply")
    private String totalSupply;

    @JsonProperty("num_blocks_per_year")
    private long numBlocksPerYear;

    @JsonProperty("protocol_treasury_account")
    private String protocolTreasuryAccount;

    @JsonProperty("fishermen_threshold")
    private String fishermenThreshold;

    @JsonProperty("minimum_stake_divisor")
    private long minimumStakeDivisor;

    @JsonProperty("shard_layout")
    private ShardLayout shardLayout;

    @JsonProperty("simple_nightshade_shard_layout")
    private SimpleNightShadeShardLayout simpleNightshadeShardLayout;

    @JsonProperty("minimum_stake_ratio")
    private Collection<Long> minimumStakeRatio;
}
