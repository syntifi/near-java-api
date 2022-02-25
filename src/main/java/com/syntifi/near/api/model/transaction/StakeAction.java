package com.syntifi.near.api.model.transaction;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
public class StakeAction implements Action {
    private BigInteger stake;
    private String publicKey;

    public StakeAction() {
    }
}
