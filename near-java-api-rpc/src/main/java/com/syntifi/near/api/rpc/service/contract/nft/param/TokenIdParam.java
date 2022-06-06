package com.syntifi.near.api.rpc.service.contract.nft.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.rpc.service.contract.common.param.ContractMethodParams;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * The token id param for contracts
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class TokenIdParam implements ContractMethodParams {
    @JsonProperty("token_id")
    private String tokenId;
}
