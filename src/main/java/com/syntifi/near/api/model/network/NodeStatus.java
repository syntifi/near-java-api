package com.syntifi.near.api.model.network;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
public class NodeStatus {
    @JsonProperty("version")
    private Version version;

    @JsonProperty("chain_id")
    private String chainId;

    @JsonProperty("protocol_version")
    private long protocolVersion;

    @JsonProperty("latest_protocol_version")
    private long latestProtocolVersion;

    @JsonProperty("rpc_addr")
    private String rpcAddr;

    @JsonProperty("validators")
    private Collection<Validator> validators;

    @JsonProperty("sync_info")
    private SyncInfo syncInfo;

    @JsonProperty("validator_account_id")
    private String validatorAccountId;
}
