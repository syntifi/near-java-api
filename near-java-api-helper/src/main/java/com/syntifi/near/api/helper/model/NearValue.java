package com.syntifi.near.api.helper.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.syntifi.near.api.helper.json.deserializer.NearValueDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * NearValue
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
@JsonDeserialize(using = NearValueDeserializer.class)
public class NearValue {
    @JsonProperty("usd")
    private float usDollars;
    @JsonProperty("eur")
    private float euros;
    @JsonProperty("cny")
    private float chineseYuan;
    @JsonProperty("last_updated_at")
    private long lastUpdatedAt;
}
