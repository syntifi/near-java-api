package com.syntifi.near.api.model.accesskey;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.model.common.EncodedHash;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

/**
 * AccessKeyChanges
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
public class AccessKeyChanges {
    @JsonProperty("block_hash")
    private EncodedHash blockHash;

    @JsonProperty("changes")
    private Collection<AccessKeyChange> changes;
}
