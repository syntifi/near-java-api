package com.syntifi.near.api.model.transaction;

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
