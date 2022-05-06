package com.syntifi.near.api.service.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.model.common.Base64String;
import com.syntifi.near.api.model.contract.ContractFunctionCallResult;
import com.syntifi.near.api.model.identifier.Finality;
import com.syntifi.near.api.service.NearService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractFunctionCall {
    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("method_name")
    @Setter(AccessLevel.PROTECTED)
    public String methodName;

    @JsonProperty("args_base64")
    private Base64String args;

    @JsonProperty("finality")
    private Finality finality;

    public ContractFunctionCallResult call(NearService nearService) {
        return nearService.callContractFunction(
                this.getFinality(),
                this.getAccountId(),
                this.getMethodName(),
                this.getArgs().getEncodedString());
    }
}
