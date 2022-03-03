package com.syntifi.near.api.model.key;

import com.syntifi.crypto.key.AbstractPrivateKey;
import com.syntifi.crypto.key.AbstractPublicKey;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

import static com.syntifi.near.api.json.JsonHelper.OBJECT_MAPPER;
import static com.syntifi.near.api.json.JsonHelper.loadJsonFromResourceFile;

public abstract class AbstractKeyTest {
    protected static Wallet aliceWallet;
    protected static Wallet bobWallet;

    protected static PrivateKey aliceNearPrivateKey;
    protected static PublicKey aliceNearPublicKey;
    protected static AbstractPrivateKey alicePrivateKey;
    protected static AbstractPublicKey alicePublicKey;

    protected static PrivateKey bobNearPrivateKey;
    protected static PublicKey bobNearPublicKey;
    protected static AbstractPrivateKey bobPrivateKey;
    protected static AbstractPublicKey bobPublicKey;

    @BeforeAll
    static void loadKeys() throws IOException {
        aliceWallet = OBJECT_MAPPER.readValue(loadJsonFromResourceFile("testnet-wallets/alice.json"), Wallet.class);
        bobWallet = OBJECT_MAPPER.readValue(loadJsonFromResourceFile("testnet-wallets/bob.json"), Wallet.class);

        aliceNearPrivateKey = aliceWallet.getPrivateKey();
        aliceNearPublicKey = aliceWallet.getPublicKey();
        alicePrivateKey = aliceNearPrivateKey.getPrivateKey();
        alicePublicKey = aliceNearPublicKey.getPublicKey();

        bobNearPrivateKey = bobWallet.getPrivateKey();
        bobNearPublicKey = bobWallet.getPublicKey();
        bobPrivateKey = bobNearPrivateKey.getPrivateKey();
        bobPublicKey = bobNearPublicKey.getPublicKey();
    }
}
