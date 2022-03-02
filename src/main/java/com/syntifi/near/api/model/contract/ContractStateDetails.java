package com.syntifi.near.api.model.contract;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.model.transaction.Proof;

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
public class ContractStateDetails {
    @JsonProperty("key")
    private String key;

    @JsonProperty("value")
    private String value;

    @JsonProperty("proof")
    private Collection<Proof> proof;
}
