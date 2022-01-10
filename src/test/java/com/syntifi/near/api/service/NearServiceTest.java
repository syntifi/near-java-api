package com.syntifi.near.api.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.net.MalformedURLException;

import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.model.accesskey.Key;
import com.syntifi.near.api.model.identifier.Finality;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Basic Service call testing
 * 
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public class NearServiceTest extends BaseNearServiceTest {

    private static NearService nearService;
    private static Logger LOGGER = LoggerFactory.getLogger(NearServiceTest.class);

    private final String blockHash = "D77J7WnXXstAtoeuHzPFtg7Mt2FXDffm2WF8uvbsigeS";
    private final long blockHeight = 78430690;

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        String peerAddress = "rpc.testnet.near.org";
        // String peerAddress = "archival-rpc.testnet.near.org";

        LOGGER.debug("======== Running tests with peer {} ========", peerAddress);
        nearService = NearService.usingPeer(peerAddress);
    }

    @Test
    void getBlock_byFinality_block_notNull() {
        JsonNode block = nearService.getBlock(Finality.FINAL);

        assertNotNull(block);
    }

    @Test
    void getBlock_byHash_block_notNull() throws JSONException, IOException {
        JsonNode block = nearService.getBlock(blockHash);

        assertNotNull(block);

        String inputJson = loadJsonFromFile("json-test-samples/block/by-hash.json");

        JSONAssert.assertEquals(getPrettyJson(block), inputJson, false);
    }

    @Test
    void getBlock_byHeight_block_notNull() throws JSONException, IOException {
        JsonNode block = nearService.getBlock(blockHeight);

        assertNotNull(block);

        String inputJson = loadJsonFromFile("json-test-samples/block/by-height.json");

        JSONAssert.assertEquals(getPrettyJson(block), inputJson, false);
    }

    @Test
    void getNetworkInfo_networkInfo_notNull() {
        JsonNode networkInfo = nearService.getNetworkInfo();

        assertNotNull(networkInfo);
    }

    @Test
    void getNetworkStatus_networkStatus_notNull() {
        JsonNode networkStatus = nearService.getNetworkStatus();

        assertNotNull(networkStatus);
    }

    @Test
    void getNetworkValidationStatus_byNull_networkValidationStatus_notNull() {
        JsonNode networkValidationStatus = nearService.getNetworkValidationStatus(null);

        assertNotNull(networkValidationStatus);
    }

    // FIXME: No validators found
    // (https://docs.near.org/docs/api/rpc/network#validation-status)
    @Test
    void getNetworkValidationStatus_byBlockHash_networkValidationStatus_notNull() {
        JsonNode lastBlock = nearService.getBlock(Finality.FINAL);

        JsonNode networkValidationStatus = nearService
                .getNetworkValidationStatus(lastBlock.get("header").get("hash").asText());

        assertNotNull(networkValidationStatus);
    }

    // FIXME: No validators found
    // (https://docs.near.org/docs/api/rpc/network#validation-status)
    @Test
    void getNetworkValidationStatus_byBlockNumber_networkValidationStatus_notNull() {
        JsonNode lastBlock = nearService.getBlock(Finality.OPTIMISTIC);

        JsonNode networkValidationStatus = nearService
                .getNetworkValidationStatus(lastBlock.get("header").get("height").asLong());

        assertNotNull(networkValidationStatus);
    }

    @Test
    void getGasPrice_byNull_gasPrice_notNull() throws IOException, JSONException {
        JsonNode gasPrice = nearService.getGasPrice(null);

        assertNotNull(gasPrice);

        String inputJson = loadJsonFromFile("json-test-samples/gas/by-null.json");

        JSONAssert.assertEquals(getPrettyJson(gasPrice), inputJson, false);
    }

    @Test
    void getGasPrice_byBlockHash_gasPrice_notNull() throws JSONException, IOException {
        JsonNode gasPrice = nearService.getGasPrice(blockHash);

        assertNotNull(gasPrice);

        String inputJson = loadJsonFromFile("json-test-samples/gas/by-block-hash.json");

        JSONAssert.assertEquals(getPrettyJson(gasPrice), inputJson, false);
    }

    @Test
    void getGasPrice_byBlockNumber_gasPrice_notNull() throws IOException, JSONException {
        JsonNode gasPrice = nearService.getGasPrice(78430929);

        assertNotNull(gasPrice);

        String inputJson = loadJsonFromFile("json-test-samples/gas/by-block-height.json");

        JSONAssert.assertEquals(getPrettyJson(gasPrice), inputJson, false);
    }

    @Test
    void getGenesisConfig_genesisConfig_notNull() throws JSONException, IOException {
        JsonNode genesisConfig = nearService.getGenesisConfig();

        assertNotNull(genesisConfig);

        String inputJson = loadJsonFromFile("json-test-samples/protocol/genesis.json");

        JSONAssert.assertEquals(getPrettyJson(genesisConfig), inputJson, false);
    }

    @Test
    void getProtocolConfig_byFinality_protocolConfig_notNull() throws IOException, JSONException {
        JsonNode protocolConfig = nearService.getProtocolConfig(Finality.FINAL);

        assertNotNull(protocolConfig);

        String inputJson = loadJsonFromFile("json-test-samples/protocol/protocol.json");

        JSONAssert.assertEquals(getPrettyJson(protocolConfig), inputJson, false);
    }

    @Test
    void getProtocolConfig_byHash_protocolConfig_notNullProtocolVersion() throws IOException, JSONException {
        JsonNode protocolConfig = nearService.getProtocolConfig(blockHash);

        assertNotNull(protocolConfig);

        String inputJson = loadJsonFromFile("json-test-samples/protocol/protocol-by-hash.json");

        JSONAssert.assertEquals(getPrettyJson(protocolConfig), inputJson, false);
    }

    @Test
    void getProtocolConfig_byHeight_protocolConfig_notNullProtocolVersion() throws IOException, JSONException {
        JsonNode protocolConfig = nearService.getProtocolConfig(blockHeight);

        assertNotNull(protocolConfig);

        String inputJson = loadJsonFromFile("json-test-samples/protocol/protocol-by-height.json");

        JSONAssert.assertEquals(getPrettyJson(protocolConfig), inputJson, false);
    }

    @Test
    void sendTransactionAsync_transactionHash_notNull() throws IOException, JSONException {
        String signedTransaction = "DgAAAHNlbmRlci50ZXN0bmV0AOrmAai64SZOv9e/naX4W15pJx0GAap35wTT1T/DwcbbDwAAAAAAAAAQAAAAcmVjZWl2ZXIudGVzdG5ldNMnL7URB1cxPOu3G8jTqlEwlcasagIbKlAJlF5ywVFLAQAAAAMAAACh7czOG8LTAAAAAAAAAGQcOG03xVSFQFjoagOb4NBBqWhERnnz45LY4+52JgZhm1iQKz7qAdPByrGFDQhQ2Mfga8RlbysuQ8D8LlA6bQE=";

        JsonNode transactionHash = nearService.sendTransactionAsync(signedTransaction);

        assertNotNull(transactionHash);

        String inputJson = loadJsonFromFile("json-test-samples/transaction/send-async.json");

        JSONAssert.assertEquals(getPrettyJson(transactionHash), inputJson, false);
    }

    // FIXME: Error expired
    // (https://docs.near.org/docs/api/rpc/transactions#send-transaction-await)
    @Test
    void sendTransactionAwait_transaction_notNull() throws IOException, JSONException {
        String signedTransaction = "DgAAAHNlbmRlci50ZXN0bmV0AOrmAai64SZOv9e/naX4W15pJx0GAap35wTT1T/DwcbbDQAAAAAAAAAQAAAAcmVjZWl2ZXIudGVzdG5ldIODI4YfV/QS++blXpQYT+bOsRblTRW4f547y/LkvMQ9AQAAAAMAAACh7czOG8LTAAAAAAAAAAXcaTJzu9GviPT7AD4mNJGY79jxTrjFLoyPBiLGHgBi8JK1AnhK8QknJ1ourxlvOYJA2xEZE8UR24THmSJcLQw=";

        JsonNode transaction = nearService.sendTransactionAwait(signedTransaction);

        assertNotNull(transaction);

        String inputJson = loadJsonFromFile("json-test-samples/transaction/send-await.json");

        JSONAssert.assertEquals(getPrettyJson(transaction), inputJson, false);
    }

    @Test
    void getTransactionStatus_transactionStatus_notNull() throws IOException, JSONException {
        String transactionHash = "DwWUi6WbVHKTCDjVu4gmuQfryqjwTjrZ6ntRcKcGN6Gd";
        String accountId = "isonar.testnet";

        JsonNode transactionStatus = nearService.getTransactionStatus(transactionHash, accountId);

        assertNotNull(transactionStatus);

        String inputJson = loadJsonFromFile("json-test-samples/transaction/status-by-hash.json");

        JSONAssert.assertEquals(getPrettyJson(transactionStatus), inputJson, false);
    }

    @Test
    void getTransactionStatusWithReceipts_transactionStatusWithReceipts_notNull()
            throws IOException, JSONException {
        String transactionHash = "DwWUi6WbVHKTCDjVu4gmuQfryqjwTjrZ6ntRcKcGN6Gd";
        String accountId = "isonar.testnet";

        JsonNode transactionStatusWithReceipts = nearService.getTransactionStatusWithReceipts(transactionHash,
                accountId);

        assertNotNull(transactionStatusWithReceipts);

        String inputJson = loadJsonFromFile("json-test-samples/transaction/status-by-hash-with-receipt.json");

        JSONAssert.assertEquals(getPrettyJson(transactionStatusWithReceipts), inputJson, false);
    }

    @Test
    void getTransactionReceipt_transactionReceipt_notNull() throws JSONException, IOException {
        String receiptId = "8b9Vt1xH8DZecMda1YqUcMWA41NvknUJJVd2XEQikPRs";

        JsonNode transactionReceipt = nearService.getTransactionReceipt(receiptId);

        assertNotNull(transactionReceipt);

        String inputJson = loadJsonFromFile("json-test-samples/transaction/receipt.json");

        JSONAssert.assertEquals(getPrettyJson(transactionReceipt), inputJson, false);
    }

    @Test
    void viewAccessKey_byFinality_accessKey_notNull() {
        String accountId = "client.chainlink.testnet";
        String publicKey = "ed25519:H9k5eiU4xXS3M4z8HzKJSLaZdqGdGwBG49o7orNC4eZW";

        JsonNode accessKey = nearService.viewAccessKey(Finality.FINAL, accountId, publicKey);

        assertNotNull(accessKey);
    }

    @Test
    void viewAccessKey_byHash_accessKey_notNull() throws IOException, JSONException {
        String accountId = "client.chainlink.testnet";
        String publicKey = "ed25519:H9k5eiU4xXS3M4z8HzKJSLaZdqGdGwBG49o7orNC4eZW";

        JsonNode accessKey = nearService.viewAccessKey("8bVg8wugs2QHqXr42oEsCYyH7jvR9pLaAP35dFqx2evU", accountId,
                publicKey);

        assertNotNull(accessKey);

        String inputJson = loadJsonFromFile("json-test-samples/access-key/view-access-key.json");

        JSONAssert.assertEquals(getPrettyJson(accessKey), inputJson, false);
    }

    @Test
    void viewAccessKey_byHeight_accessKey_notNull() throws JSONException, IOException {
        String accountId = "client.chainlink.testnet";
        String publicKey = "ed25519:H9k5eiU4xXS3M4z8HzKJSLaZdqGdGwBG49o7orNC4eZW";

        JsonNode accessKey = nearService.viewAccessKey(78443365, accountId, publicKey);

        assertNotNull(accessKey);

        String inputJson = loadJsonFromFile("json-test-samples/access-key/view-access-key.json");

        JSONAssert.assertEquals(getPrettyJson(accessKey), inputJson, false);
    }

    @Test
    void viewAccessKeyList_byFinality_accessKey_notNull() {
        String accountId = "client.chainlink.testnet";

        JsonNode accessKey = nearService.viewAccessKeyList(Finality.FINAL, accountId);

        assertNotNull(accessKey);
    }

    @Test
    void viewAccessKeyList_byHash_accessKey_notNull() {
        String accountId = "client.chainlink.testnet";

        JsonNode accessKey = nearService.viewAccessKeyList("4EbsLgu2Fs6Z96vf1chHrjkawkm595sNUCHeGE8QuhVx", accountId);

        assertNotNull(accessKey);
    }

    @Test
    void viewAccessKeyList_byHeight_accessKey_notNull() {
        String accountId = "client.chainlink.testnet";

        JsonNode accessKey = nearService.viewAccessKeyList(78433783, accountId);

        assertNotNull(accessKey);
    }

    // Empty changes
    // (https://docs.near.org/docs/api/rpc/access-keys#view-access-key-changes-single)
    @Test
    void viewSingleAccessKeyChanges_byFinality_accessKey_notNull() {
        Key[] keys = new Key[1];

        Key key0 = new Key("example-acct.testnet", "ed25519:25KEc7t7MQohAJ4EDThd2vkksKkwangnuJFzcoiXj9oM");
        keys[0] = key0;

        JsonNode accessKey = nearService.viewSingleAccessKeyChanges(Finality.FINAL, keys);

        assertNotNull(accessKey);
    }

    // Empty changes
    // (https://docs.near.org/docs/api/rpc/access-keys#view-access-key-changes-single)
    @Test
    void viewSingleAccessKeyChanges_byHash_accessKey_notNull() {
        Key[] keys = new Key[1];

        Key key0 = new Key("example-acct.testnet", "ed25519:25KEc7t7MQohAJ4EDThd2vkksKkwangnuJFzcoiXj9oM");
        keys[0] = key0;

        JsonNode accessKey = nearService.viewSingleAccessKeyChanges("Cr82U81VqHgCz9LzZjPivh9t16e8es6aFCv9qvDMMH88",
                keys);

        assertNotNull(accessKey);
    }

    // Empty changes
    // (https://docs.near.org/docs/api/rpc/access-keys#view-access-key-changes-single)
    @Test
    void viewSingleAccessKeyChanges_byHeight_accessKey_notNull() {
        Key[] keys = new Key[1];

        Key key0 = new Key("example-acct.testnet", "ed25519:25KEc7t7MQohAJ4EDThd2vkksKkwangnuJFzcoiXj9oM");
        keys[0] = key0;

        JsonNode accessKey = nearService.viewSingleAccessKeyChanges(78433961, keys);

        assertNotNull(accessKey);
    }

    @Test
    void viewAllAccessKeyChanges_byFinality_accessKey_notNull() {
        String[] accountIds = new String[1];

        accountIds[0] = "client.chainlink.testnet";

        JsonNode accessKey = nearService.viewAllAccessKeyChanges(Finality.FINAL, accountIds);

        assertNotNull(accessKey);
    }

    @Test
    void viewAllAccessKeyChanges_byHash_accessKey_notNull() {
        String[] accountIds = new String[1];

        accountIds[0] = "client.chainlink.testnet";

        JsonNode accessKey = nearService.viewAllAccessKeyChanges("Ais9kPbHvk6XmEYptoEpBtyFW77V16TZNHHnYtmXWr1d",
                accountIds);

        assertNotNull(accessKey);
    }

    @Test
    void viewAllAccessKeyChanges_byHeight_accessKey_notNull() {
        String[] accountIds = new String[1];

        accountIds[0] = "client.chainlink.testnet";

        JsonNode accessKey = nearService.viewAllAccessKeyChanges(78433518, accountIds);

        assertNotNull(accessKey);
    }

    @Test
    void viewAccount_byFinality_account_notNull() throws IOException, JSONException {
        JsonNode account = nearService.viewAccount(Finality.FINAL, "nearkat.testnet");

        assertNotNull(account);
    }

    @Test
    void viewAccount_byHash_account_notNull() throws IOException, JSONException {
        JsonNode account = nearService.viewAccount("5hyGx7LiGaeRiAN4RrKcGomi1QXHqZwKXFQf6xTmvUgb", "nearkat.testnet");

        assertNotNull(account);

        String inputJson = loadJsonFromFile("json-test-samples/account/view-account.json");

        JSONAssert.assertEquals(getPrettyJson(account), inputJson, false);
    }

    @Test
    void viewAccount_byHeight_account_notNull() throws JSONException, IOException {
        JsonNode account = nearService.viewAccount(78439658, "nearkat.testnet");

        assertNotNull(account);

        String inputJson = loadJsonFromFile("json-test-samples/account/view-account.json");

        JSONAssert.assertEquals(getPrettyJson(account), inputJson, false);
    }

    // FIXME: Empty sample account changes
    // (https://docs.near.org/docs/api/rpc/contracts#view-account-changes)
    @Test
    void viewAccountChanges_byFinality_account_notNull() throws IOException, JSONException {
        String[] accountIds = new String[1];

        accountIds[0] = "nearkat.testnet";

        JsonNode account = nearService.viewAccountChanges(Finality.FINAL, accountIds);

        assertNotNull(account);
    }

    // FIXME: Empty sample account changes
    // (https://docs.near.org/docs/api/rpc/contracts#view-account-changes)
    @Test
    void viewAccountChanges_byHash_account_notNull() throws IOException, JSONException {
        String[] accountIds = new String[1];

        accountIds[0] = "nearkat.testnet";

        JsonNode account = nearService.viewAccountChanges("7vWp2djKLoJ3RE1sr8RzSKQtyzKpe2wZ7NCcDuFNuL7j", accountIds);

        assertNotNull(account);

        String inputJson = loadJsonFromFile("json-test-samples/account/view-account-changes.json");

        JSONAssert.assertEquals(getPrettyJson(account), inputJson, false);
    }

    // FIXME: Empty sample account changes
    // (https://docs.near.org/docs/api/rpc/contracts#view-account-changes)
    @Test
    void viewAccountChanges_byHeight_account_notNull() throws JSONException, IOException {
        String[] accountIds = new String[1];

        accountIds[0] = "nearkat.testnet";

        JsonNode account = nearService.viewAccountChanges(78440142, accountIds);

        assertNotNull(account);

        String inputJson = loadJsonFromFile("json-test-samples/account/view-account-changes.json");

        JSONAssert.assertEquals(getPrettyJson(account), inputJson, false);
    }

    @Test
    void viewContractCode_byFinality_contractCode_notNull() throws IOException, JSONException {
        JsonNode contractCode = nearService.viewContractCode(Finality.FINAL, "guest-book.testnet");

        assertNotNull(contractCode);
    }

    @Test
    void viewContractCode_byHash_contractCode_notNull() throws IOException, JSONException {
        JsonNode contractCode = nearService.viewContractCode("uLxyauKPhSk1tebYKi3k69pHSaT2ZLzWy4JwtGm52pu",
                "guest-book.testnet");

        assertNotNull(contractCode);

        String inputJson = loadJsonFromFile("json-test-samples/account/view-contract-code.json");

        JSONAssert.assertEquals(getPrettyJson(contractCode), inputJson, false);
    }

    @Test
    void viewContractCode_byHeight_contractCode_notNull() throws JSONException, IOException {
        JsonNode contractCode = nearService.viewContractCode(78440518, "guest-book.testnet");

        assertNotNull(contractCode);

        String inputJson = loadJsonFromFile("json-test-samples/account/view-contract-code.json");

        JSONAssert.assertEquals(getPrettyJson(contractCode), inputJson, false);
    }

    // FIXME: Too large contract state
    // (https://docs.near.org/docs/api/rpc/contracts#view-contract-state)
    @Test
    void viewContractState_byFinality_contractCode_notNull() throws IOException, JSONException {
        JsonNode contractCode = nearService.viewContractState(Finality.FINAL, "guest-book.testnet", "");

        assertNotNull(contractCode);
    }

    // FIXME: Too large contract state
    // (https://docs.near.org/docs/api/rpc/contracts#view-contract-state)
    @Test
    void viewContractState_byHash_contractCode_notNull() throws IOException, JSONException {
        JsonNode contractCode = nearService.viewContractState("342bkjvnzoZ7FGRE5BwDVkzSRUYXAScTz3GsDB9sEHXg",
                "guest-book.testnet", "");

        assertNotNull(contractCode);

        String inputJson = loadJsonFromFile("json-test-samples/account/view-contract-state.json");

        JSONAssert.assertEquals(getPrettyJson(contractCode), inputJson, false);
    }

    // FIXME: Too large contract state
    // (https://docs.near.org/docs/api/rpc/contracts#view-contract-state)
    @Test
    void viewContractState_byHeight_contractCode_notNull() throws JSONException, IOException {
        JsonNode contractCode = nearService.viewContractState(78440679, "guest-book.testnet", "");

        assertNotNull(contractCode);

        String inputJson = loadJsonFromFile("json-test-samples/account/view-contract-state.json");

        JSONAssert.assertEquals(getPrettyJson(contractCode), inputJson, false);
    }

    // FIXME: Empty sample contract code changes
    // (https://docs.near.org/docs/api/rpc/contracts#view-contract-state-changes)
    @Test
    void viewContractStateChanges_byFinality_account_notNull() throws IOException, JSONException {
        String[] accountIds = new String[1];

        accountIds[0] = "guest-book.testnet";

        JsonNode account = nearService.viewContractStateChanges(Finality.FINAL, accountIds, "");

        assertNotNull(account);
    }

    // FIXME: Empty sample contract code changes
    // (https://docs.near.org/docs/api/rpc/contracts#view-contract-state-changes)
    @Test
    void viewContractStateChanges_byHash_account_notNull() throws IOException, JSONException {
        String[] accountIds = new String[1];

        accountIds[0] = "guest-book.testnet";

        JsonNode account = nearService.viewContractStateChanges("5KgQ8uu17bhUPnMUbkmciHxbpFvsbhwdkJu4ptRfR7Zn",
                accountIds, "");

        assertNotNull(account);

        String inputJson = loadJsonFromFile("json-test-samples/account/view-contract-state-changes.json");

        JSONAssert.assertEquals(getPrettyJson(account), inputJson, false);
    }

    // FIXME: Empty sample contract code changes
    // (https://docs.near.org/docs/api/rpc/contracts#view-contract-state-changes)
    @Test
    void viewContractStateChanges_byHeight_account_notNull() throws JSONException, IOException {
        String[] accountIds = new String[1];

        accountIds[0] = "guest-book.testnet";

        JsonNode account = nearService.viewContractStateChanges(78441183, accountIds, "");

        assertNotNull(account);

        String inputJson = loadJsonFromFile("json-test-samples/account/view-contract-state-changes.json");

        JSONAssert.assertEquals(getPrettyJson(account), inputJson, false);
    }

    // FIXME: Empty sample contract code changes
    // (https://docs.near.org/docs/api/rpc/contracts#view-contract-code-changes)
    @Test
    void viewContractCodeChanges_byFinality_account_notNull() throws IOException, JSONException {
        String[] accountIds = new String[1];

        accountIds[0] = "dev-1602714453032-7566969";

        JsonNode account = nearService.viewContractCodeChanges(Finality.FINAL, accountIds);

        assertNotNull(account);
    }

    // FIXME: Empty sample contract code changes
    // (https://docs.near.org/docs/api/rpc/contracts#view-contract-code-changes)
    @Test
    void viewContractCodeChanges_byHash_account_notNull() throws IOException, JSONException {
        String[] accountIds = new String[1];

        accountIds[0] = "dev-1602714453032-7566969";

        JsonNode account = nearService.viewContractCodeChanges("HpsjZvjtuxarKRsXGVrgB6qtuCcHRgx3Xof1gfT2Jfj7",
                accountIds);

        assertNotNull(account);

        String inputJson = loadJsonFromFile("json-test-samples/account/view-contract-code-changes.json");

        JSONAssert.assertEquals(getPrettyJson(account), inputJson, false);
    }

    // FIXME: Empty sample contract code changes
    // (https://docs.near.org/docs/api/rpc/contracts#view-contract-code-changes)
    @Test
    void viewContractCodeChanges_byHeight_account_notNull() throws JSONException, IOException {
        String[] accountIds = new String[1];

        accountIds[0] = "dev-1602714453032-7566969";

        JsonNode account = nearService.viewContractCodeChanges(78441560, accountIds);

        assertNotNull(account);

        String inputJson = loadJsonFromFile("json-test-samples/account/view-contract-code-changes.json");

        JSONAssert.assertEquals(getPrettyJson(account), inputJson, false);
    }

    @Test
    void callContractFunction_byFinality_contractCode_notNull() throws IOException, JSONException {
        JsonNode contractCode = nearService.callContractFunction(Finality.FINAL, "dev-1588039999690", "get_num",
                "e30=");

        assertNotNull(contractCode);
    }

    @Test
    void callContractFunction_byHash_contractCode_notNull() throws IOException, JSONException {
        JsonNode contractCode = nearService.callContractFunction("3hwVKhg7skiRmRH46v4166GJVg6gDU8bRUYrankazFnz",
                "dev-1588039999690", "get_num", "e30=");

        assertNotNull(contractCode);

        String inputJson = loadJsonFromFile("json-test-samples/account/call-function.json");

        JSONAssert.assertEquals(getPrettyJson(contractCode), inputJson, false);
    }

    @Test
    void callContractFunction_byHeight_contractCode_notNull() throws JSONException, IOException {
        JsonNode contractCode = nearService.callContractFunction(78441821, "dev-1588039999690", "get_num", "e30=");

        assertNotNull(contractCode);

        String inputJson = loadJsonFromFile("json-test-samples/account/call-function.json");

        JSONAssert.assertEquals(getPrettyJson(contractCode), inputJson, false);
    }
}
