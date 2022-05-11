package com.syntifi.near.api.rpc.model.network;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.common.model.key.PublicKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CurrentProposal
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
public class CurrentProposal {
    @JsonProperty("validator_stake_struct_version")
    private String validatorStakeStructVersion;

    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("public_key")
    private PublicKey publicKey;

    @JsonProperty("stake")
    private String stake;
}
