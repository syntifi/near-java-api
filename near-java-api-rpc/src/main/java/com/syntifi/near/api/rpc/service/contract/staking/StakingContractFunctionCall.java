package com.syntifi.near.api.rpc.service.contract.staking;

import com.syntifi.near.api.rpc.model.contract.ContractFunctionCallResult;
import com.syntifi.near.api.rpc.model.identifier.Finality;
import com.syntifi.near.api.rpc.service.NearService;
import com.syntifi.near.api.rpc.service.contract.AccountIdParam;
import com.syntifi.near.api.rpc.service.contract.FunctionCall;
import com.syntifi.near.api.rpc.service.contract.FunctionCallResult;

import java.io.IOException;
import java.math.BigInteger;

/**
 * Contract function call object for Staking
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public class StakingContractFunctionCall {

    private static final String STAKING_GET_ACCOUNT_TOTAL_BALANCE_METHOD_NAME = "get_account_total_balance";


    /**
     * Builds a contract function call and returns a typed result
     *
     * @param nearService    the near service instance to use for the contract call
     * @param accountId      the account id
     * @param accountIdParam the arguments for the target method
     * @return a typed function call result
     * @throws IOException thrown if failed to deserialize result to target class
     */
    public static FunctionCallResult<BigInteger> forAccountTotalBalance(NearService nearService, String accountId, AccountIdParam accountIdParam) throws IOException {
        ContractFunctionCallResult contractFunctionCallResult = FunctionCall.builder()
                .finality(Finality.OPTIMISTIC)
                .methodName(STAKING_GET_ACCOUNT_TOTAL_BALANCE_METHOD_NAME)
                .accountId(accountId)
                .args(accountIdParam.toJsonBase64String())
                .build()
                .call(nearService);
        return new FunctionCallResult<>(contractFunctionCallResult, BigInteger.class);
    }
}
