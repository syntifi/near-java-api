package com.syntifi.near.api.rpc;

/**
 * Lists of most relevante networks and their root domain
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public enum Network {
    MAIN_NET("rpc.near.org", "near"),
    TEST_NET("rpc.testnet.near.org", "testnet"),
    ARCHIVAL_TEST_NET("archival-rpc.testnet.near.org", "testnet");

    private final String rpcUrl;
    private final String domain;

    Network(String rpcUrl, String domain) {
        this.rpcUrl = rpcUrl;
        this.domain = domain;
    }

    public String getRpcUrl() {
        return rpcUrl;
    }

    public String getDomain() {
        return domain;
    }
}
