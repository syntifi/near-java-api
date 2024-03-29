package com.syntifi.near.api.rpc.service.staking;

import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.common.exception.NearException;
import com.syntifi.near.api.common.helper.Formats;
import com.syntifi.near.api.common.key.AbstractKeyTest;
import com.syntifi.near.api.common.model.common.EncodedHash;
import com.syntifi.near.api.common.model.key.PrivateKey;
import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.rpc.model.transaction.Status;
import com.syntifi.near.api.rpc.model.transaction.TransactionAwait;
import com.syntifi.near.api.rpc.model.transaction.TransactionStatus;
import com.syntifi.near.api.rpc.service.AccountServiceTest;
import com.syntifi.near.api.rpc.service.contract.common.ContractClient;
import com.syntifi.near.api.rpc.service.contract.common.ContractMethodProxyClient;
import com.syntifi.near.api.rpc.service.contract.common.FunctionCallResult;
import com.syntifi.near.api.rpc.service.contract.staking.StakingService;
import com.syntifi.near.api.rpc.service.contract.staking.model.RewardFee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

import static com.syntifi.near.api.rpc.NearClientTestnetHelper.nearClient;
import static org.junit.jupiter.api.Assertions.*;

class StakingServiceTest extends AbstractKeyTest {

    private static final Integer ASYNC_MAX_TRIES = 5;
    private static final Integer ASYNC_INTERVAL_IN_MILLI = 3000;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceTest.class);
    private static final StakingService service = ContractClient.createClientProxy(StakingService.class, new ContractMethodProxyClient());

    // INFO: There must be a delay for the blockchain to process each call
    @BeforeEach
    void wait_for_network() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test
    void testAccountTotalBalance_should_be_bigger_than_zero() {
        String stakingPool = "stakesstone.pool.f863973.m0";
        String accountId = "syntifi-alice.testnet";
        FunctionCallResult<BigInteger> totalValue = service.viewAccountTotalBalance(nearClient, stakingPool, accountId);
        assertEquals(1, totalValue.getResult().compareTo(BigInteger.valueOf(0L)));
    }


    @Test
    void testAccountUnstakedBalance_should_be_bigger_than_zero() {
        String stakingPool = "stakesstone.pool.f863973.m0";
        String accountId = "syntifi-alice.testnet";
        FunctionCallResult<BigInteger> totalValue = service.viewAccountUnstakedBalance(nearClient, stakingPool, accountId);
        assertEquals(1, totalValue.getResult().compareTo(BigInteger.valueOf(0L)));
    }

    @Test
    void testAccountStakedBalance_should_be_bigger_than_zero() {
        String stakingPool = "stakesstone.pool.f863973.m0";
        String accountId = "syntifi-alice.testnet";
        FunctionCallResult<BigInteger> totalValue = service.viewAccountStakedBalance(nearClient, stakingPool, accountId);
        assertEquals(1, totalValue.getResult().compareTo(BigInteger.valueOf(0L)));
    }

    @Test
    void testIsAccountStakedBalanceAvailable_should_not_be_null() {
        String stakingPool = "stakesstone.pool.f863973.m0";
        String accountId = "syntifi-alice.testnet";
        FunctionCallResult<Boolean> totalValue = service.isAccountUnstakedBalanceAvailable(nearClient, stakingPool, accountId);
        assertNotNull(totalValue.getResult());
    }

    @Test
    void testTotalStakedBalance_should_be_bigger_than_zero() {
        String stakingPool = "stakesstone.pool.f863973.m0";
        String accountId = "syntifi-alice.testnet";
        FunctionCallResult<BigInteger> totalValue = service.viewTotalStakedBalance(nearClient, stakingPool, accountId);
        assertNotNull(totalValue.getResult());
    }

    @Test
    void testOwnerId_should_not_be_null() {
        String stakingPool = "stakesstone.pool.f863973.m0";
        String accountId = "syntifi-alice.testnet";
        FunctionCallResult<String> totalValue = service.viewOwnerId(nearClient, stakingPool, accountId);
        assertNotNull(totalValue.getResult());
    }

    @Test
    void testRewardFeeFraction_should_not_be_null() {
        String stakingPool = "stakesstone.pool.f863973.m0";
        String accountId = "syntifi-alice.testnet";
        FunctionCallResult<RewardFee> totalValue = service.viewRewardFeeFraction(nearClient, stakingPool, accountId);
        assertNotNull(totalValue.getResult());
    }

    @Test
    void testFarms_should_not_be_null() {
        String stakingPool = "validator2.factory01.littlefarm.testnet";
        String accountId = "syntifi-alice.testnet";
        FunctionCallResult<JsonNode> totalValue = service.viewFarms(nearClient, stakingPool, accountId, 0L, 10L);
        assertNotNull(totalValue.getResult());
    }

    @Test
    void testFarm_should_not_be_null() {
        String stakingPool = "validator2.factory01.littlefarm.testnet";
        String accountId = "syntifi-alice.testnet";
        String farmId = "token1.littlefarm.testnet";
        FunctionCallResult<JsonNode> totalValue = service.viewFarm(nearClient, stakingPool, accountId, 0L);
        assertNotNull(totalValue.getResult());
    }

    @Test
    void testUnclaimedReward_should_not_be_null() {
        String stakingPool = "validator2.factory01.littlefarm.testnet";
        String accountId = "syntifi-alice.testnet";
        FunctionCallResult<BigInteger> totalValue = service.viewUnclaimedReward(nearClient, stakingPool, accountId, 0L);
        assertNotNull(totalValue.getResult());
    }


    @Test
    void depositAndStake_should_return_Success() {
        String stakingPool = "validator2.factory01.littlefarm.testnet";
        BigInteger amount = new BigInteger(Formats.parseNearAmount("1"), 10);
        String accountId = "syntifi-alice.testnet";
        PrivateKey privateKey = aliceNearPrivateKey;
        PublicKey publicKey = aliceNearPublicKey;

        TransactionAwait transactionAwait = service.callDepositAndStake(nearClient, stakingPool,
                amount, accountId, publicKey, privateKey);
        assertInstanceOf(Status.class, transactionAwait.getStatus());
    }


    @Test
    void depositAndStakeAsync_should_return_EncodedHash_and_complete_with_ok_status() throws InterruptedException {
        String stakingPool = "validator2.factory01.littlefarm.testnet";
        BigInteger amount = new BigInteger(Formats.parseNearAmount("1"), 10);
        String accountId = "syntifi-alice.testnet";
        PrivateKey privateKey = aliceNearPrivateKey;
        PublicKey publicKey = aliceNearPublicKey;

        EncodedHash transactionEncodedHash = service.callDepositAndStakeAsync(nearClient, stakingPool,
                amount, accountId, publicKey, privateKey);
        assertNotNull(transactionEncodedHash);
        assertNotNull(transactionEncodedHash.getEncodedHash());
        assertDoesNotThrow(transactionEncodedHash::getDecodedHash);

        LOGGER.debug("depositAndStakeAsync encodedHash {}", transactionEncodedHash);

        int tries = 0;
        TransactionStatus transactionStatus = null;
        while (tries++ < ASYNC_MAX_TRIES) {
            LOGGER.debug("depositAndStakeAsync getTransactionStatus loop {}", tries);
            try {
                transactionStatus = nearClient.getTransactionStatus(transactionEncodedHash.getEncodedHash(), accountId);
                break;
            } catch (NearException ne) {
                LOGGER.debug("depositAndStakeAsync getTransactionStatus failed, sleeping for {}", ASYNC_INTERVAL_IN_MILLI);
                Thread.sleep(ASYNC_INTERVAL_IN_MILLI);
            }
        }

        assertNotNull(transactionStatus);
        LOGGER.debug("depositAndStakeAsync transactionStatus {}", transactionStatus.getStatus());
        assertInstanceOf(Status.class, transactionStatus.getStatus());
    }

    @Test
    void unstake_should_return_Success() {
        String stakingPool = "stakesstone.pool.f863973.m0";
        String amount = Formats.parseNearAmount("1");
        String accountId = "syntifi-bob.testnet";
        PrivateKey privateKey = bobNearPrivateKey;
        PublicKey publicKey = bobNearPublicKey;

        TransactionAwait transactionAwait = service.callUnstake(nearClient, stakingPool,
                amount, accountId, publicKey, privateKey);
        assertInstanceOf(Status.class, transactionAwait.getStatus());
    }

    @Test
    void deposit_should_return_Success() {
        String stakingPool = "stakesstone.pool.f863973.m0";
        BigInteger amount = new BigInteger(Formats.parseNearAmount("1"), 10);
        String accountId = "syntifi-alice.testnet";
        PrivateKey privateKey = aliceNearPrivateKey;
        PublicKey publicKey = aliceNearPublicKey;

        TransactionAwait transactionAwait = service.callDeposit(nearClient, stakingPool,
                amount, accountId, publicKey, privateKey);
        assertInstanceOf(Status.class, transactionAwait.getStatus());
    }

    @Test
    void stake_should_return_Success() {
        String stakingPool = "stakesstone.pool.f863973.m0";
        BigInteger amount = new BigInteger(Formats.parseNearAmount("1"), 10);
        String accountId = "syntifi-bob.testnet";
        PrivateKey privateKey = bobNearPrivateKey;
        PublicKey publicKey = bobNearPublicKey;

        TransactionAwait transactionAwait = service.callStake(nearClient, stakingPool,
                amount, accountId, publicKey, privateKey);
        assertInstanceOf(Status.class, transactionAwait.getStatus());
    }

/*    @Test
    void unstakeToken_should_return_Success() throws GeneralSecurityException {
        String stakingPool = "stakesstone.pool.f863973.m0";
        BigInteger amount = new BigInteger(Formats.parseNearAmount("1"), 10);
        String accountId = "syntifi-alice.testnet";
        PrivateKey privateKey = aliceNearPrivateKey;
        PublicKey publicKey = aliceNearPublicKey;

        TransactionAwait transactionAwait = StakingService.unStakeTokens(nearClient, stakingPool,
                amount, accountId, publicKey, privateKey);
        assertInstanceOf(SuccessValueStatus.class, transactionAwait.getStatus());
    }*/
}