package com.syntifi.near.api.rpc.model.contract;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.syntifi.near.api.common.model.common.ConvertibleFromJsonToObject;
import com.syntifi.near.api.common.model.common.EncodedHash;
import com.syntifi.near.api.rpc.json.serializer.ByteArraySerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

/**
 * ContractFunctionCallResult
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
@ToString
public class ContractFunctionCallResult implements ConvertibleFromJsonToObject {
    @JsonProperty("result")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonSerialize(using = ByteArraySerializer.class)
    private byte[] result;

    @JsonProperty("logs")
    private Collection<String> logs;

    @JsonProperty("block_height")
    private long blockHeight;

    @JsonProperty("block_hash")
    private EncodedHash blockHash;

    @JsonProperty("error")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;

    @Override
    public byte[] getJson() {
        return getResult();
    }
}
