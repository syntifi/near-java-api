package com.syntifi.near.api.rpc.service.contract.nft.model;


import com.syntifi.near.api.rpc.service.contract.FunctionCallResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Model for NFT Contract data
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@Getter
@Setter
@NoArgsConstructor
public class NFTContract {
    private String contractId;
    private FunctionCallResult<NFTContractMetadata> metadata;

    public NFTContract(String contractId) {
        this.contractId = contractId;
    }
}
