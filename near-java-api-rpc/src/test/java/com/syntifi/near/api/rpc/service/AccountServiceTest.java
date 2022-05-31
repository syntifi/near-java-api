package com.syntifi.near.api.rpc.service;

import com.syntifi.crypto.key.encdec.Hex;
import com.syntifi.crypto.key.mnemonic.Language;
import com.syntifi.crypto.key.mnemonic.MnemonicCode;
import com.syntifi.crypto.key.mnemonic.exception.MnemonicException;
import com.syntifi.near.api.common.helper.Formats;
import com.syntifi.near.api.common.key.AbstractKeyTest;
import com.syntifi.near.api.common.model.common.EncodedHash;
import com.syntifi.near.api.common.model.key.PrivateKey;
import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.rpc.model.transaction.SuccessValueStatus;
import com.syntifi.near.api.rpc.model.transaction.TransactionAwait;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Random;

import static com.syntifi.near.api.rpc.NearClientTestnetHelper.nearClient;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountServiceTest extends AbstractKeyTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceTest.class);

    @Test
    void createImplicitAccountFromMnemonic_should_getStatus_SuccessValueStatus() throws GeneralSecurityException, IOException, MnemonicException.MnemonicLengthException {
        MnemonicCode mnemonicCode = new MnemonicCode(Language.EN);
        List<String> words = mnemonicCode.generateSecureRandomWords();
        PrivateKey newPrivateKey = KeyService.deriveFromSeed(mnemonicCode.toSeed(words, ""));
        PublicKey newPublicKey = KeyService.derivePublicKey(newPrivateKey);
        LOGGER.debug("=============> Mnemonic");
        LOGGER.info(String.join(" ", words));
        LOGGER.debug("=============> Public key ");
        LOGGER.debug(Hex.encode(newPublicKey.getData()));
        LOGGER.debug("=============> Privatekey ");
        LOGGER.debug(Hex.encode(newPrivateKey.getData()));
        String signerId = "syntifi-alice.testnet";
        BigInteger amount = new BigInteger(Formats.parseNearAmount("1"), 10);
        PrivateKey privateKey = aliceNearPrivateKey;
        PublicKey publicKey = aliceNearPublicKey;
        TransactionAwait transactionAwait = TransferService
                .sendTransferActionAwait(nearClient, signerId, Hex.encode(newPublicKey.getData()),
                        publicKey, privateKey, amount);
        assertInstanceOf(SuccessValueStatus.class, transactionAwait.getStatus());
    }

    @Test
    void createSubAccountAndTransferNear_should_getStatus_SuccessValueStatus() throws GeneralSecurityException {
        Random rnd = new Random();
        String signerId = "syntifi-alice.testnet";
        String newAccountId = Math.abs(rnd.nextInt()) + "." + signerId;
        BigInteger amount = new BigInteger(Formats.parseNearAmount("1"), 10);
        PrivateKey privateKey = aliceNearPrivateKey;
        PublicKey publicKey = aliceNearPublicKey;

        EncodedHash transactionHash = AccountService
                .createSubAccountAsync(nearClient, newAccountId, amount, signerId, publicKey, privateKey);
        assertTrue(transactionHash.getDecodedHash().length > 0);
    }

    @Test
    void createNamedAccount_should_getStatus_SuccessValueStatus() throws GeneralSecurityException {
        Random rnd = new Random();
        PrivateKey newPrivateKey = KeyService.deriveRandomKey();
        PublicKey newPublicKey = KeyService.derivePublicKey(newPrivateKey);
        String creatorId = "syntifi-alice.testnet";
        PrivateKey privateKey = aliceNearPrivateKey;
        PublicKey publicKey = aliceNearPublicKey;
        BigInteger amount = new BigInteger(Formats.parseNearAmount("1"), 10);
        EncodedHash transactionHash = AccountService
                .createNamedAccountAsync(nearClient, "testnet",
                        "syntifi-" + Math.abs(rnd.nextInt()) + ".testnet",
                        newPublicKey, amount, creatorId, publicKey, privateKey);
        assertTrue(transactionHash.getDecodedHash().length > 0);
    }
}
