package com.syntifi.near.api.rpc.model.transaction.error.action;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.syntifi.near.api.rpc.model.transaction.error.action.*;

/**
 * Action Error Kind interface
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.3.0
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes({@JsonSubTypes.Type(value = AccountAlreadyExists.class, name = "AccountAlreadyExists"),
        @JsonSubTypes.Type(value = AccountDoesNotExist.class, name = "AccountDoesNotExist"),
        @JsonSubTypes.Type(value = ActorNoPermission.class, name = "ActorNoPermission"),
        @JsonSubTypes.Type(value = AddKeyAlreadyExists.class, name = "AddKeyAlreadyExists"),
        @JsonSubTypes.Type(value = CreateAccountNotAllowed.class, name = "CreateAccountNotAllowed"),
        @JsonSubTypes.Type(value = CreateAccountOnlyByRegistrar.class, name = "CreateAccountOnlyByRegistrar"),
        @JsonSubTypes.Type(value = DeleteAccountStaking.class, name = "DeleteAccountStaking"),
        @JsonSubTypes.Type(value = DeleteKeyDoesNotExist.class, name = "DeleteKeyDoesNotExist"),
        @JsonSubTypes.Type(value = FunctionCallError.class, name = "FunctionCallError"),
        @JsonSubTypes.Type(value = InsufficientStake.class, name = "InsufficientStake"),
        @JsonSubTypes.Type(value = LackBalanceForState.class, name = "LackBalanceForState"),
        @JsonSubTypes.Type(value = TriesToStake.class, name = "TriesToStake"),
        @JsonSubTypes.Type(value = TriesToUnstake.class, name = "TriesToUnstake")})
public interface ActionErrorKind {
}
