package com.syntifi.near.api.model.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractStateChangeDetails {
    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("key_base64")
    private String keyBase64;

    @JsonProperty("value_base64")
    private String valueBase64;
}
