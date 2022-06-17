package com.syntifi.near.api.rpc.service.contract.common;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.syntifi.near.api.common.model.common.Base64String;
import com.syntifi.near.api.common.model.common.EncodedHash;
import com.syntifi.near.api.common.model.key.PrivateKey;
import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.rpc.NearClient;
import com.syntifi.near.api.rpc.model.transaction.TransactionAwait;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractMethodType;

import java.math.BigInteger;

/**
 * The interface for contract method clients
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public interface ContractMethodProxy {
    /**
     * Proxy invoke the contracts view methods by sending the Base64String
     *
     * @param nearClient        near client instance
     * @param contractAccountId contract id
     * @param methodName        view method to call
     * @param methodType        view or call
     * @param arguments         contract arguments
     * @param returnClass       return object class
     * @param <T>               type
     * @return a functioncallresult object with type T
     * @throws Throwable thrown if fails to invoke
     */
    <T> FunctionCallResult<T> invoke(NearClient nearClient, String contractAccountId, String methodName,
                                     ContractMethodType methodType, Base64String arguments,
                                     Class<T> returnClass) throws Throwable;

    /**
     * Proxy invoke the contracts call methods via signed transactions
     *
     * @param nearClient        near client instance
     * @param contractAccountId contract id
     * @param methodName        view method to call
     * @param methodType        view or call
     * @param accountId         user accountId
     * @param publicKey         user publicKey
     * @param privateKey        user privateKey to sign the contract
     * @param arguments         contract arguments
     * @param deposit           transaction deposit
     * @return a transactionAwait
     * @throws Throwable thrown if fails to invoke
     */
    TransactionAwait invoke(NearClient nearClient, String contractAccountId, String methodName,
                            ContractMethodType methodType, String accountId, PublicKey publicKey,
                            PrivateKey privateKey, ObjectNode arguments, BigInteger deposit) throws Throwable;

    /**
     * Proxy invoke the contracts call methods via signed transactions async
     *
     * @param nearClient        near client instance
     * @param contractAccountId contract id
     * @param methodName        view method to call
     * @param methodType        view or call
     * @param accountId         user accountId
     * @param publicKey         user publicKey
     * @param privateKey        user privateKey to sign the contract
     * @param arguments         contract arguments
     * @param deposit           transaction deposit
     * @return an EncodedHash of the future transaction
     * @throws Throwable thrown if fails to invoke
     */
    EncodedHash invokeAsync(NearClient nearClient, String contractAccountId, String methodName,
                            ContractMethodType methodType, String accountId, PublicKey publicKey,
                            PrivateKey privateKey, ObjectNode arguments, BigInteger deposit) throws Throwable;
}
