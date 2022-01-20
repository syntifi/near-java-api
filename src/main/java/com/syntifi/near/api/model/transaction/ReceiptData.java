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
public class ReceiptData {
    @JsonProperty("Action")
    private ReceiptAction receiptAction;
}
