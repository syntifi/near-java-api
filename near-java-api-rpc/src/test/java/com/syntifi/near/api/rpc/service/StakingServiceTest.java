package com.syntifi.near.api.rpc.service;

import com.syntifi.near.api.common.helper.Formats;
import com.syntifi.near.api.common.key.AbstractKeyTest;
import com.syntifi.near.api.common.model.key.PrivateKey;
import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.rpc.model.transaction.SuccessValueStatus;
import com.syntifi.near.api.rpc.model.transaction.TransactionAwait;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.GeneralSecurityException;

import static com.syntifi.near.api.rpc.service.NearServiceTestnetHelper.nearService;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StakingServiceTest extends AbstractKeyTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceTest.class);

    @Test
    void depositAndStakeToken_should_return_Success() throws GeneralSecurityException {
        String stakingPool = "stakesstone.pool.f863973.m0";
        BigInteger amount = new BigInteger(Formats.parseNearAmount("1"), 10);
        String accountId = "syntifi-alice.testnet";
        PrivateKey privateKey = aliceNearPrivateKey;
        PublicKey publicKey = aliceNearPublicKey;

        TransactionAwait transactionAwait = StakingService.depositAndStakeTokens(nearService, stakingPool,
                amount, accountId, publicKey, privateKey);
        assertInstanceOf(SuccessValueStatus.class, transactionAwait.getStatus());
    }

}