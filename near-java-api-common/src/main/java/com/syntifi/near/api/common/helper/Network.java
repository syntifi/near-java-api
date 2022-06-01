package com.syntifi.near.api.common.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Lists of most relevant networks and their root domain
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@AllArgsConstructor
@Getter
public enum Network {
    MAIN_NET("rpc.near.org", "api.kitwallet.app", "helper.near.org", "https://explorer.near.org/transactions/", "near"),
    TEST_NET("rpc.testnet.near.org", "testnet-api.kitwallet.app", "helper.testnet.near.org", "https://explorer.testnet.near.org/transactions/", "testnet"),
    ARCHIVAL_TEST_NET("archival-rpc.testnet.near.org", "testnet-api.kitwallet.app", "helper.testnet.near.org", "https://explorer.testnet.near.org/transactions/", "testnet");

    private final String rpcUrl;
    private final String indexerUrl;
    private final String helperUrl;
    private final String explorerUrl;
    private final String domain;
}
