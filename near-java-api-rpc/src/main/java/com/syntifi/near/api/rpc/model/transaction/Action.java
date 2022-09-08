package com.syntifi.near.api.rpc.model.transaction;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.syntifi.near.borshj.Borsh;
import com.syntifi.near.borshj.annotation.BorshSubTypes;

/**
 * Action Interface
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@JsonTypeInfo(use = Id.NAME, include = As.WRAPPER_OBJECT)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreateAccountAction.class, name = "CreateAccount"),
        @JsonSubTypes.Type(value = DeployContractAction.class, name = "DeployContract"),
        @JsonSubTypes.Type(value = FunctionCallAction.class, name = "FunctionCall"),
        @JsonSubTypes.Type(value = StakeAction.class, name = "Stake"),
        @JsonSubTypes.Type(value = AddKeyAction.class, name = "AddKey"),
        @JsonSubTypes.Type(value = DeleteKeyAction.class, name = "DeleteKey"),
        @JsonSubTypes.Type(value = DeleteAccountAction.class, name = "DeleteAccount"),
        @JsonSubTypes.Type(value = TransferAction.class, name = "Transfer")
})
@BorshSubTypes({
        @BorshSubTypes.BorshSubType(when = Action.CREATE_ACCOUNT, use = CreateAccountAction.class),
        @BorshSubTypes.BorshSubType(when = Action.DEPLOY_CONTRACT, use = DeployContractAction.class),
        @BorshSubTypes.BorshSubType(when = Action.FUNCTION_CALL, use = FunctionCallAction.class),
        @BorshSubTypes.BorshSubType(when = Action.STAKE, use = StakeAction.class),
        @BorshSubTypes.BorshSubType(when = Action.ADD_KEY, use = AddKeyAction.class),
        @BorshSubTypes.BorshSubType(when = Action.DELETE_KEY, use = DeleteKeyAction.class),
        @BorshSubTypes.BorshSubType(when = Action.DELETE_ACCOUNT, use = DeleteAccountAction.class),
        @BorshSubTypes.BorshSubType(when = Action.TRANSFER, use = TransferAction.class)})
public interface Action extends Borsh {
    byte CREATE_ACCOUNT = 0;
    byte DEPLOY_CONTRACT = 1;
    byte FUNCTION_CALL = 2;
    byte TRANSFER = 3;
    byte STAKE = 4;
    byte ADD_KEY = 5;
    byte DELETE_KEY = 6;
    byte DELETE_ACCOUNT = 7;
}