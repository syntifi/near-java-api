package com.syntifi.near.api.model.transaction;

import com.syntifi.near.api.model.key.PublicKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

/**
 * StakeAction
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
public class StakeAction implements Action {
    private BigInteger stake;

    private PublicKey publicKey;
}
