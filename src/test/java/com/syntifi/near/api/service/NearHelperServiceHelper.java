package com.syntifi.near.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NearHelperServiceHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(NearHelperServiceHelper.class);

    public static NearHelperService nearHelperService;

    static {
        String peerAddress = "helper.testnet.near.org";

        LOGGER.debug("======== Running tests with peer {} ========", peerAddress);
        nearHelperService = NearHelperService.usingPeer(peerAddress);
    }
}
