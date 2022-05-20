package com.syntifi.near.api.rpc.service.contract;

import com.syntifi.near.api.common.service.NearObjectMapper;
import com.syntifi.near.api.rpc.model.contract.ContractFunctionCallResult;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

/**
 * A basic typed function call result
 *
 * @param <R> the result type to deserialize to
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@Getter
@Setter
public class FunctionCallResult<R> {
    private ContractFunctionCallResult contractFunctionCallResult;
    private R result;

    /**
     * Creates an instance of FunctionCallResult with the type for deserializing the result data and
     * the full contract response object
     *
     * @param contractFunctionCallResult the full result object from the request
     * @param clazz                      the class of type R to load the result to
     * @throws IOException thrown if failed to map json to class
     */
    public FunctionCallResult(ContractFunctionCallResult contractFunctionCallResult, Class<R> clazz) throws IOException {
        this.contractFunctionCallResult = contractFunctionCallResult;
        if (contractFunctionCallResult.getResult() != null) {
            result = NearObjectMapper.INSTANCE.readValue(contractFunctionCallResult.getResult(), clazz);
        }
    }
}
