package com.syntifi.near.api.rpc.service.nft;

import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.rpc.service.contract.AccountIdParam;
import com.syntifi.near.api.rpc.service.contract.FunctionCallResult;
import com.syntifi.near.api.rpc.service.contract.nft.NFTContractFunctionCall;
import com.syntifi.near.api.rpc.service.contract.nft.NFTContractMetadata;
import com.syntifi.near.api.rpc.service.contract.nft.NFTToken;
import com.syntifi.near.api.rpc.service.contract.nft.NFTTokenList;
import com.syntifi.near.api.rpc.service.contract.nft.NFTTokensForOwnerParam;
import com.syntifi.near.api.rpc.service.contract.nft.NFTTokensParam;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.syntifi.near.api.rpc.service.NearServiceHelper.nearService;

public class ContractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContractTest.class);

    @Test
    void callContractFunction_NFTContractFunctionCall_forTotalSupply_return_list() throws IOException {
        FunctionCallResult<String> result = NFTContractFunctionCall.forTotalSupply(nearService, "paras-token-v2.testnet");

        LOGGER.debug("{}", result.getContractFunctionCallResult().getResult());
        LOGGER.debug("{}", result.getResult());
    }

    @Test
    void callContractFunction_NFTContractFunctionCall_forTokens_return_list() throws IOException {
        FunctionCallResult<JsonNode> result = NFTContractFunctionCall.forTokens(nearService, "paras-token-v2.testnet", new NFTTokensParam("0", 10));

        LOGGER.debug("{}", result.getContractFunctionCallResult().getResult());
        LOGGER.debug("{}", result.getResult());
    }

    @Test
    void callContractFunction_NFTContractFunctionCall_forTokensForOwner_return_list() throws IOException {
        FunctionCallResult<NFTTokenList> resultList = NFTContractFunctionCall.forTokensForOwner(nearService, "paras-token-v2.testnet", new NFTTokensForOwnerParam("wallet-test.testnet", "0", 10));

        LOGGER.debug("{}", resultList.getContractFunctionCallResult().getResult());
        for (NFTToken result : resultList.getResult()) {
            LOGGER.debug("{}", result);
        }
    }

    @Test
    void callContractFunction_NFTContractFunctionCall_forMetadata_return_list() throws IOException {
        FunctionCallResult<NFTContractMetadata> result = NFTContractFunctionCall.forMetadata(nearService, "paras-token-v2.testnet");

        LOGGER.debug("{}", result.getContractFunctionCallResult().getResult());
        LOGGER.debug("{}", result.getResult());
    }

    @Test
    void callContractFunction_NFTContractFunctionCall_forSupplyForOwner_return_list() throws IOException {
        FunctionCallResult<String> result = NFTContractFunctionCall.forSupplyForOwner(nearService, "paras-token-v2.testnet", new AccountIdParam("wallet-test.testnet"));

        LOGGER.debug("{}", result.getContractFunctionCallResult().getResult());
        LOGGER.debug("{}", result.getResult());
    }
}
