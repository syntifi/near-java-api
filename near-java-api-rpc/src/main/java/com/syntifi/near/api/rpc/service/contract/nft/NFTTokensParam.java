package com.syntifi.near.api.rpc.service.contract.nft;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.rpc.service.contract.ContractMethodParams;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Parameter class to pass to some NFT contract calls
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class NFTTokensParam implements ContractMethodParams {
    @JsonProperty("from_index")
    private String fromIndex;
    private Integer limit;
}
