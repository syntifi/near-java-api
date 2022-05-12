package com.syntifi.near.api.rpc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntifi.near.api.rpc.model.contract.ContractFunctionCallResult;
import com.syntifi.near.api.rpc.model.contract.NFTMetadata;
import com.syntifi.near.api.rpc.service.contract.ContractFunctionCall;
import com.syntifi.near.api.rpc.service.contract.NFTContractFunctionCall;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.syntifi.near.api.rpc.service.NearServiceHelper.nearService;

public class NearServiceContractsNFTsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NearServiceContractsNFTsTest.class);

    @Test
    void callContractFunction_NFTContractFunctionCall_builderForMetadata_return_list() throws IOException {
        ContractFunctionCall contractCall = NFTContractFunctionCall.builderForMetadata().accountId("paras-token-v2.testnet").build();

        ContractFunctionCallResult result = contractCall.call(nearService);

        LOGGER.debug("{}", result.getResult());

        NFTMetadata metadata = NFTMetadata.fromBytes(result.getResult(), new ObjectMapper());
        LOGGER.debug("{}", metadata);
    }
}
