package com.syntifi.near.api.rpc.service.contract.nft.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.common.helper.Strings;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * NFT Token data
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(value = {"approved_account_ids"})
public class NFTToken {

    private static final String DEFAULT_MEDIA_URL = "https://cloudflare-ipfs.com/ipfs/%s";

    @JsonProperty("token_id")
    private String tokenId;
    @JsonProperty("owner_id")
    private String ownerId;

    private NFTTokenMetadata metadata;

    @JsonIgnore
    public String getMediaUrl(NFTContract contract) throws URISyntaxException, MalformedURLException {
        URI uri;
        if (metadata.getMedia() == null) {
            uri = null; // no url if no media
        } else if (Strings.isURL(metadata.getMedia()) || metadata.getMedia().startsWith("data:image")) {
            uri = new URI(metadata.getMedia());
        } else if (contract.getMetadata().getResult().getBaseUri() != null) {
            uri = new URI(contract.getMetadata().getResult().getBaseUri() + "/" + metadata.getMedia());
        } else {
            uri = new URI(String.format(DEFAULT_MEDIA_URL, metadata.getMedia()));
        }

        return uri == null ? null : uri.normalize().toURL().toString();
    }
}
