package com.syntifi.near.api.rpc.model.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.common.model.common.EncodedHash;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

/**
 * ContractStateChanges
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
public class ContractStateChanges {
    @JsonProperty("block_hash")
    private EncodedHash blockHash;

    @JsonProperty("changes")
    private Collection<ContractStateChange> changes;
}
