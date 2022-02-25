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
public class DeployContractAction implements Action {
    private byte[] code;

    public DeployContractAction() {
    }
}