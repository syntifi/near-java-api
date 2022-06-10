package com.syntifi.near.api.rpc.service.staking;

import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.common.helper.Formats;
import com.syntifi.near.api.common.key.AbstractKeyTest;
import com.syntifi.near.api.common.model.key.PrivateKey;
import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.rpc.model.transaction.SuccessValueStatus;
import com.syntifi.near.api.rpc.model.transaction.TransactionAwait;
import com.syntifi.near.api.rpc.service.AccountServiceTest;
import com.syntifi.near.api.rpc.service.contract.common.ContractClient;
import com.syntifi.near.api.rpc.service.contract.common.ContractMethodProxyClient;
import com.syntifi.near.api.rpc.service.contract.common.FunctionCallResult;
import com.syntifi.near.api.rpc.service.contract.staking.StakingService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

import static com.syntifi.near.api.rpc.NearClientTestnetHelper.nearClient;
import static org.junit.jupiter.api.Assertions.*;

class StakingServiceTest extends AbstractKeyTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceTest.class);
    private static final StakingService service = ContractClient.createClientProxy(StakingService.class, new ContractMethodProxyClient());

    @Test
    void testAccountTotalBalance_should_be_bigger_than_zero() {
        String stakingPool = "stakesstone.pool.f863973.m0";
        String accountId = "syntifi-alice.testnet";
        FunctionCallResult<BigInteger> totalValue = service.getAccountTotalBalance(nearClient, stakingPool, accountId);
        assertEquals(1, totalValue.getResult().compareTo(BigInteger.valueOf(0L)));
    }


    @Test
    void testAccountUnstakedBalance_should_be_bigger_than_zero() {
        String stakingPool = "stakesstone.pool.f863973.m0";
        String accountId = "syntifi-alice.testnet";
        FunctionCallResult<BigInteger> totalValue = service.getAccountUnstakedBalance(nearClient, stakingPool, accountId);
        assertEquals(1, totalValue.getResult().compareTo(BigInteger.valueOf(0L)));
    }

    @Test
    void testAccountStakedBalance_should_be_bigger_than_zero() {
        String stakingPool = "stakesstone.pool.f863973.m0";
        String accountId = "syntifi-alice.testnet";
        FunctionCallResult<BigInteger> totalValue = service.getAccountStakedBalance(nearClient, stakingPool, accountId);
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
        FunctionCallResult<BigInteger> totalValue = service.getTotalStakedBalance(nearClient, stakingPool, accountId);
        assertNotNull(totalValue.getResult());
    }

    @Test
    void testOwnerId_should_not_be_null() {
        String stakingPool = "stakesstone.pool.f863973.m0";
        String accountId = "syntifi-alice.testnet";
        FunctionCallResult<String> totalValue = service.getOwnerId(nearClient, stakingPool, accountId);
        assertNotNull(totalValue.getResult());
    }

    @Test
    void testRewardFeeFraction_should_not_be_null() {
        String stakingPool = "stakesstone.pool.f863973.m0";
        String accountId = "syntifi-alice.testnet";
        FunctionCallResult<JsonNode> totalValue = service.getRewardFeeFraction(nearClient, stakingPool, accountId);
        assertNotNull(totalValue.getResult());
    }

    @Test
    void testFarms_should_not_be_null() {
        String stakingPool = "validator2.factory01.littlefarm.testnet";
        String accountId = "syntifi-alice.testnet";
        FunctionCallResult<JsonNode> totalValue = service.getFarms(nearClient, stakingPool, accountId, 0L, 10L);
        assertNotNull(totalValue.getResult());
    }

    @Test
    void testFarm_should_not_be_null() {
        String stakingPool = "validator2.factory01.littlefarm.testnet";
        String accountId = "syntifi-alice.testnet";
        String farmId = "token1.littlefarm.testnet";
        FunctionCallResult<JsonNode> totalValue = service.getFarm(nearClient, stakingPool, accountId, 0L);
        assertNotNull(totalValue.getResult());
    }

    @Test
    void testUnclaimedReward_should_not_be_null() {
        String stakingPool = "validator2.factory01.littlefarm.testnet";
        String accountId = "syntifi-alice.testnet";
        FunctionCallResult<BigInteger> totalValue = service.getUnclaimedReward(nearClient, stakingPool, accountId, 0L);
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
        assertInstanceOf(SuccessValueStatus.class, transactionAwait.getStatus());
    }

    @Test
    void unstake_should_return_Success() {
        String stakingPool = "stakesstone.pool.f863973.m0";
        String amount = Formats.parseNearAmount("1");
        String accountId = "syntifi-alice.testnet";
        PrivateKey privateKey = aliceNearPrivateKey;
        PublicKey publicKey = aliceNearPublicKey;

        TransactionAwait transactionAwait = service.callUnstake(nearClient, stakingPool,
                amount, accountId, publicKey, privateKey);
        assertInstanceOf(SuccessValueStatus.class, transactionAwait.getStatus());
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
        assertInstanceOf(SuccessValueStatus.class, transactionAwait.getStatus());
    }

    @Test
    void stake_should_return_Success() {
        String stakingPool = "stakesstone.pool.f863973.m0";
        BigInteger amount = new BigInteger(Formats.parseNearAmount("1"), 10);
        String accountId = "syntifi-alice.testnet";
        PrivateKey privateKey = aliceNearPrivateKey;
        PublicKey publicKey = aliceNearPublicKey;

        TransactionAwait transactionAwait = service.callStake(nearClient, stakingPool,
                amount, accountId, publicKey, privateKey);
        assertInstanceOf(SuccessValueStatus.class, transactionAwait.getStatus());
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