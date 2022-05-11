package com.syntifi.near.api.helper.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * RecentActivityPermission
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecentActivityAccessKey {
    @JsonProperty("nonce")
    private long nonce;

    @JsonProperty("permission")
    private RecentActivityPermission permission;
}
