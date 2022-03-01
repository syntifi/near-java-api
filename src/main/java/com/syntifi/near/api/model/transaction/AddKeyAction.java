package com.syntifi.near.api.model.transaction;

import com.syntifi.near.api.model.accesskey.AccessKey;
import com.syntifi.near.api.model.key.PublicKey;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
public class AddKeyAction implements Action {
    private PublicKey publicKey;

    private AccessKey accessKey;

    public AddKeyAction() {
    }
}
