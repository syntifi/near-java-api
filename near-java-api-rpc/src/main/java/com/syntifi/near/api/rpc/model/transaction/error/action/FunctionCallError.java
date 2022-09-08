package com.syntifi.near.api.rpc.model.transaction.error.action;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Error during function call
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.3.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("FunctionCallError")
public class FunctionCallError implements ActionErrorKind {
    @JsonProperty("ExecutionError")
    private String executionError;
}
