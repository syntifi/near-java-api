package com.syntifi.near.api.rpc.service.contract;

import com.syntifi.near.api.rpc.NearClient;
import com.syntifi.near.api.rpc.service.contract.annotation.MethodType;

public interface ContractMethodProxy {
    <T> FunctionCallResult<T> invoke(NearClient nearClient, String contractAccountId, String methodName, MethodType methodType,
                  ContractMethodParams arguments, Class<T> returnClass) throws Throwable;

}
