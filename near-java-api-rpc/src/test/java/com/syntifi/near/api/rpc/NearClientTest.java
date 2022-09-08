package com.syntifi.near.api.rpc;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.syntifi.near.api.common.exception.NearException;
import com.syntifi.near.api.common.exception.NoSuchTypeException;
import com.syntifi.near.api.common.model.common.EncodedHash;
import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.rpc.jsonrpc4j.exception.NearExceptionResolver;
import com.syntifi.near.api.rpc.model.accesskey.AccessKey;
import com.syntifi.near.api.rpc.model.accesskey.AccessKeyChanges;
import com.syntifi.near.api.rpc.model.accesskey.AccessKeyList;
import com.syntifi.near.api.rpc.model.accesskey.Key;
import com.syntifi.near.api.rpc.model.account.Account;
import com.syntifi.near.api.rpc.model.account.AccountChanges;
import com.syntifi.near.api.rpc.model.block.Block;
import com.syntifi.near.api.rpc.model.block.BlockChanges;
import com.syntifi.near.api.rpc.model.block.Chunk;
import com.syntifi.near.api.rpc.model.contract.ContractCode;
import com.syntifi.near.api.rpc.model.contract.ContractCodeChanges;
import com.syntifi.near.api.rpc.model.contract.ContractFunctionCallResult;
import com.syntifi.near.api.rpc.model.contract.ContractState;
import com.syntifi.near.api.rpc.model.contract.ContractStateChanges;
import com.syntifi.near.api.rpc.model.gas.GasPrice;
import com.syntifi.near.api.rpc.model.identifier.Finality;
import com.syntifi.near.api.rpc.model.network.NetworkInfo;
import com.syntifi.near.api.rpc.model.network.NodeStatus;
import com.syntifi.near.api.rpc.model.network.ValidationStatus;
import com.syntifi.near.api.rpc.model.protocol.GenesisConfig;
import com.syntifi.near.api.rpc.model.protocol.ProtocolConfig;
import com.syntifi.near.api.rpc.model.transaction.Receipt;
import com.syntifi.near.api.rpc.model.transaction.TransactionAwait;
import com.syntifi.near.api.rpc.model.transaction.TransactionStatus;
import com.syntifi.near.api.rpc.model.transaction.error.TxExecutionError;
import com.syntifi.near.api.rpc.model.transaction.error.tx.InvalidTxError;
import org.json.JSONException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

import static com.syntifi.near.api.common.json.JsonHelper.OBJECT_MAPPER;
import static com.syntifi.near.api.common.json.JsonHelper.getPrettyJson;
import static com.syntifi.near.api.common.json.JsonHelper.loadJsonFromResourceFile;
import static com.syntifi.near.api.rpc.NearClientArchivalNetHelper.nearClient;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Basic Service call testing
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public class NearClientTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NearClientTest.class);

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
        Block block = nearClient.getBlock(Finality.FINAL);

        assertNotNull(block);
    }

    @Test
    void getBlock_byHash_block_notNull() throws JSONException, IOException {
        Block block = nearClient.getBlock("FXTWzPjqWztjHfneqJb9cBDB2QLTY1Rja4GHrswAv1b9");

        assertNotNull(block);

        String inputJson = loadJsonFromResourceFile("json-test-samples/block-chunk/block-details-by-hash.json");

        JSONAssert.assertEquals(getPrettyJson(block), inputJson, false);
    }

    @Test
    void getBlock_byHash_block_notNull_with_validator_proposals() {
        Block block = nearClient.getBlock("BCEqCXnKijpvQfTMJDn6Bh2We1v1sAZoihApTnJsd32B");

        assertNotNull(block);

        assertEquals(block.getHeader().getValidatorProposals()[0].getValidatorStakeStructVersion(), "V1");
        assertEquals(block.getHeader().getValidatorProposals()[0].getAccountId(), "pontiff.pool.f863973.m0");
        assertEquals(block.getHeader().getValidatorProposals()[0].getPublicKey(),
                PublicKey.fromEncodedBase58String("ed25519:4i8j7nwNyy18hfARtrVpckT8MiicdCXuWBX1TubdMb5Y", PublicKey.class));
        assertEquals(block.getHeader().getValidatorProposals()[0].getStake(), "478888363238890192732941173068");
    }

    @Test
    void getBlock_byHeight_block_notNull() throws JSONException, IOException {
        Block block = nearClient.getBlock(78770817);

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
        BlockChanges blockChanges = nearClient.getBlockChanges(Finality.FINAL);

        assertNotNull(blockChanges);
    }

    @Test
    void getBlockChanges_byHash_blockChanges_notNull() throws JSONException, IOException {
        BlockChanges blockChanges = nearClient.getBlockChanges("BmEZnrmov6h6rMPpWkMV2JtU1C5LP563V5Y5yXwUW2N5");

        assertNotNull(blockChanges);

        String inputJson = loadJsonFromResourceFile("json-test-samples/block-chunk/changes-in-block-by-hash.json");

        JSONAssert.assertEquals(getPrettyJson(blockChanges), inputJson, false);
    }

    @Test
    void getBlockChanges_byHeight_blockChanges_notNull() throws JSONException, IOException {
        BlockChanges blockChanges = nearClient.getBlockChanges(78770674);

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
        Chunk chunk = nearClient.getChunkDetails("9mdG2cRcV8Dsb1EoSjtya81NddjRB2stYCTVukZh7zzw");

        assertNotNull(chunk);

        String inputJson = loadJsonFromResourceFile("json-test-samples/block-chunk/chunk-details.json");

        JSONAssert.assertEquals(getPrettyJson(chunk), inputJson, false);
    }

    @Test
    void getChunkDetails_byBlockHashAndShardId_chunk_notNull() throws JSONException, IOException {
        Chunk chunk = nearClient.getChunkDetails("F1HXTzeYgYq28rgsHuKUrRbo5QTBGKFYG7rbxXkRZWXN", 0);

        assertNotNull(chunk);

        String inputJson = loadJsonFromResourceFile("json-test-samples/block-chunk/chunk-details.json");

        JSONAssert.assertEquals(getPrettyJson(chunk), inputJson, false);
    }

    @Test
    void getChunkDetails_byBlockHeightAndShardId_chunk_notNull() throws JSONException, IOException {
        Chunk chunk = nearClient.getChunkDetails(78567387, 0);

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
        NetworkInfo networkInfo = nearClient.getNetworkInfo();

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
        NodeStatus nodeStatus = nearClient.getNodeStatus();

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
        ValidationStatus networkValidationStatus = nearClient.getNetworkValidationStatus(null);

        assertNotNull(networkValidationStatus);
    }

    // TODO: enable this test when working test data is available
    @Disabled("No validators found! (https://docs.near.org/docs/api/rpc/network#validation-status)")
    @Test
    void getNetworkValidationStatus_byBlockHash_validationStatus_notNull() {
        Block lastBlock = nearClient.getBlock(Finality.FINAL);

        ValidationStatus networkValidationStatus = nearClient
                .getNetworkValidationStatus(lastBlock.getHeader().getHash().getEncodedHash());

        assertNotNull(networkValidationStatus);
    }

    // TODO: enable this test when working test data is available
    @Disabled("No validators found! (https://docs.near.org/docs/api/rpc/network#validation-status)")
    @Test
    void getNetworkValidationStatus_byBlockNumber_validationStatus_notNull() {
        Block lastBlock = nearClient.getBlock(Finality.OPTIMISTIC);

        ValidationStatus networkValidationStatus = nearClient
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
        GasPrice gasPrice = nearClient.getGasPrice(null);

        assertNotNull(gasPrice);

        String inputJson = loadJsonFromResourceFile("json-test-samples/gas/gas-price-by-null.json");

        JSONAssert.assertEquals(getPrettyJson(gasPrice), inputJson, false);
    }

    @Test
    void getGasPrice_byBlockHash_gasPrice_notNull() throws JSONException, IOException {
        GasPrice gasPrice = nearClient.getGasPrice("FXTWzPjqWztjHfneqJb9cBDB2QLTY1Rja4GHrswAv1b9");

        assertNotNull(gasPrice);

        String inputJson = loadJsonFromResourceFile("json-test-samples/gas/gas-price-by-block-hash.json");

        JSONAssert.assertEquals(getPrettyJson(gasPrice), inputJson, false);
    }

    @Test
    void getGasPrice_byBlockNumber_gasPrice_notNull() throws IOException, JSONException {
        GasPrice gasPrice = nearClient.getGasPrice(78770817);

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
        GenesisConfig genesisConfig = nearClient.getGenesisConfig();

        assertNotNull(genesisConfig);
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
    void getProtocolConfig_byFinality_protocolConfig_notNull() {
        ProtocolConfig protocolConfig = nearClient.getProtocolConfig(Finality.FINAL);

        assertNotNull(protocolConfig);
    }

    @Test
    void getProtocolConfig_byHash_protocolConfig_notNullProtocolVersion() {
        ProtocolConfig protocolConfig = nearClient.getProtocolConfig("FXTWzPjqWztjHfneqJb9cBDB2QLTY1Rja4GHrswAv1b9");

        assertNotNull(protocolConfig);
    }

    @Test
    void getProtocolConfig_byHeight_protocolConfig_notNullProtocolVersion() {
        ProtocolConfig protocolConfig = nearClient.getProtocolConfig(78770817);

        assertNotNull(protocolConfig);
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

        String transactionHash = nearClient.sendTransactionAsync(signedTransaction);

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

        TransactionAwait transaction = nearClient.sendTransactionAwait(signedTransaction);

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

    //TODO: Check implement the code to run this test
    // Errors at:  https://docs.near.org/integrator/errors/introduction#near-platform-errors
    @Test
    void loadedFromExample_transactionStatusFailure()
            throws IOException, JSONException {

        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/transaction/example/transaction-status-failure.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TransactionStatus.class));

        TransactionStatus transactionStatus = OBJECT_MAPPER.readValue(inputJson, TransactionStatus.class);

        JSONAssert.assertEquals(inputJson, getPrettyJson(transactionStatus), false);
    }

    @Test
    void getTransactionStatus_transactionStatus_notNull() {
        String transactionHash = "DwWUi6WbVHKTCDjVu4gmuQfryqjwTjrZ6ntRcKcGN6Gd";
        String accountId = "isonar.testnet";

        TransactionStatus transactionStatus = nearClient.getTransactionStatus(transactionHash, accountId);

        assertNotNull(transactionStatus);
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
    void getTransactionStatusWithReceipts_transactionStatusWithReceipts_notNull() {
        String transactionHash = "DwWUi6WbVHKTCDjVu4gmuQfryqjwTjrZ6ntRcKcGN6Gd";
        String accountId = "isonar.testnet";

        TransactionStatus transactionStatusWithReceipts = nearClient.getTransactionStatusWithReceipts(transactionHash,
                accountId);

        assertNotNull(transactionStatusWithReceipts);
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

        Receipt transactionReceipt = nearClient.getTransactionReceipt(receiptId);

        assertNotNull(transactionReceipt);

        String inputJson = loadJsonFromResourceFile("json-test-samples/transaction/receipt.json");

        JSONAssert.assertEquals(getPrettyJson(transactionReceipt), inputJson, false);
    }

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

        AccessKey accessKey = nearClient.viewAccessKey(Finality.FINAL, accountId, publicKey);

        assertNotNull(accessKey);
    }

    @Test
    void viewAccessKey_byHash_accessKey_notNull() throws IOException, JSONException {
        String accountId = "client.chainlink.testnet";
        String publicKey = "ed25519:H9k5eiU4xXS3M4z8HzKJSLaZdqGdGwBG49o7orNC4eZW";

        AccessKey accessKey = nearClient.viewAccessKey("8bVg8wugs2QHqXr42oEsCYyH7jvR9pLaAP35dFqx2evU", accountId,
                publicKey);

        assertNotNull(accessKey);

        String inputJson = loadJsonFromResourceFile("json-test-samples/access-key/view-access-key.json");

        JSONAssert.assertEquals(getPrettyJson(accessKey), inputJson, false);
    }

    @Test
    void viewAccessKey_byHeight_accessKey_notNull() throws JSONException, IOException {
        String accountId = "client.chainlink.testnet";
        String publicKey = "ed25519:H9k5eiU4xXS3M4z8HzKJSLaZdqGdGwBG49o7orNC4eZW";

        AccessKey accessKey = nearClient.viewAccessKey(78443365, accountId, publicKey);

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

        AccessKeyList accessKeyList = nearClient.viewAccessKeyList(Finality.FINAL, accountId);

        assertNotNull(accessKeyList);
    }

    @Test
    void viewAccessKeyList_byHash_accessKey_notNull() throws IOException, JSONException {
        String accountId = "client.chainlink.testnet";

        AccessKeyList accessKeyList = nearClient.viewAccessKeyList("DwFpDPiQXBaX6Vw3aKazQ4nXjgzw1uk6XpUkfTSJrbXf",
                accountId);

        assertNotNull(accessKeyList);

        String inputJson = loadJsonFromResourceFile("json-test-samples/access-key/view-access-key-list-by-hash.json");

        JSONAssert.assertEquals(getPrettyJson(accessKeyList), inputJson, false);
    }

    @Test
    void viewAccessKeyList_byHeight_accessKey_notNull() throws IOException, JSONException {
        String accountId = "client.chainlink.testnet";

        AccessKeyList accessKeyList = nearClient.viewAccessKeyList(78772585, accountId);

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

        AccessKeyChanges accessKeyChanges = nearClient.viewSingleAccessKeyChanges(Finality.FINAL, keys);

        assertNotNull(accessKeyChanges);
    }

    // TODO: access key with some changes, the given one is empty:
    // (https://docs.near.org/docs/api/rpc/access-keys#view-access-key-changes-single)
    @Test
    void viewSingleAccessKeyChanges_byHash_accessKey_notNull() {
        Key[] keys = new Key[1];

        Key key0 = new Key("example-acct.testnet", PublicKey.fromEncodedBase58String("ed25519:25KEc7t7MQohAJ4EDThd2vkksKkwangnuJFzcoiXj9oM", PublicKey.class));
        keys[0] = key0;

        AccessKeyChanges accessKeyChanges = nearClient.viewSingleAccessKeyChanges(
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

        AccessKeyChanges accessKeyChanges = nearClient.viewSingleAccessKeyChanges(78433961, keys);

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

        AccessKeyChanges accessKeyChanges = nearClient.viewAllAccessKeyChanges(Finality.FINAL, accountIds);

        assertNotNull(accessKeyChanges);
    }

    // TODO: access key with some changes, the given block does not exist:
    // (https://docs.near.org/docs/api/rpc/access-keys#view-access-key-changes-all)
    @Test
    void viewAllAccessKeyChanges_byHash_accessKey_notNull() {
        String[] accountIds = new String[1];

        accountIds[0] = "client.chainlink.testnet";

        AccessKeyChanges accessKeyChanges = nearClient.viewAllAccessKeyChanges(
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

        AccessKeyChanges accessKeyChanges = nearClient.viewAllAccessKeyChanges(78433518, accountIds);

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
        Account account = nearClient.viewAccount(Finality.FINAL, "nearkat.testnet");

        assertNotNull(account);
    }

    @Test
    void viewAccount_byHash_account_notNull() throws IOException, JSONException {
        Account account = nearClient.viewAccount("5hyGx7LiGaeRiAN4RrKcGomi1QXHqZwKXFQf6xTmvUgb", "nearkat.testnet");

        assertNotNull(account);

        String inputJson = loadJsonFromResourceFile("json-test-samples/accounts-contracts/view-account.json");

        JSONAssert.assertEquals(getPrettyJson(account), inputJson, false);
    }

    @Test
    void viewAccount_byHeight_account_notNull() throws JSONException, IOException {
        Account account = nearClient.viewAccount(78439658, "nearkat.testnet");

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

        AccountChanges accountChanges = nearClient.viewAccountChanges(Finality.FINAL, accountIds);

        assertNotNull(accountChanges);
    }

    // TODO: Empty sample account changes
    // (https://docs.near.org/docs/api/rpc/contracts#view-account-changes)
    @Test
    void viewAccountChanges_byHash_account_notNull() throws IOException, JSONException {
        String[] accountIds = new String[1];

        accountIds[0] = "nearkat.testnet";

        AccountChanges accountChanges = nearClient.viewAccountChanges("7vWp2djKLoJ3RE1sr8RzSKQtyzKpe2wZ7NCcDuFNuL7j",
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

        AccountChanges accountChanges = nearClient.viewAccountChanges(78440142, accountIds);

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
        ContractCode contractCode = nearClient.viewContractCode(Finality.FINAL, "guest-book.testnet");

        assertNotNull(contractCode);
    }

    @Test
    void viewContractCode_byHash_contractCode_notNull() throws IOException, JSONException {
        ContractCode contractCode = nearClient.viewContractCode("uLxyauKPhSk1tebYKi3k69pHSaT2ZLzWy4JwtGm52pu",
                "guest-book.testnet");

        assertNotNull(contractCode);

        String inputJson = loadJsonFromResourceFile("json-test-samples/accounts-contracts/view-contract-code.json");

        JSONAssert.assertEquals(getPrettyJson(contractCode), inputJson, false);
    }

    @Test
    void viewContractCode_byHeight_contractCode_notNull() throws JSONException, IOException {
        ContractCode contractCode = nearClient.viewContractCode(78440518, "guest-book.testnet");

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
        ContractState contractState = nearClient.viewContractState(Finality.FINAL,
                "guest-book.testnet", "");

        assertNotNull(contractState);
    }

    // TODO: enable this test when working test data is available
    @Disabled("Too large contract state! (https://docs.near.org/docs/api/rpc/contracts#view-contract-state)")
    @Test
    void viewContractState_byHash_contractCode_notNull() throws IOException,
            JSONException {
        ContractState contractState = nearClient.viewContractState("342bkjvnzoZ7FGRE5BwDVkzSRUYXAScTz3GsDB9sEHXg",
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
        ContractState contractState = nearClient.viewContractState(78440679,
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

        ContractStateChanges contractStateChanges = nearClient.viewContractStateChanges(Finality.FINAL, accountIds,
                "");

        assertNotNull(contractStateChanges);
    }

    // TODO: Empty sample contract code changes
    // (https://docs.near.org/docs/api/rpc/contracts#view-contract-state-changes)
    @Test
    void viewContractStateChanges_byHash_contractStateChanges_notNull() throws IOException, JSONException {
        String[] accountIds = new String[1];

        accountIds[0] = "guest-book.testnet";

        ContractStateChanges contractStateChanges = nearClient.viewContractStateChanges(
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

        ContractStateChanges contractStateChanges = nearClient.viewContractStateChanges(78441183, accountIds, "");

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

        ContractCodeChanges contractCodeChanges = nearClient.viewContractCodeChanges(Finality.FINAL, accountIds);

        assertNotNull(contractCodeChanges);
    }

    // TODO: Empty sample contract code changes
    // (https://docs.near.org/docs/api/rpc/contracts#view-contract-code-changes)
    @Test
    void viewContractCodeChanges_byHash_contractCodeChanges_notNull() throws IOException, JSONException {
        String[] accountIds = new String[1];

        accountIds[0] = "dev-1602714453032-7566969";

        ContractCodeChanges contractCodeChanges = nearClient.viewContractCodeChanges(
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

        ContractCodeChanges contractCodeChanges = nearClient.viewContractCodeChanges(78441560, accountIds);

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

        String expectedJson = getPrettyJson(contractFunctionCallResult);

        JSONAssert.assertEquals(inputJson, expectedJson, false);
    }

    @Test
    void callContractFunction_byFinality_contractFunctionCallResult_notNull() {
        ContractFunctionCallResult contractFunctionCallResult = nearClient
                .callContractFunction(
                        Finality.FINAL,
                        "guest-book.testnet",
                        "getMessages",
                        "e30=");

        assertNotNull(contractFunctionCallResult);
    }

    @Test
    void callContractFunction_byHash_contractFunctionCallResult_notNull() throws IOException, JSONException {
        ContractFunctionCallResult contractFunctionCallResult = nearClient
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
        ContractFunctionCallResult contractFunctionCallResult = nearClient
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

        Throwable t = assertThrows(NearException.class, () -> nearClient.getBlock(invalidBlockHash));

        LOGGER.debug("Threw {}", t.getClass().getSimpleName());
    }

    @Test
    void getTransactionStatus_bySwapWithdrawHash_should_work() {
        EncodedHash encodedHash = EncodedHash.builder().encodedHash("F8VYtmexjeaKzgAoF4BvqVcnPov4cqXUeV1hpEysSS57").build();

        LOGGER.debug("Calling getTransactionStatus with hash {}", encodedHash.getEncodedHash());

        TransactionStatus transactionStatus = nearClient.getTransactionStatus(encodedHash.getEncodedHash(), "syntifi-alice.testnet");

        LOGGER.debug("Result is {}", transactionStatus.getStatus());
    }

    @Test
    void getTransactionStatus_bySwapDepositHash_should_work() {
        EncodedHash encodedHash = EncodedHash.builder().encodedHash("GcKT9Vg2jpAvVRbZf8s6GCpPJFw9RwmDFpb6rAB5nkwU").build();

        LOGGER.debug("Calling getTransactionStatus with hash {}", encodedHash.getEncodedHash());

        TransactionStatus transactionStatus = nearClient.getTransactionStatus(encodedHash.getEncodedHash(), "syntifi-alice.testnet");

        LOGGER.debug("Result is {}", transactionStatus.getStatus());
    }




}
