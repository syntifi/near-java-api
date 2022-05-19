package com.syntifi.near.api.rpc.service.contract.nft;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Model for holding NFT token metadata
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@Getter
@Setter
@ToString
public class NFTTokenMetadata {
    private String title;
    private String description;
    private String media;
    @JsonProperty("media_hash")
    private String mediaHash;
    private long copies;
    @JsonProperty("issued_at")
    private String issuedAt;
    @JsonProperty("expires_at")
    private String expiresAt;
    @JsonProperty("starts_at")
    private String startsAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    private String extra;
    private String reference;
    @JsonProperty("reference_hash")
    private String referenceHash;
}
