package com.syntifi.near.api.rpc.service.contract.ft;

import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.common.helper.Formats;
import com.syntifi.near.api.common.model.key.PrivateKey;
import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.rpc.NearClient;
import com.syntifi.near.api.rpc.model.transaction.TransactionAwait;
import com.syntifi.near.api.rpc.service.contract.common.FunctionCallResult;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractMethod;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractMethodType;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractParameter;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractParameterType;

import java.math.BigInteger;


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
     * NEP-141 method to get the total supply of the given token
     *
     * @param nearClient near rpc client to use
     * @param tokenId    the token's account id
     * @return the total token supply as a string
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "ft_total_supply")
    FunctionCallResult<String> getTotalSupply(NearClient nearClient, String tokenId);

    /**
     * NEP-141 method to get the balance of the given token for the given account
     *
     * @param nearClient near rpc client to use
     * @param tokenId    the token's account id
     * @param accountId  the accountId
     * @return the total token supply as a string
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "ft_balance_of")
    FunctionCallResult<BigInteger> getBalanceOf(NearClient nearClient, String tokenId, @ContractParameter("account_id") String accountId);

    /**
     * NEP-148 containing the metadata for the token
     *
     * @param nearClient near rpc client to use
     * @param tokenId    the token's account id
     * @return a json object with the token's metadata
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "ft_metadata")
    FunctionCallResult<JsonNode> getMetadata(NearClient nearClient, String tokenId);

    @ContractMethod(type = ContractMethodType.CALL, name = "ft_transfer")
    TransactionAwait callTransfer(NearClient nearClient, String tokenId,
                                  @ContractParameter("amount") String amount,
                                  @ContractParameter(value = "receiver_id", type = {ContractParameterType.ARGUMENT}) String receiverId,
                                  @ContractParameter(value = "account_id", type = {ContractParameterType.ACCOUNT_ID}) String accountId,
                                  @ContractParameter(type = ContractParameterType.PUBLIC_KEY) PublicKey publicKey,
                                  @ContractParameter(type = ContractParameterType.PRIVATE_KEY) PrivateKey privateKey);

    @ContractMethod(type = ContractMethodType.CALL, name = "ft_transfer")
    TransactionAwait callTransfer(NearClient nearClient, String tokenId,
                                  @ContractParameter("amount") String amount,
                                  @ContractParameter(value = "receiver_id", type = {ContractParameterType.ARGUMENT}) String receiverId,
                                  @ContractParameter(value = "account_id", type = {ContractParameterType.ACCOUNT_ID}) String accountId,
                                  @ContractParameter(type = ContractParameterType.PUBLIC_KEY) PublicKey publicKey,
                                  @ContractParameter(type = ContractParameterType.PRIVATE_KEY) PrivateKey privateKey,
                                  @ContractParameter(value = "memo") String memo);

    @ContractMethod(type = ContractMethodType.CALL, name = "ft_transfer_call")
    TransactionAwait callTransferCall(NearClient nearClient, String tokenId,
                                      @ContractParameter("amount") String amount,
                                      @ContractParameter(value = "receiver_id", type = {ContractParameterType.ARGUMENT}) String receiverId,
                                      @ContractParameter(value = "account_id", type = {ContractParameterType.ACCOUNT_ID}) String accountId,
                                      @ContractParameter(value = "msg") String msg,
                                      @ContractParameter(type = ContractParameterType.PUBLIC_KEY) PublicKey publicKey,
                                      @ContractParameter(type = ContractParameterType.PRIVATE_KEY) PrivateKey privateKey);

    @ContractMethod(type = ContractMethodType.CALL, name = "ft_transfer_call")
    TransactionAwait callTransferCall(NearClient nearClient, String tokenId,
                                      @ContractParameter("amount") String amount,
                                      @ContractParameter(value = "receiver_id", type = {ContractParameterType.ARGUMENT}) String receiverId,
                                      @ContractParameter(value = "account_id", type = {ContractParameterType.ACCOUNT_ID}) String accountId,
                                      @ContractParameter(value = "msg") String msg,
                                      @ContractParameter(type = ContractParameterType.PUBLIC_KEY) PublicKey publicKey,
                                      @ContractParameter(type = ContractParameterType.PRIVATE_KEY) PrivateKey privateKey,
                                      @ContractParameter(value = "memo") String memo);
}


