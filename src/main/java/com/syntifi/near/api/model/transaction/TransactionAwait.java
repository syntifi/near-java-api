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
public class TransactionAwait {
    @JsonProperty("status")
    private Status status;

    @JsonProperty("transaction")
    private Transaction transaction;

    @JsonProperty("transaction_outcome")
    private TransactionOutcome transactionOutcome;

    @JsonProperty("receipts_outcome")
    private Collection<ReceiptOutcome> receiptsOutcome;
}
