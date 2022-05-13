package com.syntifi.near.api.rpc.service.contract;

import com.syntifi.near.api.rpc.model.contract.ContractFunctionCallResult;
import com.syntifi.near.api.rpc.model.identifier.Finality;
import com.syntifi.near.api.rpc.service.NearService;

import java.io.IOException;
import java.math.BigInteger;

/**
 * Contract function call object for Staking
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public class StakingFunctionCall {

    private static final String STAKING_GET_ACCOUNT_TOTAL_BALANCE_METHOD_NAME = "get_account_total_balance";

    /**
     * @return a builder for total balance for account call
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
