package com.syntifi.near.api.rpc.model.transaction.error.action;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A top-level account ID can only be created by registrar.
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.3.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("CreateAccountOnlyByRegistrar")
public class CreateAccountOnlyByRegistrar implements ActionErrorKind {
    @JsonProperty("account_id")
    String accountId;

    @JsonProperty("registrar_account_id")
    String registrarAccountId;

    @JsonProperty("predecessor_id")
    String predecessorId;
}
