package com.syntifi.near.api.rpc.model.transaction.error.action;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.syntifi.near.api.common.model.key.PublicKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Account tries to remove an access key that doesn't exist
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.3.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("DeleteKeyDoesNotExist")
public class DeleteKeyDoesNotExist implements ActionErrorKind {
    @JsonProperty("account_id")
    String accountId;

    @JsonProperty("public_key")
    PublicKey publicKey;
}
