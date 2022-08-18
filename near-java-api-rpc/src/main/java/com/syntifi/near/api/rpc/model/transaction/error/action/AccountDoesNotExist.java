package com.syntifi.near.api.rpc.model.transaction.error.action;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Happens when TX receiver_id doesn't exist (but action is not CreateAccount)
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.3.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("AccountDoesNotExist")
public class AccountDoesNotExist implements ActionErrorKind {
    @JsonProperty("account_id")
    String accountId;
}
