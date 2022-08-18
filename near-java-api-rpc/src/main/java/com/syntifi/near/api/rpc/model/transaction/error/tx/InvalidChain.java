package com.syntifi.near.api.rpc.model.transaction.error.tx;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Transaction parent block hash doesn't belong to the current chain
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.3.0
 */
@Getter
@Setter
@AllArgsConstructor
@JsonTypeName("InvalidChain")
@JsonDeserialize //This is needed to override the Polymorphic deserializers
public class InvalidChain implements InvalidTxError {
}
