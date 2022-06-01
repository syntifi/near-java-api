package com.syntifi.near.api.rpc.service;

import com.syntifi.near.api.common.helper.Network;
import com.syntifi.near.api.rpc.service.exception.NearServiceException;

import java.net.MalformedURLException;


public class NearServiceHelper {

    public static NearService getService(Network network) {
        NearService nearService;
        try {
            nearService = NearService.usingNetwork(network);
        } catch (MalformedURLException e) {
            throw new NearServiceException("Invalid URL " + network.getRpcUrl(), e.getCause());
        }
        return nearService;
    }
}
