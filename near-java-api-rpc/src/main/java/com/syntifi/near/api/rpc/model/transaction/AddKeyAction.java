package com.syntifi.near.api.rpc.model.transaction;

import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.rpc.model.accesskey.AccessKey;
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
    private PublicKey publicKey;

    private AccessKey accessKey;
}
