package com.syntifi.near.api.indexer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * RecentActivityArg
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecentActivityArgAddKey implements RecentActivityArg {
    @JsonProperty("access_key")
    private RecentActivityAccessKey accessKey;

    @JsonProperty("public_key")
    private String publicKey;
}
