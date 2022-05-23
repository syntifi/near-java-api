package com.syntifi.near.api.rpc.service;

import com.syntifi.crypto.key.Ed25519PrivateKey;
import com.syntifi.near.api.common.key.AbstractKeyTest;
import com.syntifi.near.api.common.model.key.KeyType;
import com.syntifi.near.api.common.model.key.PrivateKey;
import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.rpc.model.transaction.SuccessValueStatus;
import com.syntifi.near.api.rpc.model.transaction.TransactionAwait;
import org.junit.jupiter.api.Test;
import static com.syntifi.near.api.rpc.service.NearServiceTestnetHelper.nearService;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.Random;

public class AccountServiceTest extends AbstractKeyTest {

    /*
    @Test
    void createSubAccountAndTransferNear_should_getStatus_SuccessValueStatus() throws GeneralSecurityException {
        String signerId = "syntifi-alice.testnet";
        String newAccountId = "syntifi-alice.new.testnet";
        BigInteger amount = new BigInteger("1", 10);
        PrivateKey privateKey = aliceNearPrivateKey;
        PublicKey publicKey = aliceNearPublicKey;

        TransactionAwait transactionAwait = AccountService
                .createSubAccount(nearService, newAccountId, amount, signerId, publicKey, privateKey);
        assertInstanceOf(SuccessValueStatus.class, transactionAwait.getStatus());
    }

    @Test
    void createImplicitAccount_should_getStatus_SuccessValueStatus() throws GeneralSecurityException {
        Random random = new Random();
        byte[] key = new byte[32];
        random.nextBytes(key);
        Ed25519PrivateKey pk = new Ed25519PrivateKey();
        pk.setKey(key);
        PrivateKey privateKey = new PrivateKey();
        privateKey.setData(pk.getKey());
        privateKey.setType(KeyType.ED25519);
        PublicKey publicKey = new PublicKey();
        publicKey.setData(pk.derivePublicKey().getKey());
        TransactionAwait transactionAwait = AccountService
                .createImplicitAccount(nearService, publicKey, privateKey);
        assertInstanceOf(SuccessValueStatus.class, transactionAwait.getStatus());
    }
    */
}
