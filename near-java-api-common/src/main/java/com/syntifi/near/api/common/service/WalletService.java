package com.syntifi.near.api.common.service;

import com.syntifi.near.api.common.json.JsonHelper;
import com.syntifi.near.api.common.model.key.Wallet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Service methods to manipulate wallets
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public class WalletService {
    /**
     * Loads a wallet from filesystem
     *
     * @param file the file to read from
     * @return a wallet with loaded content
     * @throws IOException thrown if file not found or not a valid wallet json
     */
    public static Wallet loadWalletFromFile(File file) throws IOException {
        return JsonHelper.OBJECT_MAPPER.readValue(
                JsonHelper.loadJsonFromFile(file),
                Wallet.class);
    }

    /**
     * Writes a wallet to filesystem
     *
     * @param file   the file to write to
     * @param wallet the wallet to persist
     * @throws IOException thrown if file could not be written
     */
    public static void writeWalletToFile(File file, Wallet wallet) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(JsonHelper.OBJECT_MAPPER.writeValueAsString(wallet));
        }
    }
}
