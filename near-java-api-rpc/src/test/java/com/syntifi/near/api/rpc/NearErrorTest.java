package com.syntifi.near.api.rpc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.syntifi.near.api.common.exception.NearException;
import com.syntifi.near.api.rpc.jsonrpc4j.exception.NearExceptionResolver;
import com.syntifi.near.api.rpc.model.transaction.error.TxExecutionError;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.syntifi.near.api.common.json.JsonHelper.*;
import static org.junit.jupiter.api.Assertions.*;

public class NearErrorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NearClientTest.class);

    @Test
    void loadError_validInput_doesHaveErrorData() throws IOException {
        NearExceptionResolver nearExceptionResolver = new NearExceptionResolver();

        String validErrorJson = loadJsonFromResourceFile(
                "json-test-samples/error/valid-error.json");

        Throwable t = nearExceptionResolver
                .resolveException((ObjectNode) OBJECT_MAPPER.readTree(validErrorJson));

        assertInstanceOf(NearException.class, t);

        assertNotNull(((NearException) t).getNearErrorData());
    }

    @Test
    void loadError_invalidInput_doesNotHaveErrorData() throws IOException {
        NearExceptionResolver nearExceptionResolver = new NearExceptionResolver();

        String invalidErrorJson = loadJsonFromResourceFile(
                "json-test-samples/error/invalid-error.json");

        Throwable t = nearExceptionResolver
                .resolveException((ObjectNode) OBJECT_MAPPER.readTree(invalidErrorJson));

        assertInstanceOf(NearException.class, t);

        assertNull(((NearException) t).getNearErrorData());
    }

    @Test
    void loadTxExecutionError_ActionError_AccountAlreadyExist() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/error/account-already-exists.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class));

        TxExecutionError error = OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class);

        String expectedJson = getPrettyJson(error);

        JSONAssert.assertEquals(inputJson, expectedJson, true);
    }


    @Test
    void loadTxExecutionError_ActionError_AccountDoesNotExist() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/error/account-doesnot-exist.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class));

        TxExecutionError error = OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class);

        String expectedJson = getPrettyJson(error);

        JSONAssert.assertEquals(inputJson, expectedJson, true);
    }

    @Test
    void loadTxExecutionError_ActionError_ActorNoPermission() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/error/actor-no-permission.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class));

        TxExecutionError error = OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class);

        String expectedJson = getPrettyJson(error);

        JSONAssert.assertEquals(inputJson, expectedJson, true);
    }


    @Test
    void loadTxExecutionError_ActionError_AddKeyAlreadyExists() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/error/add-key-already-exists.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class));

        TxExecutionError error = OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class);

        String expectedJson = getPrettyJson(error);

        JSONAssert.assertEquals(inputJson, expectedJson, true);
    }


    @Test
    void loadTxExecutionError_ActionError_CreateAccountNotAllowed() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/error/create-account-not-allowed.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class));

        TxExecutionError error = OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class);

        String expectedJson = getPrettyJson(error);

        JSONAssert.assertEquals(inputJson, expectedJson, true);
    }

    @Test
    void loadTxExecutionError_ActionError_CreateAccountOnlyByRegistrar() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/error/create-account-only-by-registrar.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class));

        TxExecutionError error = OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class);

        String expectedJson = getPrettyJson(error);

        JSONAssert.assertEquals(inputJson, expectedJson, true);
    }

    @Test
    void loadTxExecutionError_ActionError_DeleteAccountOnlyStaking() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/error/delete-account-staking.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class));

        TxExecutionError error = OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class);

        String expectedJson = getPrettyJson(error);

        JSONAssert.assertEquals(inputJson, expectedJson, true);
    }

    @Test
    void loadTxExecutionError_ActionError_DeleteKeyDoesNotExist() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/error/delete-key-doesnot-exist.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class));

        TxExecutionError error = OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class);

        String expectedJson = getPrettyJson(error);

        JSONAssert.assertEquals(inputJson, expectedJson, true);
    }

    @Test
    void loadTxExecutionError_ActionError_InsufficientStake() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/error/insufficient-stake.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class));

        TxExecutionError error = OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class);

        String expectedJson = getPrettyJson(error);

        JSONAssert.assertEquals(inputJson, expectedJson, true);
    }

    @Test
    void loadTxExecutionError_ActionError_LackBalanceForState() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/error/lack-balance-for-state.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class));

        TxExecutionError error = OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class);

        String expectedJson = getPrettyJson(error);

        JSONAssert.assertEquals(inputJson, expectedJson, true);
    }

    @Test
    void loadTxExecutionError_ActionError_TriesToStake() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/error/tries-to-stake.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class));

        TxExecutionError error = OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class);

        String expectedJson = getPrettyJson(error);

        JSONAssert.assertEquals(inputJson, expectedJson, true);
    }

    @Test
    void loadTxExecutionError_ActionError_TriesToUnstake() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/error/tries-to-unstake.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class));

        TxExecutionError error = OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class);

        String expectedJson = getPrettyJson(error);

        JSONAssert.assertEquals(inputJson, expectedJson, true);
    }

    @Test
    void loadTxExecutionError_InvalidTxError_InvalidSignerId() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/error/invalid-signer-id.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class));

        TxExecutionError error = OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class);

        String expectedJson = getPrettyJson(error);

        ObjectMapper objectMapper = new ObjectMapper();

        JSONAssert.assertEquals(objectMapper.readTree(inputJson).get("InvalidTxError").toString(),
                expectedJson, true);
    }

    @Test
    void loadTxExecutionError_InvalidTxError_InvalidNonce() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/error/invalid-nonce.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class));

        TxExecutionError error = OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class);

        String expectedJson = getPrettyJson(error);

        ObjectMapper objectMapper = new ObjectMapper();

        JSONAssert.assertEquals(objectMapper.readTree(inputJson).get("InvalidTxError").toString(),
                expectedJson, true);
    }

    @Test
    void loadTxExecutionError_InvalidTxError_InvalidReceivedId() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/error/invalid-receiver-id.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class));

        TxExecutionError error = OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class);

        String expectedJson = getPrettyJson(error);

        ObjectMapper objectMapper = new ObjectMapper();

        JSONAssert.assertEquals(objectMapper.readTree(inputJson).get("InvalidTxError").toString(),
                expectedJson, true);
    }

    @Test
    void loadTxExecutionError_InvalidTxError_LackBalanceForStateTxError() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/error/lack-balance-for-state-tx-error.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class));

        TxExecutionError error = OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class);

        String expectedJson = getPrettyJson(error);

        ObjectMapper objectMapper = new ObjectMapper();

        JSONAssert.assertEquals(objectMapper.readTree(inputJson).get("InvalidTxError").toString(),
                expectedJson, true);
    }

    @Test
    void loadTxExecutionError_InvalidTxError_NonceTooLarge() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/error/nonce-too-large.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class));

        TxExecutionError error = OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class);

        String expectedJson = getPrettyJson(error);

        ObjectMapper objectMapper = new ObjectMapper();

        JSONAssert.assertEquals(objectMapper.readTree(inputJson).get("InvalidTxError").toString(),
                expectedJson, true);
    }

    @Test
    void loadTxExecutionError_InvalidTxError_NotEnoughBalance() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/error/not-enough-balance.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class));

        TxExecutionError error = OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class);

        String expectedJson = getPrettyJson(error);

        ObjectMapper objectMapper = new ObjectMapper();

        JSONAssert.assertEquals(objectMapper.readTree(inputJson).get("InvalidTxError").toString(),
                expectedJson, true);
    }

    @Test
    void loadTxExecutionError_InvalidTxError_SignerDoesNotExist() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/error/signer-does-not-exist.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class));

        TxExecutionError error = OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class);

        String expectedJson = getPrettyJson(error);

        ObjectMapper objectMapper = new ObjectMapper();

        JSONAssert.assertEquals(objectMapper.readTree(inputJson).get("InvalidTxError").toString(),
                expectedJson, true);
    }

    @Test
    void loadTxExecutionError_InvalidTxError_TransactionSizeExceeded() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/error/transaction-size-exceeded.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class));

        TxExecutionError error = OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class);

        String expectedJson = getPrettyJson(error);

        ObjectMapper objectMapper = new ObjectMapper();

        JSONAssert.assertEquals(objectMapper.readTree(inputJson).get("InvalidTxError").toString(),
                expectedJson, true);
    }

    @Test
    void loadTxExecutionError_InvalidTxError_CostOverflow() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/error/cost-overflow.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class));

        TxExecutionError error = OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class);

        String expectedJson = getPrettyJson(error);

        ObjectMapper objectMapper = new ObjectMapper();

        JSONAssert.assertEquals(objectMapper.readTree(inputJson).get("InvalidTxError").toString(),
                expectedJson, true);
    }

    @Test
    void loadTxExecutionError_InvalidTxError_Expired() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/error/expired.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class));

        TxExecutionError error = OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class);

        String expectedJson = getPrettyJson(error);

        ObjectMapper objectMapper = new ObjectMapper();

        JSONAssert.assertEquals(objectMapper.readTree(inputJson).get("InvalidTxError").toString(),
                expectedJson, true);
    }


    @Test
    void loadTxExecutionError_InvalidTxError_InvalidChain() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/error/invalid-chain.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class));

        TxExecutionError error = OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class);

        String expectedJson = getPrettyJson(error);

        ObjectMapper objectMapper = new ObjectMapper();

        JSONAssert.assertEquals(objectMapper.readTree(inputJson).get("InvalidTxError").toString(),
                expectedJson, true);
    }


    @Test
    void loadTxExecutionError_InvalidTxError_InvalidSignature() throws IOException, JSONException {
        String inputJson = loadJsonFromResourceFile(
                "json-test-samples/error/invalid-signature.json");

        assertDoesNotThrow(() -> OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class));

        TxExecutionError error = OBJECT_MAPPER.readValue(inputJson, TxExecutionError.class);

        String expectedJson = getPrettyJson(error);

        ObjectMapper objectMapper = new ObjectMapper();

        JSONAssert.assertEquals(objectMapper.readTree(inputJson).get("InvalidTxError").toString(),
                expectedJson, true);
    }
}
