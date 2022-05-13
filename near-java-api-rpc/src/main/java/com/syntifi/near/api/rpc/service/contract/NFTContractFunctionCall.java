package com.syntifi.near.api.rpc.service.contract;

import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.common.model.common.Base64String;
import com.syntifi.near.api.rpc.model.contract.ContractFunctionCallResult;
import com.syntifi.near.api.rpc.model.identifier.Finality;
import com.syntifi.near.api.rpc.service.NearService;

import java.io.IOException;

import static com.syntifi.near.api.common.helper.Format.parseNearAmount;

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
    private static final String NFT_TRANSFER_GAS = parseNearAmount("0.00000000003");
    private static final int NFT_TRANSFER_DEPOSIT = 1; // 1 yocto Near

    // Contract Methods
    private static final String NFT_TOTAL_SUPPLY_METHOD_NAME = "nft_total_supply";
    private static final String NFT_METADATA_METHOD_NAME = "nft_metadata";
    private static final String NFT_SUPPLY_FOR_OWNER_METHOD_NAME = "nft_supply_for_owner";
    private static final String NFT_TOKENS_METHOD_NAME = "nft_tokens";
    private static final String NFT_TOKENS_FOR_OWNER_METHOD_NAME = "nft_tokens_for_owner";

    /**
     * @return a builder for NFT Tokens for Owner call
     */
    public static FunctionCallResult<JsonNode> callForTokensForOwner(NearService nearService, String accountId, NFTTokensForOwnerParam nftTokensForOwnerParam) throws IOException {
        ContractFunctionCallResult contractFunctionCallResult = FunctionCall.builder()
                .finality(Finality.OPTIMISTIC)
                .methodName(NFT_TOKENS_FOR_OWNER_METHOD_NAME)
                .accountId(accountId)
                .args(nftTokensForOwnerParam.toJsonBase64String()).build()
                .call(nearService);
        return new FunctionCallResult<>(contractFunctionCallResult, JsonNode.class);
    }

    /**
     * @return a builder for NFT Tokens call
     */
    public static FunctionCallResult<JsonNode> callForTokens(NearService nearService, String accountId, NFTTokensParam nftTokensParam) throws IOException {
        ContractFunctionCallResult contractFunctionCallResult = FunctionCall.builder()
                .finality(Finality.OPTIMISTIC)
                .methodName(NFT_TOKENS_METHOD_NAME)
                .accountId(accountId)
                .args(nftTokensParam.toJsonBase64String()).build()
                .call(nearService);
        return new FunctionCallResult<>(contractFunctionCallResult, JsonNode.class);
    }

    /**
     * @return a builder for NFT Total Supply call
     */
    public static FunctionCallResult<String> callForTotalSupply(NearService nearService, String accountId) throws IOException {
        ContractFunctionCallResult contractFunctionCallResult = FunctionCall.builder()
                .finality(Finality.OPTIMISTIC)
                .methodName(NFT_TOTAL_SUPPLY_METHOD_NAME)
                .accountId(accountId)
                .args(Base64String.fromDecodedString("")).build()
                .call(nearService);
        return new FunctionCallResult<>(contractFunctionCallResult, String.class);
    }

    /**
     * @return a builder for NFT Metadata call
     */
    public static FunctionCallResult<NFTMetadataResult> callForMetadata(NearService nearService, String accountId) throws IOException {
        ContractFunctionCallResult contractFunctionCallResult = FunctionCall.builder()
                .finality(Finality.OPTIMISTIC)
                .methodName(NFT_METADATA_METHOD_NAME)
                .accountId(accountId)
                .args(Base64String.fromDecodedString("")).build()
                .call(nearService);
        return new FunctionCallResult<>(contractFunctionCallResult, NFTMetadataResult.class);

    }

    /**
     * @return a builder for NFT Supply for Owner call
     */
    public static FunctionCallResult<String> callForSupplyForOwner(NearService nearService, String accountId, AccountIdParam accountIdParam) throws IOException {
        ContractFunctionCallResult contractFunctionCallResult = FunctionCall.builder()
                .finality(Finality.OPTIMISTIC)
                .methodName(NFT_SUPPLY_FOR_OWNER_METHOD_NAME)
                .accountId(accountId)
                .args(accountIdParam.toJsonBase64String()).build()
                .call(nearService);
        return new FunctionCallResult<>(contractFunctionCallResult, String.class);
    }
}
