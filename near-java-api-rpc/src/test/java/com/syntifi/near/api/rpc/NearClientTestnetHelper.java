package com.syntifi.near.api.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;

public class NearClientTestnetHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(NearClientTestnetHelper.class);

    public static NearClient nearClient = null;

    public static Network network = Network.TEST_NET;

    static {
        LOGGER.debug("======== Running tests with peer {} ========", network.getRpcUrl());
        try {
            nearClient = NearClient.usingNetwork(network);
        } catch (MalformedURLException e) {
            LOGGER.error("Invalid URL {}", network.getRpcUrl());
            e.printStackTrace();
        }
    }
}
