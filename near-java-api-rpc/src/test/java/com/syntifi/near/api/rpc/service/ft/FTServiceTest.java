package com.syntifi.near.api.rpc.service.ft;

import com.syntifi.near.api.common.key.AbstractKeyTest;
import com.syntifi.near.api.common.model.key.PrivateKey;
import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.rpc.model.transaction.SuccessValueStatus;
import com.syntifi.near.api.rpc.model.transaction.TransactionAwait;
import com.syntifi.near.api.rpc.service.contract.common.ContractClient;
import com.syntifi.near.api.rpc.service.contract.common.ContractMethodProxyClient;
import com.syntifi.near.api.rpc.service.contract.common.FunctionCallResult;
import com.syntifi.near.api.rpc.service.contract.ft.FTService;
import com.syntifi.near.api.rpc.service.contract.ft.model.FTTokenMetadata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

import static com.syntifi.near.api.rpc.NearClientArchivalNetHelper.nearClient;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FTServiceTest extends AbstractKeyTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FTServiceTest.class);

    private static final FTService service = ContractClient.createClientProxy(FTService.class, new ContractMethodProxyClient());

    // INFO: There must be a delay for the blockchain to process each call
    @BeforeEach
    void wait_for_network() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test
    void testTotalSupply_should_be_greater_than_zero() {
        String tokenId = "ft.demo.testnet";
        FunctionCallResult<String> totalAmount = service.getTotalSupply(nearClient, tokenId);
        assertNotNull(totalAmount.getResult());
    }

    @Test
    void testBalanceOf_should_not_be_null() {
        String tokenId = "ft.demo.testnet";
        String accountId = "syntifi-alice.testnet";
        FunctionCallResult<BigInteger> result = service.getBalanceOf(nearClient, tokenId, accountId);
        assertNotNull(result.getResult());
    }

    @Test
    void testMetadata_should_not_be_null() {
        String tokenId = "ft.demo.testnet";
        FunctionCallResult<FTTokenMetadata> result = service.getMetadata(nearClient, tokenId);
        assertNotNull(result.getResult());
    }

    @Test
    @Disabled("may time out")
    void testTransfer_should_be_success() {
        String tokenId = "demo.testnet";
        String accountId = "syntifi-alice.testnet";
        String amount = "100000000";
        PrivateKey privateKey = aliceNearPrivateKey;
        PublicKey publicKey = aliceNearPublicKey;
        TransactionAwait result = service.callTransfer(nearClient, tokenId, amount, tokenId, accountId, publicKey, privateKey);
        assertInstanceOf(SuccessValueStatus.class, result.getStatus());
    }


    @Test
    @Disabled("may time out")
    void testTransferCall_should_be_success() {
        String tokenId = "ft.demo.testnet";
        String accountId = "syntifi-alice.testnet";
        String amount = "100000000";
        PrivateKey privateKey = aliceNearPrivateKey;
        PublicKey publicKey = aliceNearPublicKey;
        TransactionAwait result = service.callTransferCall(nearClient, tokenId, amount, tokenId, accountId, "msg", publicKey, privateKey);
        assertInstanceOf(SuccessValueStatus.class, result.getStatus());
    }
}
