package com.syntifi.near.api.rpc.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.rpc.model.accesskey.AccessKey;
import com.syntifi.near.borshj.annotation.BorshField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * AddKeyAction
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
@JsonTypeName("AddKey")
public class AddKeyAction implements Action {
    @BorshField(order = 1)
    @JsonProperty("public_key")
    private PublicKey publicKey;

    @BorshField(order = 2)
    @JsonProperty("access_key")
    private AccessKey accessKey;
}
