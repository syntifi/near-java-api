package com.syntifi.near.api.model.network;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidationStatus {
    @JsonProperty("current_validators")
    private Collection<Validator> currentValidators;

    @JsonProperty("next_validators")
    private Collection<Validator> nextValidators;

    @JsonProperty("current_fishermen")
    private Collection<Fisherman> currentFishermen;

    @JsonProperty("next_fishermen")
    private Collection<Fisherman> nextFishermen;

    @JsonProperty("current_proposals")
    private Collection<CurrentProposal> currentProposals;

    @JsonProperty("prev_epoch_kickout")
    private Collection<PrevEpochKickout> prevEpochKickout;

    @JsonProperty("epoch_start_height")
    private long epochStartHeight;

    @JsonProperty("epoch_height")
    private long epochHeight;
}
