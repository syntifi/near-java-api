package com.syntifi.near.api.model.block;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.model.key.PublicKey;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidatorProposal {
    @JsonInclude(value = Include.NON_EMPTY)
    @JsonProperty("validator_stake_struct_version")
    private String validatorStakeStructVersion;

    @JsonInclude(value = Include.NON_EMPTY)
    @JsonProperty("account_id")
    private String accountId;

    @JsonInclude(value = Include.NON_EMPTY)
    @JsonProperty("public_key")
    private PublicKey publicKey;

    @JsonInclude(value = Include.NON_EMPTY)
    @JsonProperty("stake")
    private String stake;
}