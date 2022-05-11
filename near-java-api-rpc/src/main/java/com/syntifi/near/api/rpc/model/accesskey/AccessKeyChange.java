package com.syntifi.near.api.rpc.model.accesskey;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.common.model.common.ChangeCause;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * AccessKeyChange
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
public class AccessKeyChange {
    @JsonProperty("cause")
    private ChangeCause cause;

    @JsonProperty("type")
    private String type;

    @JsonProperty("change")
    private AccessKeyChangeDetails details;
}
