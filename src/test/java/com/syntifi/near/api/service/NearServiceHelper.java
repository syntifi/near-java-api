package com.syntifi.near.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;

public class NearServiceHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(NearServiceHelper.class);

    public static NearService nearService = null;

    static {
        // WARN: Using archival (archival-rpc.testnet.near.org) testnet (instead of "rpc.testnet.near.org") for stabler
        // results
        String peerAddress = "archival-rpc.testnet.near.org";

        LOGGER.debug("======== Running tests with peer {} ========", peerAddress);
        try {
            nearService = NearService.usingPeer(peerAddress);
        } catch (MalformedURLException e) {
            LOGGER.error("Invalid URL {}", peerAddress);
            e.printStackTrace();
        }
    }
}
