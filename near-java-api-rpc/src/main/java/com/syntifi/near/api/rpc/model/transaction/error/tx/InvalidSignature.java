package com.syntifi.near.api.rpc.model.transaction.error.tx;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


/**
 * TX signature is not valid
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.3.0
 */
@Getter
@Setter
@AllArgsConstructor
@JsonTypeName("InvalidSignature")
@JsonDeserialize //This is needed to override the Polymorphic deserializers
public class InvalidSignature implements InvalidTxError {
}
