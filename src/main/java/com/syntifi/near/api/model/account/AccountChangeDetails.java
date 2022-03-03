package com.syntifi.near.api.model.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * AccountChangeDetails
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
public class AccountChangeDetails {
    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("locked")
    private String locked;

    @JsonProperty("code_hash")
    private String codeHash;

    @JsonProperty("storage_usage")
    private long storageUsage;

    @JsonProperty("storage_paid_at")
    private long storagePaidAt;
}
