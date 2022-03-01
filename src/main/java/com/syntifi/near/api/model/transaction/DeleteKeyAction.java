package com.syntifi.near.api.model.transaction;

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
public class DeleteKeyAction implements Action {
    private PublicKey publicKey;

    public DeleteKeyAction() {
    }
}
