package com.syntifi.near.api.rpc.service.nft;

import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.rpc.service.contract.AccountIdParam;
import com.syntifi.near.api.rpc.service.contract.FunctionCallResult;
import com.syntifi.near.api.rpc.service.contract.nft.NFTContractFunctionCall;
import com.syntifi.near.api.rpc.service.contract.nft.NFTTokensForOwnerParam;
import com.syntifi.near.api.rpc.service.contract.nft.NFTTokensParam;
import com.syntifi.near.api.rpc.service.contract.nft.model.NFTContract;
import com.syntifi.near.api.rpc.service.contract.nft.model.NFTToken;
import com.syntifi.near.api.rpc.service.contract.nft.model.NFTTokenList;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.syntifi.near.api.rpc.service.NearServiceHelper.nearService;

public class ContractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContractTest.class);

    @Test
    void callContractFunction_NFTContractFunctionCall_forTotalSupply_return_list() throws IOException {
        FunctionCallResult<String> result = NFTContractFunctionCall
                .forTotalSupply(nearService, "paras-token-v2.testnet");

        LOGGER.debug("{}", result.getResult());
    }

    @Test
    void callContractFunction_NFTContractFunctionCall_forTokens_return_list() throws IOException {
        FunctionCallResult<JsonNode> result = NFTContractFunctionCall
                .forTokens(nearService, "paras-token-v2.testnet", NFTTokensParam.builder().fromIndex("0").build());

        LOGGER.debug("{}", result.getResult());
    }

    @Test
    void callContractFunction_NFTContractFunctionCall_forTokensForOwner_return_list() throws IOException, URISyntaxException {
        NFTContract contract = new NFTContract("paras-token-v2.testnet");

        NFTContractFunctionCall.loadContractMetadata(nearService, contract);

        FunctionCallResult<NFTTokenList> resultList = NFTContractFunctionCall
                .forTokensForOwner(nearService,
                        contract.getContractId(),
                        NFTTokensForOwnerParam.builder().accountId("wallet-test.testnet").fromIndex("0").build());

        LOGGER.debug("{}", resultList.getContractFunctionCallResult().getResult());
        for (NFTToken result : resultList.getResult()) {
            LOGGER.debug("Token: {}", result);
            LOGGER.debug("Token Media: {}", result.getMediaUrl(contract));
        }
    }

    @Test
    void callContractFunction_NFTContractFunctionCall_forMetadata_return_list() throws IOException {
        NFTContract contract = new NFTContract("paras-token-v2.testnet");
        NFTContractFunctionCall.loadContractMetadata(nearService, contract);

        LOGGER.debug("{}", contract.getMetadata().getResult());
    }

    @Test
    void callContractFunction_NFTContractFunctionCall_forSupplyForOwner_return_list() throws IOException {
        FunctionCallResult<String> result = NFTContractFunctionCall
                .forSupplyForOwner(nearService,
                        "paras-token-v2.testnet",
                        AccountIdParam.builder().accountId("wallet-test.testnet").build());

        LOGGER.debug("{}", result.getResult());
    }
}
