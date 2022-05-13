package com.syntifi.near.api.rpc.service.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@Getter
@Setter
@AllArgsConstructor
public class NFTTokensParam implements ConvertibleToBase64String {
    @JsonProperty("from_index")
    private String fromIndex;
    private int limit;
}
