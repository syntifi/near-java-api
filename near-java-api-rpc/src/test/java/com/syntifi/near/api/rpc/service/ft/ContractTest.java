package com.syntifi.near.api.rpc.service.ft;

import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.rpc.service.contract.AccountIdParam;
import com.syntifi.near.api.rpc.service.contract.FunctionCallResult;
import com.syntifi.near.api.rpc.service.contract.ft.FTContractFunctionCall;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.syntifi.near.api.rpc.service.NearServiceHelper.nearService;

public class ContractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContractTest.class);

    @Test
    void callContractFunction_FTContractFunctionCall_forBalanceOf_return_list() throws IOException {
        FunctionCallResult<JsonNode> result = FTContractFunctionCall
                .forBalanceOf(nearService, "meta.pool.testnet", new AccountIdParam("wallet-test.testnet"));

        LOGGER.debug("{}", result.getContractFunctionCallResult().getResult());
        LOGGER.debug("{}", result.getResult());
    }

    @Test
    void callContractFunction_FTContractFunctionCall_forMetadata_return_list() throws IOException {
        FunctionCallResult<JsonNode> result = FTContractFunctionCall.forMetadata(nearService, "paras-marketplace-v2.testnet");

        LOGGER.debug("{}", result.getContractFunctionCallResult().getResult());
        LOGGER.debug("{}", result.getResult());
    }
}
