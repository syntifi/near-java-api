package com.syntifi.near.api.rpc.service.contract.ft.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Model for holding FT token metadata
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@Getter
@Setter
@ToString
public class FTTokenMetadata {
    private String spec;
    private String name;
    private String symbol;
    private String icon;
    private String reference;
    @JsonProperty("reference_hash")
    private String referenceHash;
    private Integer decimals;
}
