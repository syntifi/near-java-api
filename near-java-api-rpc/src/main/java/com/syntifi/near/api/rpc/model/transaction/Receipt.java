package com.syntifi.near.api.rpc.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Receipt
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
public class Receipt {
    @JsonProperty("predecessor_id")
    private String predecessorId;

    @JsonProperty("receipt_id")
    private String receiptId;

    @JsonProperty("receiver_id")
    private String receiverId;

    @JsonProperty("receipt")
    private ReceiptData receiptData;
}
