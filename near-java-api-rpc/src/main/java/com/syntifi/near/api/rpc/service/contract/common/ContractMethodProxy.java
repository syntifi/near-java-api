package com.syntifi.near.api.rpc.service.contract.common;

import com.syntifi.near.api.rpc.NearClient;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractMethodType;
import com.syntifi.near.api.rpc.service.contract.common.param.ContractMethodParams;

/**
 * The interface for contract method clients
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public interface ContractMethodProxy {
    <T> FunctionCallResult<T> invoke(NearClient nearClient, String contractAccountId, String methodName, ContractMethodType methodType,
                                     ContractMethodParams arguments, Class<T> returnClass) throws Throwable;

}
