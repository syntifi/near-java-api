package com.syntifi.near.api.helper.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.helper.model.RecentActivityAccessKey;
import com.syntifi.near.api.helper.model.RecentActivityArgAddKey;
import com.syntifi.near.api.helper.model.RecentActivityArgCreateAccount;
import com.syntifi.near.api.helper.model.RecentActivityArgDeleteAccount;
import com.syntifi.near.api.helper.model.RecentActivityArgDeleteKey;
import com.syntifi.near.api.helper.model.RecentActivityArgDeployContract;
import com.syntifi.near.api.helper.model.RecentActivityArgFunctionCall;
import com.syntifi.near.api.helper.model.RecentActivityArgStake;
import com.syntifi.near.api.helper.model.RecentActivityArgTransfer;
import com.syntifi.near.api.helper.model.RecentActivityItem;
import com.syntifi.near.api.helper.model.RecentActivityPermission;
import com.syntifi.near.api.helper.service.exception.NearHelperServiceException;

import java.io.IOException;
import java.math.BigInteger;

/**
 * Custom serializer for a recent activity item
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public class RecentActivityItemDeserializer extends JsonDeserializer<RecentActivityItem> {
    public RecentActivityItem deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        final RecentActivityItem recentActivityItem = RecentActivityItem.builder()
                .blockHash(node.get("block_hash").asText())
                .blockTimestamp(node.get("block_timestamp").asText())
                .hash(node.get("hash").asText())
                .actionIndex(node.get("action_index").asInt())
                .signerId(node.get("signer_id").asText())
                .receiverId(node.get("receiver_id").asText())
                .actionKind(RecentActivityItem.RecentActivityActionKind.valueOf(node.get("action_kind").asText()))
                .build();

        JsonNode argsNode = node.get("args");
        if (RecentActivityItem.RecentActivityActionKind.CREATE_ACCOUNT.equals(recentActivityItem.getActionKind())) {
            recentActivityItem.setArgs(RecentActivityArgCreateAccount.builder().build());
        } else if (RecentActivityItem.RecentActivityActionKind.DEPLOY_CONTRACT.equals(recentActivityItem.getActionKind())) {
            recentActivityItem.setArgs(RecentActivityArgDeployContract.builder()
                    .code(argsNode.get("code").binaryValue()).build());
        } else if (RecentActivityItem.RecentActivityActionKind.FUNCTION_CALL.equals(recentActivityItem.getActionKind())) {
            recentActivityItem.setArgs(RecentActivityArgFunctionCall.builder()
                    .methodName(argsNode.get("method_name").asText())
                    .argsJson(argsNode.get("args_json").asText())
                    .argsBase64(argsNode.get("args_base64").asText())
                    .gas(argsNode.get("gas").asLong())
                    .deposit(argsNode.get("deposit").asText())
                    .build());
        } else if (RecentActivityItem.RecentActivityActionKind.TRANSFER.equals(recentActivityItem.getActionKind())) {
            recentActivityItem.setArgs(
                    RecentActivityArgTransfer.builder()
                            .deposit(argsNode.get("deposit").asText())
                            .build());
        } else if (RecentActivityItem.RecentActivityActionKind.STAKE.equals(recentActivityItem.getActionKind())) {
            recentActivityItem.setArgs(RecentActivityArgStake.builder()
                    .stake(new BigInteger(argsNode.get("stake").asText()))
                    .publicKey(PublicKey.getPublicKeyFromJson(argsNode.get("public_key").asText()))
                    .build());
        } else if (RecentActivityItem.RecentActivityActionKind.ADD_KEY.equals(recentActivityItem.getActionKind())) {
            JsonNode accessKey = argsNode.get("access_key");
            recentActivityItem.setArgs(
                    RecentActivityArgAddKey.builder()
                            .accessKey(
                                    RecentActivityAccessKey.builder()
                                            .nonce(accessKey.get("nonce").asLong())
                                            .permission(RecentActivityPermission.builder()
                                                    .permissionKind(
                                                            RecentActivityPermission.PermissionKind.valueOf(accessKey.get("permission").get("permission_kind").asText()))
                                                    .build())
                                            .build())
                            .publicKey(argsNode.get("public_key").asText())
                            .build());
        } else if (RecentActivityItem.RecentActivityActionKind.DELETE_KEY.equals(recentActivityItem.getActionKind())) {
            recentActivityItem.setArgs(RecentActivityArgDeleteKey.builder()
                    .publicKey(PublicKey.getPublicKeyFromJson(argsNode.get("public_key").asText()))
                    .build());
        } else if (RecentActivityItem.RecentActivityActionKind.DELETE_ACCOUNT.equals(recentActivityItem.getActionKind())) {
            recentActivityItem.setArgs(RecentActivityArgDeleteAccount.builder()
                    .beneficiaryId(argsNode.get("beneficiary_id").asText())
                    .build());
        } else {
            throw new NearHelperServiceException(
                    String.format("Action %s not mapped for deserialization", recentActivityItem.getActionKind()));
        }

        return recentActivityItem;
    }
}