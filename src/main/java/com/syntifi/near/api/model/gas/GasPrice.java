package com.syntifi.near.api.model.gas;

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
public class GasPrice {
    @JsonProperty("gas_price")
    private String value;
}
