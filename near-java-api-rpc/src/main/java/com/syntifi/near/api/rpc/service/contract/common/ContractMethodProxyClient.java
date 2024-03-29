package com.syntifi.near.api.rpc.service.contract.common;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.syntifi.near.api.common.exception.NearException;
import com.syntifi.near.api.common.model.common.Base64String;
import com.syntifi.near.api.common.model.common.EncodedHash;
import com.syntifi.near.api.common.model.key.PrivateKey;
import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.rpc.NearClient;
import com.syntifi.near.api.rpc.model.transaction.Action;
import com.syntifi.near.api.rpc.model.transaction.FunctionCallAction;
import com.syntifi.near.api.rpc.model.transaction.TransactionAwait;
import com.syntifi.near.api.rpc.service.TransactionService;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractMethodType;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

/**
 * The default contract method client for proxies
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public class ContractMethodProxyClient implements ContractMethodProxy {

    @Override
    public <T> FunctionCallResult<T> invoke(NearClient nearClient, String contractAccountId, String methodName, ContractMethodType methodType,
                                            Base64String arguments, Class<T> returnClass) throws Throwable {
        if (methodType == ContractMethodType.VIEW) {
            return ContractViewMethodCaller.call(nearClient, contractAccountId, methodName, arguments, returnClass);
        } else {
            throw new NearException("Method Type not specified or invalid for view");
        }
    }

    @Override
    public TransactionAwait invoke(NearClient nearClient, String contractAccountId, String methodName,
                                   ContractMethodType methodType, String accountId, PublicKey accountPublicKey,
                                   PrivateKey accountPrivateKey, ObjectNode arguments, BigInteger deposit) throws Throwable {
        if (methodType == ContractMethodType.CALL) {
            List<Action> actions = getActionList(methodName, arguments, deposit);
            return nearClient.sendTransactionAwait(TransactionService.prepareTransactionForActionList(
                    nearClient, accountId, contractAccountId, accountPublicKey, accountPrivateKey,
                    actions));
        } else {
            throw new NearException("Method Type not specified or invalid for call");
        }
    }

    @Override
    public EncodedHash invokeAsync(NearClient nearClient, String contractAccountId, String methodName,
                                   ContractMethodType methodType, String accountId, PublicKey accountPublicKey,
                                   PrivateKey accountPrivateKey, ObjectNode arguments, BigInteger deposit) throws Throwable {
        if (methodType == ContractMethodType.CALL_ASYNC) {
            List<Action> actions = getActionList(methodName, arguments, deposit);
            return EncodedHash.builder().encodedHash(
                            nearClient.sendTransactionAsync(
                                    TransactionService
                                            .prepareTransactionForActionList(nearClient, accountId, contractAccountId,
                                                    accountPublicKey, accountPrivateKey, actions)))
                    .build();
        } else {
            throw new NearException("Method Type not specified or invalid for async call");
        }
    }

    private List<Action> getActionList(String methodName, ObjectNode arguments, BigInteger deposit) {
        return Arrays.asList(FunctionCallAction.builder()
                .methodName(methodName)
                .args(arguments.toString())
                .gas(30000000000000L)
                //.deposit(new BigInteger(Formats.parseNearAmount("1"), 10))
                //.deposit(BigInteger.valueOf(0L))
                .deposit(deposit)
                .build());
    }
}
