package com.syntifi.near.api.rpc.model.transaction;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Status
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes({@JsonSubTypes.Type(value = SuccessReceiptIdStatus.class, name = "SuccessReceiptId"),
        @JsonSubTypes.Type(value = SuccessValueStatus.class, name = "SuccessValue"),
        @JsonSubTypes.Type(value = FailureStatus.class, name = "Failure")})
public interface Status {
}
