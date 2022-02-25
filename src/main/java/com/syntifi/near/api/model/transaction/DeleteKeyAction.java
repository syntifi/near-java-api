package com.syntifi.near.api.model.transaction;

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
    private String publicKey;

    public DeleteKeyAction() {
    }
}