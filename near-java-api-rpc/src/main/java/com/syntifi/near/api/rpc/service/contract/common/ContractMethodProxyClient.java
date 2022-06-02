package com.syntifi.near.api.rpc.service.contract.common;

import com.syntifi.near.api.common.exception.NearException;
import com.syntifi.near.api.rpc.NearClient;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractMethodType;
import com.syntifi.near.api.rpc.service.contract.common.param.ContractMethodParams;

import java.io.IOException;

/**
 * The default contract method client for proxies
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public class ContractMethodProxyClient implements ContractMethodProxy {

    /**
     * Effectively calls the contract method with all needed data
     *
     * @param nearClient        the near rpc client
     * @param contractAccountId the contract account id
     * @param methodName        the method name to call
     * @param methodType        the type of the method
     * @param arguments         the {@link ContractMethodParams} object for the call
     * @param returnClass       the return class type object
     * @param <T>               the return type
     * @return a {@link FunctionCallResult} mapped for the given type
     * @throws IOException thrown if fails to call the method and map the response
     */
    @Override
    public <T> FunctionCallResult<T> invoke(NearClient nearClient, String contractAccountId, String methodName, ContractMethodType methodType,
                                            ContractMethodParams arguments, Class<T> returnClass) throws IOException {
        if (methodType == ContractMethodType.CALL) {
            return null;
        } else if (methodType == ContractMethodType.VIEW) {
            return ContractViewMethodCaller.call(nearClient, contractAccountId, methodName, arguments, returnClass);
        } else {
            throw new NearException("Method Type not specified");
        }
    }
}
