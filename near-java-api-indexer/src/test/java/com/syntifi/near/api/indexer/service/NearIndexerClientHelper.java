package com.syntifi.near.api.indexer.service;

import com.syntifi.near.api.common.helper.Network;
import com.syntifi.near.api.indexer.NearIndexerClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NearIndexerClientHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(NearIndexerClientHelper.class);

    public static NearIndexerClient nearIndexerClient;

    static {
        Network network = Network.TEST_NET;

        LOGGER.debug("======== Running tests with indexer {} ========", network.getIndexerUrl());
        nearIndexerClient = NearIndexerClient.usingNetwork(network);
    }
}
