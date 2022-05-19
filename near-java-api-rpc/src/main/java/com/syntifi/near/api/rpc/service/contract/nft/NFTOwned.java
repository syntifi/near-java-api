package com.syntifi.near.api.rpc.service.contract.nft;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Model for mapping owned NFTs data
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@Getter
@Setter
public class NFTOwned {
    private String contractId;
    private NFTContract contract;
    private List<NFTToken> tokens;
    private int total;
}
