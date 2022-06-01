package com.syntifi.near.api.rpc;

import com.syntifi.near.api.common.exception.NearException;
import com.syntifi.near.api.common.helper.Network;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NearClientTestnetHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(NearClientTestnetHelper.class);

    public static NearClient nearClient = null;

    public static Network network = Network.TEST_NET;

    static {
        LOGGER.debug("======== Running tests with peer {} ========", network.getRpcUrl());
        try {
            nearClient = NearClient.usingNetwork(network);
        } catch (NearException e) {
            LOGGER.error("{}", e.getMessage());
            e.printStackTrace();
        }
    }
}
