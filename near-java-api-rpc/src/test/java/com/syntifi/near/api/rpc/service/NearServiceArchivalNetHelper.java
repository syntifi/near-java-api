package com.syntifi.near.api.rpc.service;

import com.syntifi.near.api.common.helper.Network;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;

public class NearServiceArchivalNetHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(NearServiceArchivalNetHelper.class);

    public static NearService nearService = null;

    public static Network network = Network.ARCHIVAL_TEST_NET;

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
