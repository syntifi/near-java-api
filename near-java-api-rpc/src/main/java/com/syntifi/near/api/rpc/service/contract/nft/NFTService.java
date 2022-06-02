package com.syntifi.near.api.rpc.service.contract.nft;

import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.rpc.NearClient;
import com.syntifi.near.api.rpc.service.contract.common.FunctionCallResult;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractMethod;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractMethodType;
import com.syntifi.near.api.rpc.service.contract.common.param.AccountIdParam;
import com.syntifi.near.api.rpc.service.contract.nft.model.NFTContractMetadata;
import com.syntifi.near.api.rpc.service.contract.nft.model.NFTTokenList;
import com.syntifi.near.api.rpc.service.contract.nft.param.NFTTokensForOwnerParam;
import com.syntifi.near.api.rpc.service.contract.nft.param.NFTTokensParam;

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
     * Gets tokens for the given owner
     *
     * @param nearClient             near rpc client to use
     * @param contractAccountId      the contract's account id
     * @param nftTokensForOwnerParam parameter object for method call
     * @return a {@link FunctionCallResult} for the call
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "nft_tokens_for_owner")
    FunctionCallResult<NFTTokenList> getTokensForOwner(NearClient nearClient, String contractAccountId, NFTTokensForOwnerParam nftTokensForOwnerParam);

    /**
     * Get tokens for given parameters
     *
     * @param nearClient        near rpc client to use
     * @param contractAccountId the contract's account id
     * @param nftTokensParam    parameter object for method call
     * @return a {@link FunctionCallResult} for the call
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "nft_tokens")
    FunctionCallResult<JsonNode> getTokens(NearClient nearClient, String contractAccountId, NFTTokensParam nftTokensParam);

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
     * @param accountIdParam    parameter object for method call
     * @return a {@link FunctionCallResult} for the call
     */
    @ContractMethod(type = ContractMethodType.VIEW, name = "nft_supply_for_owner")
    FunctionCallResult<String> getSupplyForOwner(NearClient nearClient, String contractAccountId, AccountIdParam accountIdParam);
}
