package com.syntifi.near.api.model.transaction;

import com.syntifi.near.api.model.accesskey.AccessKey;
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
    private String publicKey;
    private AccessKey accessKey;

    public AddKeyAction() {
    }
}
