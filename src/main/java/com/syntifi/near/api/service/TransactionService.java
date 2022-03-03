package com.syntifi.near.api.service;

import com.syntifi.crypto.key.hash.Sha256;
import com.syntifi.near.api.model.accesskey.AccessKey;
import com.syntifi.near.api.model.block.Block;
import com.syntifi.near.api.model.common.EncodedHash;
import com.syntifi.near.api.model.identifier.Finality;
import com.syntifi.near.api.model.key.PrivateKey;
import com.syntifi.near.api.model.key.PublicKey;
import com.syntifi.near.api.model.key.Signature;
import com.syntifi.near.api.model.transaction.Action;
import com.syntifi.near.api.model.transaction.SignedTransaction;
import com.syntifi.near.api.model.transaction.Transaction;
import com.syntifi.near.api.model.transaction.TransactionAwait;
import com.syntifi.near.api.model.transaction.TransferAction;
import com.syntifi.near.borshj.Borsh;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/**
 * Transaction service provides methods to easily work with transactions
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public class TransactionService {
    /**
     * Sends a {@link TransferAction} transaction waiting for result using  {@link NearService#sendTransactionAwait(String)}
     *
     * @param nearService      the near service instance to use
     * @param signerId         the signer id
     * @param receiverId       the receiver id
     * @param signerPublicKey  signer public key
     * @param signerPrivateKey signer private key
     * @param amount           the amount to transfer
     * @return {@link TransactionAwait} object with the result
     * @throws GeneralSecurityException thrown if failed to sign the transaction
     */
    public static TransactionAwait sendTransferActionAwait(NearService nearService, String signerId, String receiverId,
                                                           PublicKey signerPublicKey, PrivateKey signerPrivateKey,
                                                           BigInteger amount)
            throws GeneralSecurityException {
        return sendTransactionAwait(nearService, signerId, receiverId, signerPublicKey, signerPrivateKey,
                Arrays.asList(
                        TransferAction.builder()
                                .deposit(amount)
                                .build()));
    }

    /**
     * Sends a {@link TransferAction} transaction async using {@link NearService#sendTransactionAsync(String)}
     *
     * @param nearService      the near service instance to use
     * @param signerId         the signer id
     * @param receiverId       the receiver id
     * @param signerPublicKey  signer public key
     * @param signerPrivateKey signer private key
     * @param amount           the amount to transfer
     * @return transaction Hash
     * @throws GeneralSecurityException thrown if failed to sign the transaction
     */
    public static EncodedHash sendTransferActionAsync(NearService nearService, String signerId, String receiverId,
                                                      PublicKey signerPublicKey, PrivateKey signerPrivateKey,
                                                      BigInteger amount)
            throws GeneralSecurityException {
        return sendTransactionAsync(nearService, signerId, receiverId, signerPublicKey, signerPrivateKey,
                Arrays.asList(
                        TransferAction.builder()
                                .deposit(amount)
                                .build()));
    }

    /**
     * Sends a list of {@link Action} transaction waiting for result using {@link NearService#sendTransactionAwait(String)}
     *
     * @param nearService      the near service instance to use
     * @param signerId         the signer id
     * @param receiverId       the receiver id
     * @param signerPublicKey  signer public key
     * @param signerPrivateKey signer private key
     * @param actionList       list of {@link Action} to send
     * @return {@link TransactionAwait} object with the result
     * @throws GeneralSecurityException thrown if failed to sign the transaction
     */
    public static TransactionAwait sendTransactionAwait(NearService nearService, String signerId, String receiverId,
                                                        PublicKey signerPublicKey, PrivateKey signerPrivateKey,
                                                        List<Action> actionList)
            throws GeneralSecurityException {
        return nearService.sendTransactionAwait(prepareTransactionForActionList(nearService, signerId, receiverId, signerPublicKey, signerPrivateKey, actionList));
    }

    /**
     * Sends a list of {@link Action} transaction waiting for result using  {@link NearService#sendTransactionAsync(String)}
     *
     * @param nearService      the near service instance to use
     * @param signerId         the signer id
     * @param receiverId       the receiver id
     * @param signerPublicKey  signer public key
     * @param signerPrivateKey signer private key
     * @param actionList       list of {@link Action} to send
     * @return {@link TransactionAwait} object with the result
     * @throws GeneralSecurityException thrown if failed to sign the transaction
     */
    public static EncodedHash sendTransactionAsync(NearService nearService, String signerId, String receiverId,
                                                   PublicKey signerPublicKey, PrivateKey signerPrivateKey,
                                                   List<Action> actionList)
            throws GeneralSecurityException {
        return EncodedHash.builder()
                .encodedHash(nearService.sendTransactionAsync(
                        prepareTransactionForActionList(nearService, signerId, receiverId, signerPublicKey, signerPrivateKey, actionList)))
                .build();
    }

    /**
     * Prepares the transaction to send
     *
     * @param nearService      the near service instance to use
     * @param signerId         the signer id
     * @param receiverId       the receiver id
     * @param signerPublicKey  signer public key
     * @param signerPrivateKey signer private key
     * @param actionList       list of {@link Action} to send
     * @return the base64 encoded signed transaction string
     * @throws GeneralSecurityException thrown if failed to sign the transaction
     */
    private static String prepareTransactionForActionList(NearService nearService, String signerId, String receiverId,
                                                          PublicKey signerPublicKey, PrivateKey signerPrivateKey,
                                                          List<Action> actionList)
            throws GeneralSecurityException {
        Block block = nearService.getBlock(Finality.FINAL);

        AccessKey accessKey = nearService.viewAccessKey(Finality.FINAL, signerId, signerPublicKey.toEncodedBase58String());

        long nextNonce = accessKey.getNonce() + 1L;

        Transaction transaction = Transaction
                .builder()
                .signerId(signerId)
                .publicKey(signerPublicKey)
                .nonce(nextNonce)
                .receiverId(receiverId)
                .blockHash(block.getHeader().getHash().getDecodedHash())
                .actions(actionList)
                .build();

        byte[] serializedTx = Borsh.serialize(transaction);
        byte[] hashedTx = Sha256.digest(serializedTx);
        byte[] signedTx = signerPrivateKey.getPrivateKey().sign(hashedTx);

        SignedTransaction signedTransaction =
                SignedTransaction.builder()
                        .transaction(transaction)
                        .signature(Signature.builder()
                                .keyType(signerPublicKey.getType())
                                .data(signedTx).build())
                        .build();

        byte[] borshTx = Borsh.serialize(signedTransaction);

        return Base64.getEncoder().encodeToString(borshTx);
    }
}
