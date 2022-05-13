package com.syntifi.near.api.rpc.service.contract;

import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.common.model.common.Base64String;
import com.syntifi.near.api.rpc.model.contract.ContractFunctionCallResult;
import com.syntifi.near.api.rpc.model.identifier.Finality;
import com.syntifi.near.api.rpc.service.NearService;

import java.io.IOException;

import static com.syntifi.near.api.common.helper.Format.parseNearAmount;

/**
 * Contract function call object for FTs (Tokens)
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public class FTFunctionCall {

    // NFT Constants

    // Contract Methods
    private static final String FT_METADATA_METHOD_NAME = "ft_metadata";
    private static final String FT_TRANSFER_CALL_METHOD_NAME = "ft_transfer_call";
    // FT_MINIMUM_STORAGE_BALANCE: nUSDC, nUSDT require minimum 0.0125 NEAR. Came to this conclusion using trial and error.
    public static String FT_MINIMUM_STORAGE_BALANCE_LARGE = parseNearAmount("0.0125");
    // account creation costs 0.00125 NEAR for storage, 0.00000000003 NEAR for gas
    // https://docs.near.org/docs/api/naj-cookbook#wrap-and-unwrap-near
    static String FT_MINIMUM_STORAGE_BALANCE = parseNearAmount("0.00125");
    static String FT_STORAGE_DEPOSIT_GAS = parseNearAmount("0.00000000003");
    // set this to the same value as we use for creating an account and the remainder is refunded
    static String FT_TRANSFER_GAS = parseNearAmount("0.00000000003");
    private static final String FT_BALANCE_OF_METHOD_NAME = "ft_balance_of";
    // contract might require an attached deposit of of at least 1 yoctoNear on transfer methods
    // "This 1 yoctoNEAR is not enforced by this standard, but is encouraged to do. While ability to receive attached deposit is enforced by this token."
    // from: https://github.com/near/NEPs/issues/141
    static int FT_TRANSFER_DEPOSIT = 1;

    /**
     * @return a builder for FT metadata
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
     * @return a builder for FT Balance
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
     * @return a builder for FT Transfer
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
