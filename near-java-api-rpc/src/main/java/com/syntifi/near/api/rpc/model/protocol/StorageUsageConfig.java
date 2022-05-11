package com.syntifi.near.api.rpc.model.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * StorageUsageConfig
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
public class StorageUsageConfig {
    @JsonProperty("num_bytes_account")
    private long numBytesAccount;

    @JsonProperty("num_extra_bytes_record")
    private long numExtraBytesRecord;
}
