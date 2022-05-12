package com.syntifi.near.api.rpc.service.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * The param for FT transfers
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class FTTransferParam {
    @JsonProperty("receiver_id")
    private String receiverId;
    @JsonProperty("amount")
    private BigInteger amount;
}
