package com.syntifi.near.api.model.key;

import com.syntifi.crypto.key.encdec.Base58;
import com.syntifi.near.api.model.accesskey.AccessKey;
import com.syntifi.near.api.model.block.Block;
import com.syntifi.near.api.model.identifier.Finality;
import com.syntifi.near.api.model.transaction.SignedTransaction;
import com.syntifi.near.api.model.transaction.Transaction;
import com.syntifi.near.api.model.transaction.TransferAction;
import com.syntifi.near.borshj.Borsh;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Base64;

import static com.syntifi.near.api.service.NearServiceHelper.nearService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionSigningTest extends AbstractKeyTest {

    @Test
    void testSerializeAndDeserializeTransaction() {
        String signerId = "syntifi-alice.testnet";
        String receiverId = "syntifi-bob.testnet";
        PublicKey publicKey = aliceNearPublicKey;

        Block block = nearService.getBlock(Finality.FINAL);
        TransferAction transferAction = new TransferAction();
        transferAction.setDeposit(new BigInteger("100", 10));
        Transaction transaction = Transaction
                .builder()
                .signerId(signerId)
                .publicKey(publicKey)
                .nonce(0L)
                .receiverId(receiverId)
                .blockHash(Base58.decode(block.getHeader().getHash()))
                .actions(Arrays.asList(transferAction))
                .build();

        byte[] serializedTx = Borsh.serialize(transaction);
        Transaction deserialized = Borsh.deserialize(serializedTx, Transaction.class);
        assertEquals(transaction, deserialized);
    }

    @Test
    void sent100toBob() throws GeneralSecurityException {
        String signerId = "syntifi-alice.testnet";
        String receiverId = "syntifi-bob.testnet";
        String amount = "100";
        PublicKey publicKey = aliceNearPublicKey;

        Block block = nearService.getBlock(Finality.FINAL);

        AccessKey accessKey = nearService.viewAccessKey(Finality.FINAL, signerId, publicKey.toEncodedBase58String());

        long nextNonce = accessKey.getNonce() + 1L;

        Transaction transaction = Transaction
                .builder()
                .signerId(signerId)
                .publicKey(publicKey)
                .nonce(nextNonce)
                .receiverId(receiverId)
                .blockHash(Base58.decode(block.getHeader().getHash()))
                .actions(Arrays.asList(
                        TransferAction.builder()
                                .deposit(new BigInteger(amount, 10))
                                .build()))
                .build();

        byte[] serializedTx = Borsh.serialize(transaction);
        byte[] hashedTx = sha256DigestOf(serializedTx);
        byte[] signedTx = alicePrivateKey.sign(hashedTx);

        SignedTransaction signedTransaction =
                SignedTransaction.builder()
                        .transaction(transaction)
                        .signature(Signature.builder()
                                .keyType(publicKey.getKeyType())
                                .data(signedTx).build())
                        .build();

        byte[] borshTx = Borsh.serialize(signedTransaction);

        String base64Tx = Base64.getEncoder().encodeToString(borshTx);

        nearService.sendTransactionAwait(base64Tx);

        byte[] base64TxDec = Base64.getDecoder().decode(base64Tx);

        SignedTransaction signedTransactionDec = Borsh.deserialize(base64TxDec, SignedTransaction.class);

        assertEquals(signedTransaction, signedTransactionDec);

        Transaction transactionDec = signedTransactionDec.getTransaction();

        assertEquals(transaction, transactionDec);

        assertTrue(publicKey.getPublicKey().verify(hashedTx, signedTransactionDec.getSignature().getData()));
    }

    @Test
    void sent100toAlice() throws GeneralSecurityException {
        String signerId = "syntifi-bob.testnet";
        String receiverId = "syntifi-alice.testnet";
        String amount = "100";
        PublicKey publicKey = bobNearPublicKey;

        Block block = nearService.getBlock(Finality.FINAL);

        AccessKey accessKey = nearService.viewAccessKey(Finality.FINAL, signerId, publicKey.toEncodedBase58String());

        long nextNonce = accessKey.getNonce() + 1L;

        Transaction transaction = Transaction
                .builder()
                .signerId(signerId)
                .publicKey(publicKey)
                .nonce(nextNonce)
                .receiverId(receiverId)
                .blockHash(Base58.decode(block.getHeader().getHash()))
                .actions(Arrays.asList(
                        TransferAction.builder()
                                .deposit(new BigInteger(amount, 10))
                                .build()))
                .build();

        byte[] serializedTx = Borsh.serialize(transaction);
        byte[] hashedTx = sha256DigestOf(serializedTx);
        byte[] signedTx = bobPrivateKey.sign(hashedTx);

        SignedTransaction signedTransaction =
                SignedTransaction.builder()
                        .transaction(transaction)
                        .signature(Signature.builder()
                                .keyType(publicKey.getKeyType())
                                .data(signedTx).build())
                        .build();

        byte[] borshTx = Borsh.serialize(signedTransaction);

        String base64Tx = Base64.getEncoder().encodeToString(borshTx);

        nearService.sendTransactionAwait(base64Tx);

        byte[] base64TxDec = Base64.getDecoder().decode(base64Tx);

        SignedTransaction signedTransactionDec = Borsh.deserialize(base64TxDec, SignedTransaction.class);

        assertEquals(signedTransaction, signedTransactionDec);

        Transaction transactionDec = signedTransactionDec.getTransaction();

        assertEquals(transaction, transactionDec);

        assertTrue(publicKey.getPublicKey().verify(hashedTx, signedTransactionDec.getSignature().getData()));
    }

    static byte[] sha256DigestOf(byte[] input) {
        SHA256Digest d = new SHA256Digest();
        d.update(input, 0, input.length);
        byte[] result = new byte[d.getDigestSize()];
        d.doFinal(result, 0);
        return result;
    }
}
