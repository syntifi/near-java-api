package com.syntifi.near.api.rpc.model.transaction.error.action;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A newly created account must be under a namespace of the creator account
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.3.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("CreateAccountNotAllowed")
public class CreateAccountNotAllowed implements ActionErrorKind {
    @JsonProperty("account_id")
    String accountId;

    @JsonProperty("predecessor_id")
    String predecessorId;
}
