package com.syntifi.near.api.rpc.model.transaction.error.tx;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *  An integer overflow occurred during transaction cost estimation.
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.3.0
 */
@Getter
@Setter
@AllArgsConstructor
@JsonTypeName("CostOverflow")
@JsonDeserialize //This is needed to override the Polymorphic deserializers
public class CostOverflow implements InvalidTxError {
}
