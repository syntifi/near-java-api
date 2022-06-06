package com.syntifi.near.api.rpc.service.contract.nft.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.common.exception.NearException;
import com.syntifi.near.api.common.helper.Strings;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

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
@JsonIgnoreProperties(ignoreUnknown = true)
public class NFTToken {

    @JsonProperty("token_id")
    private String tokenId;
    @JsonProperty("owner_id")
    private String ownerId;
    @JsonProperty("metadata")
    private NFTTokenMetadata metadata;

    /**
     * Get the token media or media reference url given a contract
     *
     * You can parse correctly to an image or reference json by checking the response content-type
     *
     * 1 - First no media and no reference -> no url nor object -> returns null
     * 2 - has media (ignore reference in this case) -> returns String with url
     * 3 - has reference (only considered if media is null) -> returns String with url for off-chain data JSON
     *
     * @param contract the token contract
     * @return the media url
     * @throws NearException if fails to parse the url
     */
    @JsonIgnore
    public URL getMediaOrReferenceURL(NFTContract contract) throws NearException {
        URL returnURL;

        try {
            URI uri = null;
            String baseUri = contract.getMetadata().getResult().getBaseUri();

            if (metadata.getMedia() != null && (Strings.isURL(metadata.getMedia()) || metadata.getMedia().startsWith("data:image"))) {
                uri = new URI((baseUri != null ? baseUri + "/" : "") + metadata.getMedia());
            } else if (metadata.getMedia() != null) {
                uri = new URI((baseUri != null ? baseUri + "/" : "") + metadata.getMedia());
            } else if (metadata.getReference() != null) {
                uri = new URI((baseUri != null ? baseUri + "/" : "") + metadata.getReference());
            }

            returnURL = (uri == null) ? null : uri.normalize().toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new NearException("Invalid url for media/reference", e);
        }

        return returnURL;
    }
}
