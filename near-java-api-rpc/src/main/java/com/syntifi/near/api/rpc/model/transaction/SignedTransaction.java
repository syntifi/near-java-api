package com.syntifi.near.api.rpc.model.transaction;

import com.syntifi.near.api.common.model.key.Signature;
import com.syntifi.near.borshj.Borsh;
import com.syntifi.near.borshj.annotation.BorshField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SignedTransaction
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
@EqualsAndHashCode
public class SignedTransaction implements Borsh {
    @BorshField(order = 1)
    private Transaction transaction;
    @BorshField(order = 2)
    private Signature signature;
}
