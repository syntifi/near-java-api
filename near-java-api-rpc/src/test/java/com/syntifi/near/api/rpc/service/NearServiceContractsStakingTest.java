package com.syntifi.near.api.rpc.service;

import com.syntifi.near.api.rpc.model.contract.ContractFunctionCallResult;
import com.syntifi.near.api.rpc.model.identifier.Finality;
import com.syntifi.near.api.rpc.service.contract.AccountIdParam;
import com.syntifi.near.api.rpc.service.contract.FunctionCallResult;
import com.syntifi.near.api.rpc.service.contract.StakingFunctionCall;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;

import static com.syntifi.near.api.rpc.service.NearServiceHelper.nearService;

public class NearServiceContractsStakingTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NearServiceContractsStakingTest.class);

    @Test
    void callContractFunction_ContractFunctionCallResult_get_whitelisted_tokens_return_list() throws IOException {
        String args = Base64.getEncoder().encodeToString("{}".getBytes());

        ContractFunctionCallResult callResult =
                nearService.callContractFunction(Finality.OPTIMISTIC, "ref-finance-101.testnet", "get_whitelisted_tokens", args);

        @SuppressWarnings("rawtypes")
        FunctionCallResult<ArrayList> result = new FunctionCallResult<>(callResult, ArrayList.class);

        LOGGER.debug("{}", result.getContractFunctionCallResult().getResult());
        LOGGER.debug("{}", result.getResult());
    }

    @Test
    void callContractFunction_StakingContractFunctionCall_builderForAccountTotalBalance_return_value() throws IOException {
        FunctionCallResult<BigInteger> result = StakingFunctionCall
                .forAccountTotalBalance(nearService, "prophet.pool.f863973.m0", new AccountIdParam("wallet-test.testnet"));

        LOGGER.debug("{}", result.getContractFunctionCallResult().getResult());
        LOGGER.debug("{}", result.getResult());
    }
}
