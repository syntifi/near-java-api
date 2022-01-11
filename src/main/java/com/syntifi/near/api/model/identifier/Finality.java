package com.syntifi.near.api.model.identifier;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The finality param has two options: optimistic and final.
 * - optimistic uses the latest block recorded on the node that responded to
 * your query (&lt;1 second delay after the transaction is submitted)
 * - final is for a block that has been validated on at least 66% of the nodes
 * in the network (usually takes 2 blocks / approx. 2 second delay)
 * 
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public enum Finality {
    OPTIMISTIC("optimistic"), FINAL("final");

    private String type;

    private Finality(String type) {
        this.type = type;
    }

    @Override
    @JsonValue
    public String toString() {
        return type;
    }
}
