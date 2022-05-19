package com.syntifi.near.api.rpc.service.contract.nft;

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
 * Contract function call object for NFTs
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public class NFTContractFunctionCall {

    // NFT Constants
    private static final int TOKENS_PER_PAGE = 4;
    private static final String NFT_TRANSFER_GAS = Formats.parseNearAmount("0.00000000003");
    private static final int NFT_TRANSFER_DEPOSIT = 1; // 1 yocto Near

    // Contract Methods
    private static final String NFT_TOTAL_SUPPLY_METHOD_NAME = "nft_total_supply";
    private static final String NFT_METADATA_METHOD_NAME = "nft_metadata";
    private static final String NFT_SUPPLY_FOR_OWNER_METHOD_NAME = "nft_supply_for_owner";
    private static final String NFT_TOKENS_METHOD_NAME = "nft_tokens";
    private static final String NFT_TOKENS_FOR_OWNER_METHOD_NAME = "nft_tokens_for_owner";


    /**
     * Builds a contract function call and returns a typed result
     *
     * @param nearService            the near service instance to use for the contract call
     * @param accountId              the account id
     * @param nftTokensForOwnerParam the arguments for the target method
     * @return a typed function call result
     * @throws IOException thrown if failed to deserialize result to target class
     */
    public static FunctionCallResult<NFTTokenList> forTokensForOwner(NearService nearService, String accountId, NFTTokensForOwnerParam nftTokensForOwnerParam) throws IOException {
        ContractFunctionCallResult contractFunctionCallResult = FunctionCall.builder()
                .finality(Finality.OPTIMISTIC)
                .methodName(NFT_TOKENS_FOR_OWNER_METHOD_NAME)
                .accountId(accountId)
                .args(nftTokensForOwnerParam.toJsonBase64String()).build()
                .call(nearService);
        return new FunctionCallResult<>(contractFunctionCallResult, NFTTokenList.class);
    }

    /**
     * Builds a contract function call and returns a typed result
     *
     * @param nearService    the near service instance to use for the contract call
     * @param accountId      the account id
     * @param nftTokensParam the arguments for the target method
     * @return a typed function call result
     * @throws IOException thrown if failed to deserialize result to target class
     */
    public static FunctionCallResult<JsonNode> forTokens(NearService nearService, String accountId, NFTTokensParam nftTokensParam) throws IOException {
        ContractFunctionCallResult contractFunctionCallResult = FunctionCall.builder()
                .finality(Finality.OPTIMISTIC)
                .methodName(NFT_TOKENS_METHOD_NAME)
                .accountId(accountId)
                .args(nftTokensParam.toJsonBase64String()).build()
                .call(nearService);
        return new FunctionCallResult<>(contractFunctionCallResult, JsonNode.class);
    }

    /**
     * Builds a contract function call and returns a typed result
     *
     * @param nearService the near service instance to use for the contract call
     * @param accountId   the account id
     * @return a typed function call result
     * @throws IOException thrown if failed to deserialize result to target class
     */
    public static FunctionCallResult<String> forTotalSupply(NearService nearService, String accountId) throws IOException {
        ContractFunctionCallResult contractFunctionCallResult = FunctionCall.builder()
                .finality(Finality.OPTIMISTIC)
                .methodName(NFT_TOTAL_SUPPLY_METHOD_NAME)
                .accountId(accountId)
                .args(Base64String.fromDecodedString("")).build()
                .call(nearService);
        return new FunctionCallResult<>(contractFunctionCallResult, String.class);
    }

    /**
     * Builds a contract function call and returns a typed result
     *
     * @param nearService the near service instance to use for the contract call
     * @param accountId   the account id
     * @return a typed function call result
     * @throws IOException thrown if failed to deserialize result to target class
     */
    public static FunctionCallResult<NFTContractMetadata> forMetadata(NearService nearService, String accountId) throws IOException {
        ContractFunctionCallResult contractFunctionCallResult = FunctionCall.builder()
                .finality(Finality.OPTIMISTIC)
                .methodName(NFT_METADATA_METHOD_NAME)
                .accountId(accountId)
                .args(Base64String.fromDecodedString("")).build()
                .call(nearService);
        return new FunctionCallResult<>(contractFunctionCallResult, NFTContractMetadata.class);

    }

    /**
     * Builds a contract function call and returns a typed result
     *
     * @param nearService    the near service instance to use for the contract call
     * @param accountId      the account id
     * @param accountIdParam the arguments for the target method
     * @return a typed function call result
     */
    public static FunctionCallResult<String> forSupplyForOwner(NearService nearService, String accountId, AccountIdParam accountIdParam) throws IOException {
        ContractFunctionCallResult contractFunctionCallResult = FunctionCall.builder()
                .finality(Finality.OPTIMISTIC)
                .methodName(NFT_SUPPLY_FOR_OWNER_METHOD_NAME)
                .accountId(accountId)
                .args(accountIdParam.toJsonBase64String()).build()
                .call(nearService);
        return new FunctionCallResult<>(contractFunctionCallResult, String.class);
    }
}
