package com.syntifi.near.api.rpc.service.contract;

import com.syntifi.near.api.common.service.NearObjectMapper;
import com.syntifi.near.api.rpc.model.contract.ContractFunctionCallResult;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

/**
 * @param <T>
 */
@Getter
@Setter
public class FunctionCallResult<T> {
    private ContractFunctionCallResult contractFunctionCallResult;
    private T result;

    public FunctionCallResult(ContractFunctionCallResult contractFunctionCallResult, Class<T> clazz) throws IOException {
        this.contractFunctionCallResult = contractFunctionCallResult;
        if (contractFunctionCallResult.getResult() != null) {
            result = NearObjectMapper.INSTANCE.readValue(contractFunctionCallResult.getResult(), clazz);
        }
    }
}
