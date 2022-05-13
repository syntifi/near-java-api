package com.syntifi.near.api.rpc.service.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * NFT Metadata holder for contract calls
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@Getter
@Setter
@ToString
public class NFTMetadataResult {
    private String spec;
    private String name;
    private String symbol;
    private String icon;
    @JsonProperty("base_uri")
    private String baseUri;
    private Object reference;
    @JsonProperty("reference_hash")
    private Object referenceHash;
}
