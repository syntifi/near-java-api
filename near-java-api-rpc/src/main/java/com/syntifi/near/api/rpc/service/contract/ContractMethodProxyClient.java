package com.syntifi.near.api.rpc.service.contract;

import com.syntifi.near.api.common.exception.NearException;
import com.syntifi.near.api.rpc.NearClient;
import com.syntifi.near.api.rpc.service.contract.annotation.MethodType;

import java.io.IOException;

public class ContractMethodProxyClient implements ContractMethodProxy {

    @Override
    public <T> FunctionCallResult<T> invoke(NearClient nearClient, String contractAccountId, String methodName, MethodType methodType,
                                     ContractMethodParams arguments, Class<T> returnClass) throws IOException {
        if (methodType == MethodType.CALL) {
            return null;
        } else if (methodType == MethodType.VIEW) {
            return ContractViewMethod.call(nearClient, contractAccountId, methodName, arguments, returnClass);
        } else {
            throw new NearException("Method Type not specified");
        }
    }
}
