package com.syntifi.near.api.model.transaction;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@JsonTypeInfo(use = Id.NAME, include = As.WRAPPER_OBJECT)
@JsonSubTypes({ @JsonSubTypes.Type(value = SuccessReceiptIdStatus.class, name = "SuccessReceiptId"),
                @JsonSubTypes.Type(value = SuccessValueStatus.class, name = "SuccessValue") })
public interface Status {
}
