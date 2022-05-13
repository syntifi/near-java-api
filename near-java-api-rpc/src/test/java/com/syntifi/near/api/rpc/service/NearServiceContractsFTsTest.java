package com.syntifi.near.api.rpc.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.rpc.model.contract.ContractFunctionCallResult;
import com.syntifi.near.api.rpc.service.contract.AccountIdParam;
import com.syntifi.near.api.rpc.service.contract.ContractFunctionCall;
import com.syntifi.near.api.rpc.service.contract.FTContractFunctionCall;
import com.syntifi.near.api.rpc.service.contract.NFTMetadataResult;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.syntifi.near.api.rpc.service.NearServiceHelper.nearService;

public class NearServiceContractsFTsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NearServiceContractsFTsTest.class);

    @Test
    void callContractFunction_FTContractFunctionCall_builderForBalanceOf_return_list() throws IOException {
        ContractFunctionCall contractCall = FTContractFunctionCall
                .forBalanceOf("meta.pool.testnet", new AccountIdParam("wallet-test.testnet"));

        ContractFunctionCallResult result = contractCall.call(nearService);

        LOGGER.debug("{}", result.getResult());

        JsonNode value = result.toResultObject(JsonNode.class);
        LOGGER.debug("{}", value);
    }

    @Test
    void callContractFunction_FTContractFunctionCall_builderForMetadata_return_list() throws IOException {
        ContractFunctionCall contractCall = FTContractFunctionCall.forMetadata("paras-marketplace-v2.testnet");

        ContractFunctionCallResult result = contractCall.call(nearService);

        LOGGER.debug("{}", result);

        if (result.getResult() != null) {
            NFTMetadataResult metadata = result.toResultObject(NFTMetadataResult.class);
            LOGGER.debug("{}", metadata);
        }
    }
}
