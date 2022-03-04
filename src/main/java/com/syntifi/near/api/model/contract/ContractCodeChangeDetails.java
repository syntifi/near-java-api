package com.syntifi.near.api.model.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ContractCodeChangeDetails
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractCodeChangeDetails {
    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("code_base64")
    private String codeBase64;
}
