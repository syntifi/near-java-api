package com.syntifi.near.api.model.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.model.common.ChangeCause;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
public class AccountChange {
    @JsonProperty("cause")
    private ChangeCause cause;

    @JsonProperty("type")
    private String type;

    @JsonProperty("change")
    private AccountChangeDetails change;
}
