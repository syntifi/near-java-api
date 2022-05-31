package com.syntifi.near.api.rpc.service.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.common.model.common.Base64String;
import com.syntifi.near.api.rpc.model.contract.ContractFunctionCallResult;
import com.syntifi.near.api.rpc.model.identifier.Finality;
import com.syntifi.near.api.rpc.NearClient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Base contract function call object
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FunctionCall {
    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("method_name")
    @Setter(AccessLevel.PROTECTED)
    public String methodName;

    @JsonProperty("args_base64")
    private Base64String args;

    @JsonProperty("finality")
    private Finality finality;

    public ContractFunctionCallResult call(NearClient nearClient) {
        return nearClient.callContractFunction(
                this.getFinality(),
                this.getAccountId(),
                this.getMethodName(),
                this.getArgs().getEncodedString());
    }
}
