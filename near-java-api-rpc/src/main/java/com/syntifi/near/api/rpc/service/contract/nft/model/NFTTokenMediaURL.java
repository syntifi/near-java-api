package com.syntifi.near.api.rpc.service.contract.nft.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

/**
 * Holder class for the token media URL
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@Getter
@Setter
public class NFTTokenMediaURL {
    public enum Type {
        MEDIA,
        REFERENCE,
        EMPTY
    }

    private Type type;
    private URL url;
}
