package com.syntifi.near.api.service.contract;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntifi.near.api.model.common.Base64String;
import com.syntifi.near.api.model.identifier.Finality;

/**
 *
 */
public class FTContractFunctionCall extends ContractFunctionCall {

    private static final String FT_METADATA_METHOD_NAME = "ft_metadata";
    private static final String FT_BALANCE_OF_METHOD_NAME = "ft_balance_of";

    /**
     * @return a builder for NFT metadata
     */
    public static ContractFunctionCallBuilder builderForMetadata() {
        return new ContractFunctionCallBuilder()
                .finality(Finality.OPTIMISTIC)
                .methodName(FT_METADATA_METHOD_NAME)
                .args(new Base64String(""));
    }

    /**
     * @return a builder for FT Balance
     */
    public static ContractFunctionCallBuilder builderForBalanceOf(AccountIdParam accountIdParam) throws JsonProcessingException {
        return new ContractFunctionCallBuilder()
                .finality(Finality.OPTIMISTIC)
                .methodName(FT_BALANCE_OF_METHOD_NAME)
                .args(new Base64String(new ObjectMapper().writeValueAsString(accountIdParam)));
    }
}
