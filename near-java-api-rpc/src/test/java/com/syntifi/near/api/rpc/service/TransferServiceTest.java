package com.syntifi.near.api.rpc.service;

import com.syntifi.crypto.key.hash.Sha256;
import com.syntifi.near.api.common.key.AbstractKeyTest;
import com.syntifi.near.api.common.model.common.EncodedHash;
import com.syntifi.near.api.common.model.key.PrivateKey;
import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.common.model.key.Signature;
import com.syntifi.near.api.rpc.model.accesskey.AccessKey;
import com.syntifi.near.api.rpc.model.block.Block;
import com.syntifi.near.api.rpc.model.identifier.Finality;
import com.syntifi.near.api.rpc.model.transaction.SignedTransaction;
import com.syntifi.near.api.rpc.model.transaction.Transaction;
import com.syntifi.near.api.rpc.model.transaction.TransferAction;
import com.syntifi.near.borshj.Borsh;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Base64;

import static com.syntifi.near.api.rpc.NearClientTestnetHelper.nearClient;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferServiceTest extends AbstractKeyTest {

    @Test
    void serializeSignAndDeserializeVerifyTransaction_should_match() throws GeneralSecurityException {
        String signerId = "syntifi-alice.testnet";
        String receiverId = "syntifi-bob.testnet";
        String amount = "100";
        PublicKey publicKey = aliceNearPublicKey;

        Block block = nearClient.getBlock(Finality.FINAL);

        AccessKey accessKey = nearClient.viewAccessKey(Finality.FINAL, signerId, publicKey.toEncodedBase58String());

        long nextNonce = accessKey.getNonce() + 1L;

        Transaction transaction = Transaction
                .builder()
                .signerId(signerId)
                .publicKey(publicKey)
                .nonce(nextNonce)
                .receiverId(receiverId)
                .blockHash(block.getHeader().getHash().getDecodedHash())
                .actions(Arrays.asList(
                        TransferAction.builder()
                                .deposit(new BigInteger(amount, 10))
                                .build()))
                .build();

        byte[] serializedTx = Borsh.serialize(transaction);
        byte[] hashedTx = Sha256.digest(serializedTx);
        byte[] signedTx = alicePrivateKey.sign(hashedTx);

        SignedTransaction signedTransaction =
                SignedTransaction.builder()
                        .transaction(transaction)
                        .signature(Signature.builder()
                                .keyType(publicKey.getType())
                                .data(signedTx).build())
                        .build();

        byte[] borshTx = Borsh.serialize(signedTransaction);

        String base64Tx = Base64.getEncoder().encodeToString(borshTx);

        byte[] base64TxDec = Base64.getDecoder().decode(base64Tx);

        SignedTransaction signedTransactionDec = Borsh.deserialize(base64TxDec, SignedTransaction.class);

        assertEquals(signedTransaction, signedTransactionDec);

        Transaction transactionDec = signedTransactionDec.getTransaction();

        assertEquals(transaction, transactionDec);

        assertTrue(publicKey.getPublicKey().verify(hashedTx, signedTransactionDec.getSignature().getData()));
    }

/*    @Test
    void sent100toBobAwait_should_getStatus_SuccessValueStatus() throws GeneralSecurityException {
        String signerId = "syntifi-alice.testnet";
        String receiverId = "syntifi-bob.testnet";
        BigInteger amount = new BigInteger("100", 10);
        PrivateKey privateKey = aliceNearPrivateKey;
        PublicKey publicKey = aliceNearPublicKey;

        TransactionAwait transactionAwait = TransactionService
                .sendTransferActionAwait(nearService, signerId, receiverId, publicKey, privateKey, amount);

        assertInstanceOf(SuccessValueStatus.class, transactionAwait.getStatus());
    }

    @Test
    void sent100toAliceAwait_should_getStatus_SuccessValueStatus() throws GeneralSecurityException {
        String signerId = "syntifi-bob.testnet";
        String receiverId = "syntifi-alice.testnet";
        BigInteger amount = new BigInteger("100", 10);
        PrivateKey privateKey = bobNearPrivateKey;
        PublicKey publicKey = bobNearPublicKey;

        TransactionAwait transactionAwait = TransactionService
                .sendTransferActionAwait(nearService, signerId, receiverId, publicKey, privateKey, amount);

        assertInstanceOf(SuccessValueStatus.class, transactionAwait.getStatus());
    }
*/
    @Test
    void sent100toBobAsync_should_getStatus_SuccessValueStatus() throws GeneralSecurityException {
        String signerId = "syntifi-alice.testnet";
        String receiverId = "syntifi-bob.testnet";
        BigInteger amount = new BigInteger("100", 10);
        PrivateKey privateKey = aliceNearPrivateKey;
        PublicKey publicKey = aliceNearPublicKey;

        EncodedHash transactionAsync = TransferService
                .sendTransferActionAsync(nearClient, signerId, receiverId, publicKey, privateKey, amount);

        assertNotNull(transactionAsync.getEncodedHash());
    }

    @Test
    void sent100toAliceAsync_should_getStatus_SuccessValueStatus() throws GeneralSecurityException {
        String signerId = "syntifi-bob.testnet";
        String receiverId = "syntifi-alice.testnet";
        BigInteger amount = new BigInteger("100", 10);
        PrivateKey privateKey = bobNearPrivateKey;
        PublicKey publicKey = bobNearPublicKey;

        EncodedHash transactionAsync = TransferService
                .sendTransferActionAsync(nearClient, signerId, receiverId, publicKey, privateKey, amount);

        assertNotNull(transactionAsync.getEncodedHash());
    }
}
