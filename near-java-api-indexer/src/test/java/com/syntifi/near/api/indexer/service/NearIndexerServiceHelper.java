package com.syntifi.near.api.indexer.service;

import com.syntifi.near.api.common.helper.Network;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NearIndexerServiceHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(NearIndexerServiceHelper.class);

    public static NearIndexerService nearIndexerService;

    static {
        String peerAddress = Network.TEST_NET.getIndexerUrl();

        LOGGER.debug("======== Running tests with indexer {} ========", peerAddress);
        nearIndexerService = NearIndexerService.usingPeer(peerAddress);
    }
}
