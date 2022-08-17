package com.syntifi.near.api.rpc.model.transaction.error.action;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Happens when CreateAccount action tries to create an account with
 * account_id which is already exists in the storage
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.3.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("AccountAlreadyExists")
public class AccountAlreadyExists implements ActionErrorKind {
    @JsonProperty("account_id")
    String accountId;
}
