package com.syntifi.near.api.model.transaction;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.syntifi.near.borshj.annotation.BorshField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

/**
 * TransferAction
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
@EqualsAndHashCode
@JsonTypeName("Transfer")
public class TransferAction implements Action {
    @BorshField(order = 1)
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
