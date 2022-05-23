package com.syntifi.near.api.rpc.service.contract.ft;

import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.common.helper.Formats;
import com.syntifi.near.api.rpc.service.NearService;
import com.syntifi.near.api.rpc.service.contract.AccountIdParam;
import com.syntifi.near.api.rpc.service.contract.ContractMethod;
import com.syntifi.near.api.rpc.service.contract.ContractMethodCaller;
import com.syntifi.near.api.rpc.service.contract.FunctionCallResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;


/**
 * Contract function call object for FTs (Tokens)
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public class FTContractFunctionCall {

    // NFT Constants
    // FT_MINIMUM_STORAGE_BALANCE: nUSDC, nUSDT require minimum 0.0125 NEAR. Came to this conclusion using trial and error.
    public static String FT_MINIMUM_STORAGE_BALANCE_LARGE = Formats.parseNearAmount("0.0125");
    // account creation costs 0.00125 NEAR for storage, 0.00000000003 NEAR for gas
    // https://docs.near.org/docs/api/naj-cookbook#wrap-and-unwrap-near
    static String FT_MINIMUM_STORAGE_BALANCE = Formats.parseNearAmount("0.00125");
    static String FT_STORAGE_DEPOSIT_GAS = Formats.parseNearAmount("0.00000000003");
    // set this to the same value as we use for creating an account and the remainder is refunded
    static String FT_TRANSFER_GAS = Formats.parseNearAmount("0.00000000003");
    // contract might require an attached deposit of of at least 1 yoctoNear on transfer methods
    // "This 1 yoctoNEAR is not enforced by this standard, but is encouraged to do. While ability to receive attached deposit is enforced by this token."
    // from: https://github.com/near/NEPs/issues/141
    static int FT_TRANSFER_DEPOSIT = 1;

    /**
     * Builds a contract function call and returns a typed result
     *
     * @param nearService       the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @return a typed function call result
     * @throws IOException thrown if failed to deserialize result to target class
     */
    public static FunctionCallResult<JsonNode> forMetadata(NearService nearService, String contractAccountId) throws IOException {
        return ContractMethodCaller
                .callFor(StakingMethodCaller.ViewMethod.FT_METADATA, JsonNode.class, nearService, contractAccountId, null);
    }

    /**
     * Builds a contract function call and returns a typed result
     *
     * @param nearService       the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param accountIdParam    the arguments for the target method
     * @return a typed function call result
     * @throws IOException thrown if failed to deserialize result to target class
     */
    public static FunctionCallResult<JsonNode> forBalanceOf(NearService nearService, String contractAccountId, AccountIdParam accountIdParam) throws IOException {
        return ContractMethodCaller
                .callFor(StakingMethodCaller.ViewMethod.FT_BALANCE_OF, JsonNode.class, nearService, contractAccountId, accountIdParam);
    }

    /**
     * Builds a contract function call and returns a typed result
     *
     * @param nearService       the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param ftTransferParam   the arguments for the target method
     * @return a typed function call result
     * @throws IOException thrown if failed to deserialize result to target class
     */
    public static FunctionCallResult<JsonNode> forTransferCall(NearService nearService, String contractAccountId, FTTransferParam ftTransferParam) throws IOException {
        return ContractMethodCaller
                .callFor(StakingMethodCaller.ViewMethod.FT_TRANSFER_CALL, JsonNode.class, nearService, contractAccountId, ftTransferParam);
    }

    /**
     * Contract method definitions
     */
    public static class StakingMethodCaller extends ContractMethodCaller {
        @Getter
        @AllArgsConstructor
        public enum ViewMethod implements ContractMethod {
            FT_METADATA("ft_metadata"),
            FT_TRANSFER_CALL("ft_transfer_call"),
            FT_BALANCE_OF("ft_balance_of");

            private final String methodName;
        }
    }
}
