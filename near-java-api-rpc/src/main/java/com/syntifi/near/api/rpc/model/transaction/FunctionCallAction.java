package com.syntifi.near.api.rpc.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.syntifi.near.borshj.annotation.BorshField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

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
    @BorshField(order = 1)
    @JsonProperty("method_name")
    private String methodName;

    @BorshField(order = 2)
    @JsonProperty("args")
    private String args;

    @BorshField(order = 3)
    @JsonProperty("gas")
    private BigInteger gas;

    @BorshField(order = 4)
    @JsonProperty("deposit")
    private String deposit;
}
