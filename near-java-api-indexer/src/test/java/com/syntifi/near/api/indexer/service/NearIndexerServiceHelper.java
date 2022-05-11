package com.syntifi.near.api.indexer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NearIndexerServiceHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(NearIndexerServiceHelper.class);

    public static NearIndexerService nearIndexerService;

    static {
        String peerAddress = "testnet-api.kitwallet.app";

        LOGGER.debug("======== Running tests with indexer {} ========", peerAddress);
        nearIndexerService = NearIndexerService.usingPeer(peerAddress);
    }
}
