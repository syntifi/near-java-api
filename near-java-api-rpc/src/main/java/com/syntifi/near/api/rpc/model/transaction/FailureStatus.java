package com.syntifi.near.api.rpc.model.transaction;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.syntifi.near.api.rpc.model.transaction.error.TxExecutionError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonTypeName("Failure")
public class FailureStatus implements Status {

    private TxExecutionError error;
}