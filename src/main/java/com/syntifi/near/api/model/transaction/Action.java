package com.syntifi.near.api.model.transaction;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.syntifi.near.borshj.annotation.BorshSubTypes;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@JsonTypeInfo(use = Id.NAME, include = As.WRAPPER_OBJECT)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TransferAction.class, name = "Transfer"),
        @JsonSubTypes.Type(value = FunctionCallAction.class, name = "FunctionCall")})
@BorshSubTypes({
        @BorshSubTypes.BorshSubType(when = Action.CREATE_ACCOUNT, use = CreateAccountAction.class),
        @BorshSubTypes.BorshSubType(when = Action.DEPLOY_CONTRACT, use = DeployContractAction.class),
        @BorshSubTypes.BorshSubType(when = Action.FUNCTION_CALL, use = FunctionCallAction.class),
        @BorshSubTypes.BorshSubType(when = Action.STAKE, use = StakeAction.class),
        @BorshSubTypes.BorshSubType(when = Action.ADD_KEY, use = AddKeyAction.class),
        @BorshSubTypes.BorshSubType(when = Action.DELETE_KEY, use = DeleteKeyAction.class),
        @BorshSubTypes.BorshSubType(when = Action.DELETE_ACCOUNT, use = DeleteAccountAction.class),
        @BorshSubTypes.BorshSubType(when = Action.TRANSFER, use = TransferAction.class)})
public interface Action {
    int CREATE_ACCOUNT = 0;
    int DEPLOY_CONTRACT = 1;
    int FUNCTION_CALL = 2;
    int TRANSFER = 3;
    int STAKE = 4;
    int ADD_KEY = 5;
    int DELETE_KEY = 6;
    int DELETE_ACCOUNT = 7;
}