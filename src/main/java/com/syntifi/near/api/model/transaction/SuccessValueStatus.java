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
@JsonTypeName("SuccessValue")
public class SuccessValueStatus implements Status {
    @JsonValue
    private String successValue;

    public SuccessValueStatus(String successValue) {
        this.successValue = successValue;
        if (successValue == null) {
            this.successValue = "";
        }
    }
}
