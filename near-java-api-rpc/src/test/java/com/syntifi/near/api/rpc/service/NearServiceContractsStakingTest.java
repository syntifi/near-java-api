package com.syntifi.near.api.rpc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntifi.near.api.rpc.model.contract.ContractFunctionCallResult;
import com.syntifi.near.api.rpc.model.identifier.Finality;
import com.syntifi.near.api.rpc.service.contract.AccountIdParam;
import com.syntifi.near.api.rpc.service.contract.ContractFunctionCall;
import com.syntifi.near.api.rpc.service.contract.StakingContractFunctionCall;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static com.syntifi.near.api.common.json.JsonHelper.OBJECT_MAPPER;
import static com.syntifi.near.api.rpc.service.NearServiceHelper.nearService;

public class NearServiceContractsStakingTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NearServiceContractsStakingTest.class);

    @Test
    void callContractFunction_ContractFunctionCallResult_get_whitelisted_tokens_return_list() throws IOException {
        String args = Base64.getEncoder().encodeToString("{}".getBytes());

        ContractFunctionCallResult result =
                nearService.callContractFunction(Finality.OPTIMISTIC, "ref-finance-101.testnet", "get_whitelisted_tokens", args);

        LOGGER.debug("{}", result.getResult());

        List<String> value = OBJECT_MAPPER.readValue(new String(result.getResult()), ArrayList.class);
        value.forEach(item -> LOGGER.debug("{}", item));
    }

    @Test
    void callContractFunction_StakingContractFunctionCall_builderForAccountTotalBalance_return_value() throws IOException {
        ContractFunctionCall contractCall = StakingContractFunctionCall
                .builderForAccountTotalBalance(AccountIdParam.builder().accountId("wallet-test.testnet").build())
                .accountId("prophet.pool.f863973.m0").build();

        ContractFunctionCallResult result = contractCall.call(nearService);

        LOGGER.debug("{}", result.getResult());

        BigInteger value = new ObjectMapper().readValue(result.getResult(), BigInteger.class);
        LOGGER.debug("{}", value);
    }
}
