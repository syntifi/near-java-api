package com.syntifi.near.api.rpc.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.rpc.service.contract.AccountIdParam;
import com.syntifi.near.api.rpc.service.contract.FTFunctionCall;
import com.syntifi.near.api.rpc.service.contract.FunctionCallResult;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.syntifi.near.api.rpc.service.NearServiceArchivalNetHelper.nearService;

public class NearServiceContractsFTsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NearServiceContractsFTsTest.class);

    @Test
    void callContractFunction_FTContractFunctionCall_builderForBalanceOf_return_list() throws IOException {
        FunctionCallResult<JsonNode> result = FTFunctionCall
                .forBalanceOf(nearService, "meta.pool.testnet", new AccountIdParam("wallet-test.testnet"));

        LOGGER.debug("{}", result.getContractFunctionCallResult().getResult());
        LOGGER.debug("{}", result.getResult());
    }

    @Test
    void callContractFunction_FTContractFunctionCall_builderForMetadata_return_list() throws IOException {
        FunctionCallResult<JsonNode> result = FTFunctionCall.forMetadata(nearService, "paras-marketplace-v2.testnet");

        LOGGER.debug("{}", result.getContractFunctionCallResult().getResult());
        LOGGER.debug("{}", result.getResult());
    }
}
