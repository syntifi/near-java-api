package com.syntifi.near.api.service.contract;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntifi.near.api.model.common.Base64String;
import com.syntifi.near.api.model.identifier.Finality;

/**
 *
 */
public class StakingContractFunctionCall extends ContractFunctionCall {

    private static final String STAKING_GET_ACCOUNT_TOTAL_BALANCE_METHOD_NAME = "get_account_total_balance";

    /**
     * @return a builder for NFT metadata
     */
    public static ContractFunctionCallBuilder builderForAccountTotalBalance(AccountIdParam accountIdParam) throws JsonProcessingException {
        return new ContractFunctionCallBuilder()
                .finality(Finality.OPTIMISTIC)
                .methodName(STAKING_GET_ACCOUNT_TOTAL_BALANCE_METHOD_NAME)
                .args(new Base64String(new ObjectMapper().writeValueAsString(accountIdParam)));
    }
}
