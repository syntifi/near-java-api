package com.syntifi.near.api.rpc.service.contract;

import com.syntifi.near.api.common.model.common.Base64String;
import com.syntifi.near.api.rpc.model.identifier.Finality;

import static com.syntifi.near.api.common.helper.Format.parseNearAmount;

/**
 * Contract function call object for NFTs
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public class NFTContractFunctionCall extends ContractFunctionCall {

    // NFT Constants
    private static final int TOKENS_PER_PAGE = 4;
    private static final String NFT_TRANSFER_GAS = parseNearAmount("0.00000000003");
    private static final int NFT_TRANSFER_DEPOSIT = 1; // 1 yocto Near

    // Contract Methods
    private static final String NFT_METADATA_METHOD_NAME = "nft_metadata";
    private static final String NFT_SUPPLY_FOR_OWNER_METHOD_NAME = "nft_supply_for_owner";
    private static final String NFT_TOKEN_METHOD_NAME = "nft_token";

    /**
     * @return a builder for NFT metadata
     */
    public static ContractFunctionCallBuilder builderForMetadata() {
        return new ContractFunctionCallBuilder()
                .finality(Finality.OPTIMISTIC)
                .methodName(NFT_METADATA_METHOD_NAME)
                .args(new Base64String(""));
    }
}
