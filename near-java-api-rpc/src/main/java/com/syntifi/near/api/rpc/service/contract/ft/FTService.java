package com.syntifi.near.api.rpc.service.contract.ft;

import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.common.helper.Formats;
import com.syntifi.near.api.rpc.NearClient;
import com.syntifi.near.api.rpc.service.contract.common.FunctionCallResult;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractMethod;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractMethodType;
import com.syntifi.near.api.rpc.service.contract.common.param.AccountIdParam;
import com.syntifi.near.api.rpc.service.contract.ft.param.FTTransferParam;


/**
 * Contract function call object for FTs (Tokens)
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public interface FTService {

    // NFT Constants
    // FT_MINIMUM_STORAGE_BALANCE: nUSDC, nUSDT require minimum 0.0125 NEAR. Came to this conclusion using trial and error.
    String FT_MINIMUM_STORAGE_BALANCE_LARGE = Formats.parseNearAmount("0.0125");
    // account creation costs 0.00125 NEAR for storage, 0.00000000003 NEAR for gas
    // https://docs.near.org/docs/api/naj-cookbook#wrap-and-unwrap-near
    String FT_MINIMUM_STORAGE_BALANCE = Formats.parseNearAmount("0.00125");
    String FT_STORAGE_DEPOSIT_GAS = Formats.parseNearAmount("0.00000000003");
    // set this to the same value as we use for creating an account and the remainder is refunded
    String FT_TRANSFER_GAS = Formats.parseNearAmount("0.00000000003");
    // contract might require an attached deposit of of at least 1 yoctoNear on transfer methods
    // "This 1 yoctoNEAR is not enforced by this standard, but is encouraged to do. While ability to receive attached deposit is enforced by this token."
    // from: https://github.com/near/NEPs/issues/141
    int FT_TRANSFER_DEPOSIT = 1;

    /**
     * @param nearClient        near rpc client to use
     * @param contractAccountId the contract's account id
     * @return a {@link FunctionCallResult} for the call
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "ft_metadata")
    FunctionCallResult<JsonNode> getMetadata(NearClient nearClient, String contractAccountId);

    /**
     * @param nearClient        near rpc client to use
     * @param contractAccountId the contract's account id
     * @param accountIdParam    a {@link FunctionCallResult} for the call
     * @return a {@link FunctionCallResult} for the call
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "ft_balance_of")
    FunctionCallResult<JsonNode> getBalanceOf(NearClient nearClient, String contractAccountId, AccountIdParam accountIdParam);

    /**
     * @param nearClient        near rpc client to use
     * @param contractAccountId the contract's account id
     * @param ftTransferParam   parameter object for method call
     * @return a {@link FunctionCallResult} for the call
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "ft_transfer_call")
    FunctionCallResult<JsonNode> transferCall(NearClient nearClient, String contractAccountId, FTTransferParam ftTransferParam);
}
