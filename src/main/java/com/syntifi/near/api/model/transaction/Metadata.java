package com.syntifi.near.api.model.transaction;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
public class Metadata {
    @JsonProperty("version")
    private long version;

    @JsonProperty("gas_profile")
    private Collection<GasProfile> gasProfile;
}
