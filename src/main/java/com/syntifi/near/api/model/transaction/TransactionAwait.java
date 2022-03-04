package com.syntifi.near.api.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

/**
 * TransactionAwait
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
