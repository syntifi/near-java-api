package com.syntifi.near.api.rpc;

import com.syntifi.near.api.common.helper.Network;
import com.syntifi.near.api.rpc.service.exception.NearServiceException;

import java.net.MalformedURLException;


public class NearClientHelper {

    public static NearClient getClient(Network network) {
        NearClient nearClient;
        try {
            nearClient = NearClient.usingNetwork(network);
        } catch (MalformedURLException e) {
            throw new NearServiceException("Invalid URL " + network.getRpcUrl(), e.getCause());
        }
        return nearClient;
    }
}
