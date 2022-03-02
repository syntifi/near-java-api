package com.syntifi.near.api.model.transaction;

import java.util.Collection;

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
public class ReceiptAction {
    @JsonProperty("actions")
    private Collection<Action> actions;

    @JsonProperty("gas_price")
    private String gasPrice;

    // TODO: find which object represents this
    @JsonProperty("input_data_ids")
    private Object[] inputDataIds;

    // TODO: find which object represents this
    @JsonProperty("output_data_receivers")
    private Object[] outputDataReceivers;

    @JsonProperty("signer_id")
    private String signerId;

    @JsonProperty("signer_public_key")
    private String signerPublicKey;
}
