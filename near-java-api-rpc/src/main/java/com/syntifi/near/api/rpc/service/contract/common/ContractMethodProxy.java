package com.syntifi.near.api.rpc.service.contract.common;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.syntifi.near.api.common.model.common.Base64String;
import com.syntifi.near.api.common.model.key.PrivateKey;
import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.rpc.NearClient;
import com.syntifi.near.api.rpc.model.transaction.TransactionAwait;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractMethodType;
import com.syntifi.near.api.rpc.service.contract.common.param.ContractMethodParams;

import java.math.BigInteger;

/**
 * The interface for contract method clients
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public interface ContractMethodProxy {
    <T> FunctionCallResult<T> invoke(NearClient nearClient, String contractAccountId, String methodName,
                                     ContractMethodType methodType, ContractMethodParams arguments,
                                     Class<T> returnClass) throws Throwable;

    <T> FunctionCallResult<T> invoke(NearClient nearClient, String contractAccountId, String methodName,
                                     ContractMethodType methodType, Base64String arguments,
                                     Class<T> returnClass) throws Throwable;

    TransactionAwait invoke(NearClient nearClient, String contractAccountId, String methodName,
                            ContractMethodType methodType, String accountId, PublicKey publicKey,
                            PrivateKey privateKey, ObjectNode arguments, BigInteger deposit) throws Throwable;
}
