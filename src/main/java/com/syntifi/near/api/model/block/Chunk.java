package com.syntifi.near.api.model.block;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.model.transaction.Receipt;
import com.syntifi.near.api.model.transaction.Transaction;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
public class Chunk {
    @JsonProperty("author")
    private String author;

    @JsonProperty("header")
    private ChunkHeader header;

    @JsonProperty("transactions")
    private Collection<Transaction> transactions;

    @JsonProperty("receipts")
    private Collection<Receipt> receipts;
}
