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
public class NetworkInfo {
    @JsonProperty("active_peers")
    private Collection<Peer> activePeers;

    @JsonProperty("num_active_peers")
    private long numActivePeers;

    @JsonProperty("peer_max_count")
    private long peerMaxCount;

    @JsonProperty("sent_bytes_per_sec")
    private long sentBytesPerSec;

    @JsonProperty("received_bytes_per_sec")
    private long receivedBytesPerSec;

    @JsonProperty("known_producers")
    private Collection<Producer> knownProducers;
}
