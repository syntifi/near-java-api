package com.syntifi.near.api.indexer.service;

import com.syntifi.near.api.indexer.model.AccountIdList;
import com.syntifi.near.api.indexer.model.NearValue;
import com.syntifi.near.api.indexer.model.RecentActivity;
import com.syntifi.near.api.indexer.model.StakingDeposit;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static com.syntifi.near.api.indexer.service.NearIndexerClientHelper.nearIndexerClient;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Basic Service call testing
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public class NearIndexerClientTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NearIndexerClientTest.class);

    @Test
    void getNearValue_valid() throws IOException {
        Response<NearValue> value = nearIndexerClient.getNearValue().execute();

        assertTrue(value.isSuccessful());

        NearValue nearValue = value.body();

        assertNotNull(nearValue);

        LOGGER.debug("Response {}", nearValue.getUsDollars());
        LOGGER.debug("Response {}", nearValue.getEuros());
        LOGGER.debug("Response {}", nearValue.getChineseYuan());
        LOGGER.debug("Response {}", nearValue.getLastUpdatedAt());

        assertNotNull(nearValue);
    }

    @Test
    void getNearAccounts_valid() throws IOException {
        Response<List<String>> value = nearIndexerClient.getNearAccounts("ed25519:F8jARHGZdHqnwrxrnv1pFVzzirXZR2vJzeYbvwQbxZyP").execute();

        assertTrue(value.isSuccessful());

        List<String> nearAccounts = value.body();

        assertNotNull(nearAccounts);

        assertTrue(nearAccounts.size()>=1);

        assertTrue(nearAccounts.contains("syntifi-alice.testnet"));
    }

    @Test
    void getAccountLikelyNFTs_valid() throws IOException {
        Response<AccountIdList> value = nearIndexerClient.getAccountLikelyNFTs("wallet-test.testnet").execute();

        assertTrue(value.isSuccessful());

        AccountIdList dappsAccountList = value.body();

        assertNotNull(dappsAccountList);

        dappsAccountList.forEach(i -> LOGGER.debug("{}", i));
    }

    @Test
    void getAccountLikelyFTs_valid() throws IOException {
        Response<AccountIdList> value = nearIndexerClient.getAccountLikelyFTs("wallet-test.testnet").execute();

        assertTrue(value.isSuccessful());

        AccountIdList accountIdList = value.body();

        assertNotNull(accountIdList);

        accountIdList.forEach(i -> LOGGER.debug("{}", i));
    }

    @Test
    void getStakingDeposits_valid() throws IOException {
        Response<List<StakingDeposit>> value = nearIndexerClient.getStakingDeposits("wallet-test.testnet").execute();

        assertTrue(value.isSuccessful());

        List<StakingDeposit> stakingDeposits = value.body();

        assertNotNull(stakingDeposits);

        stakingDeposits.forEach(i -> LOGGER.debug("deposit: {} validator: {}", i.getDeposit(), i.getValidator()));
    }

    @Test
    void getNearRecentActivity_valid() throws IOException {
        Response<RecentActivity> value = nearIndexerClient.getNearRecentActivity("wallet-test.testnet").execute();

        assertTrue(value.isSuccessful());

        RecentActivity nearValue = value.body();

        assertNotNull(nearValue);

        nearValue.forEach(item -> LOGGER.debug("Item kind: {}", item.getActionKind()));

        LOGGER.debug("Response {}", nearValue.size());

        assertNotNull(nearValue);
    }
}