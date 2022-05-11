package com.syntifi.near.api.rpc.model.network;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Version
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Version {
    @JsonProperty("version")
    private String number;

    @JsonProperty("build")
    private String build;

    @JsonProperty("rustc_version")
    private String rustcVersion;
}
