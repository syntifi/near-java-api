package com.syntifi.near.api.rpc.service.contract.nft;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.rpc.service.contract.ConvertibleToBase64String;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class NFTTokensForOwnerParam implements ConvertibleToBase64String {
    @JsonProperty("account_id")
    private String accountId;
    @JsonProperty("from_index")
    private String fromIndex;
    private int limit;
}
