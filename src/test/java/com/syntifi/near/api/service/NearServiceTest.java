package com.syntifi.near.api.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.syntifi.near.api.exception.NoSuchTypeException;
import com.syntifi.near.api.model.accesskey.AccessKey;
import com.syntifi.near.api.model.accesskey.AccessKeyChanges;
import com.syntifi.near.api.model.accesskey.AccessKeyList;
import com.syntifi.near.api.model.accesskey.Key;
import com.syntifi.near.api.model.account.Account;
import com.syntifi.near.api.model.account.AccountChanges;
import com.syntifi.near.api.model.block.Block;
import com.syntifi.near.api.model.block.BlockChanges;
import com.syntifi.near.api.model.block.Chunk;
import com.syntifi.near.api.model.contract.ContractCode;
import com.syntifi.near.api.model.contract.ContractCodeChanges;
import com.syntifi.near.api.model.contract.ContractFunctionCallResult;
import com.syntifi.near.api.model.contract.ContractState;
import com.syntifi.near.api.model.contract.ContractStateChanges;
import com.syntifi.near.api.model.gas.GasPrice;
import com.syntifi.near.api.model.identifier.Finality;
import com.syntifi.near.api.model.key.PublicKey;
import com.syntifi.near.api.model.network.NetworkInfo;
import com.syntifi.near.api.model.network.NodeStatus;
import com.syntifi.near.api.model.network.ValidationStatus;
import com.syntifi.near.api.model.protocol.GenesisConfig;
import com.syntifi.near.api.model.protocol.ProtocolConfig;
import com.syntifi.near.api.model.transaction.Receipt;
import com.syntifi.near.api.model.transaction.TransactionAwait;
import com.syntifi.near.api.model.transaction.TransactionStatus;
import com.syntifi.near.api.service.exception.NearServiceException;
import com.syntifi.near.api.service.exception.NearServiceExceptionResolver;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import static com.syntifi.near.api.service.JsonHelper.*;
import static com.syntifi.near.api.service.NearServiceHelper.nearService;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Basic Service call testing
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public class NearServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NearServiceTest.class);

    // From sample response at:
    // https://docs.near.org/docs/api/rpc/block-chunk#block-details
    @Test
    void loadedFromExample_block() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile("json-test-samples/block-chunk/example/block-details.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, Block.class));

        Block block = OBJECT_MAPPER.readValue(inputJson, Block.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(block), false);
    }

    @Test
    void getBlock_byFinality_block_notNull() {
        Block block = nearService.getBlock(Finality.FINAL);

        assertNotNull(block);
    }

    @Test
    void getBlock_byHash_block_notNull() throws JSONException, IOException {
        Block block = nearService.getBlock("FXTWzPjqWztjHfneqJb9cBDB2QLTY1Rja4GHrswAv1b9");

        assertNotNull(block);

        String inputJson = loadJsonFromResourceFile("json-test-samples/block-chunk/block-details-by-hash.json");

        JSONAssert.assertEquals(getPrettyJson(block), inputJson, false);
    }

    @Test
    void getBlock_byHash_block_notNull_with_validator_proposals() {
        Block block = nearService.getBlock("BCEqCXnKijpvQfTMJDn6Bh2We1v1sAZoihApTnJsd32B");

        assertNotNull(block);

        assertEquals(block.getHeader().getValidatorProposals()[0].getValidatorStakeStructVersion(), "V1");
        assertEquals(block.getHeader().getValidatorProposals()[0].getAccountId(), "pontiff.pool.f863973.m0");
        assertEquals(block.getHeader().getValidatorProposals()[0].getPublicKey(),
                PublicKey.fromEncodedBase58String("ed25519:4i8j7nwNyy18hfARtrVpckT8MiicdCXuWBX1TubdMb5Y", PublicKey.class));
        assertEquals(block.getHeader().getValidatorProposals()[0].getStake(), "478888363238890192732941173068");
    }

    @Test
    void getBlock_byHeight_block_notNull() throws JSONException, IOException {
        Block block = nearService.getBlock(78770817);

        assertNotNull(block);

        String inputJson = loadJsonFromResourceFile("json-test-samples/block-chunk/block-details-by-height.json");

        JSONAssert.assertEquals(getPrettyJson(block), inputJson, false);
    }

    // From sample response at:
    // https://docs.near.org/docs/api/rpc/block-chunk#changes-in-block
    @Test
    void loadedFromExample_blockChanges() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile("json-test-samples/block-chunk/example/changes-in-block.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, BlockChanges.class));

        BlockChanges blockChanges = OBJECT_MAPPER.readValue(inputJson, BlockChanges.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(blockChanges), false);
    }

    @Test
    void getBlockChanges_byFinality_blockChanges_notNull() {
        BlockChanges blockChanges = nearService.getBlockChanges(Finality.FINAL);

        assertNotNull(blockChanges);
    }

    @Test
    void getBlockChanges_byHash_blockChanges_notNull() throws JSONException, IOException {
        BlockChanges blockChanges = nearService.getBlockChanges("BmEZnrmov6h6rMPpWkMV2JtU1C5LP563V5Y5yXwUW2N5");

        assertNotNull(blockChanges);

        String inputJson = loadJsonFromResourceFile("json-test-samples/block-chunk/changes-in-block-by-hash.json");

        JSONAssert.assertEquals(getPrettyJson(blockChanges), inputJson, false);
    }

    @Test
    void getBlockChanges_byHeight_blockChanges_notNull() throws JSONException, IOException {
        BlockChanges blockChanges = nearService.getBlockChanges(78770674);

        assertNotNull(blockChanges);

        String inputJson = loadJsonFromResourceFile("json-test-samples/block-chunk/changes-in-block-by-height.json");

        JSONAssert.assertEquals(getPrettyJson(blockChanges), inputJson, false);
    }

    // From sample response at:
    // https://docs.near.org/docs/api/rpc/block-chunk#chunk-details
    @Test
    void loadedFromExample_chunk() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile("json-test-samples/block-chunk/example/chunk-details.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, Chunk.class));

        Chunk chunk = OBJECT_MAPPER.readValue(inputJson, Chunk.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(chunk), false);
    }

    @Test
    void getChunkDetails_byChunkId_chunk_notNull() throws JSONException, IOException {
        Chunk chunk = nearService.getChunkDetails("9mdG2cRcV8Dsb1EoSjtya81NddjRB2stYCTVukZh7zzw");

        assertNotNull(chunk);

        String inputJson = loadJsonFromResourceFile("json-test-samples/block-chunk/chunk-details.json");

        JSONAssert.assertEquals(getPrettyJson(chunk), inputJson, false);
    }

    @Test
    void getChunkDetails_byBlockHashAndShardId_chunk_notNull() throws JSONException, IOException {
        Chunk chunk = nearService.getChunkDetails("F1HXTzeYgYq28rgsHuKUrRbo5QTBGKFYG7rbxXkRZWXN", 0);

        assertNotNull(chunk);

        String inputJson = loadJsonFromResourceFile("json-test-samples/block-chunk/chunk-details.json");

        JSONAssert.assertEquals(getPrettyJson(chunk), inputJson, false);
    }

    @Test
    void getChunkDetails_byBlockHeightAndShardId_chunk_notNull() throws JSONException, IOException {
        Chunk chunk = nearService.getChunkDetails(78567387, 0);

        assertNotNull(chunk);

        String inputJson = loadJsonFromResourceFile("json-test-samples/block-chunk/chunk-details.json");

        JSONAssert.assertEquals(getPrettyJson(chunk), inputJson, false);
    }

    // From sample response at:
    // https://docs.near.org/docs/api/rpc/network#network-info
    @Test
    void loadedFromExample_networkInfo() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile("json-test-samples/network/example/network-info.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, NetworkInfo.class));

        NetworkInfo networkInfo = OBJECT_MAPPER.readValue(inputJson, NetworkInfo.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(networkInfo), false);
    }

    @Test
    void getNetworkInfo_networkInfo_notNull() {
        NetworkInfo networkInfo = nearService.getNetworkInfo();

        assertNotNull(networkInfo);
    }

    // From sample response at:
    // https://docs.near.org/docs/api/rpc/network#node-status
    @Test
    void loadedFromExample_nodeStatus() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile("json-test-samples/network/example/node-status.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, NodeStatus.class));

        NodeStatus nodeStatus = OBJECT_MAPPER.readValue(inputJson, NodeStatus.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(nodeStatus), false);
    }

    @Test
    void getNodeStatus_nodeStatus_notNull() {
        NodeStatus nodeStatus = nearService.getNodeStatus();

        assertNotNull(nodeStatus);
    }

    // From sample response at:
    // https://docs.near.org/docs/api/rpc/network#validation-status
    @Test
    void loadedFromExample_validationStatus() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile("json-test-samples/network/example/validation-status.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, ValidationStatus.class));

        ValidationStatus validationStatus = OBJECT_MAPPER.readValue(inputJson, ValidationStatus.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(validationStatus), false);
    }

    @Test
    void getNetworkValidationStatus_byNull_validationStatus_notNull() {
        ValidationStatus networkValidationStatus = nearService.getNetworkValidationStatus(null);

        assertNotNull(networkValidationStatus);
    }

    // TODO: enable this test when working test data is available
    @Disabled("No validators found! (https://docs.near.org/docs/api/rpc/network#validation-status)")
    @Test
    void getNetworkValidationStatus_byBlockHash_validationStatus_notNull() {
        Block lastBlock = nearService.getBlock(Finality.FINAL);

        ValidationStatus networkValidationStatus = nearService
                .getNetworkValidationStatus(lastBlock.getHeader().getHash());

        assertNotNull(networkValidationStatus);
    }

    // TODO: enable this test when working test data is available
    @Disabled("No validators found! (https://docs.near.org/docs/api/rpc/network#validation-status)")
    @Test
    void getNetworkValidationStatus_byBlockNumber_validationStatus_notNull() {
        Block lastBlock = nearService.getBlock(Finality.OPTIMISTIC);

        ValidationStatus networkValidationStatus = nearService
                .getNetworkValidationStatus(lastBlock.getHeader().getHeight());

        assertNotNull(networkValidationStatus);
    }

    // From sample response at:
    // https://docs.near.org/docs/api/rpc/gas#gas-price
    @Test
    void loadedFromExample_gasPrice() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile("json-test-samples/gas/example/gas-price.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, GasPrice.class));

        GasPrice gasPrice = OBJECT_MAPPER.readValue(inputJson, GasPrice.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(gasPrice), false);
    }

    @Test
    void getGasPrice_byNull_gasPrice_notNull() throws IOException, JSONException {
        GasPrice gasPrice = nearService.getGasPrice(null);

        assertNotNull(gasPrice);

        String inputJson = loadJsonFromResourceFile("json-test-samples/gas/gas-price-by-null.json");

        JSONAssert.assertEquals(getPrettyJson(gasPrice), inputJson, false);
    }

    @Test
    void getGasPrice_byBlockHash_gasPrice_notNull() throws JSONException, IOException {
        GasPrice gasPrice = nearService.getGasPrice("FXTWzPjqWztjHfneqJb9cBDB2QLTY1Rja4GHrswAv1b9");

        assertNotNull(gasPrice);

        String inputJson = loadJsonFromResourceFile("json-test-samples/gas/gas-price-by-block-hash.json");

        JSONAssert.assertEquals(getPrettyJson(gasPrice), inputJson, false);
    }

    @Test
    void getGasPrice_byBlockNumber_gasPrice_notNull() throws IOException, JSONException {
        GasPrice gasPrice = nearService.getGasPrice(78770817);

        assertNotNull(gasPrice);

        String inputJson = loadJsonFromResourceFile("json-test-samples/gas/gas-price-by-block-height.json");

        JSONAssert.assertEquals(getPrettyJson(gasPrice), inputJson, false);
    }

    // From sample response at:
    // https://docs.near.org/docs/api/rpc/protocol#genesis-config
    @Test
    void loadedFromExample_genesisConfig() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile("json-test-samples/protocol/example/genesis-config.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, GenesisConfig.class));

        GenesisConfig genesisConfig = OBJECT_MAPPER.readValue(inputJson, GenesisConfig.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(genesisConfig), false);
    }

    @Test
    void getGenesisConfig_genesisConfig_notNull() throws JSONException, IOException {
        GenesisConfig genesisConfig = nearService.getGenesisConfig();

        assertNotNull(genesisConfig);

        String inputJson = loadJsonFromResourceFile("json-test-samples/protocol/genesis-config.json");

        JSONAssert.assertEquals(getPrettyJson(genesisConfig), inputJson, false);
    }

    // From sample response at:
    // https://docs.near.org/docs/api/rpc/protocol#protocol-config
    @Test
    void loadedFromExample_protocolConfig() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile("json-test-samples/protocol/example/protocol-config.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, ProtocolConfig.class));

        ProtocolConfig protocolConfig = OBJECT_MAPPER.readValue(inputJson, ProtocolConfig.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(protocolConfig), false);
    }

    @Test
    void getProtocolConfig_byFinality_protocolConfig_notNull() throws IOException, JSONException {
        ProtocolConfig protocolConfig = nearService.getProtocolConfig(Finality.FINAL);

        assertNotNull(protocolConfig);

        String inputJson = loadJsonFromResourceFile("json-test-samples/protocol/protocol-config-final.json");

        JSONAssert.assertEquals(getPrettyJson(protocolConfig), inputJson, false);
    }

    @Test
    void getProtocolConfig_byHash_protocolConfig_notNullProtocolVersion() throws IOException, JSONException {
        ProtocolConfig protocolConfig = nearService.getProtocolConfig("FXTWzPjqWztjHfneqJb9cBDB2QLTY1Rja4GHrswAv1b9");

        assertNotNull(protocolConfig);

        String inputJson = loadJsonFromResourceFile("json-test-samples/protocol/protocol-config.json");

        JSONAssert.assertEquals(getPrettyJson(protocolConfig), inputJson, false);
    }

    @Test
    void getProtocolConfig_byHeight_protocolConfig_notNullProtocolVersion() throws IOException, JSONException {
        ProtocolConfig protocolConfig = nearService.getProtocolConfig(78770817);

        assertNotNull(protocolConfig);

        String inputJson = loadJsonFromResourceFile("json-test-samples/protocol/protocol-config.json");

        JSONAssert.assertEquals(getPrettyJson(protocolConfig), inputJson, false);
    }

    // From sample response at:
    // https://docs.near.org/docs/api/rpc/transactions#send-transaction-async
    @Test
    void loadedFromExample_transactionAsync() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile("json-test-samples/transaction/example/send-transaction-async.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, Map.class));

        @SuppressWarnings("unchecked")
        Map<String, String> transactionAsync = OBJECT_MAPPER.readValue(inputJson, Map.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(transactionAsync), false);
    }

    @Test
    void sendTransactionAsync_transactionHash_notNull() {
        String signedTransaction = "DgAAAHNlbmRlci50ZXN0bmV0AOrmAai64SZOv9e/naX4W15pJx0GAap35wTT1T/DwcbbDwAAAAAAAAAQAAAAcmVjZWl2ZXIudGVzdG5ldNMnL7URB1cxPOu3G8jTqlEwlcasagIbKlAJlF5ywVFLAQAAAAMAAACh7czOG8LTAAAAAAAAAGQcOG03xVSFQFjoagOb4NBBqWhERnnz45LY4+52JgZhm1iQKz7qAdPByrGFDQhQ2Mfga8RlbysuQ8D8LlA6bQE=";
        String expectedTransactionHash = "6zgh2u9DqHHiXzdy9ouTP7oGky2T4nugqzqt9wJZwNFm";

        String transactionHash = nearService.sendTransactionAsync(signedTransaction);

        assertNotNull(transactionHash);
        assertEquals(expectedTransactionHash, transactionHash);
    }

    // From sample response at:
    // https://docs.near.org/docs/api/rpc/transactions#send-transaction-await
    @Test
    void loadedFromExample_transactionAwait() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile("json-test-samples/transaction/example/send-transaction-await.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TransactionAwait.class));

        TransactionAwait transactionAwait = OBJECT_MAPPER.readValue(inputJson, TransactionAwait.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(transactionAwait), false);
    }

    // TODO: enable this test when working test data is available
    @Disabled("Error expired! (https://docs.near.org/docs/api/rpc/transactions#send-transaction-await)")
    @Test
    void sendTransactionAwait_transaction_notNull() throws IOException,
            JSONException {
        String signedTransaction = "DgAAAHNlbmRlci50ZXN0bmV0AOrmAai64SZOv9e/naX4W15pJx0GAap35wTT1T/DwcbbDQAAAAAAAAAQAAAAcmVjZWl2ZXIudGVzdG5ldIODI4YfV/QS++blXpQYT+bOsRblTRW4f547y/LkvMQ9AQAAAAMAAACh7czOG8LTAAAAAAAAAAXcaTJzu9GviPT7AD4mNJGY79jxTrjFLoyPBiLGHgBi8JK1AnhK8QknJ1ourxlvOYJA2xEZE8UR24THmSJcLQw=";

        TransactionAwait transaction = nearService.sendTransactionAwait(signedTransaction);

        assertNotNull(transaction);

        String inputJson = loadJsonFromResourceFile("json-test-samples/transaction/send-transaction-await.json");

        JSONAssert.assertEquals(getPrettyJson(transaction), inputJson, false);
    }

    // From sample response at:
    // https://docs.near.org/docs/api/rpc/transactions#transaction-status
    @Test
    void loadedFromExample_transactionStatus()
            throws IOException, JSONException {

        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/transaction/example/transaction-status.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TransactionStatus.class));

        TransactionStatus transactionStatus = OBJECT_MAPPER.readValue(inputJson, TransactionStatus.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(transactionStatus), false);
    }

    @Test
    void getTransactionStatus_transactionStatus_notNull() throws IOException, JSONException {
        String transactionHash = "DwWUi6WbVHKTCDjVu4gmuQfryqjwTjrZ6ntRcKcGN6Gd";
        String accountId = "isonar.testnet";

        TransactionStatus transactionStatus = nearService.getTransactionStatus(transactionHash, accountId);

        assertNotNull(transactionStatus);

        String inputJson = loadJsonFromResourceFile("json-test-samples/transaction/transaction-status-by-hash.json");

        JSONAssert.assertEquals(getPrettyJson(transactionStatus), inputJson, false);
    }

    // From sample response at:
    // https://docs.near.org/docs/api/rpc/transactions#transaction-status-with-receipts
    @Test
    void loadedFromExample_transactionStatusWithReceipts()
            throws IOException, JSONException {

        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/transaction/example/transaction-status-with-receipts.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TransactionStatus.class));

        TransactionStatus transactionStatusWithReceipts = OBJECT_MAPPER.readValue(inputJson, TransactionStatus.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(transactionStatusWithReceipts), false);
    }

    @Test
    void getTransactionStatusWithReceipts_transactionStatusWithReceipts_notNull()
            throws IOException, JSONException {
        String transactionHash = "DwWUi6WbVHKTCDjVu4gmuQfryqjwTjrZ6ntRcKcGN6Gd";
        String accountId = "isonar.testnet";

        TransactionStatus transactionStatusWithReceipts = nearService.getTransactionStatusWithReceipts(transactionHash,
                accountId);

        assertNotNull(transactionStatusWithReceipts);

        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/transaction/transaction-status-by-hash-with-receipt.json");

        JSONAssert.assertEquals(getPrettyJson(transactionStatusWithReceipts), inputJson, false);
    }

    // From sample response at:
    // https://docs.near.org/docs/api/rpc/transactions#receipt-by-id
    @Test
    void loadedFromExample_transactionReceipt()
            throws IOException, JSONException {

        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/transaction/example/receipt-by-id.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, Receipt.class));

        Receipt transactionReceipt = OBJECT_MAPPER.readValue(inputJson, Receipt.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(transactionReceipt), false);
    }

    @Test
    void getTransactionReceipt_transactionReceipt_notNull() throws JSONException, IOException {
        String receiptId = "8b9Vt1xH8DZecMda1YqUcMWA41NvknUJJVd2XEQikPRs";

        Receipt transactionReceipt = nearService.getTransactionReceipt(receiptId);

        assertNotNull(transactionReceipt);

        String inputJson = loadJsonFromResourceFile("json-test-samples/transaction/receipt.json");

        JSONAssert.assertEquals(getPrettyJson(transactionReceipt), inputJson, false);
    }

    /*
     * @Test
     * void getTransactionReceipt_transactionReceipt_notNull_InputDataIds() throws
     * JSONException, IOException {
     * String receiptId = "AWbGp5rEPgcQJHfyJXhsseLGaB8nTHD6iCRnMrwiSTF4";
     *
     * Receipt transactionReceipt = nearService.getTransactionReceipt(receiptId);
     *
     * assertNotNull(transactionReceipt);
     *
     * String inputJson =
     * loadJsonFromFile("json-test-samples/transaction/receipt.json");
     *
     * JSONAssert.assertEquals(getPrettyJson(transactionReceipt), inputJson, false);
     * }
     */

    // From sample response at:
    // https://docs.near.org/docs/api/rpc/access-keys#view-access-key
    @Test
    void loadedFromExample_accessKey() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile("json-test-samples/access-key/example/view-access-key.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, AccessKey.class));

        AccessKey accessKey = OBJECT_MAPPER.readValue(inputJson, AccessKey.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(accessKey), false);
    }

    @Test
    void viewAccessKey_byFinality_accessKey_notNull() {
        String accountId = "client.chainlink.testnet";
        String publicKey = "ed25519:H9k5eiU4xXS3M4z8HzKJSLaZdqGdGwBG49o7orNC4eZW";

        AccessKey accessKey = nearService.viewAccessKey(Finality.FINAL, accountId, publicKey);

        assertNotNull(accessKey);
    }

    @Test
    void viewAccessKey_byHash_accessKey_notNull() throws IOException, JSONException {
        String accountId = "client.chainlink.testnet";
        String publicKey = "ed25519:H9k5eiU4xXS3M4z8HzKJSLaZdqGdGwBG49o7orNC4eZW";

        AccessKey accessKey = nearService.viewAccessKey("8bVg8wugs2QHqXr42oEsCYyH7jvR9pLaAP35dFqx2evU", accountId,
                publicKey);

        assertNotNull(accessKey);

        String inputJson = loadJsonFromResourceFile("json-test-samples/access-key/view-access-key.json");

        JSONAssert.assertEquals(getPrettyJson(accessKey), inputJson, false);
    }

    @Test
    void viewAccessKey_byHeight_accessKey_notNull() throws JSONException, IOException {
        String accountId = "client.chainlink.testnet";
        String publicKey = "ed25519:H9k5eiU4xXS3M4z8HzKJSLaZdqGdGwBG49o7orNC4eZW";

        AccessKey accessKey = nearService.viewAccessKey(78443365, accountId, publicKey);

        assertNotNull(accessKey);

        String inputJson = loadJsonFromResourceFile("json-test-samples/access-key/view-access-key.json");

        JSONAssert.assertEquals(getPrettyJson(accessKey), inputJson, false);
    }

    // From sample response at:
    // https://docs.near.org/docs/api/rpc/access-keys#view-access-key-list
    @Test
    void loadedFromExample_accessKeyList() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile("json-test-samples/access-key/example/view-access-key-list.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, AccessKeyList.class));

        AccessKeyList accessKeyList = OBJECT_MAPPER.readValue(inputJson, AccessKeyList.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(accessKeyList), false);
    }

    @Test
    void viewAccessKeyList_byFinality_accessKey_notNull() {
        String accountId = "client.chainlink.testnet";

        AccessKeyList accessKeyList = nearService.viewAccessKeyList(Finality.FINAL, accountId);

        assertNotNull(accessKeyList);
    }

    @Test
    void viewAccessKeyList_byHash_accessKey_notNull() throws IOException, JSONException {
        String accountId = "client.chainlink.testnet";

        AccessKeyList accessKeyList = nearService.viewAccessKeyList("DwFpDPiQXBaX6Vw3aKazQ4nXjgzw1uk6XpUkfTSJrbXf",
                accountId);

        assertNotNull(accessKeyList);

        String inputJson = loadJsonFromResourceFile("json-test-samples/access-key/view-access-key-list-by-hash.json");

        JSONAssert.assertEquals(getPrettyJson(accessKeyList), inputJson, false);
    }

    @Test
    void viewAccessKeyList_byHeight_accessKey_notNull() throws IOException, JSONException {
        String accountId = "client.chainlink.testnet";

        AccessKeyList accessKeyList = nearService.viewAccessKeyList(78772585, accountId);

        assertNotNull(accessKeyList);

        String inputJson = loadJsonFromResourceFile("json-test-samples/access-key/view-access-key-list-by-height.json");

        JSONAssert.assertEquals(getPrettyJson(accessKeyList), inputJson, false);
    }

    // From sample response at:
    // https://docs.near.org/docs/api/rpc/access-keys#view-access-key-changes-single
    @Test
    void loadedFromExample_accessKeyChangesSingle() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile("json-test-samples/access-key/example/view-access-key-changes-single.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, AccessKeyChanges.class));

        AccessKeyChanges accessKeyChanges = OBJECT_MAPPER.readValue(inputJson, AccessKeyChanges.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(accessKeyChanges), false);
    }

    // TODO: access key with some changes, the given one is empty:
    // (https://docs.near.org/docs/api/rpc/access-keys#view-access-key-changes-single)
    @Test
    void viewSingleAccessKeyChanges_byFinality_accessKey_notNull() {
        Key[] keys = new Key[1];

        Key key0 = new Key("example-acct.testnet", PublicKey.fromEncodedBase58String("ed25519:25KEc7t7MQohAJ4EDThd2vkksKkwangnuJFzcoiXj9oM", PublicKey.class));
        keys[0] = key0;

        AccessKeyChanges accessKeyChanges = nearService.viewSingleAccessKeyChanges(Finality.FINAL, keys);

        assertNotNull(accessKeyChanges);
    }

    // TODO: access key with some changes, the given one is empty:
    // (https://docs.near.org/docs/api/rpc/access-keys#view-access-key-changes-single)
    @Test
    void viewSingleAccessKeyChanges_byHash_accessKey_notNull() {
        Key[] keys = new Key[1];

        Key key0 = new Key("example-acct.testnet", PublicKey.fromEncodedBase58String("ed25519:25KEc7t7MQohAJ4EDThd2vkksKkwangnuJFzcoiXj9oM", PublicKey.class));
        keys[0] = key0;

        AccessKeyChanges accessKeyChanges = nearService.viewSingleAccessKeyChanges(
                "Cr82U81VqHgCz9LzZjPivh9t16e8es6aFCv9qvDMMH88",
                keys);

        assertNotNull(accessKeyChanges);
    }

    // TODO: access key with some changes, the given one is empty:
    // (https://docs.near.org/docs/api/rpc/access-keys#view-access-key-changes-single)
    @Test
    void viewSingleAccessKeyChanges_byHeight_accessKey_notNull() {
        Key[] keys = new Key[1];

        Key key0 = new Key("example-acct.testnet", PublicKey.fromEncodedBase58String("ed25519:25KEc7t7MQohAJ4EDThd2vkksKkwangnuJFzcoiXj9oM", PublicKey.class));
        keys[0] = key0;

        AccessKeyChanges accessKeyChanges = nearService.viewSingleAccessKeyChanges(78433961, keys);

        assertNotNull(accessKeyChanges);
    }

    // From sample response at:
    // https://docs.near.org/docs/api/rpc/access-keys#view-access-key-changes-all
    @Test
    void loadedFromExample_accessKeyChangesAll() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile("json-test-samples/access-key/example/view-access-key-changes-all.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, AccessKeyChanges.class));

        AccessKeyChanges accessKeyChanges = OBJECT_MAPPER.readValue(inputJson, AccessKeyChanges.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(accessKeyChanges), false);
    }

    @Test
    void loadedJson_accessKeyChangesAll_invalidPropertyPermission_shouldThrow_NoSuchTypeException()
            throws IOException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/access-key/view-access-key-changes-all-invalid-permission-property.json");

        LOGGER.debug(
                "Mapping AccessKey with an invalid Permission type as json property should throw NoSuchTypeException");

        Throwable t = assertThrows(IOException.class, () -> OBJECT_MAPPER.readValue(inputJson, AccessKeyChanges.class));

        // assert inner exception, since deserializer signature does not allow for non
        // IOException to be thrown
        assertEquals(NoSuchTypeException.class, t.getCause().getCause().getClass());

        LOGGER.debug("Throwed {} with inner {}", t.getClass().getSimpleName(),
                t.getCause().getCause().getClass().getSimpleName());
    }

    // TODO: access key with some changes
    @Test
    void viewAllAccessKeyChanges_byFinality_accessKey_notNull() {
        String[] accountIds = new String[1];

        accountIds[0] = "client.chainlink.testnet";

        AccessKeyChanges accessKeyChanges = nearService.viewAllAccessKeyChanges(Finality.FINAL, accountIds);

        assertNotNull(accessKeyChanges);
    }

    // TODO: access key with some changes, the given block does not exist:
    // (https://docs.near.org/docs/api/rpc/access-keys#view-access-key-changes-all)
    @Test
    void viewAllAccessKeyChanges_byHash_accessKey_notNull() {
        String[] accountIds = new String[1];

        accountIds[0] = "client.chainlink.testnet";

        AccessKeyChanges accessKeyChanges = nearService.viewAllAccessKeyChanges(
                "Ais9kPbHvk6XmEYptoEpBtyFW77V16TZNHHnYtmXWr1d",
                accountIds);

        assertNotNull(accessKeyChanges);
    }

    // TODO: access key with some changes, the given block does not exist:
    // (https://docs.near.org/docs/api/rpc/access-keys#view-access-key-changes-all)
    @Test
    void viewAllAccessKeyChanges_byHeight_accessKey_notNull() {
        String[] accountIds = new String[1];

        accountIds[0] = "client.chainlink.testnet";

        AccessKeyChanges accessKeyChanges = nearService.viewAllAccessKeyChanges(78433518, accountIds);

        assertNotNull(accessKeyChanges);
    }

    // From sample response at:
    // https://docs.near.org/docs/api/rpc/contracts#view-account
    @Test
    void loadedFromExample_account() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile("json-test-samples/accounts-contracts/example/view-account.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, Account.class));

        Account account = OBJECT_MAPPER.readValue(inputJson, Account.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(account), false);
    }

    @Test
    void viewAccount_byFinality_account_notNull() {
        Account account = nearService.viewAccount(Finality.FINAL, "nearkat.testnet");

        assertNotNull(account);
    }

    @Test
    void viewAccount_byHash_account_notNull() throws IOException, JSONException {
        Account account = nearService.viewAccount("5hyGx7LiGaeRiAN4RrKcGomi1QXHqZwKXFQf6xTmvUgb", "nearkat.testnet");

        assertNotNull(account);

        String inputJson = loadJsonFromResourceFile("json-test-samples/accounts-contracts/view-account.json");

        JSONAssert.assertEquals(getPrettyJson(account), inputJson, false);
    }

    @Test
    void viewAccount_byHeight_account_notNull() throws JSONException, IOException {
        Account account = nearService.viewAccount(78439658, "nearkat.testnet");

        assertNotNull(account);

        String inputJson = loadJsonFromResourceFile("json-test-samples/accounts-contracts/view-account.json");

        JSONAssert.assertEquals(getPrettyJson(account), inputJson, false);
    }

    // From sample response at:
    // https://docs.near.org/docs/api/rpc/contracts#view-account-changes
    @Test
    void loadedFromExample_accountChanges() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile("json-test-samples/accounts-contracts/example/view-account-changes.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, AccountChanges.class));

        AccountChanges accountChanges = OBJECT_MAPPER.readValue(inputJson, AccountChanges.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(accountChanges), false);
    }

    // TODO: Empty sample account changes
    // (https://docs.near.org/docs/api/rpc/contracts#view-account-changes)
    @Test
    void viewAccountChanges_byFinality_account_notNull() {
        String[] accountIds = new String[1];

        accountIds[0] = "nearkat.testnet";

        AccountChanges accountChanges = nearService.viewAccountChanges(Finality.FINAL, accountIds);

        assertNotNull(accountChanges);
    }

    // TODO: Empty sample account changes
    // (https://docs.near.org/docs/api/rpc/contracts#view-account-changes)
    @Test
    void viewAccountChanges_byHash_account_notNull() throws IOException, JSONException {
        String[] accountIds = new String[1];

        accountIds[0] = "nearkat.testnet";

        AccountChanges accountChanges = nearService.viewAccountChanges("7vWp2djKLoJ3RE1sr8RzSKQtyzKpe2wZ7NCcDuFNuL7j",
                accountIds);

        assertNotNull(accountChanges);

        String inputJson = loadJsonFromResourceFile("json-test-samples/accounts-contracts/view-account-changes.json");

        JSONAssert.assertEquals(getPrettyJson(accountChanges), inputJson, false);
    }

    // TODO: Empty sample account changes
    // (https://docs.near.org/docs/api/rpc/contracts#view-account-changes)
    @Test
    void viewAccountChanges_byHeight_account_notNull() throws JSONException, IOException {
        String[] accountIds = new String[1];

        accountIds[0] = "nearkat.testnet";

        AccountChanges accountChanges = nearService.viewAccountChanges(78440142, accountIds);

        assertNotNull(accountChanges);

        String inputJson = loadJsonFromResourceFile("json-test-samples/accounts-contracts/view-account-changes.json");

        JSONAssert.assertEquals(getPrettyJson(accountChanges), inputJson, false);
    }

    // From sample response at:
    // https://docs.near.org/docs/api/rpc/contracts#view-contract-code
    @Test
    void loadedFromExample_contractCode() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile("json-test-samples/accounts-contracts/example/view-contract-code.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, ContractCode.class));

        ContractCode contractCode = OBJECT_MAPPER.readValue(inputJson, ContractCode.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(contractCode), false);
    }

    @Test
    void viewContractCode_byFinality_contractCode_notNull() {
        ContractCode contractCode = nearService.viewContractCode(Finality.FINAL, "guest-book.testnet");

        assertNotNull(contractCode);
    }

    @Test
    void viewContractCode_byHash_contractCode_notNull() throws IOException, JSONException {
        ContractCode contractCode = nearService.viewContractCode("uLxyauKPhSk1tebYKi3k69pHSaT2ZLzWy4JwtGm52pu",
                "guest-book.testnet");

        assertNotNull(contractCode);

        String inputJson = loadJsonFromResourceFile("json-test-samples/accounts-contracts/view-contract-code.json");

        JSONAssert.assertEquals(getPrettyJson(contractCode), inputJson, false);
    }

    @Test
    void viewContractCode_byHeight_contractCode_notNull() throws JSONException, IOException {
        ContractCode contractCode = nearService.viewContractCode(78440518, "guest-book.testnet");

        assertNotNull(contractCode);

        String inputJson = loadJsonFromResourceFile("json-test-samples/accounts-contracts/view-contract-code.json");

        JSONAssert.assertEquals(getPrettyJson(contractCode), inputJson, false);
    }

    // From sample response at:
    // https://docs.near.org/docs/api/rpc/contracts#view-contract-state
    @Test
    void loadedFromExample_contractState() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile("json-test-samples/accounts-contracts/example/view-contract-state.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, ContractState.class));

        ContractState contractState = OBJECT_MAPPER.readValue(inputJson, ContractState.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(contractState), false);
    }

    // TODO: enable this test when working test data is available
    @Disabled("Too large contract state! (https://docs.near.org/docs/api/rpc/contracts#view-contract-state)")
    @Test
    void viewContractState_byFinality_contractCode_notNull() {
        ContractState contractState = nearService.viewContractState(Finality.FINAL,
                "guest-book.testnet", "");

        assertNotNull(contractState);
    }

    // TODO: enable this test when working test data is available
    @Disabled("Too large contract state! (https://docs.near.org/docs/api/rpc/contracts#view-contract-state)")
    @Test
    void viewContractState_byHash_contractCode_notNull() throws IOException,
            JSONException {
        ContractState contractState = nearService.viewContractState("342bkjvnzoZ7FGRE5BwDVkzSRUYXAScTz3GsDB9sEHXg",
                "guest-book.testnet", "");

        assertNotNull(contractState);

        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/accounts-contracts/view-contract-state.json");

        JSONAssert.assertEquals(getPrettyJson(contractState), inputJson, false);
    }

    // TODO: enable this test when working test data is available
    @Disabled("Too large contract state! (https://docs.near.org/docs/api/rpc/contracts#view-contract-state)")
    @Test
    void viewContractState_byHeight_contractCode_notNull() throws JSONException,
            IOException {
        ContractState contractState = nearService.viewContractState(78440679,
                "guest-book.testnet", "");

        assertNotNull(contractState);

        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/accounts-contracts/view-contract-state.json");

        JSONAssert.assertEquals(getPrettyJson(contractState), inputJson, false);
    }

    // From sample response at:
    // https://docs.near.org/docs/api/rpc/contracts#view-contract-state-changes
    @Test
    void loadedFromExample_contractStateChanges() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/accounts-contracts/example/view-contract-state-changes.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, ContractStateChanges.class));

        ContractStateChanges contractStateChanges = OBJECT_MAPPER.readValue(inputJson, ContractStateChanges.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(contractStateChanges), false);
    }

    // TODO: Empty sample contract code changes
    // (https://docs.near.org/docs/api/rpc/contracts#view-contract-state-changes)
    @Test
    void viewContractStateChanges_byFinality_contractStateChanges_notNull() {
        String[] accountIds = new String[1];

        accountIds[0] = "guest-book.testnet";

        ContractStateChanges contractStateChanges = nearService.viewContractStateChanges(Finality.FINAL, accountIds,
                "");

        assertNotNull(contractStateChanges);
    }

    // TODO: Empty sample contract code changes
    // (https://docs.near.org/docs/api/rpc/contracts#view-contract-state-changes)
    @Test
    void viewContractStateChanges_byHash_contractStateChanges_notNull() throws IOException, JSONException {
        String[] accountIds = new String[1];

        accountIds[0] = "guest-book.testnet";

        ContractStateChanges contractStateChanges = nearService.viewContractStateChanges(
                "5KgQ8uu17bhUPnMUbkmciHxbpFvsbhwdkJu4ptRfR7Zn",
                accountIds, "");

        assertNotNull(contractStateChanges);

        String inputJson = loadJsonFromResourceFile("json-test-samples/accounts-contracts/view-contract-state-changes.json");

        JSONAssert.assertEquals(getPrettyJson(contractStateChanges), inputJson, false);
    }

    // TODO: Empty sample contract code changes
    // (https://docs.near.org/docs/api/rpc/contracts#view-contract-state-changes)
    @Test
    void viewContractStateChanges_byHeight_contractStateChanges_notNull() throws JSONException, IOException {
        String[] accountIds = new String[1];

        accountIds[0] = "guest-book.testnet";

        ContractStateChanges contractStateChanges = nearService.viewContractStateChanges(78441183, accountIds, "");

        assertNotNull(contractStateChanges);

        String inputJson = loadJsonFromResourceFile("json-test-samples/accounts-contracts/view-contract-state-changes.json");

        JSONAssert.assertEquals(getPrettyJson(contractStateChanges), inputJson, false);
    }

    // From sample response at:
    // https://docs.near.org/docs/api/rpc/contracts#view-contract-state-changes
    @Test
    void loadedFromExample_contractCodeChanges() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/accounts-contracts/example/view-contract-code-changes.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, ContractCodeChanges.class));

        ContractCodeChanges contractCodeChanges = OBJECT_MAPPER.readValue(inputJson, ContractCodeChanges.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(contractCodeChanges), false);
    }

    // TODO: Empty sample contract code changes
    // (https://docs.near.org/docs/api/rpc/contracts#view-contract-code-changes)
    @Test
    void viewContractCodeChanges_byFinality_contractCodeChanges_notNull() {
        String[] accountIds = new String[1];

        accountIds[0] = "dev-1602714453032-7566969";

        ContractCodeChanges contractCodeChanges = nearService.viewContractCodeChanges(Finality.FINAL, accountIds);

        assertNotNull(contractCodeChanges);
    }

    // TODO: Empty sample contract code changes
    // (https://docs.near.org/docs/api/rpc/contracts#view-contract-code-changes)
    @Test
    void viewContractCodeChanges_byHash_contractCodeChanges_notNull() throws IOException, JSONException {
        String[] accountIds = new String[1];

        accountIds[0] = "dev-1602714453032-7566969";

        ContractCodeChanges contractCodeChanges = nearService.viewContractCodeChanges(
                "HpsjZvjtuxarKRsXGVrgB6qtuCcHRgx3Xof1gfT2Jfj7",
                accountIds);

        assertNotNull(contractCodeChanges);

        String inputJson = loadJsonFromResourceFile("json-test-samples/accounts-contracts/view-contract-code-changes.json");

        JSONAssert.assertEquals(getPrettyJson(contractCodeChanges), inputJson, false);
    }

    // TODO: Empty sample contract code changes
    // (https://docs.near.org/docs/api/rpc/contracts#view-contract-code-changes)
    @Test
    void viewContractCodeChanges_byHeight_contractCodeChanges_notNull() throws JSONException, IOException {
        String[] accountIds = new String[1];

        accountIds[0] = "dev-1602714453032-7566969";

        ContractCodeChanges contractCodeChanges = nearService.viewContractCodeChanges(78441560, accountIds);

        assertNotNull(contractCodeChanges);

        String inputJson = loadJsonFromResourceFile("json-test-samples/accounts-contracts/view-contract-code-changes.json");

        JSONAssert.assertEquals(getPrettyJson(contractCodeChanges), inputJson, false);
    }

    // From sample response at:
    // https://docs.near.org/docs/api/rpc/contracts#view-contract-state-changes
    @Test
    void loadedFromExample_contractFunctionCallResult() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/accounts-contracts/example/call-a-contract-function.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, ContractFunctionCallResult.class));

        ContractFunctionCallResult contractFunctionCallResult = OBJECT_MAPPER.readValue(inputJson,
                ContractFunctionCallResult.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(contractFunctionCallResult), false);
    }

    @Test
    void callContractFunction_byFinality_contractFunctionCallResult_notNull() {
        ContractFunctionCallResult contractFunctionCallResult = nearService
                .callContractFunction(
                        Finality.FINAL,
                        "guest-book.testnet",
                        "getMessages",
                        "e30=");

        assertNotNull(contractFunctionCallResult);
    }

    @Test
    void callContractFunction_byHash_contractFunctionCallResult_notNull() throws IOException, JSONException {
        ContractFunctionCallResult contractFunctionCallResult = nearService
                .callContractFunction(
                        "J5QTB4Stz3iwtHvgr5KnVzNUgzm4J1bE5Et6JWrJPC8o",
                        "guest-book.testnet",
                        "getMessages",
                        "e30=");

        assertNotNull(contractFunctionCallResult);

        String inputJson = loadJsonFromResourceFile("json-test-samples/accounts-contracts/call-a-contract-function.json");
        JSONAssert.assertEquals(getPrettyJson(contractFunctionCallResult), inputJson, false);
    }

    /*
     * the result [ 91, ...103, 103, 103, 34, 125, 93]
     * is nothing but the ASCII array of the following message
     * [
     * { premium: false, sender: 'waverlymaven.testnet', text: 'TGIF' },
     * { premium: true, sender: 'waverlymaven.testnet', text: 'Hello from New York
     * },
     * { premium: false, sender: 'fhr.testnet', text: 'Hi' },
     * { premium: true, sender: 'eugenethedream', text: 'test' },
     * { premium: false, sender: 'dongri.testnet', text: 'test' },
     * { premium: false, sender: 'dongri.testnet', text: 'hello' },
     * { premium: true, sender: 'dongri.testnet', text: 'hey' },
     * { premium: false, sender: 'hirokihori.testnet', text: 'hello' },
     * { premium: true, sender: 'eugenethedream', text: 'hello' },
     * { premium: false, sender: 'example-acct.testnet', text: 'Aloha' },
     * ]
     */
    @Test
    void callContractFunction_byHeight_contractFunctionCallResult_notNull() throws JSONException, IOException {
        ContractFunctionCallResult contractFunctionCallResult = nearService
                .callContractFunction(79272492,
                        "guest-book.testnet",
                        "getMessages",
                        "e30=");

        assertNotNull(contractFunctionCallResult);

        String inputJson = loadJsonFromResourceFile("json-test-samples/accounts-contracts/call-a-contract-function.json");

        JSONAssert.assertEquals(getPrettyJson(contractFunctionCallResult), inputJson, false);
    }

    @Test
    void getBlock_byHash_invalidInput_shouldThrow_NearServiceException() {
        String invalidBlockHash = "invalidBlockHash";

        LOGGER.debug("Calling getBlock with hash {} should throw NearServiceException", invalidBlockHash);

        Throwable t = assertThrows(NearServiceException.class, () -> nearService.getBlock(invalidBlockHash));

        LOGGER.debug("Throwed {}", t.getClass().getSimpleName());
    }

    @Test
    void loadError_validInput_doesHaveErrorData() throws IOException {
        NearServiceExceptionResolver nearServiceExceptionResolver = new NearServiceExceptionResolver();

        String validErrorJson = loadJsonFromResourceFile(
                "json-test-samples/error/valid-error.json");

        Throwable t = nearServiceExceptionResolver
                .resolveException((ObjectNode) OBJECT_MAPPER.readTree(validErrorJson));

        assertInstanceOf(NearServiceException.class, t);

        assertNotNull(((NearServiceException) t).getNearServiceErrorData());
    }

    @Test
    void loadError_invalidInput_doesNotHaveErrorData() throws IOException {
        NearServiceExceptionResolver nearServiceExceptionResolver = new NearServiceExceptionResolver();

        String invalidErrorJson = loadJsonFromResourceFile(
                "json-test-samples/error/invalid-error.json");

        Throwable t = nearServiceExceptionResolver
                .resolveException((ObjectNode) OBJECT_MAPPER.readTree(invalidErrorJson));

        assertInstanceOf(NearServiceException.class, t);

        assertNull(((NearServiceException) t).getNearServiceErrorData());
    }
}
