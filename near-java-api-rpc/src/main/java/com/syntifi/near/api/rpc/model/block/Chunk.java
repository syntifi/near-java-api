package com.syntifi.near.api.rpc.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syntifi.near.api.rpc.model.transaction.Receipt;
import com.syntifi.near.api.rpc.model.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

/**
 * Chunk
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
