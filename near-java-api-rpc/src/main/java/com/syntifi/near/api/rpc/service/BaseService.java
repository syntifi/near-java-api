package com.syntifi.near.api.rpc.service;

import com.syntifi.crypto.key.hash.Sha256;
import com.syntifi.near.api.common.model.key.PrivateKey;
import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.common.model.key.Signature;
import com.syntifi.near.api.rpc.model.accesskey.AccessKey;
import com.syntifi.near.api.rpc.model.block.Block;
import com.syntifi.near.api.rpc.model.identifier.Finality;
import com.syntifi.near.api.rpc.model.transaction.Action;
import com.syntifi.near.api.rpc.model.transaction.SignedTransaction;
import com.syntifi.near.api.rpc.model.transaction.Transaction;
import com.syntifi.near.borshj.Borsh;

import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.List;

public class BaseService {
    /**
     * Prepares the transaction to send
     *
     * @param nearService      the near service instance to use
     * @param signerId         the signer id
     * @param receiverId       the receiver id
     * @param signerPublicKey  signer public key
     * @param signerPrivateKey signer private key
     * @param actionList       list of {@link Action} to send
     * @param nonce
     * @param block
     * @return the base64 encoded signed transaction string
     * @throws GeneralSecurityException thrown if failed to sign the transaction
     */
    public static String prepareTransactionForActionList(NearService nearService, String signerId, String receiverId,
                                                         PublicKey signerPublicKey, PrivateKey signerPrivateKey,
                                                         List<Action> actionList, Long nonce, Block block)
            throws GeneralSecurityException {
        Block block1 = block == null ?nearService.getBlock(Finality.FINAL) : block;
        long nextNonce;

        if (nonce == null) {
            AccessKey accessKey = nearService.viewAccessKey(Finality.FINAL, signerId, signerPublicKey.toEncodedBase58String());
            nextNonce = accessKey.getNonce() + 1L;
        } else {
            nextNonce = 0L;
        }

        Transaction transaction = Transaction
                .builder()
                .signerId(signerId)
                .publicKey(signerPublicKey)
                .nonce(nextNonce)
                .receiverId(receiverId)
                .blockHash(block1.getHeader().getHash().getDecodedHash())
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
