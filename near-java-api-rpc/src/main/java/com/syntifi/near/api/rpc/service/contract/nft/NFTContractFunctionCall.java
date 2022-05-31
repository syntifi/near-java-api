package com.syntifi.near.api.rpc.service.contract.nft;

import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.common.helper.Formats;
import com.syntifi.near.api.rpc.NearClient;
import com.syntifi.near.api.rpc.service.contract.AccountIdParam;
import com.syntifi.near.api.rpc.service.contract.ContractMethod;
import com.syntifi.near.api.rpc.service.contract.ContractMethodCaller;
import com.syntifi.near.api.rpc.service.contract.FunctionCallResult;
import com.syntifi.near.api.rpc.service.contract.nft.model.NFTContract;
import com.syntifi.near.api.rpc.service.contract.nft.model.NFTContractMetadata;
import com.syntifi.near.api.rpc.service.contract.nft.model.NFTTokenList;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
    private static final String NFT_TRANSFER_GAS = Formats.parseNearAmount("0.00000000003");
    private static final int NFT_TRANSFER_DEPOSIT = 1; // 1 yocto Near

    /**
     * Builds a contract function call and returns a typed result
     *
     * @param nearClient the near service instance to use for the contract call
     * @param contract    the contract to load the metadata
     * @throws IOException thrown if failed to deserialize result to target class
     */
    public static void loadContractMetadata(NearClient nearClient, NFTContract contract) throws IOException {
        contract.setMetadata(ContractMethodCaller
                .callFor(StakingMethodCaller.ViewMethod.NFT_METADATA, NFTContractMetadata.class, nearClient, contract.getContractId(), null));
    }

    /**
     * Builds a contract function call and returns a typed result
     *
     * @param nearClient            the near service instance to use for the contract call
     * @param contractAccountId      the contract's account id
     * @param nftTokensForOwnerParam the arguments for the target method
     * @return a typed function call result
     * @throws IOException thrown if failed to deserialize result to target class
     */
    public static FunctionCallResult<NFTTokenList> forTokensForOwner(NearClient nearClient, String contractAccountId, NFTTokensForOwnerParam nftTokensForOwnerParam) throws IOException {
        return ContractMethodCaller
                .callFor(StakingMethodCaller.ViewMethod.NFT_TOKENS_FOR_OWNER, NFTTokenList.class, nearClient, contractAccountId, nftTokensForOwnerParam);
    }

    /**
     * Builds a contract function call and returns a typed result
     *
     * @param nearClient       the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param nftTokensParam    the arguments for the target method
     * @return a typed function call result
     * @throws IOException thrown if failed to deserialize result to target class
     */
    public static FunctionCallResult<JsonNode> forTokens(NearClient nearClient, String contractAccountId, NFTTokensParam nftTokensParam) throws IOException {
        return ContractMethodCaller
                .callFor(StakingMethodCaller.ViewMethod.NFT_TOKENS, JsonNode.class, nearClient, contractAccountId, nftTokensParam);
    }

    /**
     * Builds a contract function call and returns a typed result
     *
     * @param nearClient       the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @return a typed function call result
     * @throws IOException thrown if failed to deserialize result to target class
     */
    public static FunctionCallResult<String> forTotalSupply(NearClient nearClient, String contractAccountId) throws IOException {
        return ContractMethodCaller
                .callFor(StakingMethodCaller.ViewMethod.NFT_TOTAL_SUPPLY, String.class, nearClient, contractAccountId, null);
    }

    /**
     * Builds a contract function call and returns a typed result
     *
     * @param nearClient       the near service instance to use for the contract call
     * @param contractAccountId the contract's account id
     * @param accountIdParam    the arguments for the target method
     * @return a typed function call result
     */
    public static FunctionCallResult<String> forSupplyForOwner(NearClient nearClient, String contractAccountId, AccountIdParam accountIdParam) throws IOException {
        return ContractMethodCaller
                .callFor(StakingMethodCaller.ViewMethod.NFT_SUPPLY_FOR_OWNER, String.class, nearClient, contractAccountId, accountIdParam);
    }

    /**
     * Contract method definitions
     */
    public static class StakingMethodCaller extends ContractMethodCaller {
        @Getter
        @AllArgsConstructor
        public enum ViewMethod implements ContractMethod {
            NFT_TOTAL_SUPPLY("nft_total_supply"),
            NFT_METADATA("nft_metadata"),
            NFT_SUPPLY_FOR_OWNER("nft_supply_for_owner"),
            NFT_TOKENS("nft_tokens"),
            NFT_TOKENS_FOR_OWNER("nft_tokens_for_owner");

            private final String methodName;
        }
    }
}
