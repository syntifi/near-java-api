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
public class RecentActivityArgFunctionCall implements RecentActivityArg {
    @JsonProperty("method_name")
    private String methodName;

    @JsonProperty("args_json")
    private String argsJson;

    @JsonProperty("args_base64")
    private String argsBase64;

    @JsonProperty("gas")
    private long gas;

    @JsonProperty("deposit")
    private String deposit;
}
