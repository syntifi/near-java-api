package com.syntifi.near.api.helper.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NearKitWalletServiceHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(NearKitWalletServiceHelper.class);

    public static NearKitWalletService nearKitWalletService;

    static {
        String peerAddress = "testnet-api.kitwallet.app";

        LOGGER.debug("======== Running tests with helper {} ========", peerAddress);
        nearKitWalletService = NearKitWalletService.usingPeer(peerAddress);
    }
}
