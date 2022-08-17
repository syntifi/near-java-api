package com.syntifi.near.api.rpc.model.transaction.error.action;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.syntifi.near.api.rpc.model.transaction.error.TxExecutionError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

 /**
 * Returned when an error happened during an action execution
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.3.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("ActionError")
public class ActionError implements TxExecutionError {
    private Long index;

    private ActionErrorKind kind;
}


