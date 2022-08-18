package com.syntifi.near.api.rpc.model.transaction.error.tx;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Transaction nonce is larger than the upper bound given by the block height
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.3.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("NonceTooLarge")
@JsonDeserialize //This is needed to override the Polymorphic deserializers
public class NonceTooLarge implements InvalidTxError {
    @JsonProperty("tx_nonce")
    Long txNonce;

    @JsonProperty("ak_nonce")
    Long akNonce;
}
