package com.syntifi.near.api.rpc.model.transaction.error.tx;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Transaction nonce must be `account[access_key].nonce + 1`.
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.3.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("InvalidNonce")
@JsonDeserialize //This is needed to override the Polymorphic deserializers
public class InvalidNonce implements InvalidTxError {
    @JsonProperty("tx_nonce")
    Long txNonce;

    @JsonProperty("ak_nonce")
    Long akNonce;
}
