package com.syntifi.near.api.rpc.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.rpc.service.contract.AccountIdParam;
import com.syntifi.near.api.rpc.service.contract.FunctionCallResult;
import com.syntifi.near.api.rpc.service.contract.NFTContractFunctionCall;
import com.syntifi.near.api.rpc.service.contract.NFTMetadataResult;
import com.syntifi.near.api.rpc.service.contract.NFTTokensForOwnerParam;
import com.syntifi.near.api.rpc.service.contract.NFTTokensParam;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.syntifi.near.api.rpc.service.NearServiceHelper.nearService;

public class NearServiceContractsNFTsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NearServiceContractsNFTsTest.class);

    @Test
    void callContractFunction_NFTContractFunctionCall_builderForTotalSupply_return_list() throws IOException {
        FunctionCallResult<String> result = NFTContractFunctionCall.callForTotalSupply(nearService, "paras-token-v2.testnet");

        LOGGER.debug("{}", result.getContractFunctionCallResult().getResult());
        LOGGER.debug("{}", result.getResult());
    }

    @Test
    void callContractFunction_NFTContractFunctionCall_builderForTokens_return_list() throws IOException {
        FunctionCallResult<JsonNode> result = NFTContractFunctionCall.callForTokens(nearService, "paras-token-v2.testnet", new NFTTokensParam("0", 10));

        LOGGER.debug("{}", result.getContractFunctionCallResult().getResult());
        LOGGER.debug("{}", result.getResult());
    }

    @Test
    void callContractFunction_NFTContractFunctionCall_builderForTokensForOwner_return_list() throws IOException {
        FunctionCallResult<JsonNode> result = NFTContractFunctionCall.callForTokensForOwner(nearService, "paras-token-v2.testnet", new NFTTokensForOwnerParam("wallet-test.testnet", "0", 10));

        LOGGER.debug("{}", result.getContractFunctionCallResult().getResult());
        LOGGER.debug("{}", result.getResult());
    }

    @Test
    void callContractFunction_NFTContractFunctionCall_builderForMetadata_return_list() throws IOException {
        FunctionCallResult<NFTMetadataResult> result = NFTContractFunctionCall.callForMetadata(nearService, "paras-token-v2.testnet");

        LOGGER.debug("{}", result.getContractFunctionCallResult().getResult());
        LOGGER.debug("{}", result.getResult());
    }

    @Test
    void callContractFunction_NFTContractFunctionCall_builderForSupplyForOwner_return_list() throws IOException {
        FunctionCallResult<String> result = NFTContractFunctionCall.callForSupplyForOwner(nearService, "paras-token-v2.testnet", new AccountIdParam("wallet-test.testnet"));

        LOGGER.debug("{}", result.getContractFunctionCallResult().getResult());
        LOGGER.debug("{}", result.getResult());
    }
}
