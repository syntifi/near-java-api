package com.syntifi.near.api.rpc.service.contract.nft;

import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.common.model.common.EncodedHash;
import com.syntifi.near.api.common.model.key.PrivateKey;
import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.rpc.NearClient;
import com.syntifi.near.api.rpc.model.transaction.TransactionAwait;
import com.syntifi.near.api.rpc.service.contract.common.FunctionCallResult;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractMethod;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractMethodType;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractParameter;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractParameterType;
import com.syntifi.near.api.rpc.service.contract.nft.model.NFTContractMetadata;
import com.syntifi.near.api.rpc.service.contract.nft.model.NFTTokenList;
import com.syntifi.near.api.rpc.service.contract.nft.model.NFTTokenMetadata;

import java.math.BigInteger;

/**
 * Contract function call object for NFTs
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public interface NFTService {

    /**
     * Gets NFT metadata
     *
     * @param nearClient        near rpc client to use
     * @param contractAccountId the contract's account id
     * @return a {@link FunctionCallResult} for the call
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "nft_metadata")
    FunctionCallResult<NFTContractMetadata> getMetadata(NearClient nearClient, String contractAccountId);

    /**
     * Gets NFT metadata
     *
     * @param nearClient        near rpc client to use
     * @param contractAccountId the contract's account id
     * @return a {@link FunctionCallResult} for the call
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "nft_token_metadata")
    FunctionCallResult<NFTTokenMetadata> getTokenMetadata(NearClient nearClient, String contractAccountId,
                                                          @ContractParameter("token_id") String tokenId);

    /**
     * Gets tokens for the given owner
     *
     * @param nearClient near rpc client to use
     * @param accountId  the contract's account id
     * @param fromIndex  the starting index
     * @param limit      limit
     * @return a {@link FunctionCallResult} for the call
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "nft_tokens_for_owner")
    FunctionCallResult<NFTTokenList> getTokensForOwner(NearClient nearClient, String contractAccountId,
                                                       @ContractParameter("account_id") String accountId,
                                                       @ContractParameter("from_index") String fromIndex,
                                                       @ContractParameter("limit") Integer limit);

    /**
     * Gets tokens for the given owner
     *
     * @param nearClient near rpc client to use
     * @param accountId  the contract's account id
     * @param fromIndex  the starting index
     * @return a {@link FunctionCallResult} for the call
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "nft_tokens_for_owner")
    FunctionCallResult<NFTTokenList> getTokensForOwner(NearClient nearClient, String contractAccountId,
                                                       @ContractParameter("account_id") String accountId,
                                                       @ContractParameter("from_index") String fromIndex);

    /**
     * Get tokens for given parameters
     *
     * @param nearClient        near rpc client to use
     * @param contractAccountId the contract's account id
     * @param fromIndex         the starting index
     * @param limit             limit
     * @return a {@link FunctionCallResult} for the call
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "nft_tokens")
    FunctionCallResult<JsonNode> getTokens(NearClient nearClient, String contractAccountId,
                                           @ContractParameter("from_index") String fromIndex,
                                           @ContractParameter("limit") Integer limit);

    /**
     * Get tokens for given parameters
     *
     * @param nearClient        near rpc client to use
     * @param contractAccountId the contract's account id
     * @param fromIndex         the starting index
     * @return a {@link FunctionCallResult} for the call
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "nft_tokens")
    FunctionCallResult<JsonNode> getTokens(NearClient nearClient, String contractAccountId,
                                           @ContractParameter("from_index") String fromIndex);

    /**
     * Get total supply of NFT for given contract
     *
     * @param nearClient        near rpc client to use
     * @param contractAccountId the contract's account id
     * @return a {@link FunctionCallResult} for the call
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "nft_total_supply")
    FunctionCallResult<String> getTotalSupply(NearClient nearClient, String contractAccountId);

    /**
     * Gets the supply of an NFT for an owner
     *
     * @param nearClient        near rpc client to use
     * @param contractAccountId the contract's account id
     * @param accountId         the user's account id
     * @return a {@link FunctionCallResult} for the call
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "nft_supply_for_owner")
    FunctionCallResult<String> getSupplyForOwner(NearClient nearClient, String contractAccountId,
                                                 @ContractParameter("account_id") String accountId);

    /**
     * Synchronous contractCall to transfer nft
     *
     * @param nearClient        the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param receiverAccountId the receiver account id
     * @param tokenId           the token id to transfer
     * @param accountId         the arguments for the view method
     * @param publicKey         the arguments for the view method
     * @param privateKey        the arguments for the view method
     * @param deposit           the deposit for the transfer (1 yocto)
     * @return a {@link TransactionAwait} object
     */
    @ContractMethod(type = ContractMethodType.CALL, name = "nft_transfer")
    TransactionAwait callTransfer(NearClient nearClient, String contractAccountId,
                                  @ContractParameter("receiver_id") String receiverAccountId,
                                  @ContractParameter("token_id") String tokenId,
                                  @ContractParameter(type = ContractParameterType.ACCOUNT_ID) String accountId,
                                  @ContractParameter(type = ContractParameterType.PUBLIC_KEY) PublicKey publicKey,
                                  @ContractParameter(type = ContractParameterType.PRIVATE_KEY) PrivateKey privateKey,
                                  @ContractParameter(type = ContractParameterType.DEPOSIT) BigInteger deposit);

    /**
     * Asynchronous contractCall to transfer nft
     *
     * @param nearClient        the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param receiverAccountId the receiver account id
     * @param tokenId           the token id to transfer
     * @param accountId         the arguments for the view method
     * @param publicKey         the arguments for the view method
     * @param privateKey        the arguments for the view method
     * @param deposit           the deposit for the transfer (1 yocto)
     * @return a {@link EncodedHash} object
     */
    @ContractMethod(type = ContractMethodType.CALL_ASYNC, name = "nft_transfer")
    EncodedHash callTransferAsync(NearClient nearClient, String contractAccountId,
                             @ContractParameter("receiver_id") String receiverAccountId,
                             @ContractParameter("token_id") String tokenId,
                             @ContractParameter(type = ContractParameterType.ACCOUNT_ID) String accountId,
                             @ContractParameter(type = ContractParameterType.PUBLIC_KEY) PublicKey publicKey,
                             @ContractParameter(type = ContractParameterType.PRIVATE_KEY) PrivateKey privateKey,
                             @ContractParameter(type = ContractParameterType.DEPOSIT) BigInteger deposit);
}
