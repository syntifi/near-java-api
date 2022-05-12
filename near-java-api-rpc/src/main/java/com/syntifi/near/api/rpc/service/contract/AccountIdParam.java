package com.syntifi.near.api.rpc.service.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * The account id param for contracts
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class AccountIdParam {
    @JsonProperty("account_id")
    private String accountId;
}
