package com.syntifi.near.api.rpc.model.transaction.error.action;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.syntifi.near.api.common.model.key.PublicKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  The public key is already used for an existing access key
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.3.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("AddKeyAlreadyExists")
public class AddKeyAlreadyExists implements ActionErrorKind {
    @JsonProperty("account_id")
    String accountId;

    @JsonProperty("public_key")
    PublicKey publicKey;
}
