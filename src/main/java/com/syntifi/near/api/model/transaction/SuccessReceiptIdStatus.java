package com.syntifi.near.api.model.transaction;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
@JsonTypeName("SuccessReceiptId")
public class SuccessReceiptIdStatus implements Status {
    @JsonValue
    private String successReceiptId;

    public SuccessReceiptIdStatus(String successReceiptId) {
        this.successReceiptId = successReceiptId;
    }
}
