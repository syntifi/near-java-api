package com.syntifi.near.api.rpc.service.contract.nft;


import lombok.Getter;
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
public class NFTContract {
    private String contractId;
    private NFTContractMetadata metadata;
}
