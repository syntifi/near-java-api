package com.syntifi.near.api.rpc.service.nft;

import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.rpc.service.contract.common.ContractClient;
import com.syntifi.near.api.rpc.service.contract.common.ContractMethodProxyClient;
import com.syntifi.near.api.rpc.service.contract.common.FunctionCallResult;
import com.syntifi.near.api.rpc.service.contract.common.param.AccountIdParam;
import com.syntifi.near.api.rpc.service.contract.nft.NFTService;
import com.syntifi.near.api.rpc.service.contract.nft.model.NFTContract;
import com.syntifi.near.api.rpc.service.contract.nft.model.NFTToken;
import com.syntifi.near.api.rpc.service.contract.nft.model.NFTTokenList;
import com.syntifi.near.api.rpc.service.contract.nft.model.NFTTokenMediaURL;
import com.syntifi.near.api.rpc.service.contract.nft.param.NFTTokensForOwnerParam;
import com.syntifi.near.api.rpc.service.contract.nft.param.NFTTokensParam;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.syntifi.near.api.rpc.NearClientArchivalNetHelper.nearClient;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NFTServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NFTServiceTest.class);

    private static final NFTService service = ContractClient.createClientProxy(NFTService.class, new ContractMethodProxyClient());

    @Test
    void callContractFunction_NFTContractFunctionCall_forTotalSupply_return_list() {
        FunctionCallResult<String> result = service.getTotalSupply(nearClient, "paras-token-v2.testnet");

        LOGGER.debug("{}", result.getResult());
    }

    @Test
    void callContractFunction_NFTContractFunctionCall_forTokens_return_list() {
        FunctionCallResult<JsonNode> result = service.getTokens(nearClient, "paras-token-v2.testnet", NFTTokensParam.builder().fromIndex("0").build());

        LOGGER.debug("{}", result.getResult());
    }

    @Test
    void callContractFunction_NFTContractFunctionCall_forTokensForOwner_return_list() {
        // Other contracts to test
        // deadmau55.mintspace2.testnet
        // bananafratclub.mintspace2.testnet
        // paras-token-v2.testnet
        NFTContract contract = new NFTContract("paras-token-v2.testnet");

        contract.setMetadata(service.getMetadata(nearClient, contract.getContractId()));

        FunctionCallResult<NFTTokenList> tokenList = service.getTokensForOwner(nearClient,
                contract.getContractId(),
                NFTTokensForOwnerParam.builder().accountId("wallet-test.testnet").fromIndex("1").build());

        LOGGER.debug("{}", tokenList.getContractFunctionCallResult().getResult());
        for (NFTToken token : tokenList.getResult()) {
            LOGGER.debug("Token: {}", token);
            assertNotNull(token);
            assertNotNull(token.getTokenId());

            assertDoesNotThrow(() -> token.getMediaOrReferenceURL(contract));
            NFTTokenMediaURL nftTokenMediaURL = token.getMediaOrReferenceURL(contract);
            LOGGER.debug("Token Media: {} of type {}", nftTokenMediaURL.getUrl().toString(), nftTokenMediaURL.getType().toString());
            if (nftTokenMediaURL.getUrl() == null) {
                assertEquals(NFTTokenMediaURL.Type.EMPTY, nftTokenMediaURL.getType());
            } else {
                assertNotEquals(NFTTokenMediaURL.Type.EMPTY, nftTokenMediaURL.getType());
            }
        }
    }

    @Test
    void callContractFunction_NFTContractFunctionCall_forMetadata_return_list() {
        NFTContract contract = new NFTContract("paras-token-v2.testnet");
        contract.setMetadata(service.getMetadata(nearClient, contract.getContractId()));

        LOGGER.debug("{}", contract.getMetadata().getResult());
    }

    @Test
    void callContractFunction_NFTContractFunctionCall_forSupplyForOwner_return_list() {
        FunctionCallResult<String> result = service.getSupplyForOwner(nearClient,
                "paras-token-v2.testnet",
                AccountIdParam.builder().accountId("wallet-test.testnet").build());

        LOGGER.debug("{}", result.getResult());
    }
}
