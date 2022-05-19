package com.syntifi.near.api.rpc.service.contract.nft;

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
    @JsonProperty("token_id")
    private String tokenId;
    @JsonProperty("owner_id")
    private String ownerId;

    private NFTTokenMetadata metadata;

    @JsonIgnore
    private String getUrl(NFTContract contract) throws URISyntaxException, MalformedURLException {
        URI uri;
        if (metadata.getMedia() == null) {
            // no url if no media
            uri = null;
        } else if (Strings.isURL(metadata.getMedia())) {
            uri = new URI(metadata.getMedia());
        } else if (contract.getMetadata().getBaseUri() != null) {
            uri = new URI(contract.getMetadata().getBaseUri() + metadata.getMedia());
        } else {
            uri = new URI("https://cloudflare-ipfs.com/ipfs/" + metadata.getMedia());
        }

        return uri == null ? null : uri.toURL().toString();
    }
}
