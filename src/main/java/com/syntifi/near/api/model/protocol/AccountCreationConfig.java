package com.syntifi.near.api.model.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * AccountCreationConfig
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
public class AccountCreationConfig {
    @JsonProperty("min_allowed_top_level_account_length")
    private long minAllowedTopLevelAccountLength;

    @JsonProperty("registrar_account_id")
    private String registrarAccountId;
}
