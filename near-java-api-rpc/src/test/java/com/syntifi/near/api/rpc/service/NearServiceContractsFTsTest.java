package com.syntifi.near.api.rpc.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntifi.near.api.rpc.model.contract.ContractFunctionCallResult;
import com.syntifi.near.api.rpc.model.contract.NFTMetadata;
import com.syntifi.near.api.rpc.service.contract.AccountIdParam;
import com.syntifi.near.api.rpc.service.contract.ContractFunctionCall;
import com.syntifi.near.api.rpc.service.contract.FTContractFunctionCall;
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
                .builderForBalanceOf(AccountIdParam.builder().accountId("wallet-test.testnet").build())
                .accountId("meta.pool.testnet").build();

        ContractFunctionCallResult result = contractCall.call(nearService);

        LOGGER.debug("{}", result.getResult());

        JsonNode value = new ObjectMapper().readValue(result.getResult(), JsonNode.class);
        LOGGER.debug("{}", value);
    }

    @Test
    void callContractFunction_FTContractFunctionCall_builderForMetadata_return_list() throws IOException {
        ContractFunctionCall contractCall = FTContractFunctionCall.builderForMetadata().accountId("paras-marketplace-v2.testnet").build();

        ContractFunctionCallResult result = contractCall.call(nearService);

        LOGGER.debug("{}", result);

        if (result.getResult() != null) {
            NFTMetadata metadata = NFTMetadata.fromBytes(result.getResult(), new ObjectMapper());
            LOGGER.debug("{}", metadata);
        }
    }
}
