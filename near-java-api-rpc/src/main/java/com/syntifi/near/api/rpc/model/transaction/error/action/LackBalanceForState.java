package com.syntifi.near.api.rpc.model.transaction.error.action;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

/**
 * ActionReceipt can't be completed, because the remaining balance will
 * not be enough to cover storage.
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.3.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("LackBalanceForState")
public class LackBalanceForState implements ActionErrorKind{
    @JsonProperty("account_id")
    String accountId;

    BigInteger amount;
}
