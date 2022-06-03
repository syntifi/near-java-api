package com.syntifi.near.api.rpc.service.ft;

import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.rpc.service.contract.common.param.AccountIdParam;
import com.syntifi.near.api.rpc.service.contract.common.ContractClient;
import com.syntifi.near.api.rpc.service.contract.common.ContractMethodProxyClient;
import com.syntifi.near.api.rpc.service.contract.common.FunctionCallResult;
import com.syntifi.near.api.rpc.service.contract.ft.FTService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.syntifi.near.api.rpc.NearClientArchivalNetHelper.nearClient;

public class FTServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FTServiceTest.class);

    private static final FTService service = ContractClient.createClientProxy(FTService.class, new ContractMethodProxyClient());

    @Test
    void callContractFunction_FTContractFunctionCall_forBalanceOf_return_list() {
        FunctionCallResult<JsonNode> result = service.getBalanceOf(nearClient, "meta.pool.testnet", new AccountIdParam("wallet-test.testnet"));

        LOGGER.debug("{}", result.getContractFunctionCallResult().getResult());
        LOGGER.debug("{}", result.getResult());
    }

    @Test
    void callContractFunction_FTContractFunctionCall_forMetadata_return_list() {
        FunctionCallResult<JsonNode> result = service.getMetadata(nearClient, "paras-marketplace-v2.testnet");

        LOGGER.debug("{}", result.getContractFunctionCallResult().getResult());
        LOGGER.debug("{}", result.getResult());
    }
}
