package com.syntifi.near.api.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.JsonRpcMethod;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.ProxyUtil;

public interface NearService {

    @JsonRpcMethod("block")
    public Object getLastBlock(@JsonRpcParam("finality") String finalBlock);

    @JsonRpcMethod("block")
    public Object getBlock(@JsonRpcParam("block_id") String blockIdentitier);

    @JsonRpcMethod("block")
    public Object getBlock(@JsonRpcParam("block_id") long blockIdentifier);

    public static NearService usingPeer(String url) throws MalformedURLException {
        Map<String, String> newHeaders = new HashMap<>();
        newHeaders.put("Content-Type", "application/json");
        JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("https://" + url),
                newHeaders);

        return ProxyUtil.createClientProxy(NearService.class.getClassLoader(), NearService.class, client);
    }
}
