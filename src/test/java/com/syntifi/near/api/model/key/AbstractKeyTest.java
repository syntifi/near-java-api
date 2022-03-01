package com.syntifi.near.api.model.key;

import com.syntifi.crypto.key.AbstractPrivateKey;
import com.syntifi.crypto.key.AbstractPublicKey;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

import static com.syntifi.near.api.service.JsonHelper.OBJECT_MAPPER;
import static com.syntifi.near.api.service.JsonHelper.loadJsonFromResourceFile;

public abstract class AbstractKeyTest {
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
        Wallet aliceWallet = OBJECT_MAPPER.readValue(loadJsonFromResourceFile("testnet-wallets/alice.json"), Wallet.class);
        Wallet bobWallet = OBJECT_MAPPER.readValue(loadJsonFromResourceFile("testnet-wallets/bob.json"), Wallet.class);

        aliceNearPrivateKey = PrivateKey.fromEncodedBase58String(aliceWallet.getPrivateKey(), PrivateKey.class);
        aliceNearPublicKey = PublicKey.fromEncodedBase58String(aliceWallet.getPublicKey(), PublicKey.class);
        alicePrivateKey = aliceNearPrivateKey.getPrivateKey();
        alicePublicKey = aliceNearPublicKey.getPublicKey();

        bobNearPrivateKey = PrivateKey.fromEncodedBase58String(bobWallet.getPrivateKey(), PrivateKey.class);
        bobNearPublicKey = PublicKey.fromEncodedBase58String(bobWallet.getPublicKey(), PublicKey.class);
        bobPrivateKey = bobNearPrivateKey.getPrivateKey();
        bobPublicKey = bobNearPublicKey.getPublicKey();
    }
}
