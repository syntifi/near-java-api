package com.syntifi.near.api.model.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

/**
 * Outcome
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
public class Outcome {
    @JsonProperty("logs")
    private Collection<String> logs;

    @JsonProperty("receipt_ids")
    private Collection<String> receiptIds;

    @JsonProperty("gas_burnt")
    private long gasBurnt;

    @JsonProperty("tokens_burnt")
    private String tokensBurnt;

    @JsonProperty("executor_id")
    private String executorId;

    @JsonProperty("status")
    private Status status;
    @JsonInclude(value = Include.NON_NULL)

    @JsonProperty("metadata")
    private Metadata metadata;
}
