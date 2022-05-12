package com.syntifi.near.api.rpc.model.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;

import static java.util.Objects.requireNonNull;

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
public class NFTMetadata {
    private String spec;
    private String name;
    private String symbol;
    private String icon;
    @JsonProperty("base_uri")
    private String baseUri;
    private Object reference;
    @JsonProperty("reference_hash")
    private Object referenceHash;

    public static NFTMetadata fromBytes(final byte[] result) throws IOException {
        return fromBytes(result, new ObjectMapper());
    }

    public static NFTMetadata fromBytes(final byte[] result, final ObjectMapper objectMapper) throws IOException {
        requireNonNull(result);
        return objectMapper.readValue(result, NFTMetadata.class);
    }
}
