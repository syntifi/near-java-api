package com.syntifi.near.api.rpc.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class AddKeyAction implements Action {
    @BorshField(order = 1)
    private PublicKey publicKey;

    @BorshField(order = 2)
    private AccessKey accessKey;
}
