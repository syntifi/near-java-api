package com.syntifi.near.api.rpc.model.transaction;

import com.fasterxml.jackson.annotation.*;
import com.syntifi.near.api.rpc.model.transaction.error.TxExecutionError;
import lombok.*;

/**
 * Status
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Status {

    @JsonProperty("Failure")
    public TxExecutionError failure;

    @JsonProperty("SuccessValue")
    public SuccessValueStatus successValue;

    @JsonProperty("SuccessReceiptId")
    public SuccessReceiptIdStatus successReceiptId;
}
