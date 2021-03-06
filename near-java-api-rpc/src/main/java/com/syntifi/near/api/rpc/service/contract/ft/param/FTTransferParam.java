package com.syntifi.near.api.rpc.service.contract.ft.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.rpc.service.contract.common.param.ContractMethodParams;
import lombok.AllArgsConstructor;
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
public class FTTransferParam implements ContractMethodParams {
    @JsonProperty("receiver_id")
    private String receiverId;
    @JsonProperty("amount")
    private BigInteger amount;
}
