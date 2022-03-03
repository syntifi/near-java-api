package com.syntifi.near.api.service;

import com.syntifi.near.api.json.JsonHelper;
import com.syntifi.near.api.model.key.Wallet;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WalletServiceTest {

    @Test
    void loadWalletFromFile_inexist_should_throw_IOException() {
        assertThrows(IOException.class, () -> WalletService.loadWalletFromFile(""));
    }

    @Test
    void writeWalletToFile_invalidPath_should_throw_IOException() {
        assertThrows(IOException.class, () -> WalletService.writeWalletToFile("", new Wallet()));
    }

    @Test
    void writeWalletToFile_should_write_wallet_to_file() throws IOException {
        String fileName = "./wallet-d1fe5b15a491ad5bf4c1cdb290f17bee71d21449ddac80fb7b92f38fe530cf5e.json";

        Wallet wallet = loadATestWallet();

        assertDoesNotThrow(() -> WalletService.writeWalletToFile(fileName, wallet));

        assertTrue(Files.exists(Paths.get(fileName)));

        assertNotNull(WalletService.loadWalletFromFile(fileName));

        Files.delete(Paths.get(fileName));
    }

    @Test
    void loadWalletFromFile_should_load_to_Wallet() throws IOException {
        String fileName = "./wallet-d1fe5b15a491ad5bf4c1cdb290f17bee71d21449ddac80fb7b92f38fe530cf5e.json";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(JsonHelper.loadJsonFromResourceFile("testnet-wallets/alice.json"));
        }

        Wallet wallet = WalletService.loadWalletFromFile(fileName);

        Files.delete(Paths.get(fileName));

        assertNotNull(wallet);
    }

    static Wallet loadATestWallet() throws IOException {
        return JsonHelper.OBJECT_MAPPER.readValue(JsonHelper.loadJsonFromResourceFile("testnet-wallets/alice.json"), Wallet.class);
    }
}