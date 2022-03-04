package com.syntifi.near.api.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * FunctionCallAction
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
@JsonTypeName("FunctionCall")
public class FunctionCallAction implements Action {
    @JsonProperty("method_name")
    private String methodName;

    @JsonProperty("args")
    private String args;

    @JsonProperty("gas")
    private long gas;

    @JsonProperty("deposit")
    private String deposit;
}
