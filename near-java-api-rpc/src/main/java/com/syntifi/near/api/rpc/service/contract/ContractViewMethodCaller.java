package com.syntifi.near.api.rpc.service.contract;


import com.syntifi.near.api.common.model.common.Base64String;
import com.syntifi.near.api.rpc.model.contract.ContractFunctionCallResult;
import com.syntifi.near.api.rpc.model.identifier.Finality;
import com.syntifi.near.api.rpc.NearClient;

import java.io.IOException;

/**
 * Caller for contract methods
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public abstract class ContractViewMethodCaller {

    /**
     * Calls a contract for specific method
     *
     * @param method            the method to call
     * @param returnClass       the type of result object
     * @param nearClient       the near service to use
     * @param contractAccountId the contract account id
     * @param params            the method parameters
     * @param <T>               type of the result object
     * @return a typed FunctionCallResult for the requested contract method
     * @throws IOException thrown when fails to map json to result object
     */
    public static <T> FunctionCallResult<T> callFor(ContractMethodClass method, Class<T> returnClass, NearClient nearClient, String contractAccountId, ContractMethodParams params) throws IOException {
        ContractFunctionCallResult contractFunctionCallResult = FunctionCall.builder()
                .finality(Finality.OPTIMISTIC)
                .methodName(method.getMethodName())
                .accountId(contractAccountId)
                .args(params != null ? params.toJsonBase64String() : Base64String.fromDecodedString(""))
                .build()
                .call(nearClient);
        return new FunctionCallResult<>(contractFunctionCallResult, returnClass);
    }
}
