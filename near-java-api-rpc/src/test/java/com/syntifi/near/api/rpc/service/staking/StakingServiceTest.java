package com.syntifi.near.api.rpc.service.staking;

import com.syntifi.near.api.rpc.service.AccountServiceTest;
import com.syntifi.near.api.rpc.service.contract.common.param.AccountIdParam;
import com.syntifi.near.api.rpc.service.contract.common.ContractClient;
import com.syntifi.near.api.rpc.service.contract.common.ContractMethodProxyClient;
import com.syntifi.near.api.rpc.service.contract.common.FunctionCallResult;
import com.syntifi.near.api.rpc.service.contract.staking.StakingService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

import static com.syntifi.near.api.rpc.NearClientTestnetHelper.nearClient;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StakingServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceTest.class);

    @Test
    void testAccountTotalBalance_should_be_bigger_than_zero() {
        String stakingPool = "stakesstone.pool.f863973.m0";
        String accountId = "syntifi-alice.testnet";
        StakingService service = ContractClient.createClientProxy(StakingService.class, new ContractMethodProxyClient());
        FunctionCallResult<BigInteger> totalValue = service.getAccountTotalBalance(nearClient, stakingPool, AccountIdParam.builder().accountId(accountId).build());
        assertEquals(1, totalValue.getResult().compareTo(BigInteger.valueOf(0L)));
    }

    @Test
    void testAccountStakedBalance_should_be_bigger_than_zero() {
        String stakingPool = "stakesstone.pool.f863973.m0";
        String accountId = "syntifi-alice.testnet";
        StakingService service = ContractClient.createClientProxy(StakingService.class, new ContractMethodProxyClient());
        FunctionCallResult<BigInteger> totalValue = service.getAccountStakedBalance(nearClient, stakingPool, AccountIdParam.builder().accountId(accountId).build());
        assertEquals(1, totalValue.getResult().compareTo(BigInteger.valueOf(0L)));
    }

/*    @Test
    void depositAndStakeToken_should_return_Success() throws GeneralSecurityException {
        String stakingPool = "stakesstone.pool.f863973.m0";
        BigInteger amount = new BigInteger(Formats.parseNearAmount("1"), 10);
        String accountId = "syntifi-alice.testnet";
        PrivateKey privateKey = aliceNearPrivateKey;
        PublicKey publicKey = aliceNearPublicKey;

        TransactionAwait transactionAwait = StakingService.depositAndStakeTokens(nearClient, stakingPool,
                amount, accountId, publicKey, privateKey);
        assertInstanceOf(SuccessValueStatus.class, transactionAwait.getStatus());
    }
*/
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