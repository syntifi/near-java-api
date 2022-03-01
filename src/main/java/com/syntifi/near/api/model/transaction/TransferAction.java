package com.syntifi.near.api.model.transaction;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
@JsonTypeName("Transfer")
public class TransferAction implements Action {
    @JsonProperty("deposit")
    private BigInteger deposit;

    /**
     * Json expects string, not number
     *
     * @return the deposit as string for json serialization
     */
    @JsonGetter("deposit")
    private String getJsonDeposit() {
        return deposit.toString();
    }
}
