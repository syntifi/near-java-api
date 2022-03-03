package com.syntifi.near.api.service;

import com.syntifi.near.api.json.JsonHelper;
import com.syntifi.near.api.model.key.Wallet;

import java.io.BufferedWriter;
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
     * @param filepath the full path to file
     * @return a wallet with loaded content
     * @throws IOException thrown if file not found or not a valid wallet json
     */
    public static Wallet loadWalletFromFile(String filepath) throws IOException {
        return JsonHelper.OBJECT_MAPPER.readValue(
                JsonHelper.loadJsonFromFile(filepath),
                Wallet.class);
    }

    /**
     * Writes a wallet to filesystem
     *
     * @param filepath the full path to file
     * @param wallet   the wallet to persist
     * @throws IOException thrown if file could not be written
     */
    public static void writeWalletToFile(String filepath, Wallet wallet) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            writer.write(JsonHelper.OBJECT_MAPPER.writeValueAsString(wallet));
        }
    }
}
