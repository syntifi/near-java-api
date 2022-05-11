package com.syntifi.near.api.common.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ChangeCause
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
public class ChangeCause {
    @JsonProperty("type")
    private String type;

    @JsonProperty("tx_hash")
    @JsonInclude(value = Include.NON_EMPTY)
    private String transactionHash;

    @JsonProperty("receipt_hash")
    @JsonInclude(value = Include.NON_EMPTY)
    private String receiptHash;
}
