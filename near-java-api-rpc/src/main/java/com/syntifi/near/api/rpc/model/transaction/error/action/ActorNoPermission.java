package com.syntifi.near.api.rpc.model.transaction.error.action;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Administrative actions like `DeployContract`, `Stake`, `AddKey`, `DeleteKey`
 * can be proceed only if sender=receiver or the first TX action is a `CreateAccount` action
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.3.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("ActorNoPermission")
public class ActorNoPermission implements ActionErrorKind {
    @JsonProperty("account_id")
    String accountId;

    @JsonProperty("actor_id")
    String actorId;
}
