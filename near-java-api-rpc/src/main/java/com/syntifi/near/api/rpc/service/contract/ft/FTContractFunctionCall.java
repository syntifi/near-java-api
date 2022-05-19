package com.syntifi.near.api.rpc.service.contract.ft;

import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.common.helper.Formats;
import com.syntifi.near.api.common.model.common.Base64String;
import com.syntifi.near.api.rpc.model.contract.ContractFunctionCallResult;
import com.syntifi.near.api.rpc.model.identifier.Finality;
import com.syntifi.near.api.rpc.service.NearService;
import com.syntifi.near.api.rpc.service.contract.AccountIdParam;
import com.syntifi.near.api.rpc.service.contract.FunctionCall;
import com.syntifi.near.api.rpc.service.contract.FunctionCallResult;

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
    private static final String FT_BALANCE_OF_METHOD_NAME = "ft_balance_of";
    // contract might require an attached deposit of of at least 1 yoctoNear on transfer methods
    // "This 1 yoctoNEAR is not enforced by this standard, but is encouraged to do. While ability to receive attached deposit is enforced by this token."
    // from: https://github.com/near/NEPs/issues/141
    static int FT_TRANSFER_DEPOSIT = 1;

    // Contract Methods
    private static final String FT_METADATA_METHOD_NAME = "ft_metadata";
    private static final String FT_TRANSFER_CALL_METHOD_NAME = "ft_transfer_call";

    /**
     * Builds a contract function call and returns a typed result
     *
     * @param nearService the near service instance to use for the contract call
     * @param accountId   the account id
     * @return a typed function call result
     * @throws IOException thrown if failed to deserialize result to target class
     */
    public static FunctionCallResult<JsonNode> forMetadata(NearService nearService, String accountId) throws IOException {
        ContractFunctionCallResult contractFunctionCallResult = FunctionCall.builder()
                .finality(Finality.OPTIMISTIC)
                .methodName(FT_METADATA_METHOD_NAME)
                .accountId(accountId)
                .args(Base64String.fromDecodedString("")).build()
                .call(nearService);
        return new FunctionCallResult<>(contractFunctionCallResult, JsonNode.class);
    }

    /**
     * Builds a contract function call and returns a typed result
     *
     * @param nearService    the near service instance to use for the contract call
     * @param accountId      the account id
     * @param accountIdParam the arguments for the target method
     * @return a typed function call result
     * @throws IOException thrown if failed to deserialize result to target class
     */
    public static FunctionCallResult<JsonNode> forBalanceOf(NearService nearService, String accountId, AccountIdParam accountIdParam) throws IOException {
        ContractFunctionCallResult contractFunctionCallResult = FunctionCall.builder()
                .finality(Finality.OPTIMISTIC)
                .methodName(FT_BALANCE_OF_METHOD_NAME)
                .accountId(accountId)
                .args(accountIdParam.toJsonBase64String()).build()
                .call(nearService);
        return new FunctionCallResult<>(contractFunctionCallResult, JsonNode.class);
    }


    /**
     * Builds a contract function call and returns a typed result
     *
     * @param nearService     the near service instance to use for the contract call
     * @param accountId       the account id
     * @param ftTransferParam the arguments for the target method
     * @return a typed function call result
     * @throws IOException thrown if failed to deserialize result to target class
     */
    public static FunctionCallResult<JsonNode> forTransferCall(NearService nearService, String accountId, FTTransferParam ftTransferParam) throws IOException {
        ContractFunctionCallResult contractFunctionCallResult = FunctionCall.builder()
                .finality(Finality.OPTIMISTIC)
                .methodName(FT_TRANSFER_CALL_METHOD_NAME)
                .accountId(accountId)
                .args(ftTransferParam.toJsonBase64String()).build()
                .call(nearService);
        return new FunctionCallResult<>(contractFunctionCallResult, JsonNode.class);
    }
}
