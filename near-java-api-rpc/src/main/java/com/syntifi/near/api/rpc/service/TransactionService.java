package com.syntifi.near.api.rpc.service;

import com.syntifi.near.api.common.model.common.EncodedHash;
import com.syntifi.near.api.common.model.key.PrivateKey;
import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.rpc.model.transaction.Action;
import com.syntifi.near.api.rpc.model.transaction.TransactionAwait;
import com.syntifi.near.api.rpc.model.transaction.TransferAction;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

/**
 * Transaction service provides methods to easily work with transactions
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public class TransactionService extends BaseService {
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
        return nearService.sendTransactionAwait(BaseService.prepareTransactionForActionList(
                nearService, signerId, receiverId, signerPublicKey, signerPrivateKey, actionList ));
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
                        BaseService.prepareTransactionForActionList(nearService, signerId,
                                receiverId, signerPublicKey, signerPrivateKey, actionList)))
                .build();
    }

}
