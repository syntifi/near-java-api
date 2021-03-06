package com.syntifi.near.api.rpc;

import com.syntifi.near.api.common.exception.NearException;
import com.syntifi.near.api.common.helper.Network;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NearClientArchivalNetHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(NearClientArchivalNetHelper.class);

    public static NearClient nearClient = null;

    public static Network network = Network.ARCHIVAL_TEST_NET;

    static {
        LOGGER.debug("======== Running tests with peer {} ========", network.getRpcUrl());
        try {
            nearClient = NearClient.usingNetwork(network);
        } catch (NearException e) {
            LOGGER.error("Invalid URL {}", network.getRpcUrl());
            e.printStackTrace();
        }
    }
}
