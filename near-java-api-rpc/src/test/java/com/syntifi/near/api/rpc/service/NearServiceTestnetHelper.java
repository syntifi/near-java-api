package com.syntifi.near.api.rpc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;

public class NearServiceTestnetHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(NearServiceTestnetHelper.class);

    public static NearService nearService = null;

    public static Network network = Network.TEST_NET;

    static {
        LOGGER.debug("======== Running tests with peer {} ========", network.getRpcUrl());
        try {
            nearService = NearService.usingNetwork(network);
        } catch (MalformedURLException e) {
            LOGGER.error("Invalid URL {}", network.getRpcUrl());
            e.printStackTrace();
        }
    }
}
