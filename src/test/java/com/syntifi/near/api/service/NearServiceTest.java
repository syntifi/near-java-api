package com.syntifi.near.api.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.MalformedURLException;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.model.accesskey.Key;
import com.syntifi.near.api.model.identifier.Finality;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NearServiceTest {

    private static NearService nearService;
    private static Logger LOGGER = LoggerFactory.getLogger(NearServiceTest.class);

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        // String peerAddress = "rpc.testnet.near.org";
        String peerAddress = "archival-rpc.testnet.near.org";

        LOGGER.debug("======== Running tests with peer {} ========", peerAddress);
        nearService = NearService.usingPeer(peerAddress);
    }

    @Test
    void getBlock_byFinality_block_notNullAndValidHashAndValidHeight() {
        JsonNode block = nearService.getBlock(Finality.FINAL);

        assertNotNull(block);
        assertNotNull(block.get("header"));
        assertNotNull(block.get("header").get("hash").asText());
        assertDoesNotThrow(() -> {
            Long.valueOf(block.get("header").get("height").asText());
        });
    }

    @Test
    void getBlock_byHash_block_notNullAndValidHeight() {
        JsonNode block = nearService.getBlock("ERWDYDwi1ZjVWCd2UfUfxQqEX3Mq4gF2gaegtKsJpKpE");

        assertNotNull(block);
        assertNotNull(block.get("header"));
        assertDoesNotThrow(() -> {
            Long.valueOf(block.get("header").get("height").asText());
        });
    }

    @Test
    void getBlock_byHeight_block_notNullAndValidHash() {
        JsonNode block = nearService.getBlock(77797237);

        assertNotNull(block);
        assertNotNull(block.get("header"));
        assertNotNull(block.get("header").get("hash").asText());
    }

    @Test
    void getNetworkInfo_networkInfo_notNullAndValidActivePeer() {
        JsonNode networkInfo = nearService.getNetworkInfo();

        assertNotNull(networkInfo);
        assertNotNull(networkInfo.get("active_peers"));
        assertNotNull(networkInfo.get("active_peers").get(0));
        assertNotNull(networkInfo.get("active_peers").get(0).get("id"));
        assertTrue(networkInfo.get("active_peers").get(0).get("id").asText().length() > 0);
    }

    @Test
    void getNetworkStatus_networkStatus_notNullAndValidSyncInfo() {
        JsonNode networkStatus = nearService.getNetworkStatus();

        assertNotNull(networkStatus);
        assertNotNull(networkStatus.get("sync_info"));
        assertNotNull(networkStatus.get("sync_info").get("latest_block_hash"));
        assertTrue(networkStatus.get("sync_info").get("latest_block_hash").asText().length() > 0);
    }

    @Test
    void getNetworkValidationStatus_byNull_networkValidationStatus_notNullAndValidCurrentValidator() {
        JsonNode networkValidationStatus = nearService.getNetworkValidationStatus(null);

        assertNotNull(networkValidationStatus);
        assertNotNull(networkValidationStatus.get("current_validators"));
        assertNotNull(networkValidationStatus.get("current_validators").get(0));
        assertNotNull(networkValidationStatus.get("current_validators").get(0).get("account_id"));
        assertTrue(networkValidationStatus.get("current_validators").get(0).get("account_id").asText().length() > 0);
    }

    // FIXME: No validators found!
    @Test
    void getNetworkValidationStatus_byBlockHash_networkValidationStatus_notNull() {
        JsonNode lastBlock = nearService.getBlock(Finality.FINAL);

        JsonNode networkValidationStatus = nearService
                .getNetworkValidationStatus(lastBlock.get("header").get("hash").asText());

        assertNotNull(networkValidationStatus);
    }

    // FIXME: No validators found!
    @Test
    void getNetworkValidationStatus_byBlockNumber_networkValidationStatus_notNull() {
        JsonNode lastBlock = nearService.getBlock(Finality.FINAL);

        JsonNode networkValidationStatus = nearService
                .getNetworkValidationStatus(lastBlock.get("header").get("height").asLong());

        assertNotNull(networkValidationStatus);
    }

    @Test
    void getGasPrice_byNull_gasPrice_notNullAndValidGasPrice() {
        JsonNode gasPrice = nearService.getGasPrice(null);

        assertNotNull(gasPrice);
        assertDoesNotThrow(() -> {
            Long.valueOf(gasPrice.get("gas_price").asText());
        });
    }

    @Test
    void getGasPrice_byBlockHash_gasPrice_notNullAndValidGasPrice() {
        JsonNode lastBlock = nearService.getBlock(Finality.FINAL);

        JsonNode gasPrice = nearService
                .getGasPrice(lastBlock.get("header").get("hash").asText());

        assertNotNull(gasPrice);
        assertDoesNotThrow(() -> {
            Long.valueOf(gasPrice.get("gas_price").asText());
        });
    }

    @Test
    void getGasPrice_byBlockNumber_gasPrice_notNullAndValidGasPrice() {
        JsonNode lastBlock = nearService.getBlock(Finality.FINAL);

        JsonNode gasPrice = nearService
                .getGasPrice(lastBlock.get("header").get("height").asLong());

        assertNotNull(gasPrice);
        assertDoesNotThrow(() -> {
            Long.valueOf(gasPrice.get("gas_price").asText());
        });
    }

    @Test
    void getGenesisConfig_genesisConfig_notNullAndValidGenesisTime() {
        JsonNode genesisConfig = nearService.getGenesisConfig();

        assertNotNull(genesisConfig);
        assertNotNull(genesisConfig.get("genesis_time"));
        assertDoesNotThrow(() -> {
            ZonedDateTime.parse(genesisConfig.get("genesis_time").asText());
        });
    }

    @Test
    void getProtocolConfig_byFinality_protocolConfig_notNullAndValidProtocolVersion() {
        JsonNode protocolConfig = nearService.getProtocolConfig(Finality.FINAL);

        assertNotNull(protocolConfig);
        assertNotNull(protocolConfig.get("protocol_version"));
        assertDoesNotThrow(() -> {
            Integer.valueOf(protocolConfig.get("protocol_version").asText());
        });
    }

    @Test
    void getProtocolConfig_byHash_protocolConfig_notNullAndValidProtocolVersion() {
        JsonNode protocolConfig = nearService.getProtocolConfig("ERWDYDwi1ZjVWCd2UfUfxQqEX3Mq4gF2gaegtKsJpKpE");

        assertNotNull(protocolConfig);
        assertNotNull(protocolConfig.get("protocol_version"));
        assertDoesNotThrow(() -> {
            Integer.valueOf(protocolConfig.get("protocol_version").asText());
        });
    }

    @Test
    void getProtocolConfig_byHeight_protocolConfig_notNullAndValidProtocolVersion() {
        JsonNode protocolConfig = nearService.getProtocolConfig(77797237);

        assertNotNull(protocolConfig);
        assertNotNull(protocolConfig.get("protocol_version"));
        assertDoesNotThrow(() -> {
            Integer.valueOf(protocolConfig.get("protocol_version").asText());
        });
    }

    @Test
    void getTransactionStatus_transactionStatus_notNullAndValid() {
        String transactionHash = "GTQggxaMvWATuXMiFaRxUcnuu4RsP2zA56UwkDYoNn1e";
        String accountId = "goldmine.testnet";

        JsonNode transactionStatus = nearService.getTransactionStatus(transactionHash, accountId);

        assertNotNull(transactionStatus);
        assertNotNull(transactionStatus.get("transaction"));
        assertNotNull(transactionStatus.get("transaction").get("signer_id"));
        assertNotNull(transactionStatus.get("transaction").get("hash"));
        assertEquals(accountId, transactionStatus.get("transaction").get("signer_id").asText());
        assertEquals(transactionHash, transactionStatus.get("transaction").get("hash").asText());
    }

    @Test
    void getTransactionStatusWithReceipts_transactionStatusWithReceipts_notNullAndValid() {
        String transactionHash = "GTQggxaMvWATuXMiFaRxUcnuu4RsP2zA56UwkDYoNn1e";
        String accountId = "goldmine.testnet";
        String receiptId = "7kZk4SggcvTxsayGV1FC8CNEqSTHHnuNcUgw7ZQHJ3w8";

        JsonNode transactionStatusWithReceipts = nearService.getTransactionStatusWithReceipts(transactionHash,
                accountId);

        assertNotNull(transactionStatusWithReceipts);
        assertNotNull(transactionStatusWithReceipts.get("transaction"));
        assertNotNull(transactionStatusWithReceipts.get("transaction").get("signer_id"));
        assertNotNull(transactionStatusWithReceipts.get("transaction").get("hash"));
        assertNotNull(transactionStatusWithReceipts.get("receipts_outcome"));
        assertNotNull(transactionStatusWithReceipts.get("receipts_outcome").get(0));
        assertNotNull(transactionStatusWithReceipts.get("receipts_outcome").get(0).get("id"));

        assertEquals(accountId, transactionStatusWithReceipts.get("transaction").get("signer_id").asText());
        assertEquals(transactionHash, transactionStatusWithReceipts.get("transaction").get("hash").asText());
        assertEquals(receiptId, transactionStatusWithReceipts.get("receipts_outcome").get(0).get("id").asText());
    }

    @Test
    void getTransactionReceipt_transactionReceipt_notNullAndValid() {
        String accountId = "goldmine.testnet";
        String receiptId = "7kZk4SggcvTxsayGV1FC8CNEqSTHHnuNcUgw7ZQHJ3w8";

        JsonNode transactionReceipt = nearService.getTransactionReceipt(receiptId);

        assertNotNull(transactionReceipt.get("receipt_id"));
        assertNotNull(transactionReceipt.get("receipt"));
        assertNotNull(transactionReceipt.get("receipt").get("Action"));
        assertNotNull(transactionReceipt.get("receipt").get("Action").get("signer_id"));

        assertEquals(receiptId, transactionReceipt.get("receipt_id").asText());
        assertEquals(accountId, transactionReceipt.get("receipt").get("Action").get("signer_id").asText());
    }

    // TODO: Missing more tests for viewAccessKey
    @Test
    void viewAccessKey_accessKey_notNullAndValid() {
        String accountId = "client.chainlink.testnet";
        String publicKey = "ed25519:H9k5eiU4xXS3M4z8HzKJSLaZdqGdGwBG49o7orNC4eZW";

        JsonNode accessKey = nearService.viewAccessKey(Finality.FINAL, accountId, publicKey);

        assertNotNull(accessKey);
    }

    // TODO: Missing more tests for viewAccessKeyList
    @Test
    void viewAccessKeyList_accessKey_notNullAndValid() {
        String accountId = "client.chainlink.testnet";

        JsonNode accessKey = nearService.viewAccessKeyList(Finality.FINAL, accountId);

        assertNotNull(accessKey);
    }

    // TODO: Missing more tests for viewSingleAccessKeyChanges
    @Test
    void viewSingleAccessKeyChanges_accessKey_notNullAndValid() {
        Key[] keys = new Key[1];

        Key key0 = new Key("example-acct.testnet", "ed25519:25KEc7t7MQohAJ4EDThd2vkksKkwangnuJFzcoiXj9oM");
        keys[0] = key0;

        JsonNode accessKey = nearService.viewSingleAccessKeyChanges(Finality.FINAL, keys);

        assertNotNull(accessKey);
    }

    // TODO: Missing more tests for viewAllAccessKeyChanges
    @Test
    void viewAllAccessKeyChanges_accessKey_notNullAndValid() {
        JsonNode lastBlock = nearService.getBlock(Finality.FINAL);

        String[] accountIds = new String[1];

        accountIds[0] = "example-acct.testnet";

        JsonNode accessKey = nearService.viewAllAccessKeyChanges(lastBlock.get("header").get("hash").asText(),
                accountIds);

        assertNotNull(accessKey);
    }
}
