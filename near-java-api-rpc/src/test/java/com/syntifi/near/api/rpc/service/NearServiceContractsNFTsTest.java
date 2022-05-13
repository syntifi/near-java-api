package com.syntifi.near.api.rpc.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.rpc.model.contract.ContractFunctionCallResult;
import com.syntifi.near.api.rpc.service.contract.AccountIdParam;
import com.syntifi.near.api.rpc.service.contract.ContractFunctionCall;
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
        ContractFunctionCall contractCall = NFTContractFunctionCall.forTotalSupply("paras-token-v2.testnet");

        ContractFunctionCallResult result = contractCall.call(nearService);

        LOGGER.debug("{}", result.getResult());

        LOGGER.debug("{}", result.toResultObject(String.class));
    }

    @Test
    void callContractFunction_NFTContractFunctionCall_builderForTokens_return_list() throws IOException {
        ContractFunctionCall contractCall = NFTContractFunctionCall.forTokens("paras-token-v2.testnet", new NFTTokensParam("0", 10));

        ContractFunctionCallResult result = contractCall.call(nearService);

        LOGGER.debug("{}", result.getResult());

        LOGGER.debug("{}", result.toResultObject(JsonNode.class));
    }

    @Test
    void callContractFunction_NFTContractFunctionCall_builderForTokensForOwner_return_list() throws IOException {
        ContractFunctionCall contractCall = NFTContractFunctionCall.forTokensForOwner("paras-token-v2.testnet", new NFTTokensForOwnerParam("wallet-test.testnet", "0", 10));

        ContractFunctionCallResult result = contractCall.call(nearService);

        LOGGER.debug("{}", result.getResult());

        LOGGER.debug("{}", result.toResultObject(JsonNode.class));
    }

    @Test
    void callContractFunction_NFTContractFunctionCall_builderForMetadata_return_list() throws IOException {
        ContractFunctionCall contractCall = NFTContractFunctionCall.forMetadata("paras-token-v2.testnet");

        ContractFunctionCallResult result = contractCall.call(nearService);

        LOGGER.debug("{}", result.getResult());

        NFTMetadataResult metadata = result.toResultObject(NFTMetadataResult.class);
        LOGGER.debug("{}", metadata);
    }

    @Test
    void callContractFunction_NFTContractFunctionCall_builderForSupplyForOwner_return_list() throws IOException {
        ContractFunctionCall contractCall = NFTContractFunctionCall.forSupplyForOwner("paras-token-v2.testnet", new AccountIdParam("wallet-test.testnet"));

        ContractFunctionCallResult result = contractCall.call(nearService);

        LOGGER.debug("{}", result.getResult());
        LOGGER.debug("{}", result.toResultObject(String.class));
    }
}
