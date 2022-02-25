package com.syntifi.near.api.model.transaction;

import com.syntifi.near.api.model.key.Signature;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
public class SignedTransaction {
    private Transaction transaction;
    private Signature signature;
}
